import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { UtilsService } from './utils.service';
import { environment } from '../../environments/environment';
import 'rxjs/Rx';
import { Exercise } from '../Interfaces/Exercise/exercise.model';
import { Answer } from '../Interfaces/Answer/answer.model';
import { ErrorService } from './error.service';

@Injectable()
export class ExerciseService {

	constructor(
		public utils: UtilsService, private errorService : ErrorService
		) { }

	async getExercise(idExercise: number): Promise<Exercise> {

		let useData = null;
			 await this.utils.restService('/Exercise/', {
				queryString: idExercise,
				method: 'get'
			  }).toPromise().then(
				(data : Exercise) => {
				  if (typeof data !== 'undefined' && data !== null) {
					console.log(data);
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
	}

	async checkExercise(idExercise: number, result) {

		let useData = null;
		console.log("result:" + JSON.stringify(result));
			 await this.utils.restService('/Exercise/', {
				queryString: idExercise + '/Solution', 
				params : result,
				method: 'post'
			  }).toPromise().then(
				(data) => {
					console.log("Solucion:" + data);
				  if (typeof data !== 'undefined' && data !== null) {
					console.log("Se mete en");
					useData = data;
				  }
				}
			  ).catch(error => this.errorService.handleError(error));
		return useData;
		
	}
}
