export class Genre {
  id: string;
  name: string;
  category: string;
  description: string;
  created_ts: Date;
  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
