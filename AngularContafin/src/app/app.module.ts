import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


import { AppComponent } from './app.component';
import { HeaderNavbarComponent } from './header/header_navbar.component';
import { BodyIndexComponent } from './body/body_index.component';
import { BodyHomeComponent } from './body/body_home.component';


@NgModule({
  declarations: [AppComponent, HeaderNavbarComponent , BodyIndexComponent, BodyHomeComponent],
  imports: [
    BrowserModule, HttpClientModule, NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
