import { ProductReviewReceive } from './productReview';

// CLASS FOR RECEIVING PRODUCTS
export class ProductReceive {
    constructor(
        public id: number,
        public name: string,
        public brand: string,
        public description: string,
        public tagNames: string[],
        public price: number,
        public stock: number,
        public imageUrls: string[],
        public averageReviewGrade: number,
        public productReviews: ProductReviewReceive[]
    ) {  }

    // Set The Color of Rating Bubble
    setBadgeClass(): string {
        if ( !this.averageReviewGrade || this.averageReviewGrade! < 2 ) {
            return 'bg-danger';
        } else if ( this.averageReviewGrade! >= 2 && this.averageReviewGrade! < 4 ) {
            return 'bg-warning';
        } else {
            return 'bg-success';
        }
    }
}

// CLASS FOR SENDING PRODUCTS
export class ProductSend {
    constructor(
        public name: string,
        public brand: string,
        public description: string,
        public tagNames: string[],
        public price: number,
        public stock: number,
        public imageUrls: string[],
    ) {  }
}