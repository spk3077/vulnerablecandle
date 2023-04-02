import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PATTERNS } from '@app/_core/constants';
import { PaymentReceive } from '@app/_core/payment';
import { UserInfoReceive } from '@app/_core/userInfo';

import { PaymentService } from '@app/_services/payment.service';
import { UserInfoService } from '@app/_services/user-info.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserProfileComponent implements OnInit {
  currentUser: any | undefined;

  displayUserInfo: UserInfoReceive = new UserInfoReceive(null, null, null, null, null, null, null, null, null, null);
  displayPayment: PaymentReceive = new PaymentReceive(null, null, null, null, null, null);

  // Form Values
  picForm: any = FormGroup;
  picSubmitted: boolean = false;
  passForm: any = FormGroup;
  passSubmitted: boolean = false;
  infoForm: any = FormGroup;
  infoSubmitted: boolean = false;
  payForm: any = FormGroup;
  paySubmitted: boolean = false;

  // Display Booleans
  picFormDisplay: boolean = false;
  infoFormDisplay: boolean = false;
  payFormDisplay: boolean = false;
  passFormDisplay: boolean = false;
  // Display Error Booleans
  getUserInfoError: boolean = false;
  getPaymentError: boolean = false;
  createOrderError: boolean = false;
  changeUserInfoError: boolean = false;

  constructor(
    private userInfoService: UserInfoService,
    private paymentService: PaymentService,
    private formBuilder: FormBuilder
    ) {
      this.picForm = this.formBuilder.group({
        fullName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]]
      });

      this.passForm = this.formBuilder.group({
        oldPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(35)]],
        password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(35)]]        
      });

      this.infoForm = this.formBuilder.group({
        email: ['', [Validators.required, Validators.email, Validators.minLength(5), Validators.maxLength(50)]],
        address: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
        city: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(40)]],
        state: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(2)]],
        zip: ['', [Validators.required, Validators.pattern(PATTERNS.ZIP), Validators.minLength(2), Validators.maxLength(20)]]
      });

      this.payForm = this.formBuilder.group({
        cardName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
        cardNumber: ['', [Validators.required, Validators.minLength(14), Validators.maxLength(19), Validators.pattern(PATTERNS.CARDNUM)]],
        expiration: ['', [Validators.required, Validators.pattern(PATTERNS.EXPIRE)]],
        cvv: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3), Validators.pattern(PATTERNS.CVV)]]
      });
    }

  ngOnInit(): void {
    this.getUserInfo();
    this.getPayment();
  }

  // get f() { return this.checkoutForm.controls; }

  // Retrieve UserInfo
  public getUserInfo(): void {
    this.userInfoService.getUserInfo().subscribe({
      next: (res) => {
        const info: any = Array.from(res)[0];

        // Pic Form Update
        this.picForm.patchValue({
          fullName: info.fullName,
          picture: info.picture
        });

        // User Info Form Update
        this.infoForm.patchValue({
          email: info.email,
          address: info.address,
          city: info.city,
          state: info.state,
          zip: info.zip
        });

        this.displayUserInfo = new UserInfoReceive(info.id, info.name, info.phone, info.email, info.address,
          info.city, info.state, info.zip, info.picture, info.create_date);
      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.getUserInfoError = true;
        return;
      }
    });
  }

  // Retrieve Shopping Cart to Display
  public getPayment(): void {
    this.paymentService.getPayment().subscribe({
      next: (res) => {
        const payment: any = Array.from(res)[0];
        // Payment not stored by API
        if (payment == undefined) {
          return;
        }

        // User Info Form Update
        this.payForm.patchValue({
          cardName: payment.ownerName,
          cardNumber: payment.cardNumber,
          expiration: String(payment.expiryMonth).padStart(2, '0') + "/" + payment.expiryYear,
          cvv: payment.secCode
        });

        this.displayPayment = new PaymentReceive(payment.id, payment.ownerName, 
          payment.cardNumber, payment.expiryMonth, payment.expiryYear, payment.secCode);
      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.getPaymentError = true;
        return;
      }
    });
  }

  public picSubmit(): void {
    this.picSubmitted = true;
    // stop here if form is invalid
    if (this.picForm.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.picSubmitted) {
      // this.userInfoService.changeUserInfo()
    }
  }

  public passSubmit(): void {
    this.picSubmitted = true;
    // stop here if form is invalid
    if (this.picForm.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.picSubmitted) {
      
    }
  }

  public infoSubmit(): void {
    this.picSubmitted = true;
    // stop here if form is invalid
    if (this.picForm.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.picSubmitted) {
      
    }
  }

  public paySubmit(): void {
    this.picSubmitted = true;
    // stop here if form is invalid
    if (this.picForm.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.picSubmitted) {
      
    }
  }

}
