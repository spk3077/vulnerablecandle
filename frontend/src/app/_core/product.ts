import { ProductReviewReceive } from './productReview';

// CLASS FOR RECEIVING PRODUCTS
export class ProductReceive {
    public id: number = 0;
    public name: string = "";
    public brand: string = "";
    public description: string = "";
    public tagNames: string[] = [];
    public price: number = 0;
    public stock: number = 0;
    public image: string = "";
    public averageReviewGrade: number = 0;
    public productReviews: ProductReviewReceive[] = [];

    public static forHome(id: number, name: string, price: number, image: string, averageReviewGrade: number): ProductReceive {
    const cls = new ProductReceive();
    cls.id = id;
    cls.name = name;
    cls.price = price;
    cls.image = image;
    cls.averageReviewGrade = averageReviewGrade;
    return cls;
}
    public static forShop(id: number, name: string, tagNames: string[], 
            price: number, image: string, averageReviewGrade: number): ProductReceive {
        const cls = new ProductReceive();
        cls.id = id;
        cls.name = name;
        cls.tagNames = tagNames;
        cls.price = price;
        cls.image = image;
        cls.averageReviewGrade = averageReviewGrade;
        return cls;
    }

    public static forProduct(id: number, name: string, brand: string, description: string, tagNames: string[], 
        price: number, stock: number, image: string, averageReviewGrade: number, productReviews: ProductReviewReceive[]): ProductReceive {
        const cls = new ProductReceive();
        cls.id = id;
        cls.name = name;
        cls.brand = brand;
        cls.description = description;
        cls.tagNames = tagNames;
        cls.price = price;
        cls.stock = stock;
        cls.image = image;
        cls.averageReviewGrade = averageReviewGrade;
        cls.productReviews = productReviews;
        return cls;
}

    // Set The Color of Rating Bubble
    public setBadgeClass(): string {
        if ( !this.averageReviewGrade || this.averageReviewGrade! < 2 ) {
            return 'bg-danger';
        } else if ( this.averageReviewGrade! >= 2 && this.averageReviewGrade! < 4 ) {
            return 'bg-warning';
        } else {
            return 'bg-success';
        }
    }

    // Parse first image from image string
    public getFirstImage(): string {
        return this.image.split(',', 3)[0];
    }
}

// CLASS FOR SENDING PRODUCTS
export class ProductSend {
    constructor(
        public name: string,
        public brand: string,
        public description: string,
        public tagNames: number[],
        public price: number,
        public stock: number,
        public image: string,
    ) {  }
}