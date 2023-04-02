// CLASS FOR RECEIVING SHOPPING CART ITEM
export class OrderReceive {
    constructor(
        public id: number,
        public purchase_date: string,
        public user_id: number
    ) {  }
}

//
//    public id: number = 0;
//    public purchase_date: string = "";
//    public user_id: number = 0;

//    public static forDisplay(id: number, purchase_date: string, user_id: number): OrderReceive {
//    const cls = new OrderReceive();
//    cls.id = id;
//    cls.purchase_date = purchase_date;
//    cls.user_id = user_id;
//    return cls;
//  }
//}
