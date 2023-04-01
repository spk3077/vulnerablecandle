import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UserInfoReceive } from '@app/_core/userInfo';
import { CartItemReceive } from '@app/_core/cartItem';
import { PaymentReceive } from '@app/_core/payment';
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

  constructor(
    private shoppingCartService: ShoppingCartService, 
    private userInfoService: UserInfoService,
    private paymentService: PaymentService,
    private orderService: OrderService,
    private formBuilder: FormBuilder,
    private router: Router) {
      const zipPattern = "^[0-9]*$";
      const cardNumberPattern = "^[0-9]*$";
      const expirationPattern = "^(0[1-9]|1[012])/(2[3-9]|3[0-3])$";
      const cvvPattern = "^[0-9]*$";

      this.checkoutForm = this.formBuilder.group({
        fullName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
        email: ['', [Validators.required, Validators.email, Validators.minLength(5), Validators.maxLength(50)]],
        address: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
        city: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(40)]],
        state: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(2)]],
        zip: ['', [Validators.required, Validators.pattern(zipPattern), Validators.minLength(2), Validators.maxLength(20)]],
        cardName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
        cardNumber: ['', [Validators.required, Validators.minLength(14), Validators.maxLength(19), Validators.pattern(cardNumberPattern)]],
        expiration: ['', [Validators.required, Validators.pattern(expirationPattern)]],
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
        // Payment 
        if (payment == undefined) {
          this.originalPayment = new PaymentReceive(null, null, null, null, null, null);
          return;
        }

        console.log(res);
        this.checkoutForm.patchValue({
          cardName: payment.ownerName,
          cardNumber: payment.cardNumber,
          expiration: payment.expiryYear + "/" + payment.expiryYear,
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

  // Retrieve Shopping Cart to Display
  public createOrder(): void {
    this.orderService.createOrder().subscribe({
      next: (res) => {
        let orderResponse: DefaultResponse = res as DefaultResponse;
        if (orderResponse.success != true) {
          this.createOrderError = true;
        }
        this.router.navigateByUrl('/');

      },
      error: () => {
        // Failed at getting UserInfo to Store
        this.createOrderError = true;
      }
    });
  }

  // Update UserInfo on API if changed & if succeeds go to Payment Function
  // public changeUserInfo(): void {
    // if (this.userInfo != this.originalUserInfo) {
    //   const userInfo: UserInfoSend = new UserInfoSend("", "", "", "", "", "", 0, "");
      // const Properties = Object.keys(userInfo);
      // console.log(Properties);

      // Object.assign({}, this.userInfo);


  //     this.userInfoService.changeUserInfo(userInfo).subscribe({
  //       next: (res) => {
  //         let orderResponse: DefaultResponse = res as DefaultResponse;
  //         if (orderResponse.success != true) {
  //           this.changeUserInfoError = true;
  //         }
  //         this.router.navigateByUrl('/');

  //       },
  //       error: () => {
  //         // Failed at getting UserInfo to Store
  //         this.changeUserInfoError = true;
  //       }
  //     });
  //   }
  //   else {

  //   }
  // }

  // Form Submission
  public checkout(form: any): void {
    this.submitted = true;
    // stop here if form is invalid
    if (form.invalid) {
        return;
    }
    //True if all the fields are valid
    if(this.submitted) {
      // this.changeUserInfo();
    }
  }

}
