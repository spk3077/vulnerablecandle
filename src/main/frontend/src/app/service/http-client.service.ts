import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { Product } from '../core/product';
import { PRODUCTS } from '../mock-products';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor() { }
  
  // getProducts(): Product[] {
  //   return PRODUCTS;
  // }

  getProducts(): Observable<Product[]> {
    const products = of(PRODUCTS);
    return products;
  }
}
