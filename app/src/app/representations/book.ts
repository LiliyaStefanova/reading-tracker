export class Book {
  id: number;
  title: string;
  authors: string;
  genre: string;
  recordTrail: [];
  type: string;
  medium: string;
  language: string;
  publisher: string;
  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
