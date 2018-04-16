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

const appRoutes = [
  { path: 'login', component: LoginComponent},
  { path: 'User/Profile', component: UserProfileComponent},
  { path: 'User/Configuration', component: UserConfigurationComponent},
  { path: 'User/Goal', component: UserGoalComponent},
  { path: 'Admin/Home', component: AdminComponent},
  { path: 'Admin/UserData', component: AdminUserDataComponent},
  { path: 'Admin/Content', component: UnitCreationComponent},
  { path: '', component: IndexComponent},
  { path: 'home', component: HomeComponent},
  { path: 'Unit/:id/Lessons', component: LessonComponent },
  { path: 'Unit/:id/Lessons/:idlesson/', component: ExerciseComponent }
];

export const routing = RouterModule.forRoot(appRoutes);
