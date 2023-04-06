import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

import { LoginEventService } from '@app/_helpers/login-event.service';
import { DefaultResponse } from '@app/_core/defaultResponse';
import { UserReceive } from '@app/_core/user';
import { SavedUser } from '@app/_core/savedUser';
import { UserService } from '@app/_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // Form Variables that assist template
  loginForm:any = FormGroup;
  submitted = false;

  // Display Booleans
  loginError: boolean = false;
  getUserDataError: boolean = false;

  constructor( 
    private userService: UserService, 
    private formBuilder: FormBuilder,
    private loginEventService: LoginEventService, 
    private router: Router) {
      this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(24)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(35)]]
      });
  }
  
  ngOnInit() {
    if (this.loginEventService.subsVar == undefined) {
      this.loginEventService.subsVar = this.loginEventService.
      invokeLoginFunction.subscribe(( form: any) => { this.login(form) });
    }
  }

  //Add user form actions
  get f() { return this.loginForm.controls; }

  // Form Submission
  public login(form: any): void {
    this.submitted = true;
    
    // stop here if form is invalid
    if (form.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.submitted) {
      this.userService.login(form.value).subscribe({
        // If Successful
        next: (res) => {
          const loginResponse: DefaultResponse = res as DefaultResponse;
          if (loginResponse.success != true) {
            this.loginError = true;
            return;
          }
          // Store UserData
          this.getUserData(form);
        },
        // If fails at server
        error: () => {
          this.loginError = true;
          return;
        }
      });
    }
  }

  // Retreve Userdata for Storage
  private getUserData(form: any): void {
    this.userService.getUserData().subscribe({
      next: (res) => {
        // Get User Information to Populate Components with User-relevant information
        let userReceive: UserReceive;
        let savedUser: SavedUser;

        if (res.length == 1 ) {
          userReceive = res[0];
        }
        else {
          userReceive = res.find((obj: UserReceive) => obj.username === form.value.username);
        }
        console.log(userReceive);

        // Save the User to Local Storage
        savedUser = new SavedUser(userReceive.username, userReceive.profiles[0].type, userReceive.userInfo.picture);
        this.userService.setLoggedUser(savedUser);
        this.router.navigateByUrl('/');
      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.getUserDataError = true;
        return;
      }
    });
  }
 }