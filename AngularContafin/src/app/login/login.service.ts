import { Injectable, OnInit } from '@angular/core';
import { User } from '../Interfaces/User/user.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import 'rxjs/Rx';

const URL = environment.apiBase;


@Injectable()
export class LoginService {

    isLogged = false;
    isAdmin = false;
    user: User;

    constructor(private http: HttpClient) { }

    getLoggedUser() {
        return this.user;
    }
    setLoggedUser(user){
        this.user = user;
        this.user.imgURL = "https://localhost:8080/api/User/Photo";
    }

    isLoggedUser() {
        return this.isLogged;
    }

    isAdministrator() {
        return this.isAdmin;
    }

    reqIsLogged() {

        const headers = new HttpHeaders({
            'X-Requested-With': 'XMLHttpRequest'
        });

        this.http.get(URL + '/login', { withCredentials: true, headers }).subscribe(
            response => this.processLogInResponse(response),
            error => {
                if (error.status !== 401) {
                    console.error('Error when asking if logged: ' + JSON.stringify(error));
                }
            }
        );
    }

    private processLogInResponse(response) {
        this.isLogged = true;
        this.user = response.json();
        this.user.imgURL = "https://localhost:8080/api/User/Photo";
        this.isAdmin = this.user.roles.indexOf('ROLE_ADMIN') !== -1;
    }

    logIn(user: string, pass: string) {

        const userPass = user + ':' + pass;

        const headers = new HttpHeaders({
            'Authorization': 'Basic ' + utf8_to_b64(userPass),
            'X-Requested-With': 'XMLHttpRequest'
        });


        return this.http.get(URL + '/login', { withCredentials: true, headers }).map(
            response => {
                this.processLogInResponse(response);
                return this.user;
            }
        );
    }

    logOut() {
        return this.http.get(URL + '/logout', { withCredentials: true }).map(
            response => {
                this.isLogged = false;
                this.isAdmin = false;
                return response;
            }
        );
    }
}

function utf8_to_b64(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {//Mirar si esta bien
        return String.fromCharCode(<any>'0x' + p1);
    }));
}