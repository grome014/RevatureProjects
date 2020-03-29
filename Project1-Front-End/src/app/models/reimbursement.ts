import { Employee } from '../models/employee';

export class Reimbursement {

    reimb_id: number;
    reimb_amount: number;
    reimb_submitted: Date;
    reimb_resolved: Date;
    reimb_description: string;
    reimb_receipt: string;
    reimb_author: Employee;
    reimb_resolver: Employee;
    reimb_status: string;
    reimb_type: string;

    constructor() { }
}
