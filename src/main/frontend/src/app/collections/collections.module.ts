import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CollectionsComponent } from './collections.component';
import { CollectionsRoutingModule } from './collections-routing.module';

import { SubmenuModule } from '@app/ui/submenu/submenu.module';

@NgModule({
  imports: [
    CommonModule,
    CollectionsRoutingModule,
    SubmenuModule
  ],
  declarations: [CollectionsComponent]
})

export class CollectionsModule { }
