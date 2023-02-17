import { Product } from './core/product';

export const PRODUCTS: Product[] = [
  {
    id: 1,
    name: 'Chocolate Candle',
    brand: 'Choco',
    price: 10,
    stock: 22,
    imageUrls: ['../assets/ice-cream-prune.svg'],
    desc: "Doggo",
    reviews: [
      {
        id: 1,
        username: 'DuckDuckGo',
        grade: 5,
        comment: "Superb. The only true candle I've seen in a long time"
      }
    ]
  }
];
