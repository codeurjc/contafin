import { SafeUrl } from '@angular/platform-browser';

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
    image?: SafeUrl;
    roles: string[];
    
}