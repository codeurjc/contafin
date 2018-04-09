import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';

const appRoutes = [
  { path: 'User/Profile', component: UserProfileComponent}
];

export const routing = RouterModule.forRoot(appRoutes);