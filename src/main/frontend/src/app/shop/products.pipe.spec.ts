import { ProductsPipe } from './products.pipe';

describe('ProductsPipe', () => {
  it('create an instance', () => {
    const pipe = new ProductsPipe();
    expect(pipe).toBeTruthy();
  });
});
