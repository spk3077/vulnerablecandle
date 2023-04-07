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
  product: ProductReceive = ProductReceive.forProduct(0, '', '', '', [], 0, 0, '', 0, []);

  // Purchase Variables
  quantity: number = 1;

  // Display Variables
  getProductError: boolean = false;
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
        if (res == undefined) {
          this.router.navigateByUrl('/');
          return;
        }
        // Assign Product
        this.product = ProductReceive.forProduct(res.id, res.name, res.brand, res.description,
            res.tagNames, res.price, res.stock, res.image, res.averageReviewGrade, res.productReviews);

        // Set ImageURLS
        let images = this.product.image.split(',', 3);
        if (images.length >= 1) {
          this.imageUrl1 = images[0];
        }
        if (images.length >= 2) {
          this.imageUrl2 = images[1];
        }
        if (images.length >= 3) {
          this.imageUrl3 = images[2];
        }

        this.primaryImageUrl = this.imageUrl1;
      },
      // If fails at server
      error: () => {
        this.getProductError = true;
        return;
      }
    });
  }

  // Add to Cart
  public addToCart(): void {
    this.shoppingCartService.addToCart(new CartItemSend(this.id, this.quantity)).subscribe({
      next: (res) => {
        // Get generic response to determine success
        const addResponse = res as DefaultResponse;
          if (addResponse.success != true) {
            this.toCartMessage = res.message;
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
