import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

import { ProductReceive } from '@app/_core/product';
import { TagReceive } from '@app/_core/tag';
import { CartItemSend } from '@app/_core/cartItem';
import { DefaultResponse } from '@app/_core/defaultResponse';
import { ProductService } from '@app/_services/product.service';
import { ShoppingCartService } from '@app/_services/shopping-cart.service';
import { TagService } from '@app/_services/tag.service';
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
  tags: TagReceive[] = [];
  tagHeads: string[] = [];

  filterSearch!: string;
  // <b> element for search result for replacing searchFilter with scripts upon reception
  @ViewChild('searchText') public filterResult!: ElementRef;

  filters: any = {
    price1: false,
    price2: false,
    price3: false,
    price4: false,
  };

  // Display Variables
  getProductsError: boolean = false;
  getTagsError: boolean = false;
  cartBtnNum: number | null = null;
  cartBtnBool: boolean | null = null;
  
  constructor(
    private productService: ProductService,
    private shoppingCartService: ShoppingCartService,
    private tagService: TagService,
    private userService: UserService) { 
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
    this.getTags();
  }

  // Retrieve Products
  private getProducts(): void {
    this.productService.getProducts().subscribe({
      next: (res) => {
        if (res.length <= 0) {
          this.getProductsError = true;
          return;
        }
        Array.from(res).forEach((element: unknown) => {
          let product: any = element;
          this.products.push(ProductReceive.forShop(product.id, product.name, product.tagNames, product.price,
            product.image, product.averageReviewGrade));
          }
        );
      },
      error: () => {
        // Failed at server
        this.getProductsError = true;
      }}
    );
  }

  // Retrieve Products
  private getTags(): void {
    this.tagService.getTags().subscribe({
      next: (res) => {
        if (res.length <= 0) {
          this.getTagsError = true;
        }
        Array.from(res).forEach((element: unknown) => {
          let tag: any = element;
          this.tags.push(new TagReceive(tag.id, tag.name, tag.type));
          if (!this.tagHeads.includes(tag.type)) {
            this.tagHeads.push(tag.type);
          }
          
          this.filters[tag.id] = false;
        });
      },
      error: () => {
        // Failed at server
        this.getTagsError = true;
      }}
    );
  }

  // Add Product Quantity: 1 to Cart
  public addToCart(productID: number, productPrice: number): void {
    this.shoppingCartService.addToCart(CartItemSend.forAdd(productID, 1, productPrice)).subscribe({
      next: (res) => {
        // Get generic response to determine success
        const addResponse = res as DefaultResponse;
          if (addResponse.success != true) {
            this.cartBtnBool = false;
          }
          else {
            this.cartBtnBool = true;
          }
          
          this.cartBtnNum = productID;
      },
      error: () => {
        // Failed at server
        this.cartBtnNum = productID;
        this.cartBtnBool = false;
      }}
    );
  }

  // Retrieve Count by Filter for Badge
  public getFilterCount(filter: TagReceive | number): number {
    if (filter instanceof TagReceive) {
      return this.products.reduce((acc, obj) => obj.tagNames.includes(filter.name) ? acc += 1 : acc, 0);
    }
    else {
      switch ( filter ) {
        case 1:
          return this.products.reduce((acc, obj) => obj.price < 10 ? acc += 1 : acc, 0);
        case 2:
          return this.products.reduce((acc, obj) => obj.price >= 10 && obj.price < 20 ? acc += 1 : acc, 0);
        case 3:
          return this.products.reduce((acc, obj) => obj.price >= 20 && obj.price <= 30 ? acc += 1 : acc, 0);
        case 4:
          return this.products.reduce((acc, obj) => obj.price > 30 ? acc += 1 : acc, 0);
      }
    }

    return 0;
  }

  // Get the Tags formatted for HTML
  public formatTags(tags: string[]): string {
    if ( tags.length <= 2 ) {
      return tags.join(', ');
    }
    else {
      return tags[0] + ", " + tags[1] + ", ...";
    }
  }

  // Upon input in searchtext input should add content unsafely to DOM
  public updateFilterText() {
    const domElement = this.filterResult.nativeElement
    const fragment = document.createRange().createContextualFragment(this.filterSearch);
    domElement.appendChild(fragment);
  }
}
