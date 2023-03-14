import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { DefaultResponse } from '@app/_core/defaultResponse';
import { UserService } from "@app/_services/user.service";

@Component({
  selector: 'app-logout',
  template: ''
})
export class LogoutComponent implements OnInit {

  logoutResponse!: DefaultResponse;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.logout();
  }
  logout(): void {
    this.userService.logout().subscribe({
      next: (res) => {
        // Get User Information to Populate Components with User-relevant information
        this.logoutResponse = res as DefaultResponse;
          console.log(this.logoutResponse);
          if (this.logoutResponse.success != true) {
            console.log("Logout Failed!  Will be terminated anyways");
          }
          else {
            console.log("Logout Success!");
            this.router.navigateByUrl('/');
          }
      },
      error: () => {
        // Failed at getting UserInfo to Store
        console.log("Logout Errored!");
      }
    });
}
}
