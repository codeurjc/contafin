import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { UserConfigurationComponent } from './user/user-configuration/user-configuration.component';
import { UserGoalComponent } from './user/user-goal/user-goal.component';
import { AdminComponent } from './admin/admin.component';
import { AdminUserDataComponent } from './admin/admin-user-data/admin-user-data.component';
import { HomeComponent } from './home/home.component';
import { UnitCreationComponent } from './unit/unit-creation/unit-creation.component';
import { IndexComponent } from './index/index.component';
import { LessonComponent } from './lesson/lesson.component';
import { ExerciseComponent } from './exercise/exercise.component';
import { ErrorComponent } from './error/error.component';
import { CompleteLessonComponent } from './completeLesson/completeLesson.component';
import { ContinueLessonComponent } from './continueLesson/continueLesson.component';
import { CanActivateUser } from './security/can-activate-user';
import { CanActivateAdmin } from './security/can-activate-admin';

const appRoutes = [
  { path: 'User/Profile', component: UserProfileComponent, canActivate: [CanActivateUser] },
  { path: 'User/Configuration', component: UserConfigurationComponent, canActivate: [CanActivateUser] },
  { path: 'User/Goal', component: UserGoalComponent, canActivate: [CanActivateUser] },
  { path: 'Admin/Home', component: AdminComponent, canActivate: [CanActivateAdmin] },
  { path: 'Admin/UserData', component: AdminUserDataComponent, canActivate: [CanActivateAdmin] },
  { path: 'Admin/Content', component: UnitCreationComponent, canActivate: [CanActivateAdmin] },
  { path: '', component: IndexComponent },
  { path: 'home', component: HomeComponent },
  { path: 'Unit/:id/Lessons', component: LessonComponent },
  { path: 'Unit/:id/Lessons/:idlesson/Exercise/:idkind/:idexercise', component: ExerciseComponent },
  { path: 'Error', component: ErrorComponent },
  { path: 'Unit/:id/Lesson/:idlesson/lessonCompleted/', component: CompleteLessonComponent },
  { path: 'ContinueLesson', component: ContinueLessonComponent }
];

export const routing = RouterModule.forRoot(appRoutes);
