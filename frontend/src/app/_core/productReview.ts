// CLASS FOR RECEIVING Reviews
export class ProductReviewReceive {
    constructor(
        public id: number,
        public title: string,
        public username: string,
        public grade: number,
        public comment: string,
        public review_date: string,
    ) {  }
}

// CLASS FOR SENDING REVIEWS
export class ProductReviewSend {
    constructor(
        public productID: number,
        public title: string,
        public grade: number,
        public comment: string,
    ) {  }
}