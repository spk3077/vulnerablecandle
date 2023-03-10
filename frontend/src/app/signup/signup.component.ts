import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';


import { DefaultResponse } from '@app/_core/defaultResponse';
import { UserService } from '@app/services/user.service';
import { MustMatch } from '@app/_core/mustMatch';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  registerForm:any = FormGroup;
  submitted = false;
  signupError:boolean = false;

  registerResponse!: DefaultResponse;

  constructor( 
    private userService: UserService, 
    private formBuilder: FormBuilder,
    private router: Router) {
      this.registerForm = this.formBuilder.group({
        username: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(16)]],
        password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(35)]],
        confirmPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(35)]]
      }, {
        validators: MustMatch('password', 'confirmPassword')
      });
  }

  ngOnInit() {}

  //Add user form actions
  get f() { return this.registerForm.controls; }

  // Login Function
  public register(): void {
    this.submitted = true;
    // stop here if form is invalid
    if (this.registerForm.invalid) {
        return;
    }
    //True if all the fields are filled
    if(this.submitted) {
      this.userService.register(this.registerForm.value).subscribe({
        // If Successful
        next: (res) => {
          let loginResponse: DefaultResponse = res as DefaultResponse;
          if (loginResponse.success != true) {
            this.signupError = true;
          
          // Emit To Login after succesful signup

          }
        },
        // If fails at server
        error: () => {
          console.log("Signup Failed: Credentials Wrong")
          this.signupError = true;
          return;
        }
      });
    }
  }
}