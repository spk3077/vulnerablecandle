import { ProductReceive } from "./product";

// CLASS FOR RECEIVING SHOPPING CART ITEM
export class CartItemReceive {
    constructor(
        public id: number,
        public product: CartItemProduct,
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

// CLASS FOR RECEIVING PRODUCT INFORMATION FOR SHOPPING CART
export class CartItemProduct {
    constructor(
        public id: number,
        public name: string,
        public brand: string,
        public price: number,
        public imageUrl: string,
    ) {  }
}