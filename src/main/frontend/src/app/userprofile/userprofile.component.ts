import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PATTERNS } from '@app/_core/constants';
import { DefaultResponse } from '@app/_core/defaultResponse';
import { PaymentReceive, PaymentSend } from '@app/_core/payment';
import { SavedUser } from '@app/_core/savedUser';
import { PasswordSend } from '@app/_core/user';
import { UserInfoReceive, UserInfoSend } from '@app/_core/userInfo';

import { PaymentService } from '@app/_services/payment.service';
import { UserInfoService } from '@app/_services/user-info.service';
import { UserService } from '@app/_services/user.service';

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
  file: any;
  tempPFP: any | null;
  picSubmitted: boolean = false;

  passForm: any = FormGroup;
  passSubmitted: boolean = false;

  infoForm: any = FormGroup;
  infoSubmitted: boolean = false;

  payForm: any = FormGroup;
  paySubmitted: boolean = false;


  // Display Form Booleans
  picFormDisplay: boolean = false;
  infoFormDisplay: boolean = false;
  payFormDisplay: boolean = false;
  passFormDisplay: boolean = false;

  // Display Error Booleans
  getUserInfoError: boolean = false;
  getPaymentError: boolean = false;
  uploadImageError: boolean = false;
  uploadTypeError: boolean = false;
  changeNameError: boolean = false;
  changePasswordError: boolean = false;
  changeUserInfoError: boolean = false;
  changePaymentError: boolean = false;

  // Display Info Booleans
  userInfoNone: boolean = false;
  paymentNone: boolean = false;

  constructor(
    private userInfoService: UserInfoService,
    private paymentService: PaymentService,
    private formBuilder: FormBuilder,
    private userService: UserService
    ) {
      this.userService.loggedInUser$.subscribe(x => this.currentUser = x);
    }

  ngOnInit(): void {
    this.getUserInfo();
    this.getPayment();

    this.picForm = this.formBuilder.group({
      fullName: ['', [Validators.minLength(3), Validators.maxLength(50)]],
    });

    this.passForm = this.formBuilder.group({
      oldPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(35)]],
      newPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(35)]]        
    });

    this.infoForm = this.formBuilder.group({
      email: ['', [Validators.email, Validators.minLength(5), Validators.maxLength(50)]],
      phone: ['', [Validators.minLength(10), Validators.maxLength(15)]],
      address: ['', [Validators.minLength(3), Validators.maxLength(100)]],
      city: ['', [Validators.minLength(2), Validators.maxLength(40)]],
      state: ['', [Validators.minLength(2), Validators.maxLength(2)]],
      zip: ['', [Validators.pattern(PATTERNS.ZIP), Validators.minLength(2), Validators.maxLength(20)]]
    });

    this.payForm = this.formBuilder.group({
      cardName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      cardNumber: ['', [Validators.required, Validators.minLength(14), Validators.maxLength(19), Validators.pattern(PATTERNS.CARDNUM)]],
      expiration: ['', [Validators.required, Validators.pattern(PATTERNS.EXPIRE)]],
      cvv: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3), Validators.pattern(PATTERNS.CVV)]]
    });
  }

  get fPic() { return this.picForm.controls; }
  get fPass() { return this.passForm.controls; }
  get fInfo() { return this.infoForm.controls; }
  get fPay() { return this.payForm.controls; }

  // Retrieve UserInfo
  public getUserInfo(): void {
    this.userInfoService.getUserInfo().subscribe({
      next: (res) => {
        const info: any = Array.from(res)[0];

        // Pic Form Update
        this.picForm.patchValue({
          fullName: info.fullName
        });

        // User Info Form Update
        this.infoForm.patchValue({
          email: info.email,
          address: info.address,
          city: info.city,
          state: info.state,
          zip: info.zip
        });

        this.displayUserInfo = new UserInfoReceive(info.id, info.name, info.email, info.phone, info.address,
          info.city, info.state, info.zip, info.picture, info.create_date);

        if (info.email == null && info.address == null && info.city == null && info.state == null && info.zip == null) {
          this.userInfoNone = true;
        }
        else {
          this.userInfoNone = false;
        }
      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.getUserInfoError = true;
        return;
      }
    });
  }

  // Retrieve Payment to Display
  public getPayment(): void {
    this.paymentService.getPayment().subscribe({
      next: (res) => {
        const payment: any = Array.from(res)[0];
        // Payment not stored by API
        if (payment == undefined) {
          this.paymentNone = true;
          return;
        }
        else {
          this.paymentNone = false;
        }

        // Payment Form Update
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

  // assist for getting image parameters for picSubmit()
  public uploadImage(event: any){
    const file = event.target.files[0];
    const mimeType = file.type;
    if (mimeType.match(/image\/*/) == null) {
        this.uploadTypeError = true;
        return;
    }
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = (_event) => { 
      this.tempPFP = reader.result; 
    }
    this.file = file;
  }

  // picSubmit helper for sending fullName
  private picInfoSend() {
    const fullName = UserInfoSend.forProfileAside(this.picForm.value.fullName); 
    this.userInfoService.changeUserInfo(fullName).subscribe({
      next: (res) => {
        const infoResponse: DefaultResponse = res;
        if (infoResponse.success != true) {
          this.changeNameError = true;
          return;
        }

        this.getUserInfo();
        this.picSubmitted = false;
        this.picFormDisplay = false;
      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.changeNameError = true;
      }
    });
  }

  // picSubmit helper for sending fullName
  private picImageSend() {
    const formData = new FormData();
    formData.append("file", this.file, this.file.name);

    this.userInfoService.uploadUserImage(formData).subscribe({
      next: (res) => {
        const imgResponse: DefaultResponse = res;
        if (imgResponse.success != true) {
          this.uploadImageError = true;
          return;
        }

        this.userService.setLoggedUser(
          new SavedUser(this.currentUser.username, this.currentUser.authority, this.tempPFP)
        );
        this.tempPFP = null;
        this.picSubmitted = false;
        this.picFormDisplay = false;
      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.uploadImageError = true;
      }
    });
  }

  // Submit Fullname and picture Form
  public picSubmit(): void {
    this.picSubmitted = true;
    // stop here if form is invalid
    if (this.picForm.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.picSubmitted) {
      if (this.file) {
        this.picImageSend()
      }
      if (this.picForm.value.fullName || this.picForm.value.fullName != '') {
        this.picInfoSend();
      }
    }
  }

  // Submit change password Form
  public passSubmit(): void {
    this.passSubmitted = true;
    // stop here if form is invalid
    if (this.passForm.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.passSubmitted) {
      const passSend = new PasswordSend(this.currentUser.username, this.passForm.value.oldPassword, this.passForm.value.newPassword);
      this.userService.changeUser(passSend).subscribe({
        next: (res) => {
          const passResponse: any = res;
          if (passResponse.success != true) {
            this.changePasswordError = true;
            return;
          }

          this.passSubmitted = false;
          this.passFormDisplay = false;
        },
        error: () => {
          // Failed at getting UserInfo to Store
          this.changePasswordError = true;
        }
      });
    }
  }

  // Submit user information form
  public infoSubmit(): void {
    this.infoSubmitted = true;
    // stop here if form is invalid
    if (this.infoForm.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.infoSubmitted) {
      const userInfo = UserInfoSend.forProfileMain(this.infoForm.value.email, this.infoForm.value.phone, this.infoForm.value.address, 
        this.infoForm.value.city, this.infoForm.value.state, this.infoForm.value.zip);
      this.userInfoService.changeUserInfo(userInfo).subscribe({
        next: (res) => {
          const infoResponse: DefaultResponse = res;
          if (infoResponse.success != true) {
            this.changeUserInfoError = true;
            return;
          }

          this.getUserInfo();
          this.infoSubmitted = false;
          this.infoFormDisplay = false;
        },
        error: () => {
          // Failed at getting UserInfo to Store
          this.changeUserInfoError = true;
        }
      });
    }
  }

  // Submit payment Form
  public paySubmit(): void {
    this.paySubmitted = true;
    // stop here if form is invalid
    if (this.payForm.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.paySubmitted) {
      const payment = new PaymentSend(this.payForm.value.cardNumber, this.payForm.value.cardName,
         this.payForm.value.expiration.substring(0,2), this.payForm.value.expiration.substring(3,5), this.payForm.value.cvv);
      
      // If this is the user's first payment
      if (!this.displayPayment.id) {
        this.paymentService.addPayment(payment).subscribe({
          next: (res) => {
            const paymentResponse: DefaultResponse = res as DefaultResponse;
            if (paymentResponse.success != true) {
              this.changePaymentError = true;
              return;
            }

            this.getPayment();
            this.paySubmitted = false;
            this.payFormDisplay = false;
          },
          error: () => {
            // Failed at getting Payment to Store
            this.changePaymentError = true;
          }
        });
      }

      // If this is NOT the user's first time adding payment, edit  existing payment
      else if (this.displayPayment.id) {
        this.paymentService.changePayment(this.displayPayment.id, payment).subscribe({
          next: (res) => {
            const paymentResponse: DefaultResponse = res as DefaultResponse;
            if (paymentResponse.success != true) {
              this.changePaymentError = true;
              return;
            }

            this.getPayment();
            this.paySubmitted = false;
            this.payFormDisplay = false;
          },
          error: () => {
            // Failed at getting Payment to Store
            this.changePaymentError = true;
          }
        });
      }

    }
  }

}
