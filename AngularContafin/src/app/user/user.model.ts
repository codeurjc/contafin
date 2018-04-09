export interface User{
    id: number;
    name: string;
    email: string;
    password: string;
    level: number;
    points: number;
    streak: number;
    fluency: number;
    dailyGoal?: number;
    lastConnection: string;
    lastUnit: string;
    lastLesson: number;
	progress: number [];
    remainingGoals?: number;
    exp: number;
	needexp: number;
    image: Blob;
    roles: string[];
    //completedExercises: CompletedExercise [];

}