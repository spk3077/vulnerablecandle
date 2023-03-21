import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { environment } from  '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  payment_endpoint = environment.apiUrl + "/payments";

  constructor(private http: HttpClient) { }


}
