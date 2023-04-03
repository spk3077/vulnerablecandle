import { Injectable } from '@angular/core';

import { ModalComponent } from '../_components';

@Injectable({ providedIn: 'root' })
export class ModalService {
  private modals: ModalComponent[] = [];

  add(modal: ModalComponent) {
    // ensure component has unique id
    if (!modal.id || this.modals.find(x => x.id === modal.id) {
      throw new Error('Modal must have a unique ID');
    }
    this.modals.push(modal);
  }
  
  remove(modal: ModalComponent) {
    
