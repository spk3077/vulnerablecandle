import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { OrderReceive } from '@app/_core/order';
import { OrderService } from '@app/_services/order.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent {
  orderItems!: OrderReceive[];
  originalOrderItems!: OrderReceive[];

  ngOnInit(): void {
    this.getOrders();
    this.originalOrderItems = this.orderItems;
  }

  // Retrive order to display
  public getOrders(): void {
    this.orderService.getOrders().subscribe(orderItems => this.orderItems = orderItems);
  }
}
