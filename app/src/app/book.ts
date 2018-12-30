export class Book {
  id: number;
  title: string;
  author: string;
  category: string;
  genre: string;
  notes: string;
  status: string;
  favourite: boolean;
  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
