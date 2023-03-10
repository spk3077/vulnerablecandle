import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';


import { DefaultResponse } from '@app/_core/defaultResponse';
import { UserFull } from '@app/_core/userFull';
import { UserService } from '@app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // Form Variables that assist template
  loginForm:any = FormGroup;
  submitted = false;
  loginError:boolean = false;
  getInfoError:boolean = false;

  constructor( 
    private userService: UserService, 
    private formBuilder: FormBuilder,
    private router: Router) {
      this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(16)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(35)]]
      });
  }
  
  ngOnInit() {}

  //Add user form actions
  get f() { return this.loginForm.controls; }

  // Login Function
  public login(): void {
    this.submitted = true;
    // stop here if form is invalid
    if (this.loginForm.invalid) {
        return;
    }
    //True if all the fields are filled
    if(this.submitted) {
      this.userService.login(this.loginForm.value).subscribe({
        // If Successful
        next: (res) => {
          let loginResponse: DefaultResponse = res as DefaultResponse;
          if (loginResponse.success != true) {
            this.loginError = true;
            return;
          }
          // Store UserData
          this.getUserData();
        },
        // If fails at server
        error: () => {
          console.log("Login Failed: Credentials Wrong");
          this.loginError = true;
          return;
        }
      });
    }
  }

  // Retreve Userdata for Storage
  private getUserData(): void {
    this.userService.getUserData().subscribe({
      next: (res) => {
        // Get User Information to Populate Components with User-relevant information
        let userFull: UserFull;
        if (res.length == 1 ) {
          userFull = res[0] as UserFull;
        }
        else {
          userFull = res.find((obj: UserFull) => obj.username === this.loginForm.value.username);
        }
        console.log(userFull);
        this.userService.setLoggedUser(userFull);
        this.router.navigateByUrl('/');
      },
      error: () => {
        // Failed at getting UserInfo to Store
        console.log("Login Failed: User Info Not Received");
        this.getInfoError = true;
        return;
      }
    });
  }
 }