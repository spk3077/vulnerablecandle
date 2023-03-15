import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Product } from '@app/_core/product';
import { ProductService } from '@app/_services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  product!: Product;

  // Image URLs
  primaryImageUrl: string | undefined;
  imageUrl1: string | undefined;
  imageUrl2: string | undefined;
  imageUrl3: string | undefined;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    ) {}

  ngOnInit(): void {
    this.getProduct();

    // Set ImageURLS
    if (this.product.imageUrls.length >= 1) {
      this.imageUrl1 = this.product.imageUrls[0];
    }
    if (this.product.imageUrls.length >= 2) {
      this.imageUrl2 = this.product.imageUrls[1];
    }
    if (this.product.imageUrls.length >= 3) {
      this.imageUrl3 = this.product.imageUrls[2];
    }
  }

  getProduct(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 1);
    this.productService.getProduct(id).subscribe(product => this.product = product);
  }
}
