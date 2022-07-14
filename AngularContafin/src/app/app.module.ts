import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { HeaderNavbarComponent } from './header/header_navbar.component';
import { BodyIndexComponent } from './index/body_index.component';
import { BodyHomeComponent } from './home/body_home.component';
import { BodyLessonComponent } from './lesson/body_lesson.component';
import { BodyExerciseComponent } from './exercise/body_exercise.component';
import { HeaderExerciseComponent } from "./exercise/header_exercise.component";
import { FooterExerciseComponent } from "./exercise/footer_exercise.component";
import { Exercise1Component } from "./exercise/exercise1/exercise1.component";
import { Exercise2Component } from "./exercise/exercise2/exercise2.component";
import { Exercise5Component } from "./exercise/exercise5/exercise5.component";
import { Exercise7Component } from "./exercise/exercise7/exercise7.component";

import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { NavBarComponent } from './navbar/navbar.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { UserConfigurationComponent } from './user/user-configuration/user-configuration.component';
import { UserGoalComponent } from './user/user-goal/user-goal.component';
import { AdminComponent } from './admin/admin.component';
import { AdminUserDataComponent } from './admin/admin-user-data/admin-user-data.component';
import { HomeComponent } from './home/home.component';
import { UnitCreationComponent } from './unit/unit-creation/unit-creation.component';
import { ExerciseComponent } from './exercise/exercise.component';
import { IndexComponent } from './index/index.component';
import { LessonComponent } from './lesson/lesson.component';
import { ErrorComponent } from './error/error.component'
import { routing } from './app.routing';
import { BodyErrorComponent } from './error/body_error.component';
import { BodyCompleteLessonComponent } from './completeLesson/body_completeLesson.component';
import { CompleteLessonComponent } from './completeLesson/completeLesson.component';
import { ContinueLessonComponent } from './continueLesson/continueLesson.component';
import { BodyContinueLessonComponent } from './continueLesson/body_continueLesson.component';

//Services
import { UnitsService } from './unit/unit.service';
import { LoginService } from './login/login.service';
import { SignUpService } from './sign-up/sign-up.service';
import { UserService } from './user/user.service';
import { LessonsService } from './lesson/lesson.service';
import { ExerciseService } from './exercise/exercise.service';
import { AdminService } from './admin/admin.service';
import { ErrorService } from './error/error.service';
import { CanActivateUser } from './security/can-activate-user';
import { CanActivateAdmin } from './security/can-activate-admin';




@NgModule({
  declarations: [
    AppComponent,
    HeaderNavbarComponent,
    BodyIndexComponent,
    BodyHomeComponent,
    LoginComponent,
    SignUpComponent,
    NavBarComponent,
    UserProfileComponent,
    UserGoalComponent,
    UserConfigurationComponent,
    AdminComponent,
    AdminUserDataComponent,
    UnitCreationComponent,
    IndexComponent,
    HomeComponent,
    LessonComponent,
    BodyLessonComponent,
    ExerciseComponent,
    BodyExerciseComponent,
    HeaderExerciseComponent,
    FooterExerciseComponent,
    Exercise1Component,
    Exercise2Component,
    Exercise5Component,
    Exercise7Component,
    BodyErrorComponent,
    ErrorComponent,
    BodyCompleteLessonComponent,
    CompleteLessonComponent,
    BodyContinueLessonComponent,
    ContinueLessonComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbNavModule,
    routing

  ],
  bootstrap: [AppComponent],
  providers: [
    LoginService,
    SignUpService,
    UserService,
    CanActivateUser,
    UnitsService,
    LessonsService,
    ExerciseService,
    AdminService,
    CanActivateAdmin,
    ErrorService
  ]
})
export class AppModule { }
