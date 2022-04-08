import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { User } from '../Interfaces/User/user.model';

const BASE_URL = environment.apiBase + '/User';


@Injectable()
export class UserService {

    public user: User;

    constructor(private http: HttpClient) {

    }

    getUser(id: number) {
        const headers = new HttpHeaders({
            'X-Requested-With': 'XMLHttpRequest'
        });

        return this.http.get(BASE_URL + '/' + id, {withCredentials: true})
            .mapTo(response => {
                this.user = response;
                return this.user;
            })
            .catch(error => this.handleError(error));
    }

    deleteAccount(id: number) {
        const headers = new HttpHeaders({
            'X-Requested-With': 'XMLHttpRequest'
        });
        return this.http.delete(BASE_URL + '/' + id, { withCredentials: true})
            .catch(error => this.handleError(error));

    }

    validation(id: number, pass: string) {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });

        return this.http.get(BASE_URL + '/' + id + '/Validation/' + pass, { withCredentials: true, headers })
            .catch(error => this.handleError(error));
    }

    updateUser(id: number, updatedUser: User) {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });

        return this.http.put(BASE_URL + '/' + id, updatedUser, { withCredentials: true, headers })
            .catch(error => this.handleError(error));
    }

    setGoal(id: number, goal: number) {
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });


        return this.http.put(BASE_URL + '/' + id + '/Goal', { withCredentials: true, headers })
            .catch(error => this.handleError(error));


    }

    uploadImage(id: number, formData) {
        const headers = new HttpHeaders({
            'Accept': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });

        return this.http.post(BASE_URL + '/' + id + '/Photo', formData, { withCredentials: true, headers })
            .catch(error => this.handleError(error));
    }

    getProgress(id: number): ErrorObservable<number[]> {//No se si el tipo<> es el correcto
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });

        return this.http.get<number[]>(BASE_URL + '/' + id + '/Progress', { withCredentials: true, headers })
            .catch(error => this.handleError(error));
    }

    private handleError(error: any) {
        console.error(error);
        return ErrorObservable.create('Server error (' + error.status + '): ' + error.text());
    }

}