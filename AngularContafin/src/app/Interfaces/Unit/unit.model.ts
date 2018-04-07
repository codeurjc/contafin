import { Lesson } from '../Lesson/lesson.model';

export interface Unit {
    id?: number;
    name: string;
    lessons: Array<Lesson>;
}
