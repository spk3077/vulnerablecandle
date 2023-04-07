import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { DefaultResponse } from '@app/_core/defaultResponse';
import { UserService } from "@app/_services/user.service";

@Component({
  selector: 'app-logout',
  template: ''
})
export class LogoutComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.logout();
  }
  logout(): void {
    this.userService.logout().subscribe({
      next: (res) => {
        const logoutResponse: DefaultResponse = res as DefaultResponse;
          console.log(logoutResponse);
          if (logoutResponse.success != true) {
            console.log("Logout Failed!  Will be terminated anyways");
          }
          else {
            console.log("Logout Success!");
            this.router.navigateByUrl('/');
          }
      },
      error: () => {
        // Failed at Server
        console.log("Logout Errored!");
      }
    });
}
}
