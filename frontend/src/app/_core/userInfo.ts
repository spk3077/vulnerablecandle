// CLASS FOR RECEIVING USERINFO
export class UserInfoReceive {
    constructor(
        public id: number | null,
        public name: string | null,
        public phone: string | null,
        public email: string | null,
        public address: string | null,
        public city: string | null,
        public state: string | null,
        public zip: number | null,
        public picture: string | null,
        public create_date: Date | null
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