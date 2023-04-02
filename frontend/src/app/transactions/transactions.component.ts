import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { OrderReceive } from '@app/_core/order';
import { OrderService } from '@app/_services/order.service';
import { UserService } from '@app/_services/user.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})

export class TransactionsComponent implements OnInit {
  displayedColumns: string[] = ['id', 'purchase_date', 'user_id'];
  dataSource = TRANSACTION_DATA;
  currentUser: any | undefined;
  // order: OrderReceive = OrderReceive;
  
  orderItems!: OrderReceive[];

  constructor(private orderService: OrderService, private router: Router, private userService: UserService) {}

  ngOnInit(): void {
    this.userService.loggedInUser$.subscribe( x => this.currentUser = x);
    this.getOrders();
  }

  // Retrive order to display
  public getOrders(): void {
    this.orderService.getOrders().subscribe(orderItems => this.orderItems = orderItems);
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
