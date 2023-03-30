import { Component, OnInit } from '@angular/core';

import { ProductReceive } from '@app/_core/product';
import { ProductService } from '@app/_services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  products: ProductReceive[] = [];

  // Display Variables
  getProductsError: boolean = false;

  constructor(private productService: ProductService){}

  ngOnInit(): void {
    this.getProducts();
  }

  // Retrieve Products
  private getProducts(): void {
    this.productService.getProducts().subscribe({
      next: (res) => {
        if (res.length <= 0) {
          this.getProductsError = true;
        }
        
        Array.from(res).forEach((element: unknown, index: number) => {
          if (index <= 7) {
            let product: any = element;
            this.products.push(ProductReceive.forHome(product.id, product.name, product.price, product.image, product.averageReviewGrade));
          }
        });

      },
      error: () => {
        // Failed at server
        this.getProductsError = true;
      }
    });
    
  }

}
