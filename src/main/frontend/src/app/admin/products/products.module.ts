import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AdminProductsRoutingModule } from './products-routing.module';
import { AdminProductsComponent } from './products.component';

import { SubmenuModule } from '@app/ui/submenu/submenu.module';

@NgModule({
  imports: [
    CommonModule,
    AdminProductsRoutingModule,
    SubmenuModule,
    ReactiveFormsModule,
    NgbModule
  ],
  declarations: [AdminProductsComponent]
})

export class AdminProductsModule { }
