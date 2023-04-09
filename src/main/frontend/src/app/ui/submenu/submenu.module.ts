import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SubmenuComponent } from '@app/ui/submenu/submenu.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    RouterModule
  ],
  declarations: [SubmenuComponent],
  exports: [SubmenuComponent]
})
export class SubmenuModule { }
