import { Injectable } from "@angular/core";
import { map, catchError } from "rxjs/operators";
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpResponse
} from "@angular/common/http";
import { Router } from "@angular/router";
import { Observable, throwError, of } from "rxjs";

@Injectable()
export class ResourceNotFoundInterceptor implements HttpInterceptor {

    constructor(private router: Router) {
    }

    intercept(request:HttpRequest<any>, next:HttpHandler):Observable<HttpEvent<any>> {
        return next.handle(request)
            .pipe(
                map((event:HttpEvent<any>) => {
                    if (event instanceof HttpResponse) {
                        // Nothing So Far
                    }
                    return event;
                }),
                catchError(error => {
                    console.log("Error response status: ", error.status);
                    if(error.status === 404) {
                        this.router.navigateByUrl('/not-found');
                    }
                    return throwError(() => (new Error("Page Not Found")));
                }));

    }
}