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
  originalCartItems!: CartItemReceive[];

  // Display Booleans
  getCartItemsEmpty: boolean = false;
  getCartItemsError: boolean = false;

  constructor(private shoppingCartService: ShoppingCartService, private router: Router) {}

  faTruck = faTruck;

  ngOnInit(): void {
    this.getCartItems();
    // For updateCartItem when quantity of items change
    this.originalCartItems = this.cartItems;
  }

  // Retrieve Shopping Cart to Display
  public getCartItems(): void {
    this.shoppingCartService.getCartItems().subscribe({
      next: (res) => {
        console.log(res);
        if (res.cartItems.length <= 0) {
          this.getCartItemsEmpty = true;
        }
        res.cartItems.forEach((cartItem: any) => {
          this.cartItems.push(
            new CartItemReceive(cartItem.id, ProductReceive.forCart(cartItem.itemId, cartItem.itemName,
               cartItem.itemBrand, cartItem.itemPrice, cartItem.itemImage), cartItem.quantity));
        });
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
        let updateResponse = res as DefaultResponse;
          if (updateResponse.success != true) {
            console.log("Update Cart Failed!");
          }
          else {
            console.log("Updated Cart Item Successfully");

          }
      },
      error: () => {
        // Failed at Server
        console.log("Update Cart Failed at Server!");
      }
    });
  }

  // Remove a Cart Item from API
  public removeCartItem(cartID: number): void {
    this.shoppingCartService.removeCartItem(cartID).subscribe({
      next: (res) => {
        // Get generic response to determine success
        let delResponse = res as DefaultResponse;
          if (delResponse.success != true) {
            console.log("Could not delete cart item!");
          }
          else {
            const index = this.cartItems.map(item => item.id).indexOf(cartID)
            this.cartItems.splice(index, 1);
            this.originalCartItems.splice(index, 1);
            console.log("Deleted Cart Item Successfully");

          }
      },
      error: () => {
        // Failed at Server
        console.log("Cart Item Deletion Errored");
      }
    });
  }

  // Make Purchase Button Click
  public updateQuantities(): void {
    this.cartItems.forEach((cartItem: CartItemReceive, index: number) => {
      let originCartItem = this.originalCartItems[index];
      if (cartItem.id == originCartItem.id && cartItem.quantity != originCartItem.quantity) {
        this.updateCartItem(new CartItemSend(cartItem.product.id, cartItem.quantity));
      }

    });
    this.router.navigateByUrl('/checkout');
  }

  public calculateTotal(): number {
    return this.cartItems.reduce((acc, obj) => acc += (obj.product.price * obj.quantity) , 0);
  }

}
