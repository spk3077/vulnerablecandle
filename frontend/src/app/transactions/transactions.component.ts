import { Component, OnInit, NgModule } from '@angular/core';

import { OrderReceive } from '@app/_core/order';
import { OrderService } from '@app/_services/order.service';
import { MatTableDataSource } from '@angular/material/table';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})

//@NgModule({
//  imports: [ ReactiveFormsModule ],
//  declarations: [],
//  exports: [ ReactiveFormsModule ]})

export class TransactionsComponent implements OnInit {
  orderItems: OrderReceive[] = [];
  originalOrderItems: OrderReceive[] = [];
  registerForm!: FormGroup;
  submitted = false;

  // Table Variables
  displayedColumns: string[] = ['id', 'address', 'card_number', 'city', 'email', 'name', 'payment_owner_name', 'purchase_date', 'state', 'zip', 'user_id'];
  dataSource = TRANSACTION_DATA;

  // Display Booleans
  getOrdersEmpty: boolean = false;
  getOrdersError: boolean = false;

  constructor(private orderService: OrderService, private formBuilder: FormBuilder) {}

  get f() { return this.registerForm.controls; }
  onSubmit() {
    this.submitted = true;
    console.log(this.registerForm.value);
    if (this.registerForm.invalid) {
      return;
    }
    if(this.submitted){
      //push to productReview
      alert("Review submitted!");
    }
  }

  ngOnInit(): void {
    this.getOrders();
    console.log(this.dataSource);
    console.log(this.dataSource[0]);
    this.dataSource = this.orderItems;
    console.log(this.orderItems);
    //Add User form validations
    this.registerForm = this.formBuilder.group({
    product: ['', [Validators.required]],
    rating: ['', [Validators.required]],
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
      },
      error: () => {
        // Failed at server
        this.getOrdersError = true;
      }
    });
    console.log(this.orderItems);
    console.log(this.orderItems);
    console.log(this.originalOrderItems);
    console.log(this.originalOrderItems[0]);
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
