import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { CartItemReceive, CartItemSend } from '@app/_core/cartItem';
import { DefaultResponse } from '@app/_core/defaultResponse';
import { ProductReceive } from '@app/_core/product';
import { ShoppingCartService } from '@app/_services/shopping-cart.service';

import { faTruck } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  cartItems: CartItemReceive[] = [];
  originalCartItems: CartItemReceive[] = [];

  // Display Booleans
  getCartItemsError: boolean = false;
  updateCartItemError: boolean = false;
  removeCartItemError: boolean = false;
  cannotCheckout: boolean = false;

  constructor(private shoppingCartService: ShoppingCartService, private router: Router) {}

  faTruck = faTruck;

  ngOnInit(): void {
    this.getCartItems();
  }

  // Retrieve Shopping Cart to Display
  public getCartItems(): void {
    this.shoppingCartService.getCartItems().subscribe({
      next: (res) => {
        res.cartItems.forEach((cartItem: any) => {
          this.cartItems.push(
            new CartItemReceive(cartItem.id, ProductReceive.forCart(cartItem.itemId, cartItem.itemName,
               cartItem.itemBrand, cartItem.itemPrice, cartItem.itemImage), cartItem.quantity));
        });

        // For updateCartItem when quantity of items change
        this.cartItems.forEach(val => this.originalCartItems.push(Object.assign({}, val)));
      },
      error: () => {
        // Failed at server
        this.getCartItemsError = true;
      }
    });
    
  }

  // Update Shopping Cart on API
  public updateCartItem(cartItem: CartItemSend): void {
    this.shoppingCartService.updateCartItem(cartItem).subscribe({
      next: (res) => {
        // Get generic response to determine success
        const updateResponse = res as DefaultResponse;
          if (updateResponse.success != true) {
            this.updateCartItemError = true;
          }
          else {
            this.updateCartItemError = false;
          }
      },
      error: () => {
        // Failed at Server
        this.updateCartItemError = true;
      }
    });
  }

  // Remove a Cart Item from API
  public removeCartItem(cartID: number): void {
    this.shoppingCartService.removeCartItem(cartID).subscribe({
      next: (res) => {
        // Get generic response to determine success
        const delResponse = res as DefaultResponse;
          if (delResponse.success != true) {
            this.removeCartItemError = true;
          }
          else {
            const index = this.cartItems.map(item => item.id).indexOf(cartID)
            this.cartItems.splice(index, 1);
            this.originalCartItems.splice(index, 1);
            this.removeCartItemError = false;
          }
      },
      error: () => {
        // Failed at Server
        this.removeCartItemError = true;
      }
    });
  }

  // Make Purchase Button Click
  public updateQuantities(): void {
    this.cartItems.forEach((cartItem: CartItemReceive, index: number) => {
      let originCartItem = this.originalCartItems[index];

      if (cartItem.id == originCartItem.id && cartItem.quantity != originCartItem.quantity) {
        let n = Math.floor(Number(cartItem.quantity));
        if (n !== Infinity && String(n) === String(cartItem.quantity) && n > 0) {
          this.updateCartItem(new CartItemSend(cartItem.id, cartItem.quantity));
        }
      }
    });
    
    if (!this.updateCartItemError && !this.removeCartItemError) {
      this.router.navigateByUrl('/checkout', {  onSameUrlNavigation: 'reload' });
    }
    else {
      this.cannotCheckout = true;
    }
  }

  public calculateTotal(): number {
    return this.cartItems.reduce((acc, obj) => acc += (obj.product.price * obj.quantity) , 0);
  }

}
