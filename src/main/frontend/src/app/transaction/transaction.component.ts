import { Component, Input } from '@angular/core';

import { Order } from '../core/order';

import { HttpClientService } from '../service/http-client.service';

import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { faChevronDown } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
