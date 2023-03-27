import { Component } from '@angular/core';

import { ProductReceive } from '@app/_core/product';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class AdminProductsComponent {
  products!: ProductReceive[];

}
