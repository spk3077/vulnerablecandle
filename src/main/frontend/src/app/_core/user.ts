import { UserInfoReceive } from './userInfo';
import { UserAuthority } from './userAuthority';

// Receive User Content
export class UserReceive {
    constructor(
        public id: number,
        public username: string,
        public userInfo: UserInfoReceive,
        public profiles: UserAuthority[]
    ) {  }
}

// Send User Content
export class UserSend {
    constructor(
        public username: string,
        public password: string
    ) {  }
}

// Change Password of User
export class PasswordSend {
    constructor(
        public username: string,
        public newPassword: string
    ) {  }
}
