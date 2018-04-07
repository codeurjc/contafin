import { User } from '../User/user.model';
import { Lesson } from './lesson.model';

export interface CompletedLesson {
    id?: number;
    user: User;
    lesson: Lesson;
    date: Date;
}
