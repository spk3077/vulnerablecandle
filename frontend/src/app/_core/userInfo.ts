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
    public name: string | null = null;
    public phone: string | null = null;
    public email: string | null = null;
    public address: string | null = null;
    public city: string | null = null;
    public state: string | null = null;
    public zip: number | null = null;
    public picture: string | null = null;

    // UserInfo Send for Checkout to complete transaction
    public static forCheckOut(name: string, email: string, address: string, city: string, state: string, zip: number): UserInfoSend {
        const cls = new UserInfoSend();
        cls.name = name;
        cls.email = email;
        cls.address = address;
        cls.city = city;
        cls.state = state;
        cls.zip = zip;
        return cls;
    }

    // UserInfo Send for UserProfile User Info Edit
    public static forProfileMain(email: string, address: string, city: string, state: string, zip: number): UserInfoSend {
        const cls = new UserInfoSend();
        cls.email = email;
        cls.address = address;
        cls.city = city;
        cls.state = state;
        cls.zip = zip;
        return cls;
    }

     // UserInfo Send for UserProfile Full Name
     public static forProfileAside(name: string): UserInfoSend {
        const cls = new UserInfoSend();
        cls.name = name;
        return cls;
    }
}