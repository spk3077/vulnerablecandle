import { Component, OnInit } from '@angular/core';

import { OrderReceive } from '@app/_core/order';
import { OrderService } from '@app/_services/order.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})

export class TransactionsComponent implements OnInit {
  orderItems: OrderReceive[] = [];
  originalOrderItems: OrderReceive[] = [];

  // Table Variables
  displayedColumns: string[] = ['id', 'address', 'card_number', 'city', 'email', 'name', 'payment_owner_name', 'purchase_date', 'state', 'zip', 'user_id'];
  dataSource = TRANSACTION_DATA;

  // Display Booleans
  getOrdersEmpty: boolean = false;
  getOrdersError: boolean = false;

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.getOrders();
  }

  // Retrive orders to display
  public getOrders(): void {
    this.orderService.getOrders().subscribe({
      next: (res) => {
        if (res.length <= 0) {
          this.getOrdersEmpty = true;
          return;
        }

        res.orderItems.forEach((orderItem: any) => {
          this.orderItems.push(
            new OrderReceive(orderItem.id, orderItem.address, orderItem.card_number, orderItem.city, orderItem.email, orderItem.name, orderItem.payment_owner_name, orderItem.purchase_date, orderItem.state, orderItem.zip, orderItem.user_id));
        });
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
