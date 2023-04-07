import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';

import { TransactionsRoutingModule } from './transactions-routing.module';
import { TransactionsComponent } from './transactions.component';
import { SubmenuModule } from '@app/ui/submenu/submenu.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    TransactionsRoutingModule,
    SubmenuModule,
    MatTableModule,
    ReactiveFormsModule
  ],
  declarations: [TransactionsComponent]
})
export class TransactionsModule { }
