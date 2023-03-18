import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParamsOptions } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { User } from '../Interfaces/User/user.model';
import { UtilsService } from '../../../src/app/services/utils.service';
import 'rxjs/Rx';
import { LoginService } from '../login/login.service';



const BASE_URL = environment.apiBase + '/Admin/UserData/';

@Injectable()
export class AdminService {

	constructor(
		private http: HttpClient,
		public utils: UtilsService,
		public user : LoginService) { }

	async getUserData() {

		console.log("El usuario administrador es " + JSON.stringify(this.user.user));

		console.log(this.user.user.email + ':' + this.user.pass);

		const userPass = this.user.user.email + ':' + this.user.pass;

		const opt = {
			headers : new HttpHeaders({
            'Authorization': 'Basic ' + utf8_to_b64(userPass),
			'X-Requested-With': 'XMLHttpRequest'
        	})
		};

		let useData = null;
			 await this.utils.restServiceHeaders('/Admin/UserData', {
				method: 'get',
				headers: opt,
			  }).toPromise().then(
				(data) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  );
		return useData;
	}

	//Export data to Excel
	async exporData() {
		let useData = null;
		console.log(this.user.user.email + ':' + this.user.pass);
		const userPass = this.user.user.email + ':' + this.user.pass;

        const opt = {
			headers : new HttpHeaders({
            'Authorization': 'Basic ' + utf8_to_b64(userPass),
			'Accept': 'application/vnd.ms-excel',
        	}),
			responseType : "arraybuffer"
		};

			 await this.utils.restServiceHeaders('/Admin/UserData/', {
				queryString: 'Excel',
				method: 'get',
				headers: opt,
			  }).toPromise().then(
				(data) => {
					if (typeof data !== 'undefined' && data !== null) {
						console.log(data);

						let d = new Date();
						let year = d.getFullYear();
						let month = d.getMonth() + 1;
						let day = d.getDate();
						d.getUTCFullYear();
						
						let fileName="listaEstudiantes_"+day + "_" + month + "_" + year +".xlsx";

						var contentType = 'application/vnd.ms-excel'; 
						var blob = new Blob([data], { type: contentType });
						var a = document.createElement('a');
						a.href = URL.createObjectURL(blob);
						a.download = fileName;
						document.body.appendChild(a);
						a.click();
						document.body.removeChild(a);
					  }
				}
			  );
		return useData;
	}

	private handleError(error: any) {
		console.error(error);
		return ErrorObservable.create("Server error (" + error.status + "): " + error.text())
	}

}

function utf8_to_b64(str) {
	return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {//Mirar si esta bien
		return String.fromCharCode(<any>'0x' + p1);
	}));
}
