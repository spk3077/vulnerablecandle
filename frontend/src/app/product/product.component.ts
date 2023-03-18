import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartItemSend } from '@app/_core/cartItem';
import { DefaultResponse } from '@app/_core/defaultResponse';

import { ProductReceive } from '@app/_core/product';
import { ProductService } from '@app/_services/product.service';
import { ShoppingCartService } from '@app/_services/shopping-cart.service';
import { UserService } from '@app/_services/user.service';

import { faStar } from '@fortawesome/free-solid-svg-icons';
import { faShoppingBasket } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  currentUser: any | undefined;
  id!: number;
  product!: ProductReceive;

  // Purchase Variables
  quantity: number = 1;

  // Display Variables
  showStock: boolean = false;
  showCartMessage: boolean = false;
  toCartMessage: string | undefined;

  // Image URLs
  primaryImageUrl!: string | undefined;
  imageUrl1: string | undefined;
  imageUrl2: string | undefined;
  imageUrl3: string | undefined;

  constructor(
    private productService: ProductService,
    private shoppingCartService: ShoppingCartService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
    ) {}
  
  // Font Awesome Exports
  faStar = faStar;
  faShoppingBasket = faShoppingBasket;

  ngOnInit(): void {
    this.id = parseInt(this.route.snapshot.paramMap.get('productid')!);
    this.userService.loggedInUser$.subscribe(x => this.currentUser = x);
    this.getProduct();
  }
  
  // Fetch the Product to Fillout Page
  private getProduct(): void {
    // If ID is not a number, return to SHOP
    if (Number.isNaN(this.id)) {
      this.router.navigateByUrl('/');
    }

    this.productService.getProduct(this.id).subscribe({
      // If Successful
      next: (res) => {
        console.log(res);
        if (res == undefined) {
          this.router.navigateByUrl('/');
          return;
        }
        // Assign Product
        this.product = res;

        // Set ImageURLS
        if (this.product.imageUrls.length >= 1) {
          this.imageUrl1 = this.product.imageUrls[0];
        }
        if (this.product.imageUrls.length >= 2) {
          this.imageUrl2 = this.product.imageUrls[1];
        }
        if (this.product.imageUrls.length >= 3) {
          this.imageUrl3 = this.product.imageUrls[2];
        }

        this.primaryImageUrl = this.imageUrl1;
      },
      // If fails at server
      error: () => {
        console.log("Server Error: Cannot retrieve product");
        return;
      }
    });
  }

  // Add to Cart
  public addToCart(): void {
    this.shoppingCartService.addToCart(new CartItemSend(this.id, this.quantity)).subscribe({
      next: (res) => {
        // Get generic response to determine success
        let addResponse = res as DefaultResponse;
          console.log(addResponse);
          if (addResponse.success != true) {
            this.toCartMessage = res.message;
          }
          else {
            console.log("Added Cart Item Successfully");
          }
          
          this.showCartMessage = true;
      },
      error: () => {
        // Failed at server
        this.showCartMessage = true;
        this.toCartMessage = "Internal Server Error";
      }}
    );
  }

  // Fetch the Stock for this Product
  // For FIXED SHOULD ONLY JUST CHANGE BOOLEAN
  public getStock(): void {
    this.showStock = true;
  }

  // Adding 1 to Quantity
  public addQuantity(): void {
    this.quantity = this.quantity + 1;
  }

  // Subtracting 1 from Quantity
  public subQuantity(): void {
    this.quantity = this.quantity - 1;
  }
}
