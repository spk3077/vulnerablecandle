import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { Product } from '@app/_core/product';
import { PRODUCTS } from '@app/mock-products';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor() { }

  getProducts(): Observable<Product[]> {
    const products = of(PRODUCTS);
    return products;
  }

  getProduct(id: number): Observable<Product> {
    const product = of(PRODUCTS[1]);
    return product;
  }
}
