import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { AppComponent } from './app.component';
import { HeaderIndexComponent } from './header/header_index.component';
import { HeaderNormalComponent } from './header/header_normal.component';
import { BodyIndexComponent } from './body/body_index.component';
import { BodyHomeComponent } from './body/body_home.component';


@NgModule({
  declarations: [AppComponent, HeaderIndexComponent, HeaderNormalComponent, BodyIndexComponent, BodyHomeComponent],
  imports: [
    BrowserModule, HttpClientModule, NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
