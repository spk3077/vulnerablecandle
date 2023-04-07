import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { environment } from  '@environments/environment';
import { TagSend } from '@app/_core/tag';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  tag_endpoint = environment.apiUrl + "/tags";

  constructor(private http: HttpClient) { }

  // Get all Tags
  public getTags(): Observable<any> {
    return this.http.get(this.tag_endpoint)
      .pipe(
          map(res => {
              return res;
          }),
          catchError(error => {
              return throwError(() => (new Error(error)));
          })
      );
  }

  // Add Tag
  public addTag(tag: TagSend): Observable<any> {
    return this.http.post(this.tag_endpoint, 
      {
        name: tag.name,
        type: tag.type
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
