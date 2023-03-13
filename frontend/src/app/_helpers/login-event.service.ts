import { Injectable, EventEmitter  } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';

@Injectable({
  providedIn: 'root'
})
export class LoginEventService {

  invokeLoginFunction = new EventEmitter();    
  subsVar: Subscription | undefined;   

  constructor() { }

  onRegistration(form: any) {    
    this.invokeLoginFunction.emit(form);    
  }
}
