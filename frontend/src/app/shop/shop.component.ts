import { Component, OnInit } from '@angular/core';

import { Product } from '@app/_core/product';
import { ProductService } from '@app/_services/product.service';
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

  constructor(private productService: ProductService, private userService: UserService) { 
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
  getProducts(): void {
    this.productService.getProducts()
      .subscribe(products => this.products = products);
  }

  // Retrieve Count by Filter for Badge
  getFilterCount(type: number): number {
    switch ( type ) {
      case 1:
        return this.products.reduce((acc, obj) => obj.tagNames.includes("isPopular") ? acc += 1 : acc, 0);
      case 2:
        return this.products.reduce((acc, obj) => obj.tagNames.includes("isCute") ? acc += 1 : acc, 0);
      case 3:
        return this.products.reduce((acc, obj) => obj.tagNames.includes("isTrending") ? acc += 1 : acc, 0);
      case 4:
        return this.products.reduce((acc, obj) => obj.tagNames.includes("isForCar") ? acc += 1 : acc, 0);
      case 5:
        return this.products.reduce((acc, obj) => obj.tagNames.includes("isUnique") ? acc += 1 : acc, 0);
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
}
