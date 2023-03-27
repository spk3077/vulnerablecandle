import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { ProductReceive } from '@app/_core/product';
import { environment } from  '@environments/environment';

import { PRODUCTS } from '@app/mock-products';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  products_endpoint = environment.apiUrl + "/products";

  constructor(private http: HttpClient) { }

   // Retrieve Products
   public getProducts(): Observable<any> {
    return this.http.get(this.products_endpoint)
        .pipe(
            map(res => {
                return res;
            }),
            catchError(error => {
                return of(error);
            })
        );
    }
  // Retrieve Single Product
  public getProduct(productID: number): Observable<any> {
    return this.http.get(this.products_endpoint + "/" + productID)
        .pipe(
            map(res => {
                return res;
            }),
            catchError(error => {
                return of(error);
            })
        );
    }

  // Retrieve Single Product
  public deleteProduct(productID: number): Observable<any> {
    return this.http.delete(this.products_endpoint + "/" + productID)
        .pipe(
            map(res => {
                return res;
            }),
            catchError(error => {
                return of(error);
            })
        );
    }

  // public getProduct(id: number): Observable<ProductReceive> {
  //   const product = of(PRODUCTS[id - 1]);
  //   return product;
  // }
  
  // public getProducts(): Observable<ProductReceive[]> {
  //   const products = of(PRODUCTS);
  //   return products;
  // }
}
