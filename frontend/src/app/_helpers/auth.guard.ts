import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

import { UserService } from '@app/_services/user.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    currentUser: any | undefined;
    

    constructor(private router: Router, private userService: UserService){
        this.userService.loggedInUser$.subscribe(x => this.currentUser = x);
    }

    canActivate() {
        if (this.currentUser) {
            // Authorized User/Admin
            return true;
        }

        this.router.navigateByUrl('/');
        return false;
    }
}

