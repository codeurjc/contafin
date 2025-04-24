import { Input } from "@angular/core";
import { Answer } from "../Answer/answer.model";

export interface Exercise {
    id?: number;
    kind: number;
    statement: string;
    texts: Array<string>;
    image1?: String;
    image2?: String;
    image3?: String;
    answer: Answer;
}
