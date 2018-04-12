import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule, JsonpModule } from '@angular/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { HeaderNavbarComponent } from './header/header_navbar.component';
import { BodyIndexComponent } from './index/body_index.component';
import { BodyHomeComponent } from './home/body_home.component';
import { LoginService } from './login/login.service';
import { LoginComponent } from './login/login.component';
import { NavBarComponent } from './navbar/navbar.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { UserConfigurationComponent } from './user/user-configuration/user-configuration.component';
import { HomeComponent } from './home/home.component';
import { IndexComponent } from './index/index.component';
import { LessonComponent } from './lesson/lesson.component';


import { routing } from './app.routing';
import { UnitsService } from './unit/unit.service';
import { UserService } from './user/user.service';



@NgModule({
  declarations: [
    AppComponent, 
    HeaderNavbarComponent , 
    BodyIndexComponent, 
    BodyHomeComponent,
    LoginComponent,
    NavBarComponent,
    UserProfileComponent,
    UserConfigurationComponent,
    IndexComponent,
    HomeComponent,
    LessonComponent
  ],
  imports: [
    BrowserModule, 
    FormsModule,
    HttpClientModule, 
    NgbModule.forRoot(),
    HttpModule, 
    JsonpModule,
    routing

  ],
  bootstrap: [AppComponent],
  providers: [
    LoginService,
    UserService,
    UnitsService

  ]
})
export class AppModule { }
