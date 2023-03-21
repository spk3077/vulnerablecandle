import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { UserInfoReceive } from '@app/_core/userInfo';
import { UserInfoSend } from '@app/_core/userInfo';
import { environment } from  '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserInfoService {
  
  userinfo_endpoint = environment.apiUrl + "/userinfos";

  constructor(private http: HttpClient) { }

   // Retrieve UserInfo
   public getUserInfo(): Observable<any> {
    return this.http.get(this.userinfo_endpoint)
      .pipe(
          map(res => {
              return res;
          }),
          catchError(error => {
              return throwError(() => (new Error(error)));
          })
      );
  }

  // Add user info to account (nulls unincluded)
  public addUserInfo(userInfo: UserInfoSend): Observable<any> {
    return this.http.post(this.userinfo_endpoint, 
        {
          name: userInfo.name,
          phone: userInfo.phone,
          email: userInfo.email,
          address: userInfo.address,
          city: userInfo.city,
          state: userInfo.state,
          zip: userInfo.zip,
          picture: userInfo.picture
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

    // Add user info to account (keeps unincluded)
    public changeUserInfo(userInfo: UserInfoSend): Observable<any> {
      return this.http.put(this.userinfo_endpoint,
        {
          name: userInfo.name,
          phone: userInfo.phone,
          email: userInfo.email,
          address: userInfo.address,
          city: userInfo.city,
          state: userInfo.state,
          zip: userInfo.zip,
          picture: userInfo.picture
        }
        )
        .pipe(
            map(res => {
                return res;
            }),
            catchError(error => {
                return of(error);
            })
        );
  }
}
