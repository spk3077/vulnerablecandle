import { ProductReview } from './productReview';

export class Product {
    constructor(
        public name: string,
        public brand: string,
        public description: string,
        public tagNames: string[],
        public price: number,
        public stock: number,
        public imageUrls: string[],
        public id?: number, // Receive Product Only
        public averageReviewGrade?: number, // Receive Product Only
        public productReviews?: ProductReview[] // Receive Product Only
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