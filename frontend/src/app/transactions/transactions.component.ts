import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { OrderReceive } from '@app/_core/order';
import { OrderService } from '@app/_services/order.service';
import { NgModule } from '@angular/core';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent {
  orderItems!: OrderReceive[];
  originalOrderItems!: OrderReceive[];

  constructor(private orderService: OrderService, private router: Router) {}

  ngOnInit(): void {
    this.getOrders();
    this.originalOrderItems = this.orderItems;
  }

  // Retrive order to display
  public getOrders(): void {
    this.orderService.getOrders().subscribe(orderItems => this.orderItems = orderItems);
  }
}

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];

/**
 * @title Basic use of `<table mat-table>`
 *
 *@Component({
 *  selector: 'table-basic-example',
 *  styleUrls: ['table-basic-example.css'],
 *  templateUrl: 'table-basic-example.html',
 *})
 *export class TableBasicExample {
 *  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
 *  dataSource = ELEMENT_DATA;
 *}
 */
