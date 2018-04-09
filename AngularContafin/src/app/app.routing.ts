import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { HomeComponent } from './home/home.component';
import { IndexComponent } from './index/index.component';

const appRoutes = [
  { path: 'User/Profile', component: UserProfileComponent},
  { path: '', component: IndexComponent},
  { path: 'Home', component: HomeComponent}
];

export const routing = RouterModule.forRoot(appRoutes);