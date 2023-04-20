import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ProductComponent } from './product.component';
import { ProductRoutingModule } from './product-routing.module';

import { SubmenuModule } from '@app/ui/submenu/submenu.module';
import { SafePipeModule } from '@app/_helpers/safe.pipe.module';

@NgModule({
  imports: [
    CommonModule,
    ProductRoutingModule,
    SubmenuModule,
    SafePipeModule,
    FormsModule,
    FontAwesomeModule,
    NgbModule
  ],
  declarations: [ProductComponent]
})

export class ProductModule { }
