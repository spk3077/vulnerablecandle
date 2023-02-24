import { ProductReview } from './productReview';

export interface Product {
    id: number;
    name: string;
    brand?: string;
    tags: string[];
    price: number;
    stock: number;
    imageUrls: string[];
    desc: string;
    reviews: ProductReview[];
}