import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MainComponent} from './layout/main/main.component';
import {LayoutModule} from "./layout/layout.module";
import {HomeComponent} from './pages/home/home.component';
import {SharedModule} from "./shared/shared.module";

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    SharedModule
  ],
  providers: [],
  bootstrap: [MainComponent]
})
export class AppModule { }
