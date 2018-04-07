export interface User {
    id?: number;
    name: string;
    email: string;
    level: number;
    points: number;
    streak: number;
    fluency: number;
    dailyGoal: number;
    lastConnection: string;
    lastUnit: number;
    lastLesson: number;
    progress: number[];
    remainingGoals: number;
}
