import { Answer } from "../Answer/answer.model";

export interface Exercise {
    id?: number;
    kind: number;
    statement: string;
    texts: Array<string>;
    image1?: Blob;
    image2?: Blob;
    image3?: Blob;
    answer: Answer;
}
