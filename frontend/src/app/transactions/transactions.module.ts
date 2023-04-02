import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';

import { TransactionsRoutingModule } from './transactions-routing.module';
import { TransactionsComponent } from './transactions.component';
import { SubmenuModule } from '@app/ui/submenu/submenu.module';

@NgModule({
  imports: [
    CommonModule,
    TransactionsRoutingModule,
    SubmenuModule,
    MatTableModule
  ],
  declarations: [
    TransactionsComponent
  ]
})
export class TransactionsModule { }
