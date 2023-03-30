import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { environment } from  '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  order_endpoint = environment.apiUrl + "/orders";

  constructor(private http: HttpClient) { }

  // Get Reviews For Single Product
  public getOrders(): Observable<any> {
    return this.http.get(this.order_endpoint)
      .pipe(
          map(res => {
              return res;
          }),
          catchError(error => {
              return throwError(() => (new Error(error)));
          })
      );
  }

    // Add payment for current user
    public createOrder(): Observable<any> {
      return this.http.post(this.order_endpoint, {})
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
