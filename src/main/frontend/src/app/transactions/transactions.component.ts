import { Component, OnInit } from '@angular/core';

import { OrderReceive } from '@app/_core/order';
import { OrderService } from '@app/_services/order.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { DefaultResponse } from '@app/_core/defaultResponse';

import { ProductReviewSend } from '@app/_core/productReview';
import { ProductReviewService } from '@app/_services/product-review.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  orderItems: OrderReceive[] = [];
  originalOrderItems: OrderReceive[] = [];
  productItems: any[] = [];
  addReviewForm!: FormGroup;
  submitted = false;
  bootlegIndex = 0;

  // Table Variables
  displayedColumns: string[] = ['id', 'address', 'card_number', 'email', 'name', 'purchase_date', 'totalPrice', 'items', 'button'];
  dataSource = TRANSACTION_DATA;
  displayedColumns2: string[] = ['itemId', 'itemName', 'itemPrice', 'quantity'];
  dataSource2: Array<Object> = [{}];
  
  // Display Booleans
  getOrdersEmpty: boolean = false;
  getOrdersError: boolean = false;
  toReviewMessage: string | undefined;
  showReviewMessage: boolean = false;

  // Validators for min/max
  controlMin = new FormControl(0, Validators.min(1));
  controlMax = new FormControl(5, Validators.max(5));

  constructor(private orderService: OrderService, private formBuilder: FormBuilder, private productReviewService: ProductReviewService) {}

  get f() { return this.addReviewForm.controls; }
  onSubmit() {
    this.submitted = true;
    console.log(this.addReviewForm.value);
    if (this.addReviewForm.invalid) {
      return;
    }
    if(this.submitted){
      //push to productReview
      console.log(this.addReviewForm.value.product);
      this.productReviewService.addProductReview(new ProductReviewSend(this.addReviewForm.value.product, this.addReviewForm.value.title, this.addReviewForm.value.rating, this.addReviewForm.value.review)).subscribe({
        next: (res) => {
          // Get generic response to determine success
          const addResponse = res as DefaultResponse;
            if (addResponse.success != true) {
              this.toReviewMessage = res.message;
            }
            this.showReviewMessage = true;
        },
        error: () => {
          // Failed at server
          this.showReviewMessage = true;
          this.toReviewMessage = "Internal Server Error";
        }
      });
      alert("Review submitted!");
    }
  }
  

  ngOnInit(): void {
    this.getOrders();
    //Add User form validations
    this.addReviewForm = this.formBuilder.group({
    product: ['', [Validators.required, Validators.min(0)]],
    rating: ['', [Validators.required, Validators.min(1), Validators.max(5)]],
    title: ['', [Validators.required]],
    review: ['', [Validators.required]],
    });
  }

  // Retrive orders to display
  public getOrders(): void {
    this.orderService.getOrders().subscribe({
      next: (res) => {
        if (res.length <= 0) {
          this.getOrdersEmpty = true;
          return;
        }

        res.forEach((orderItem: any) => {
          this.orderItems.push(
            new OrderReceive(orderItem.id, orderItem.address, orderItem.cardNumber, orderItem.city, orderItem.email, orderItem.name, orderItem.payment_owner_name, orderItem.orderItems, orderItem.purchase_date, orderItem.state, orderItem.zip, orderItem.totalPrice, orderItem.user_id));
        });
        this.orderItems.forEach(val => this.originalOrderItems.push(Object.assign({}, val)));
        // Grab order products
        res.forEach((orderItem: any) => {
          //console.log(orderItem.orderItems);
          //console.log(this.bootlegIndex);
          //console.log(orderItem.orderItems.length);
          for (let index of orderItem.orderItems) {
            this.productItems.push( {orderId: orderItem.id, itemId: orderItem.orderItems[this.bootlegIndex].itemId, itemName: orderItem.orderItems[this.bootlegIndex].itemName, itemBrand: orderItem.orderItems[this.bootlegIndex].itemBrand, itemPrice: orderItem.orderItems[this.bootlegIndex].itemPrice, quantity: orderItem.orderItems[this.bootlegIndex].quantity});
            this.bootlegIndex = this.bootlegIndex + 1;
            if((orderItem.orderItems[this.bootlegIndex] == null) || (orderItem.orderItems[this.bootlegIndex] == undefined)) { 
              this.bootlegIndex = 0;
            }
          }
        });
        // Replace dataSource with new orderItems
        this.dataSource = this.orderItems;
        this.dataSource2 = this.productItems;
      },
      error: () => {
        // Failed at server
        this.getOrdersError = true;
      }
    });
  }
  
}

export interface TransactionHistory {
  id: number;
  purchase_date: string;
  user_id: number;
}

const TRANSACTION_DATA: TransactionHistory[] = [
  {id: 1, purchase_date: '11-21-2022', user_id: 1},
];
