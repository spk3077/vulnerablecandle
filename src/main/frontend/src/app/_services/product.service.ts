import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { ProductSend } from '@app/_core/product';
import { environment } from  '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

    product_endpoint = environment.apiUrl + "/products";

    constructor(private http: HttpClient) { }

    // Retrieve Products
    public getProducts(): Observable<any> {
        return this.http.get(this.product_endpoint)
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
        return this.http.get(this.product_endpoint + "/" + productID)
            .pipe(
                map(res => {
                    return res;
                }),
                catchError(error => {
                    return of(error);
                })
            );
    }

    // Retrieve Product Stock
    public getProductStock(productID: number): Observable<any> {
        return this.http.get(this.product_endpoint + "/stock?url=http://localhost:8081/products/stock?id=" + productID)
            .pipe(
                map(res => {
                    return res;
                }),
                catchError(error => {
                    return of(error);
                })
            );
    }

    // Add Product
    public addProduct(product: ProductSend): Observable<any> {
        return this.http.post(this.product_endpoint, product)
        .pipe(
            map(res => {
                return res;
            }),
            catchError(error => {
                return throwError(() => (new Error(error)));
            })
        );
    }
    
    // Update existing product
    public updateProduct(product: ProductSend): Observable<any> {
        return this.http.put(this.product_endpoint, 
        {
            id: product.id,
            name : product.name,
            brand : product.brand,
            price : product.price,
            stock : product.stock,
            description : product.description
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

    // Delete Single Product
    public deleteProduct(productID: number): Observable<any> {
        return this.http.delete(this.product_endpoint + "/" + productID)
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
