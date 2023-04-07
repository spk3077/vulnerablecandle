import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { LOCAL_STORAGE } from 'ngx-webstorage-service';
import { CookieModule } from 'ngx-cookie';

import { AppComponent } from './app.component';
import { FooterComponent } from './ui/footer/footer.component';
import { HttpRequestInterceptor } from './_interceptors/http.interceptor';
import { USER_SERVICE_STORAGE } from './_services/user.service';
import { MenuModule } from './ui/menu/menu.module';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    CookieModule.withOptions(),
    MenuModule,
    HttpClientModule,
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
