import { ProductReceive } from "./product";

// CLASS FOR RECEIVING SHOPPING CART ITEM
export class CartItemReceive {
    constructor(
        public id: number,
        public product: ProductReceive,
        public quantity: number,
    ) {  }
}

// CLASS FOR SENDING SHOPPING CART ITEM
export class CartItemSend {
    constructor(
        public productID: number,
        public quantity: number,
    ) {  }
}