import { UserInfoReceive } from './userInfo';
import { UserAuthority } from './userAuthority';

export class UserReceive {
    constructor(
        public id: number,
        public username: string,
        public userInfo: UserInfoReceive,
        public profiles: UserAuthority[]
    ) {  }
}

export class UserSend {
    constructor(
        public username: string,
        public password: string
    ) {  }
}
