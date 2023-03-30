import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { CartItemSend } from '@app/_core/cartItem';
import { environment } from  '@environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  cart_endpoint = environment.apiUrl + "/shoppingcarts";

  constructor(private http: HttpClient) { }

  // Retrieve Cart
  public getCartItems(): Observable<any> {
    return this.http.get(this.cart_endpoint)
        .pipe(
            map(res => {
                return res;
            }),
            catchError(error => {
                return of(error);
            })
        );
}

  // Adding to Cart
  public addToCart(cartItem: CartItemSend): Observable<any> {
    return this.http.post(this.cart_endpoint + "/add/" + cartItem.productID + "/" + cartItem.quantity,
      {})
      .pipe(
          map(res => {
              return res;
          }),
          catchError(error => {
              return throwError(() => (new Error(error)));
          })
      );
  }

    // Updating existing cart item
    public updateCartItem(cartItem: CartItemSend): Observable<any> {
      return this.http.put(this.cart_endpoint + "/update/" + cartItem.productID + "/" + cartItem.quantity,
        {})
        .pipe(
            map(res => {
                return res;
            }),
            catchError(error => {
                return throwError(() => (new Error(error)));
            })
        );
    }

    // Delete CartItem
  public removeCartItem(cartID: number): Observable<any> {
    return this.http.delete(this.cart_endpoint + "/remove/" + cartID)
      .pipe(
        map(res => {
            return res;
        }),
        catchError(error => {
            return of(error);
        })
      );
  }

  // public getCartItems(): Observable<any> {
  //   const cartItems = of(CARTITEMS);
  //   return cartItems;
  // }
}
