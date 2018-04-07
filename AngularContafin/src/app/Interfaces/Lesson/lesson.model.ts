import { Exercise } from '../Exercise/exercise.model';

export interface Lesson {
    id?: number;
    name: string;
    exercises: Array<Exercise>;
}
