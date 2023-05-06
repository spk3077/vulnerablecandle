import { DomSanitizer } from '@angular/platform-browser';
import { inject } from '@angular/core/testing';

import { SafePipe } from './safe.pipe';

describe('SafePipe', () => {
  it('create an instance', inject([ DomSanitizer ], (sanitizer: DomSanitizer) => {
    const pipe = new SafePipe(sanitizer);
    expect(pipe).toBeTruthy();
  }));
});
