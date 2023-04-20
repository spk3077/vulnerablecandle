import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

import { ShopRoutingModule } from './shop-routing.module';
import { ShopComponent } from './shop.component';
import { ProductsPipe } from './products.pipe';
import { TagsPipe } from './tags.pipe';
import { SafePipe } from '@app/_helpers/safe.pipe';

import { SubmenuModule } from '@app/ui/submenu/submenu.module';

@NgModule({
  imports: [
    CommonModule,
    ShopRoutingModule,
    SubmenuModule,
    FormsModule,
    FontAwesomeModule,
    Ng2SearchPipeModule
  ],
  declarations: [
    ShopComponent,
    ProductsPipe,
    TagsPipe,
    SafePipe
  ]
})

export class ShopModule { }
