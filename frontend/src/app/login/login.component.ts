import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';


import { ServiceResponse } from '@app/core/service-response';
import { UserCred } from '@app/core/userCred';
import { UserService } from '@app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // Form Variables that assist template
  registerForm:any = FormGroup;
  submitted = false;
  loginError:boolean = false;
  internalServerError:boolean = false;

  serviceResponse!: ServiceResponse<UserCred>;

  constructor( 
    private userService: UserService, 
    private formBuilder: FormBuilder,
    private router: Router) {
      this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
      });
  }
  
  ngOnInit() {
  }

  //Add user form actions
  get f() { return this.registerForm.controls; }

  // Login Function
  login(): void {
    this.submitted = true;
    // stop here if form is invalid
    if (this.registerForm.invalid) {
        return;
    }
    //True if all the fields are filled
    if(this.submitted) {
      this.userService.login(this.registerForm.value).subscribe({
        next: (res) => {
          this.serviceResponse = res;
          if (this.serviceResponse.responseCode != "OK") {
            this.loginError = true;
          } else {
            this.userService.setLoggedUser(this.serviceResponse.responseObject);
            this.router.navigateByUrl('/');
        }
      },
        error: () => {
          this.internalServerError = true;
        }
      });
    }
  }
 }