import { Injectable, Inject, InjectionToken } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { StorageService } from 'ngx-webstorage-service';

import { UserReceive } from '@app/_core/user';
import { UserSend } from '@app/_core/user';
import { environment } from  '@environments/environment';

const STORAGE_KEY = 'current-user';
export const USER_SERVICE_STORAGE =
    new InjectionToken<StorageService>('USER_SERVICE_STORAGE');

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private loggedInUserSubject:BehaviorSubject<UserReceive>;
    // Use to provide currentUser details to other pages
    public loggedInUser$:Observable<UserReceive>;

    // List of Endpoints using Environment: Production/Development
    users_endpoint = environment.apiUrl + "/users";
    login_endpoint = environment.apiUrl + "/login";
    logout_endpoint = environment.apiUrl + "/logout";

    constructor(private http:HttpClient, @Inject(USER_SERVICE_STORAGE) private storage: StorageService) {
        this.loggedInUserSubject = new BehaviorSubject<UserReceive>(this.getUserFromStorage());
        this.loggedInUser$ = this.loggedInUserSubject.asObservable();
    }

    // Set CurrentUser
    public setLoggedUser(loggedUser: any | null): void {
        this.storage.set(STORAGE_KEY, loggedUser);
        this.loggedInUserSubject.next(this.getUserFromStorage());
    }

    // setLoggedUser: Helper Function
    private getUserFromStorage(): any {
        const currentUser: UserReceive = this.storage.get(STORAGE_KEY) || null;
        return currentUser;
    }

    // Currently unused
    public getLoggedUser(): any {
        return this.loggedInUserSubject.value;
    }

    // Login Function
    public login(user: UserSend): Observable<any> {
        const form = new FormData;
        form.append("username", user.username);
        form.append("password", user.password);

        return this.http.post(this.login_endpoint, form)
            .pipe(
                map(res => {
                    return res;
                }),
                catchError(error => {
                    return throwError(() => (new Error(error)));
                })
            );
    }

    // Logout Function
    public logout(): Observable<any> {
        return this.http.get(this.logout_endpoint)
            .pipe(
                map(res => {
                    this.setLoggedUser(null);
                    return res;
                }),
                catchError(error => {
                    return of(error);
                })
            );
    }

    // Adding User Function
    public register(user: UserSend): Observable<any> {
        return this.http.post(this.users_endpoint, 
            {
            username: user.username,
            password: user.password
            }
            )
            .pipe(
                map(res => {
                    return res;
                }),
                catchError(error => {
                    return throwError(() => (new Error(error)));
                })
            );
    }

    // get User Data
    // USER_ROLE: For User Content
    // ADMIN_ROLE: For all Users
    getUserData(): Observable<any> {
        return this.http.get(this.users_endpoint)
            .pipe(
                map(res => {
                    return res;
                }),
                catchError(error => {
                    return throwError(() => (new Error(error)));
                })
            );
    }
}
