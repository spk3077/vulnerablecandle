import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { CookieService } from 'ngx-cookie';

import { CSRFCOOKIE, CSRFHEADER } from '@app/_core/constants';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  constructor(
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
          if(error.status === 200) {
            return throwError(() => (error.error.text));
          }
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