import { ProductReceive } from "./product";

// CLASS FOR RECEIVING SHOPPING CART ITEM
export class CartItemReceive {
    constructor(
        public id: number,
        public product: ProductReceive,
        public quantity: number
    ) {  }
}

// CLASS FOR SENDING SHOPPING CART ITEM
export class CartItemSend {
    public id: number | null = null;
    public quantity: number | null = null;
    public itemPrice: number | null = null;

    // CartItemSend Class for adding products to shopping cart
    public static forAdd(id: number, quantity: number, itemPrice: number): CartItemSend {
        const cls = new CartItemSend();
        cls.id = id;
        cls.quantity = quantity;
        cls.itemPrice = itemPrice;
        return cls;
    }
    
    // CartItemSend Class for updating entries in shopping cart
    public static forUpdate(id: number, quantity: number): CartItemSend {
        const cls = new CartItemSend();
        cls.id = id;
        cls.quantity = quantity;
        return cls;
    }
}