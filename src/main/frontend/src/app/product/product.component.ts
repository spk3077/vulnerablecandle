import { Component, OnInit, ViewChild, ElementRef, ViewChildren, QueryList } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CartItemSend } from '@app/_core/cartItem';
import { DefaultResponse } from '@app/_core/defaultResponse';

import { ProductReceive } from '@app/_core/product';
import { ProductReviewReceive } from '@app/_core/productReview';
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

  // For replacing comments with scripts upon reception
  @ViewChildren('review') elements!: QueryList<any>;

  // Purchase Variables
  quantity: number = 1;

  // Display Variables
  getProductError: boolean = false;
  getProductStockError: boolean = false;
  stockNum: number | null = null;
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
    private router: Router,
    private _elementRef : ElementRef
    ) {}
  
  // Font Awesome Exports
  faStar = faStar;
  faShoppingBasket = faShoppingBasket;

  ngOnInit(): void {
    this.id = parseInt(this.route.snapshot.paramMap.get('productid')!);
    this.userService.loggedInUser$.subscribe(x => this.currentUser = x);
    this.getProduct();
  }

  ngAfterViewInit() {
    this.elements.changes.subscribe( () => {
      // Add exploitable scripts to DOM
      this.product.productReviews.forEach((review: ProductReviewReceive) => {
        let domElement : any = this._elementRef.nativeElement.querySelector("#review-" + review.id);
        let fragment = document.createRange().createContextualFragment(review.comment);
        domElement.appendChild(fragment);
      });
    })
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

  // Fetch the Stock
  public getProductStock(): void {
    this.productService.getProductStock(this.id).subscribe({
      // If Successful
      next: (res) => {
        if (res.length == 4) {
          this.getProductStockError = true;
          return;
        }
        this.stockNum = +res.slice(12);
        this.getProductStockError = false;
      },
      // If fails at server
      error: () => {
        this.getProductStockError = true;
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

  // Adding 1 to Quantity
  public addQuantity(): void {
    this.quantity = this.quantity + 1;
  }

  // Subtracting 1 from Quantity
  public subQuantity(): void {
    this.quantity = this.quantity - 1;
  }
}
