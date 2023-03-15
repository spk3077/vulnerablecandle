import { ProductReview } from './productReview';

export class Product {
    constructor(
        public id: number,
        public name: string,
        public brand: string,
        public tags: string[],
        public price: number,
        public stock: number,
        public imageUrls: string[],
        public desc: string,
        public reviews: ProductReview[]
    ) {  }

    // Get the Mean of Reviews
    getBadgeGrade(reviews: ProductReview[]): number {
        return reviews.reduce((acc, review) => acc + review.grade, 0) / reviews.length;
    }

    // Set The Color of Rating Bubble
    setBadgeClass(reviews: ProductReview[]): string {
        // Get MEAN using an accumulator and looping reviews
        let mean: number = this.getBadgeGrade(reviews);
        if ( mean < 2 ) {
            return 'bg-danger';
        } else if ( mean >= 2 && mean < 4 ) {
            return 'bg-warning';
        } else {
            return 'bg-success';
        }
    }
}