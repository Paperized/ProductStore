import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MainComponent} from './layout/main/main.component';
import {LayoutModule} from "./layout/layout.module";
import {SharedModule} from "./shared/shared.module";
import {HttpClientModule} from "@angular/common/http";
import {customInterceptors} from "./interceptor/interceptors";

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    SharedModule,
    HttpClientModule
  ],
  providers: [
    customInterceptors
  ],
  bootstrap: [MainComponent]
})
export class AppModule { }
