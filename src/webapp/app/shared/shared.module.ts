import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FlexLayoutModule} from "@angular/flex-layout";
import {TranslateModule} from "@ngx-translate/core";



@NgModule({
  imports: [
    CommonModule
  ],
  exports: [
    FlexLayoutModule,
    TranslateModule
  ]
})
export class SharedModule { }
