export class Book {
  id: string;
  title: string;
  authors: string;
  genre: string;
  recordTrail: [];
  favourite: boolean;
  type: string;
  medium: string;
  language: string;
  publisher: string;
  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
