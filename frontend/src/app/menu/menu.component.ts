import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '@app/services/user.service';
import { DefaultResponse } from '@app/_core/defaultResponse';

import { faCartShopping } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  currentUser: any | undefined;

  logoutResponse!: DefaultResponse;

  constructor(private userService: UserService, private router: Router) { }

  // Font Awesome Exports
  faCartShopping = faCartShopping;
  ngOnInit() {
    // Retrieve UserInfo
    this.userService.loggedInUser$.subscribe(x => this.currentUser = x);
  }
}
