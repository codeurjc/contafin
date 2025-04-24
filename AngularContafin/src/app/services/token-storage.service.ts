
import { Injectable } from '@angular/core';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';
import { User } from '../Interfaces/User/user.model';



const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor(private sanitizer: DomSanitizer) { }

  signOut(): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.clear();
    console.log("Hace el clear");
  }

  public saveToken(token: string): void {
    console.log("Datos token: " + JSON.stringify(token));
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    console.log("getToken: " + JSON.stringify(window.sessionStorage.getItem(TOKEN_KEY)));
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveLoginInfo(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public saveUser(user: User): void {
    let userCopy = JSON.parse(window.sessionStorage.getItem(USER_KEY));
    window.sessionStorage.removeItem(USER_KEY);
    userCopy.user = user;
    console.log("Datos user 1234: " + JSON.stringify(userCopy));
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(userCopy));
  }

  public getUser() : User | null{
    if(window.sessionStorage.getItem(USER_KEY) !== undefined && window.sessionStorage.getItem(USER_KEY) !== null){
      let user = JSON.parse(window.sessionStorage.getItem(USER_KEY));
      console.log("Datos user 567: " + JSON.stringify(user));
      return user.user;
    }else{
      return null;
    }
   
  }

  public getLoginInfo() : any {
    let user = JSON.parse(window.sessionStorage.getItem(USER_KEY));
    if (user) {
      let data = {
        info : user,
        user : user.user,
        imageView : user.user.image !== undefined && user.user.image !== null ? this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + user.user.image) : null,
        isLogged : true,
        isAdmin : user.roles.indexOf('ROLE_ADMIN') !== -1
      }
      return data;
    }

    return null;
  }
}