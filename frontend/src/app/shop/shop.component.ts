import { Component, OnInit } from '@angular/core';

import { ProductReceive } from '@app/_core/product';
import { CartItemSend } from '@app/_core/cartItem';
import { DefaultResponse } from '@app/_core/defaultResponse';
import { ProductService } from '@app/_services/product.service';
import { ShoppingCartService } from '@app/_services/shopping-cart.service';
import { UserService } from '@app/_services/user.service';

import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { faChevronDown } from '@fortawesome/free-solid-svg-icons';
import { faStar } from '@fortawesome/free-solid-svg-icons';
import { faCartShopping } from '@fortawesome/free-solid-svg-icons';
import { faPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {  
  currentUser: any | undefined;
  products: ProductReceive[] = [];

  filterSearch!: string;
  filters = {
    popular: false,
    cute: false,
    trending: false,
    car: false,
    unique: false,
    price1: false,
    price2: false,
    price3: false,
    price4: false,
  };

  // Display Variables
  getProductsError: boolean = false;
  cartBtnNum: number | null = null;
  cartBtnBool: boolean | null = null;
  
  constructor(private productService: ProductService, private shoppingCartService: ShoppingCartService, private userService: UserService) { 
    this.userService.loggedInUser$.subscribe(x => this.currentUser = x);
  }
  
  // Font Awesome Exports
  faMagnifyingGlass = faMagnifyingGlass;
  faChevronDown = faChevronDown;
  faStar = faStar;
  faCartShopping = faCartShopping;
  faPlus = faPlus;
  
  ngOnInit(): void {
    this.getProducts();
  }
  
  // Retrieve Products
  private getProducts(): void {
    this.productService.getProducts().subscribe({
      next: (res) => {
        if (res.length <= 0) {
          this.getProductsError = true;
        }
        res.every((product: ProductReceive) => this.products.push(
          new ProductReceive(product.id, product.name, product.brand, product.description,
            product.tagNames, product.price, product.stock, product.image, product.averageReviewGrade,
              product.productReviews)
          )
        );

      },
      error: () => {
        // Failed at server
        this.getProductsError = true;
      }}
    );
  }

  // Add Product Quantity: 1 to Cart
  public addToCart(productID: number): void {
    this.shoppingCartService.addToCart(new CartItemSend(productID, 1)).subscribe({
      next: (res) => {
        // Get generic response to determine success
        let addResponse = res as DefaultResponse;
          console.log(addResponse);
          if (addResponse.success != true) {
            this.cartBtnBool = false;
            console.log(res.message);
          }
          else {
            this.cartBtnBool = true;
            console.log("Added Cart Item Successfully");
          }
          
          this.cartBtnNum = productID;
      },
      error: () => {
        // Failed at server
        this.cartBtnNum = productID;
        this.cartBtnBool = false;
        console.log("Internal Server Error: Could not add to Cart");
      }}
    );
  }

  // Retrieve Count by Filter for Badge
  public getFilterCount(type: number): number {
    switch ( type ) {
      case 1:
        return this.products.reduce((acc, obj) => obj.tagNames.includes("Popular") ? acc += 1 : acc, 0);
      case 2:
        return this.products.reduce((acc, obj) => obj.tagNames.includes("Cute") ? acc += 1 : acc, 0);
      case 3:
        return this.products.reduce((acc, obj) => obj.tagNames.includes("Trending") ? acc += 1 : acc, 0);
      case 4:
        return this.products.reduce((acc, obj) => obj.tagNames.includes("ForCar") ? acc += 1 : acc, 0);
      case 5:
        return this.products.reduce((acc, obj) => obj.tagNames.includes("Unique") ? acc += 1 : acc, 0);
      case 6:
        return this.products.reduce((acc, obj) => obj.price < 10 ? acc += 1 : acc, 0);
      case 7:
        return this.products.reduce((acc, obj) => obj.price >= 10 && obj.price < 20 ? acc += 1 : acc, 0);
      case 8:
        return this.products.reduce((acc, obj) => obj.price >= 20 && obj.price <= 30 ? acc += 1 : acc, 0);
      case 9:
        return this.products.reduce((acc, obj) => obj.price > 30 ? acc += 1 : acc, 0);
    }
    return 0;
  }

  // Get the Tags formatted for HTML
  public getTags(tags: string[]): string {
    if ( tags.length <= 2 ) {
      return tags.join(', ');
    }
    else {
      return tags[0] + ", " + tags[1] + ", ...";
    }
  }
}
