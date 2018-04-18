import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import { User } from '../Interfaces/User/user.model';

const BASE_URL = environment.apiBase + '/User';


@Injectable()
export class UserService {

    public user: User;

    constructor(private http: Http) {

    }

    getUser(id: number) {
        const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true });

        return this.http.get(BASE_URL + '/' + id, options)
            .map(response => {
                this.user = response.json();
                return this.user;
            })
            .catch(error => this.handleError(error));
    }

    deleteAccount(id: number) {
        const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true});
        return this.http.delete(BASE_URL + '/' + id, options)
            .map(response => response.json())
            .catch(error => this.handleError(error));

    }

    validation(id: number, pass: string) {
        const headers = new Headers({
            'Accept': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers });

        return this.http.get(BASE_URL + '/' + id + '/Validation/' + pass, options)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    updateUser(id: number, updatedUser: User) {
        const headers = new Headers({
            'Accept': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers });

        return this.http.put(BASE_URL + '/' + id, updatedUser, options)
            .map(user => user.json())
            .catch(error => this.handleError(error));
    }

    setGoal(id: number, goal: number) {
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers });


        return this.http.put(BASE_URL + '/' + id + '/Goal', options)
            .map(response => response.json())
            .catch(error => this.handleError(error));


    }

    uploadImage(id: number, formData) {
        const headers = new Headers({
            'Accept': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers });

        return this.http.post(BASE_URL + '/' + id + '/Photo', formData, options)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    getProgress(id: number): Observable<number[]> {
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers });

        return this.http.get(BASE_URL + '/' + id + '/Progress', options)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }

}