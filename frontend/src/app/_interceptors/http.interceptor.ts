import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from "@angular/router";

import { UserService } from '@app/_services/user.service';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  private isRefreshing = false;

  constructor(private userService:UserService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials: true,
    });

    const xsrfToken = this.getXsrfToken();
    if (xsrfToken) {
      req = req.clone({
        headers: req.headers.set('X-XSRF-TOKEN', xsrfToken),
      });
    }

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
    private getXsrfToken(): string | null {
      const name = 'XSRF-TOKEN=';
      const decodedCookie = decodeURIComponent(document.cookie);
      const cookieArray = decodedCookie.split(';');
      for(let i = 0; i < cookieArray.length; i++) {
        let cookie = cookieArray[i];
        while (cookie.charAt(0) === ' ') {
          cookie = cookie.substring(1);
        }
        if (cookie.indexOf(name) === 0) {
          return cookie.substring(name.length, cookie.length);
        }
      }
      return null;
    }
}