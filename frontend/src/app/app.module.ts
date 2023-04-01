import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { LOCAL_STORAGE } from 'ngx-webstorage-service';
import { CookieModule } from 'ngx-cookie';

import { AppComponent } from './app.component';
import { MenuComponent } from './ui/menu/menu.component';
import { FooterComponent } from './ui/footer/footer.component';
import { UserProfileComponent } from './userprofile/userprofile.component';
import { CollectionsComponent } from './collections/collections.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin/admin.component';
import { AdminUsersComponent } from './admin/users/users.component';
import { AdminProductsComponent } from './admin/products/products.component';
import { HttpRequestInterceptor } from './_interceptors/http.interceptor';
import { USER_SERVICE_STORAGE } from './_services/user.service';

import { SubmenuModule } from './ui/submenu/submenu.module';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    FooterComponent,
    CollectionsComponent,
    SignupComponent,
    LoginComponent,
    AdminComponent,
    AdminUsersComponent,
    AdminProductsComponent
  ],
  imports: [
    BrowserModule,
    CookieModule.withOptions(),
    SubmenuModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    FontAwesomeModule,
    AppRoutingModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true
    },
    {
      provide: USER_SERVICE_STORAGE, 
      useExisting: LOCAL_STORAGE
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
