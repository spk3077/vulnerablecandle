import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CheckOutRoutingModule } from './check-out-routing.module';
import { CheckOutComponent } from './check-out.component';

@NgModule({
  imports: [
    CommonModule,
    CheckOutRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [CheckOutComponent]
})
export class CheckOutModule { }
