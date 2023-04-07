import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MenuComponent } from '@app/ui/menu/menu.component';
import { RouterModule } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FontAwesomeModule
  ],
  declarations: [MenuComponent],
  exports: [MenuComponent],
})
export class MenuModule { }
