export class Book {
  id: number;
  title: string;
  author: string;
  genre: string;
  notes: string;
  star: boolean;
  read: boolean;
  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
