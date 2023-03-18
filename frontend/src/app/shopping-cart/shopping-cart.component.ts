import { Component, OnInit } from '@angular/core';

import { CartItemReceive } from '@app/_core/cartItem';
import { DefaultResponse } from '@app/_core/defaultResponse';
import { ShoppingCartService } from '@app/_services/shopping-cart.service';

import { faTruck } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  cartItems!: CartItemReceive[];

  constructor(private shoppingCartService: ShoppingCartService) {}

  faTruck = faTruck;

  ngOnInit(): void {
    this.getCartItems();
  }

  // Retrieve Shopping Cart to Display
  public getCartItems(): void {
    this.shoppingCartService.getCartItems().subscribe(cartItems => this.cartItems = cartItems);
  }

  // Retrieve Products
  public delCartItem(cartID: number): void {
    this.shoppingCartService.delCartItem(cartID).subscribe({
      next: (res) => {
        // Get generic response to determine success
        let delResponse = res as DefaultResponse;
          console.log(delResponse);
          if (delResponse.success != true) {
            console.log("Could not delete cart item!");
          }
          else {
            console.log("Deleted Cart Item Successfully");
          }
      },
      error: () => {
        // Failed at Server
        console.log("Cart Item Deletion Errored");
      }
    });
  }

  public calculateTotal(): number {
    return this.cartItems.reduce((acc, obj) => acc += (obj.product.price * obj.quantity) , 0);
  }


}
