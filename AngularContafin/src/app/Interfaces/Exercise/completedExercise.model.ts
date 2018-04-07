import { User } from '../User/user.model';
import { Exercise } from './exercise.model';

export interface CompletedExercise {
    id?: number;
    user: User;
    exercise: Exercise;
}
