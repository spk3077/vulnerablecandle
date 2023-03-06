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

  serviceResponse!: ServiceResponse<boolean>;

  constructor( 
    private userService: UserService, 
    private formBuilder: FormBuilder,
    private router: Router) {
      this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4)]],
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
        // If Successful
        next: (res) => {
          this.serviceResponse = res;
          console.log(this.serviceResponse);

          if (this.serviceResponse.success != true) {
            this.loginError = true;
          } else {
            this.userService.setLoggedUser(this.serviceResponse);
            console.log(this.userService.getLoggedUser());
            this.router.navigateByUrl('/');
        }
      },
        // If fails at server
        error: () => {
          this.internalServerError = true;
        }
      });
    }
  }
 }