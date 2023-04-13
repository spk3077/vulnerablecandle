import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { ProductReviewSend } from '@app/_core/productReview';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductReviewService {

  // List of Endpoints using Environment: Production/Development
  review_endpoint = environment.apiUrl + "/productreviews";

  constructor(private http: HttpClient) { }

  // Add Review For Single Product
  public addProductReview(review: ProductReviewSend): Observable<any> {
    return this.http.post(this.review_endpoint, 
      {
        product: {id: review.productID},
        title: review.title,
        grade: review.grade,
        comment: review.comment
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
