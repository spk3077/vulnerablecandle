import { Component, OnInit } from '@angular/core';
import { DefaultResponse } from '@app/_core/defaultResponse';
import { UserReceive } from '@app/_core/user';
import { UserService } from '@app/_services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class AdminUsersComponent implements OnInit {
  users: UserReceive[] = [];

  // Display Booleans
  getUserDataError: boolean = false;
  deleteUserSuccess: boolean = false;
  deleteUserError: boolean = false;

  constructor( 
    private userService: UserService, 
  ) {}
  
  ngOnInit() {
    this.getUserData();
  }

  // Retreve Userdata for Viewing
  private getUserData(): void {
    this.userService.getUserData().subscribe({
      next: (res) => {
        console.log(res);
        if (res.length <= 0) {
          this.getUserDataError = true;
        }
        
        // Add users to users array
        Array.from(res).forEach((element: unknown) => {
            let user: any = element;
            this.users.push(new UserReceive(user.id, user.username, user.userInfo, user.profiles));
        });
      },
      error: () => {
        // Failed at retrieving userData
        this.getUserDataError = true;
        return;
      }
    });
  }

  // Remove a Cart Item from API
  public deleteUser(userID: number): void {
    this.userService.deleteUser(userID).subscribe({
      next: (res) => {
        // Get generic response to determine success
        const delResponse = res as DefaultResponse;
          if (delResponse.success != true) {
            this.deleteUserSuccess = false;
            this.deleteUserError = true;
          }
          else {
            const index = this.users.map(user => user.id).indexOf(userID)
            this.users.splice(index, 1);
            this.deleteUserSuccess = false;
            this.deleteUserError = false;
          }
      },
      error: () => {
        // Failed at Server
        this.deleteUserSuccess = false;
        this.deleteUserError = true;
      }
    });
  }

}
