import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { CookieService } from 'ngx-cookie';

import { CSRFCOOKIE, CSRFHEADER } from '@app/_core/constants';
import { UserService } from '@app/_services/user.service';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  constructor(
    private userService:UserService, 
    private cookieService: CookieService, 
    private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials: true,
    });

    const xsrfToken = this.cookieService.get(CSRFCOOKIE);
    if (xsrfToken) {
      req = req.clone({
        headers: req.headers.set(CSRFHEADER, xsrfToken),
      });
    }

    return next.handle(req).pipe(
        catchError((error) => 
          {
            console.log("Error response status: ", error.status);
            if(error.status === 403) {
              this.userService.setLoggedUser(null);
              this.router.navigateByUrl("/login");
            }
            return throwError(() => (new Error("User Not Authorized")));
          }
        ));

    }
}