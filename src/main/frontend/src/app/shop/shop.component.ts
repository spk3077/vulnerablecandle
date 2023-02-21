import { Component, OnInit } from '@angular/core';

import { Product } from '../core/product';
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
}
