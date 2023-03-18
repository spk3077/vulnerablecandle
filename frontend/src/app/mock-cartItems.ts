import { CartItemProduct, CartItemReceive } from "./_core/cartItem";

export const CARTITEMS: CartItemReceive[] = [
  new CartItemReceive(
    1,
    new CartItemProduct(1, 'Chocolate Candle', 'Chocolate Factories', 10, 'assets/images/candle1.png'),
    5
  ),
  new CartItemReceive(
    1,
    new CartItemProduct(2, 'Fantasy Candle', 'Dumbo', 22, 'assets/images/candle2.png'),
    1
  ),
  new CartItemReceive(
    1,
    new CartItemProduct(3, 'Sherley Temple Candle', 'Sherleys', 2, 'assets/images/candle3.png'),
    4
  ),
];
