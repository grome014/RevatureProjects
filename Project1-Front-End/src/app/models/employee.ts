export class Employee {

    id: number;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    email: string;
    role: string;

    constructor() { }

    getFullName():string {
        return this.firstName + " " + this.lastName;
    }
}
