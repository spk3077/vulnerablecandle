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

        console.log(res);
        res.forEach((product: ProductReceive, index: number) => {
          if (index <= 7) {
            this.products.push(
              new ProductReceive(product.id, product.name, product.brand, product.description,
                product.tagNames, product.price, product.stock, product.image, product.averageReviewGrade,
                  product.productReviews)
            );
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
