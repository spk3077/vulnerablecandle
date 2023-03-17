import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Product } from '@app/_core/product';
import { ProductService } from '@app/_services/product.service';
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
  product!: Product;

  // Purchase Variables
  toCart: boolean = false;
  quantity: number = 1;

  // Stock Variables
  showStock: boolean = false;

  // Image URLs
  primaryImageUrl!: string | undefined;
  imageUrl1: string | undefined;
  imageUrl2: string | undefined;
  imageUrl3: string | undefined;

  constructor(
    private productService: ProductService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
    ) {}
  
  // Font Awesome Exports
  faStar = faStar;
  faShoppingBasket = faShoppingBasket;

  ngOnInit(): void {
    this.userService.loggedInUser$.subscribe(x => this.currentUser = x);
    this.getProduct();
  }
  
  // Fetch the Product to Fillout Page
  private getProduct(): void {
    const id: number = parseInt(this.route.snapshot.paramMap.get('productid')!);

    // If ID is not a number, return to SHOP
    if (Number.isNaN(id)) {
      this.router.navigateByUrl('/');
    }

    this.productService.getProduct(id).subscribe({
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
