import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { LOCAL_STORAGE } from 'ngx-webstorage-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { ShopComponent } from './shop/shop.component';
import { ProductComponent } from './product/product.component';
import { CollectionsComponent } from './collections/collections.component';
import { UsersComponent } from './admin/users/users.component';
import { ProductsComponent } from './admin/products/products.component';
import { LoginComponent } from './login/login.component';
import { SubmenuComponent } from './submenu/submenu.component';
import { ProductsPipe } from './shop/products.pipe';
import { SignupComponent } from './signup/signup.component';
import { USER_SERVICE_STORAGE } from './services/user.service';

import { HttpRequestInterceptor } from './interceptors/http.interceptor';
import { ResourceNotFoundInterceptor } from './interceptors/notFound.interceptor';
import { NotFoundComponent } from './not-found/not-found.component';


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    ProductComponent,
    ShopComponent,
    CollectionsComponent,
    UsersComponent,
    ProductsComponent,
    LoginComponent,
    SubmenuComponent,
    ProductsPipe,
    SignupComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule,
    Ng2SearchPipeModule
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
