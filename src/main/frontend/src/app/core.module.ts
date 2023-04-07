import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { LOCAL_STORAGE } from 'ngx-webstorage-service';
import { CookieModule } from 'ngx-cookie';

import { USER_SERVICE_STORAGE } from './_services/user.service';
import { HttpRequestInterceptor } from './_interceptors/http.interceptor';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    CookieModule.withOptions(),
    HttpClientModule,
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
  ]
})
export class CoreModule { }
