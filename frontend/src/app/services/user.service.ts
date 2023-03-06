import { Injectable, Inject, InjectionToken } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { StorageService } from 'ngx-webstorage-service';

import { UserCred } from '@app/core/userCred';
import { RegistrationForm } from '@app/core/registration-form';
import { environment } from  '../../environments/environment';

const STORAGE_KEY = 'current-user';
export const USER_SERVICE_STORAGE =
    new InjectionToken<StorageService>('USER_SERVICE_STORAGE');

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loggedInUserSubject:BehaviorSubject<UserCred>;
  public loggedInUser$:Observable<UserCred>;

  users_endpoint = environment.apiUrl + "/users";
  login_endpoint = environment.apiUrl + "/login";
  logout_endpoint = environment.apiUrl + "/logout";

  constructor(private http:HttpClient, @Inject(USER_SERVICE_STORAGE) private storage: StorageService) {
    this.loggedInUserSubject = new BehaviorSubject<UserCred>(this.getUserFromStorage());
    this.loggedInUser$ = this.loggedInUserSubject.asObservable();
    }

  public setLoggedUser(loggedUser: any | null): void {
      this.storage.set(STORAGE_KEY, loggedUser);
      this.loggedInUserSubject.next(this.getUserFromStorage());
  }
  private getUserFromStorage(): any {
      const currentUser: UserCred = this.storage.get(STORAGE_KEY) || null;
      return currentUser;
  }

  public getLoggedUser(): any {
      return this.loggedInUserSubject.value;
  }

  login(user: UserCred): Observable<any> {
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
            }));
  }

  logout(): Observable<any> {
      return this.http.get(this.logout_endpoint)
        .pipe(
            map(res => {
                this.setLoggedUser(null);
                return res;
            }),
            catchError(error => {
                return of(error);
            }));
  }

  register(registrationForm: RegistrationForm): Observable<any> {
      return this.http.post(this.users_endpoint, registrationForm)
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
