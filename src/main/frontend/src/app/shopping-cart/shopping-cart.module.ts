import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShoppingCartRoutingModule } from './shopping-cart-routing.module';
import { ShoppingCartComponent } from './shopping-cart.component';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  imports: [
    CommonModule,
    ShoppingCartRoutingModule,
    FormsModule,
    FontAwesomeModule
  ],
  declarations: [ShoppingCartComponent]
})
export class ShoppingCartModule { }
