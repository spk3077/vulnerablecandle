import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-submenu',
  templateUrl: './submenu.component.html',
  styleUrls: ['./submenu.component.css']
})
export class SubmenuComponent implements OnInit {
  @Input() activePage?: string;
  @Input() secondPage?: string;
  @Input() secondPage_URL?: string | undefined;
  @Input() thirdPage?: string | undefined;
  @Input() thirdPage_URL?: string | undefined;
  @Input() fourthPage?: string | undefined;

  ngOnInit() {}
}
