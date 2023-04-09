import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { PATTERNS } from '@app/_core/constants';
import { UserInfoReceive, UserInfoSend } from '@app/_core/userInfo';
import { CartItemReceive } from '@app/_core/cartItem';
import { PaymentReceive, PaymentSend } from '@app/_core/payment';
import { ProductReceive } from '@app/_core/product';
import { DefaultResponse } from '@app/_core/defaultResponse';
import { UserInfoService } from '@app/_services/user-info.service';
import { ShoppingCartService } from '@app/_services/shopping-cart.service';
import { PaymentService } from '@app/_services/payment.service';
import { OrderService } from '@app/_services/order.service';

@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css']
})
export class CheckOutComponent implements OnInit{
  cartItems: CartItemReceive[] = [];
  total: number = 0;

  originalUserInfo!: UserInfoReceive;
  originalPayment!: PaymentReceive;

  // Form Values
  checkoutForm: any = FormGroup;
  submitted = false;

  // Display Booleans
  getUserInfoError: boolean = false;
  getPaymentError: boolean = false;
  getCartItemsError: boolean = false;
  createOrderError: boolean = false;
  changeUserInfoError: boolean = false;
  changePaymentError: boolean = false;

  constructor(
    private shoppingCartService: ShoppingCartService, 
    private userInfoService: UserInfoService,
    private paymentService: PaymentService,
    private orderService: OrderService,
    private formBuilder: FormBuilder,
    private router: Router) {
      this.checkoutForm = this.formBuilder.group({
        fullName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
        email: ['', [Validators.required, Validators.email, Validators.minLength(5), Validators.maxLength(50)]],
        address: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
        city: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(40)]],
        state: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(2)]],
        zip: ['', [Validators.required, Validators.pattern(PATTERNS.ZIP), Validators.minLength(2), Validators.maxLength(20)]],
        cardName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
        cardNumber: ['', [Validators.required, Validators.minLength(14), Validators.maxLength(19), Validators.pattern(PATTERNS.CARDNUM)]],
        expiration: ['', [Validators.required, Validators.pattern(PATTERNS.EXPIRE)]],
        cvv: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3), Validators.pattern(PATTERNS.CVV)]]
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
    this.userInfoService.getUserInfo().subscribe({
      next: (res) => {
        const info: any = Array.from(res)[0];
        this.checkoutForm.patchValue({
          fullName: info.name,
          email: info.email,
          address: info.address,
          city: info.city,
          state: info.state,
          zip: info.zip
        });

        this.originalUserInfo = new UserInfoReceive(info.id, info.name, info.phone, info.email, info.address,
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
        console.log(res);
        // Payment not stored by API
        if (payment == undefined) {
          this.originalPayment = new PaymentReceive(null, null, null, null, null, null);
          return;
        }

        this.checkoutForm.patchValue({
          cardName: payment.ownerName,
          cardNumber: payment.cardNumber,
          expiration: String(payment.expiryMonth).padStart(2, '0') + "/" + payment.expiryYear,
          cvv: payment.secCode
        });

        this.originalPayment = new PaymentReceive(payment.id, payment.ownerName, 
          payment.cardNumber, payment.expiryMonth, payment.expiryYear, payment.secCode);
      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.getPaymentError = true;
        return;
      }
    });
  }

  // Retrieve Shopping Cart to Display
  public getCartItems(): void {
    this.shoppingCartService.getCartItems().subscribe({
      next: (res) => {
        this.total = res.totalPrice;
        console.log(res);
        res.cartItems.forEach((cartItem: any) => {
          this.cartItems.push(
            new CartItemReceive(cartItem.id, ProductReceive.forCart(cartItem.itemId, cartItem.itemName,
                cartItem.itemBrand, cartItem.itemPrice, cartItem.itemImage), cartItem.quantity));
        });
      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.getCartItemsError = true;
        return;
      }
    });
  }

  // Update UserInfo on API if changed & if succeeds go to Payment Function
  public changeUserInfo(): void {
    const formValues = this.checkoutForm.value;
    const userInfo = UserInfoSend.forCheckOut(formValues.fullName, formValues.email, formValues.address, 
      formValues.city, formValues.state, formValues.zip);
    
    this.userInfoService.changeUserInfo(userInfo).subscribe({
      next: (res) => {
        const infoResponse: DefaultResponse = res as DefaultResponse;
        if (infoResponse.success != true) {
          this.changeUserInfoError = true;
          return;
        }
        
        if (formValues.cardName != this.originalPayment.ownerName || formValues.cardNumber != this.originalPayment.cardNumber ||
          formValues.expiration != (this.originalPayment.expiryMonth + "/" + this.originalPayment.expiryYear) ||  
          formValues.cvv != this.originalPayment.secCode) {
            this.changePayment();
        }
        else {
          this.createOrder();
        }

      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.changeUserInfoError = true;
      }
    });
  }

  // Update Payment on API if changed & if succeeds go to CreateOrder
  public changePayment(): void {
    const formValues = this.checkoutForm.value;
    const payment = new PaymentSend(formValues.cardNumber, formValues.cardName, formValues.expiration.substring(0,2),
    formValues.expiration.substring(3,5), formValues.cvv);

    this.paymentService.addPayment(payment).subscribe({
      next: (res) => {
        const paymentResponse: DefaultResponse = res as DefaultResponse;
        if (paymentResponse.success != true) {
          this.changePaymentError = true;
          return;
        }

        this.createOrder();
      },
      error: () => {
        // Failed at getting Payment to Store
        this.changePaymentError = true;
      }
    });
  }

  // Create Order
  public createOrder(): void {
    this.orderService.createOrder().subscribe({
      next: (res) => {
        if (res.orderItems.length <= 0) {
          this.createOrderError = true;
          return;
        }
        this.router.navigateByUrl('/shop');

      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.createOrderError = true;
      }
    });
  }

  // Form Submission
  public checkout(): void {
    this.submitted = true;
    // stop here if form is invalid
    if (this.checkoutForm.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.submitted) {
      const formValues = this.checkoutForm.value;

      // If UserInfo has changed
      if (formValues.fullName != this.originalUserInfo.name || formValues.email != this.originalUserInfo.email ||
        formValues.address != this.originalUserInfo.address ||  formValues.city != this.originalUserInfo.city ||
        formValues.state != this.originalUserInfo.state ||  formValues.zip != this.originalUserInfo.zip) {
          this.changeUserInfo();
      }
      // If UserInfo hasn't changed, Payment has changed
      else if (formValues.cardName != this.originalPayment.ownerName || formValues.cardNumber != this.originalPayment.cardNumber ||
        formValues.expiration != (this.originalPayment.expiryMonth + "/" + this.originalPayment.expiryYear) ||  
        formValues.cvv != this.originalPayment.secCode) {
          this.changePayment();
      }
      // Neither have changed
      else {
        this.createOrder();
      }
    }
  }

}
