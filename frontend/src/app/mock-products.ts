import { Product } from './core/product';

export const PRODUCTS: Product[] = [
  {
    id: 1,
    name: 'Chocolate Candle',
    brand: 'Choco',
    tags: ['isCute', 'isPopular'],
    price: 10,
    stock: 22,
    imageUrls: ['assets/images/candle1.png', 'assets/images/candle2.png', 'assets/images/candle3.png'],
    desc: "The utmost utmost beautiful candle of candles.  The burning whick goes far among the skies to become the truly sole king.",
    reviews: [
      {
        id: 1,
        username: 'DuckDuckGo',
        grade: 5,
        comment: "Superb. The only true candle I've seen in a long time"
      },
      {
        id: 2,
        username: 'Chad',
        grade: 5,
        comment: "Chad"
      }
    ]
  },
  {
    id: 2,
    name: 'Cain',
    brand: 'Love',
    tags: ['isTrending', 'isPopular'],
    price: 20,
    stock: 19,
    imageUrls: ['assets/images/candle2.png', 'assets/images/candle1.png', 'assets/images/candle3.png'],
    desc: "The Greatest Goat.",
    reviews: [
      {
        id: 1,
        username: 'Lola',
        grade: 5,
        comment: "10/10 Content"
      },
      {
        id: 2,
        username: 'Chad',
        grade: 3,
        comment: "Chadz"
      }
    ]
  },
  {
    id: 3,
    name: 'Banana Candle',
    brand: 'Heavenly',
    tags: ['isUnique'],
    price: 5,
    stock: 10,
    imageUrls: ['assets/images/candle3.png', 'assets/images/candle1.png', 'assets/images/candle2.png'],
    desc: "Platinum is Gold.  Gold is Bronze.",
    reviews: [
      {
        id: 1,
        username: 'Opera',
        grade: 1,
        comment: "Supertime"
      },
      {
        id: 2,
        username: 'Chad',
        grade: 2,
        comment: "Chad"
      }
    ]
  },
  {
    id: 4,
    name: 'Empty',
    brand: 'Cho',
    tags: ['isTrending', 'isPopular', 'isForCar'],
    price: 20,
    stock: 2,
    imageUrls: ['assets/images/candle1.png', 'assets/images/candle2.png'],
    desc: "The Sole king.",
    reviews: [
      {
        id: 1,
        username: 'Barren',
        grade: 5,
        comment: "Seen By Looking"
      },
      {
        id: 2,
        username: 'Jet',
        grade: 5,
        comment: "Jets Alook"
      }
    ]
  },
  {
    id: 5,
    name: 'Look',
    brand: 'Bite',
    tags: ['isPopular'],
    price: 30,
    stock: 12,
    imageUrls: ['assets/images/candle2.png'],
    desc: "COLD BRO",
    reviews: [
      {
        id: 1,
        username: 'Casino Mount',
        grade: 5,
        comment: "Lots of Poker"
      },
      {
        id: 2,
        username: 'Mount Credits',
        grade: 5,
        comment: "500"
      }
    ]
  },
  {
    id: 6,
    name: 'Veteran',
    brand: 'Armed Robber',
    tags: ['isCute', 'isPopular', 'isTrending', 'isUnique', 'isForCar'],
    price: 7,
    stock: 3,
    imageUrls: ['assets/images/candle3.png'],
    desc: "Tinkerer.",
    reviews: [
      {
        id: 1,
        username: 'Opera',
        grade: 1,
        comment: "Browser"
      },
      {
        id: 2,
        username: 'Chad',
        grade: 3,
        comment: "Chad the Chad"
      }
    ]
  }
];
