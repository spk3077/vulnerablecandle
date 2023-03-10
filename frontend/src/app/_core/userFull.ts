import { UserInfo } from './userInfo';
import { UserAuthority } from './userAuthority';

export interface UserFull {
    id: number;
    username: string;
    userInfo: UserInfo;
    mypayments: object[];
    enabled: boolean;
    authorities: UserAuthority[];
    accountNonExpired: boolean;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;
}