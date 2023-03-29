import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table'; 
import { HttpClientModule, HttpClientXsrfModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LOCAL_STORAGE } from 'ngx-webstorage-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { AppComponent } from './app.component';
import { MenuComponent } from './ui/menu/menu.component';
import { SubmenuComponent } from './ui/submenu/submenu.component';
import { FooterComponent } from './ui/footer/footer.component';
import { UserProfileComponent } from './userprofile/userprofile.component';
import { HomeComponent } from './home/home.component';
import { ShopComponent } from './shop/shop.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { CheckOutComponent } from './check-out/check-out.component';
import { ProductComponent } from './product/product.component';
import { CollectionsComponent } from './collections/collections.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AdminComponent } from './admin/admin/admin.component';
import { AdminUsersComponent } from './admin/users/users.component';
import { AdminProductsComponent } from './admin/products/products.component';
import { HttpRequestInterceptor } from './_interceptors/http.interceptor';
import { USER_SERVICE_STORAGE } from './_services/user.service';
import { ProductsPipe } from './shop/products.pipe';
import { CallbackPipe } from './_helpers/callback.pipe';
import { TagsPipe } from './shop/tags.pipe';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    SubmenuComponent,
    FooterComponent,
    UserProfileComponent,
    HomeComponent,
    ShopComponent,
    TransactionsComponent,
    ShoppingCartComponent,
    CheckOutComponent,
    ProductComponent,
    CollectionsComponent,
    SignupComponent,
    LoginComponent,
    LogoutComponent,
    NotFoundComponent,
    AdminComponent,
    AdminUsersComponent,
    AdminProductsComponent,
    ProductsPipe,
    CallbackPipe,
    TagsPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpClientXsrfModule,
    ReactiveFormsModule,
    FormsModule,
    FontAwesomeModule,
    Ng2SearchPipeModule,
    NgbModule,
    MatTableModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true
    },

    {provide: USER_SERVICE_STORAGE, useExisting: LOCAL_STORAGE}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
