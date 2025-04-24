import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { UserProfileComponent } from './component/user/user-profile/user-profile.component';
import { UserConfigurationComponent } from './component/user/user-configuration/user-configuration.component';
import { UserGoalComponent } from './component/user/user-goal/user-goal.component';
import { AdminComponent } from './component/admin/admin.component';
import { AdminUserDataComponent } from './component/admin/admin-user-data/admin-user-data.component';
import { HomeComponent } from './component/home/home.component';
import { UnitCreationComponent } from './component/unit/unit-creation/unit-creation.component';
import { UnitListComponent } from './component/unit/unit-list/unit-list.component';
import { IndexComponent } from './component/index/index.component';
import { LessonComponent } from './component/lesson/lesson.component';
import { ExerciseComponent } from './component/exercise/exercise.component';
import { ErrorComponent } from './component/error/error.component';
import { CompleteLessonComponent } from './component/completeLesson/completeLesson.component';
import { ContinueLessonComponent } from './component/continueLesson/continueLesson.component';
import { CanActivateUser } from './security/can-activate-user';
import { CanActivateAdmin } from './security/can-activate-admin';

const appRoutes = [
  { path: 'User/Profile', component: UserProfileComponent, canActivate: [CanActivateUser] },
  { path: 'User/Configuration', component: UserConfigurationComponent, canActivate: [CanActivateUser] },
  { path: 'User/Goal', component: UserGoalComponent, canActivate: [CanActivateUser] },
  { path: 'Admin/Home', component: AdminComponent, canActivate: [CanActivateAdmin] },
  { path: 'Admin/UserData', component: AdminUserDataComponent, canActivate: [CanActivateAdmin] },
  { path: 'Admin/Content/:id/:resume', component: UnitCreationComponent, canActivate: [CanActivateAdmin] },
  { path: 'Admin/UnitList', component: UnitListComponent, canActivate: [CanActivateAdmin] },
  { path: '', component: IndexComponent, pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'Unit/:id/Lessons', component: LessonComponent },
  { path: 'Unit/:id/Lessons/:idlesson/Exercise', component: ExerciseComponent },
  { path: 'Error', component: ErrorComponent },
  { path: 'Unit/:id/Lesson/:idlesson/lessonCompleted/:points', component: CompleteLessonComponent },
  { path: 'ContinueLesson', component: ContinueLessonComponent }
];

export const routing = RouterModule.forRoot(appRoutes,{useHash : true});
