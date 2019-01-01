export class Author {
  id: number;
  name: string;
  bio: string;
  notes: string;
  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
