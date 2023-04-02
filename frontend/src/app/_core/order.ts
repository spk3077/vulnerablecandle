// CLASS FOR RECEIVING SHOPPING CART ITEM
export class OrderReceive {
    constructor(
        public id: number,
        public purchase_date: string,
        public user_id: number,
    ) {  }
}
