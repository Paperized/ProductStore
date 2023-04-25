export class Account {
  constructor(
    public username: string,
    public email: string,
    public authorities: string[],
    public firstName: string | null,
    public lastName: string | null
  ) {
  }
}
