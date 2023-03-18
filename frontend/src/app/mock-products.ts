import { Product } from './_core/product';
import { ProductReview } from './_core/productReview';

export const PRODUCTS: Product[] = [
  new Product(
    'Chocolate Candle',
    'Choco',
    "The utmost utmost beautiful candle of candles.  The burning whick goes far among the skies to become the truly sole king.",
    ['isCute', 'isPopular'],
    10,
    22,
    ['assets/images/candle1.png', 'assets/images/candle2.png', 'assets/images/candle3.png'],
    1,
    5,
    [
      {
        id: 1,
        title: "Neat Product",
        username: 'DuckDuckGo',
        grade: 5,
        comment: "Superb. The only true candle I've seen in a long time"
      },
      {
        id: 2,
        title: "Chad",
        username: 'Chad',
        grade: 5,
        comment: "Chad"
      }
    ]
  ),
  new Product(
    'Cain',
    'Love',
    "The Greatest Goat.",
    ['isTrending', 'isPopular'],
    20,
    19,
    ['assets/images/candle2.png', 'assets/images/candle1.png', 'assets/images/candle3.png'],
    2,
    4,
    [
      {
        id: 1,
        title: "Terrible",
        username: 'Lola',
        grade: 5,
        comment: "10/10 Content"
      },
      {
        id: 2,
        title: "Not Chad",
        username: 'Chad',
        grade: 3,
        comment: "Chadz"
      }
    ]
  ),
  new Product(
    'Banana Candle',
    'Heavenly',
    "Platinum is Gold.  Gold is Bronze.",
    ['isUnique'],
    5,
    10,
    ['assets/images/candle3.png', 'assets/images/candle1.png', 'assets/images/candle2.png'],
    3,
    1.5,
    [
      {
        id: 1,
        title: "Not Cool",
        username: 'Opera',
        grade: 1,
        comment: "Supertime"
      },
      {
        id: 2,
        title: "Corona",
        username: 'Chad',
        grade: 2,
        comment: "Chad"
      }
    ]
  ),
  new Product(
    'Empty',
    'Cho',
    "The Sole king.",
    ['isTrending', 'isPopular', 'isForCar'],
    20,
    2,
    ['assets/images/candle1.png', 'assets/images/candle2.png'],
    4,
    5,
    [
      {
        id: 1,
        title: "Corona",
        username: 'Barren',
        grade: 5,
        comment: "Seen By Looking"
      },
      {
        id: 2,
        title: "Perish",
        username: 'Jet',
        grade: 5,
        comment: "Jets Alook"
      }
    ]
  ),
  new Product(
    'Look',
    'Bite',
    "COLD BRO",
    ['isPopular'],
    30,
    12,
    ['assets/images/candle2.png'],
    5,
    5,
    [
      {
        id: 1,
        title: "YESSSS",
        username: 'Casino Mount',
        grade: 5,
        comment: "Lots of Poker"
      },
      {
        id: 2,
        title: "Fantastic!",
        username: 'Mount Credits',
        grade: 5,
        comment: "500"
      }
    ]
  ),
  new Product (
    'Veteran',
    'Armed Robber',
    "Tinkerer.",
    ['isCute', 'isPopular', 'isTrending', 'isUnique', 'isForCar'],
    7,
    3,
    ['assets/images/candle3.png'],
    5,
    2,
    [
      {
        id: 1,
        title: "Lol Awful",
        username: 'Opera',
        grade: 1,
        comment: "Browser"
      },
      {
        id: 2,
        title: "Medium Chad",
        username: 'Chad',
        grade: 3,
        comment: "Chad the Chad"
      }
    ]
  )
];
