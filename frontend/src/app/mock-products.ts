import { Product } from './_core/product';

export const PRODUCTS: Product[] = [
  new Product(
    1,
    'Chocolate Candle',
    'Choco',
    ['isCute', 'isPopular'],
    10,
    22,
    ['assets/images/candle1.png', 'assets/images/candle2.png', 'assets/images/candle3.png'],
    "The utmost utmost beautiful candle of candles.  The burning whick goes far among the skies to become the truly sole king.",
    [
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
  ),
  new Product(
    2,
    'Cain',
    'Love',
    ['isTrending', 'isPopular'],
    20,
    19,
    ['assets/images/candle2.png', 'assets/images/candle1.png', 'assets/images/candle3.png'],
    "The Greatest Goat.",
    [
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
  ),
  new Product(
    3,
    'Banana Candle',
    'Heavenly',
    ['isUnique'],
    5,
    10,
    ['assets/images/candle3.png', 'assets/images/candle1.png', 'assets/images/candle2.png'],
    "Platinum is Gold.  Gold is Bronze.",
    [
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
  ),
  new Product(
    4,
    'Empty',
    'Cho',
    ['isTrending', 'isPopular', 'isForCar'],
    20,
    2,
    ['assets/images/candle1.png', 'assets/images/candle2.png'],
    "The Sole king.",
    [
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
  ),
  new Product(
    5,
    'Look',
    'Bite',
    ['isPopular'],
    30,
    12,
    ['assets/images/candle2.png'],
    "COLD BRO",
    [
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
  ),
  new Product (
    6,
    'Veteran',
    'Armed Robber',
    ['isCute', 'isPopular', 'isTrending', 'isUnique', 'isForCar'],
    7,
    3,
    ['assets/images/candle3.png'],
    "Tinkerer.",
    [
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
  )
];
