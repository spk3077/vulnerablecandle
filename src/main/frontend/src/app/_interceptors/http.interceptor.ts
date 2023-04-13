import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  constructor(private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials: true,
    });

    return next.handle(req).pipe(
        catchError((error) => 
          {
            console.log("Error response status: ", error.status);
            if(error.status === 403) {
              // this.userService.setLoggedUser(null);
              this.router.navigateByUrl("/logout");
            }
            return throwError(() => (new Error("Error via Intercept")));
          }
        ));

    }
}