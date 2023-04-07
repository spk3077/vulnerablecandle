import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminProductsRoutingModule } from './products-routing.module';
import { AdminProductsComponent } from './products.component';

import { SubmenuModule } from '@app/ui/submenu/submenu.module';

@NgModule({
  imports: [
    CommonModule,
    AdminProductsRoutingModule,
    SubmenuModule
  ],
  declarations: [AdminProductsComponent]
})

export class AdminProductsModule { }
