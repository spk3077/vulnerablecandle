export class ProductReview {
    constructor(
        public title: string, // Add ProductReview and Receive Product Review
        public grade: number, // Add ProductReview and Receive Product Review
        public comment: string, // Add ProductReview and Receive Product Review
        public id?: number, // Receive Product Review Only
        public username?: string, // Receive Product Review Only
        public productID?: number, // Add ProductReview Only
    ) {  }
}