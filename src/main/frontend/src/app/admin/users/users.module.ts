import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminUsersRoutingModule } from './users-routing.module';
import { AdminUsersComponent } from './users.component';

import { SubmenuModule } from '@app/ui/submenu/submenu.module';

@NgModule({
  imports: [
    CommonModule,
    AdminUsersRoutingModule,
    SubmenuModule
  ],
  declarations: [AdminUsersComponent]
})

export class AdminUsersModule { }
