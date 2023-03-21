import { UserInfoReceive } from './userInfo';
import { UserAuthority } from './userAuthority';

export class UserReceive {
    constructor(
        public id: number,
        public username: string,
        public userInfo: UserInfoReceive,
        public enabled: boolean,
        public authorities: UserAuthority[],
        public accountNonExpired: boolean,
        public accountNonLocked: boolean,
        public credentialsNonExpired: boolean
    ) {  }
}

export class UserSend {
    constructor(
        public username: string,
        public password: string
    ) {  }
}
