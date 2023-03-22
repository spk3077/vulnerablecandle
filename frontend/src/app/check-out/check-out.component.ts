import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UserInfoReceive } from '@app/_core/userInfo';
import { CartItemReceive } from '@app/_core/cartItem';
import { PaymentReceive } from '@app/_core/payment';
import { UserInfoService } from '@app/_services/user-info.service';
import { ShoppingCartService } from '@app/_services/shopping-cart.service';
import { PaymentService } from '@app/_services/payment.service';

@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css']
})
export class CheckOutComponent implements OnInit{
  cartItems!: CartItemReceive[];
  userInfo!: UserInfoReceive;
  payment!: PaymentReceive;

  checkoutForm: any = FormGroup;
  submitted = false;

  constructor(
    private shoppingCartService: ShoppingCartService, 
    private userInfoService: UserInfoService,
    private paymentService: PaymentService,
    private formBuilder: FormBuilder) {
      let zipPattern = "^[0-9]*$";
      let cardNumberPattern = "^[0-9]*$";
      let expirationPattern = "^(0[1-9]|1[012])/(2[3-9]|3[0-3])$";
      let cvvPattern = "^[0-9]*$";

      this.checkoutForm = this.formBuilder.group({
        fullName: ['', [Validators.required]],
        email: ['', [Validators.required, Validators.email]],
        address: ['', [Validators.required]],
        city: ['', [Validators.required]],
        state: ['', [Validators.required]],
        zip: ['', [Validators.required, Validators.pattern(zipPattern)]],
        cardName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
        cardNumber: ['', [Validators.required, Validators.minLength(14), Validators.maxLength(19), Validators.pattern(cardNumberPattern)]],
        expiration: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(16), Validators.pattern(expirationPattern)]],
        cvv: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3), Validators.pattern(cvvPattern)]]
      });
    }

  ngOnInit(): void {
    this.getUserInfo();
    this.getPayment();
    this.getCartItems();
  }

  //Add Checkout form actions
  get f() { return this.checkoutForm.controls; }

  // Retrieve UserInfo
  public getUserInfo(): void {
    this.userInfoService.getUserInfo().subscribe(userInfo => this.userInfo = userInfo);
  }

  // Retrieve Shopping Cart to Display
  public getPayment(): void {
    this.paymentService.getPayment().subscribe(payment => this.payment = payment);
  }

  // Retrieve Shopping Cart to Display
  public getCartItems(): void {
    this.shoppingCartService.getCartItems().subscribe(cartItems => this.cartItems = cartItems);
  }

  // Upon Form Submission
  public checkout(form: any): void {
    this.submitted = true;
    // stop here if form is invalid
    if (form.invalid) {
        return;
    }
    //True if all the fields are filled
    if(this.submitted) {

    }
  }

}
