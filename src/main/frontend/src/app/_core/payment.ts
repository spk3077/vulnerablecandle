export class PaymentReceive {
    constructor(
        public id: number | null,
        public ownerName: string | null,
        public cardNumber: number | null,
        public expiryMonth: number | null,
        public expiryYear: number | null,
        public secCode: number | null
    ) {  }
}

// CLASS FOR SENDING SHOPPING CART ITEM
export class PaymentSend {
    constructor(
        public cardNumber: number,
        public ownerName: string,
        public expiryMonth: number,
        public expiryYear: number,
        public secCode: number
    ) {  }
}