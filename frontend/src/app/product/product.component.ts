import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

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
    ) {}
  
  // Font Awesome Exports
  faStar = faStar;
  faShoppingBasket = faShoppingBasket;

  ngOnInit(): void {
    this.userService.loggedInUser$.subscribe(x => this.currentUser = x);
    this.getProduct();
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
  }
  
  // Fetch the Product to Fillout Page
  private getProduct(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 1);
    this.productService.getProduct(id).subscribe(product => this.product = product);
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
