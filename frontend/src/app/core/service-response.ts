export class ServiceResponse <T> {
    public code!: string;
    public message!: string;
    public success!: T;
}