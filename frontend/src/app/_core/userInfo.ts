// CLASS FOR RECEIVING USERINFO
export class UserInfoReceive {
    constructor(
        public id: number,
        public name: string,
        public phone: string,
        public email: string,
        public address: string,
        public city: string,
        public state: string,
        public zip: number,
        public picture: string,
        public create_date: Date
    ) {  }
}

// CLASS FOR RECEIVING USERINFO
export class UserInfoSend {
    constructor(
        public name: string,
        public phone: string,
        public email: string,
        public address: string,
        public city: string,
        public state: string,
        public zip: number,
        public picture: string,
    ) {  }
}