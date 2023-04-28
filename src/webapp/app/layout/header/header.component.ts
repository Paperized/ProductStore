import {Component, Input, TemplateRef, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent{
  StoreName: string = "AmazingShop";
  @Input() sidenav?: MatSidenav;

  @ViewChild("sidenavTemplate")
  sidenavTemplate?: TemplateRef<any>;
}
