import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { CartItemReceive } from '@app/_core/cartItem';
import { CartItemSend } from '@app/_core/cartItem';
import { environment } from  '@environments/environment';

import { CARTITEMS } from '@app/mock-cartItems';


@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  cart_endpoint = environment.apiUrl + "/shoppingcarts";

  constructor(private http: HttpClient) { }

//   // Retrieve Cart
//   public getCartItems(): Observable<any> {
//     return this.http.get(this.cart_endpoint)
//         .pipe(
//             map(res => {
//                 return res;
//             }),
//             catchError(error => {
//                 return of(error);
//             })
//         );
// }

  // Adding to Cart
  public addToCart(cartItem: CartItemSend): Observable<any> {
    return this.http.post(this.cart_endpoint, 
        {
        product: {id : cartItem.productID},
        quantity: cartItem.quantity
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

    // Delete CartItem
    public delCartItem(cartID: number): Observable<any> {
      return this.http.delete(this.cart_endpoint + "/" + cartID)
          .pipe(
              map(res => {
                  return res;
              }),
              catchError(error => {
                  return of(error);
              })
          );
  }

  public getCartItems(): Observable<any> {
    const cartItems = of(CARTITEMS);
    return cartItems;
  }
}
