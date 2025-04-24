import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgbNavModule, NgbModule, NgbProgressbarModule  } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';


import { AppComponent } from './component/app.component';
import { HeaderNavbarComponent } from './component/header/header_navbar.component';
import { BodyIndexComponent } from './component/index/body/body_index.component';
import { BodyHomeComponent } from './component/home/body/body_home.component';
import { BodyLessonComponent } from './component/lesson/body/body_lesson.component';
import { BodyExerciseComponent } from './component/exercise/body/body_exercise.component';
import { HeaderExerciseComponent } from "./component/exercise/header/header_exercise.component";
import { Exercise1Component } from "./component/exercise/body/types/exercise1/exercise1.component";
import { Exercise2Component } from "./component/exercise/body/types/exercise2/exercise2.component";
import { Exercise5Component } from "./component/exercise/body/types/exercise5/exercise5.component";
import { Exercise7Component } from "./component/exercise/body/types/exercise7/exercise7.component";
import { Exercise3Component } from './component/exercise/body/types/exercise3/exercise3.component';

import { LoginComponent } from './component/login/login.component';
import { SignUpComponent } from './component/sign-up/sign-up.component';
import { NavBarComponent } from './component/navbar/navbar.component';
import { UserProfileComponent } from './component/user/user-profile/user-profile.component';
import { UserConfigurationComponent } from './component/user/user-configuration/user-configuration.component';
import { UserGoalComponent } from './component/user/user-goal/user-goal.component';
import { AdminComponent } from './component/admin/admin.component';
import { AdminUserDataComponent } from './component/admin/admin-user-data/admin-user-data.component';
import { HomeComponent } from './component/home/home.component';
import { UnitCreationComponent } from './component/unit/unit-creation/unit-creation.component';
import { ExerciseComponent } from './component/exercise/exercise.component';
import { IndexComponent } from './component/index/index.component';
import { LessonComponent } from './component/lesson/lesson.component';
import { ErrorComponent } from './component/error/error.component'
import { routing } from './app.routing';
import { BodyErrorComponent } from './component/error/body/body_error.component';
import { BodyCompleteLessonComponent } from './component/completeLesson/body/body_completeLesson.component';
import { CompleteLessonComponent } from './component/completeLesson/completeLesson.component';
import { ContinueLessonComponent } from './component/continueLesson/continueLesson.component';
import { BodyContinueLessonComponent } from './component/continueLesson/body/body_continueLesson.component';
import { UnitListComponent } from './component/unit/unit-list/unit-list.component';

//Services
import { UnitsService } from './services/unit.service';
import { LoginService } from './services/login.service';
import { SignUpService } from './services/sign-up.service';
import { UserService } from './services/user.service';
import { LessonsService } from './services/lesson.service';
import { ExerciseService } from './services/exercise.service';
import { AdminService } from './services/admin.service';
import { ErrorService } from './services/error.service';
import { CanActivateUser } from './security/can-activate-user';
import { CanActivateAdmin } from './security/can-activate-admin';

import { authInterceptorProviders } from './helpers/auth.interceptor';




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
    Exercise1Component,
    Exercise2Component,
    Exercise5Component,
    Exercise7Component,
    Exercise3Component,
    BodyErrorComponent,
    ErrorComponent,
    BodyCompleteLessonComponent,
    CompleteLessonComponent,
    BodyContinueLessonComponent,
    ContinueLessonComponent,
    UnitListComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbNavModule,
    NgbModule,
    NgbProgressbarModule,
    routing
  ],
  bootstrap: [AppComponent],
  providers: [
    authInterceptorProviders,
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
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class AppModule { }
