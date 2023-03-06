import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from "@angular/router";

import { UserService } from '@app/services/user.service';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  private isRefreshing = false;

  constructor(private userService:UserService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials: true,
    });

    return next.handle(req).pipe(
        catchError((error) => {
            console.log("Error response status: ", error.status);
            if(error.status === 401) {
              this.userService.setLoggedUser(null);
              this.router.navigateByUrl("/login");
            }
            return throwError(() => (new Error("User Not Authorized")));
        }));

    }
}