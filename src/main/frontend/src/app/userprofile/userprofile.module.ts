import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { UserProfileComponent } from './userprofile.component';
import { UserProfileRoutingModule } from './userprofile-routing.module';

@NgModule({
  imports: [
    CommonModule,
    UserProfileRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [UserProfileComponent]
})

export class UserProfileModule { }
