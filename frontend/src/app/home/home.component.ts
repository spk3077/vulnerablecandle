import { Component, OnInit } from '@angular/core';

import { ProductReceive } from '@app/_core/product';
import { ProductService } from '@app/_services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  products!: ProductReceive[];

  constructor(private productService: ProductService){}

  ngOnInit(): void {
    this.getProducts();
  }

  // Retrieve Products
  private getProducts(): void {
    this.productService.getProducts().subscribe(products => this.products = products.slice(0, 5));
  }

}
