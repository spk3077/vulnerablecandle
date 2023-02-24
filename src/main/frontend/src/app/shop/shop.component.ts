import { Component, OnInit } from '@angular/core';

import { Product } from '../core/product';
import { ProductReview } from '../core/productReview';

import { HttpClientService } from '../service/http-client.service';

import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { faChevronDown } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {
  // Font Awesome Exports
  faMagnifyingGlass = faMagnifyingGlass;
  faChevronDown = faChevronDown;
  
  // Products
  products: Product[] = []

  constructor(private httpService: HttpClientService) { }

  ngOnInit(): void {
    this.getHeroes();
  }

  getHeroes(): void {
    this.httpService.getProducts()
      .subscribe(products => this.products = products);
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
