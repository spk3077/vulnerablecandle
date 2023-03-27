export class PaymentReceive {
    constructor(
        public id: number,
        public ownerName: string,
        public cardNumber: number,
        public expiryMonth: number,
        public expiryYear: number,
        public secCode: number,
    ) {  }
}

// CLASS FOR SENDING SHOPPING CART ITEM
export class PaymentSend {
    constructor(
        public cardNumber: number,
        public ownerName: string,
        public expiryMonth: number,
        public expiryYear: number,
        public secCode: number,
    ) {  }
}