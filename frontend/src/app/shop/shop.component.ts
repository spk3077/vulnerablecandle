import { Component, OnInit } from '@angular/core';

import { Product } from '@app/_core/product';
import { ProductReview } from '@app/_core/productReview';

import { ProductService } from '@app/_services/product.service';

import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { faChevronDown } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {
  // Title of Page
  title: string = "Our Shop";
  
  // Products
  products: Product[] = [];

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

  constructor(private productService: ProductService) { }
  
  // Font Awesome Exports
  faMagnifyingGlass = faMagnifyingGlass;
  faChevronDown = faChevronDown;

  ngOnInit(): void {
    this.getProducts();
  }
  
  // Retrieve Products
  getProducts(): void {
    this.productService.getProducts()
      .subscribe(products => this.products = products);
  }

  // Retrieve Count by Filter for Badge
  getFilterCount(type: number): number {
    switch ( type ) {
      case 1:
        return this.products.reduce((acc, obj) => obj.tags.includes("isPopular") ? acc += 1 : acc, 0);
      case 2:
        return this.products.reduce((acc, obj) => obj.tags.includes("isCute") ? acc += 1 : acc, 0);
      case 3:
        return this.products.reduce((acc, obj) => obj.tags.includes("isTrending") ? acc += 1 : acc, 0);
      case 4:
        return this.products.reduce((acc, obj) => obj.tags.includes("isForCar") ? acc += 1 : acc, 0);
      case 5:
        return this.products.reduce((acc, obj) => obj.tags.includes("isUnique") ? acc += 1 : acc, 0);
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
  getTags(tags: string[]): string {
    if ( tags.length <= 2 ) {
      return tags.join(', ');
    }
    else {
      return tags[0] + ", " + tags[1] + ", ...";
    }
  }

  // Get the Mean of Reviews
  getBadgeGrade(reviews: ProductReview[]): number {
    return reviews.reduce((acc, review) => acc + review.grade, 0) / reviews.length;
  }

  // Set The Color of Rating Bubble
  setBadgeClass(reviews: ProductReview[]): string {
    // Get MEAN using an accumulator and looping reviews
    let mean: number = this.getBadgeGrade(reviews);
    if ( mean < 2 ) {
      return 'bg-danger';
    } else if ( mean > 2 && mean < 4 ) {
      return 'bg-warning';
    } else {
      return 'bg-success';
    }
  }
}
