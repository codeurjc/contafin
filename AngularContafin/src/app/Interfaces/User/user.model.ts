import { CompletedExercise } from '../Exercise/completedExercise.model'; 

export interface User{
    id: number;
    name: string;
    email: string;
    passwordHash: string;
    level: number;
    points: number;
    streak: number;
    fluency: number;
    dailyGoal?: number;
    lastConnection: string;
    lastUnit: number;
    lastLesson: number;
	progress?: number [];
    remainingGoals?: number;
    exp: number;
	needexp: number;
    image?: Blob;
    roles: string[];
    completedExercises?: CompletedExercise [];
    imgURL?: string;
    
}