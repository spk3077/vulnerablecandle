import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

import { DefaultResponse } from '@app/_core/defaultResponse';
import { UserService } from '@app/_services/user.service';
import { MustMatch } from '@app/_core/mustMatch';
import { LoginEventService } from '@app/_helpers/login-event.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  registerForm: any = FormGroup;
  submitted: boolean = false;

  // Display Booleans
  signupError:boolean = false;

  constructor( 
    private userService: UserService, 
    private formBuilder: FormBuilder,
    private loginEventService: LoginEventService) {
      this.registerForm = this.formBuilder.group({
        username: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(24)]],
        password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(35)]],
        confirmPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(35)]]
      }, {
        validators: MustMatch('password', 'confirmPassword')
      });
  }

  ngOnInit() {}

  //Add user form actions
  get f() { return this.registerForm.controls; }

  // Form Submission
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
          const loginResponse: DefaultResponse = res as DefaultResponse;
          if (loginResponse.success != true) {
            this.signupError = true;
            return;
          }
          
          // Emit To perform login function after succesful signup
          this.loginEventService.onRegistration(this.registerForm);

        },
        // If fails at server
        error: () => {
          console.log("Signup Failed: Internal Server Failure")
          this.signupError = true;
          return;
        }
      });
    }
  }
}