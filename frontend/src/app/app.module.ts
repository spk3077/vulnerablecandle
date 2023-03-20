import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LOCAL_STORAGE } from 'ngx-webstorage-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { UserProfileComponent } from './userprofile/userprofile.component';
import { SubmenuComponent } from './submenu/submenu.component';
import { ShopComponent } from './shop/shop.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { CheckOutComponent } from './check-out/check-out.component';
import { ProductComponent } from './product/product.component';
import { ProductsPipe } from './shop/products.pipe';
import { CollectionsComponent } from './collections/collections.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AdminUsersComponent } from './admin/users/users.component';
import { AdminProductsComponent } from './admin/products/products.component';
import { HttpRequestInterceptor } from './_interceptors/http.interceptor';
import { ResourceNotFoundInterceptor } from './_interceptors/notFound.interceptor';
import { USER_SERVICE_STORAGE } from './_services/user.service';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    UserProfileComponent,
    SubmenuComponent,
    ShopComponent,
    TransactionsComponent,
    ShoppingCartComponent,
    CheckOutComponent,
    ProductComponent,
    ProductsPipe,
    CollectionsComponent,
    SignupComponent,
    LoginComponent,
    LogoutComponent,
    NotFoundComponent,
    AdminUsersComponent,
    AdminProductsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule,
    Ng2SearchPipeModule,
    NgbModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ResourceNotFoundInterceptor,
      multi: true
    },

    {provide: USER_SERVICE_STORAGE, useExisting: LOCAL_STORAGE}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
