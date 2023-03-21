import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { environment } from  '@environments/environment';
import { PaymentSend } from '@app/_core/payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  payment_endpoint = environment.apiUrl + "/payments";

  constructor(private http: HttpClient) { }

  // Get Reviews For Single Product
  public getPayment(): Observable<any> {
    return this.http.get(this.payment_endpoint)
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
    public addPayment(payment: PaymentSend): Observable<any> {
      return this.http.post(this.payment_endpoint, 
        {
          cardNumber: payment.cardNumber,
          ownerName: payment.ownerName,
          expiryMonth: payment.expiryMonth,
          expiryYear: payment.expiryYear,
          secCode: payment.secCode
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

}
