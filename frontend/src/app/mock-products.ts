import { ProductReceive } from './_core/product';
import { ProductReviewReceive } from './_core/productReview';

export const PRODUCTS: ProductReceive[] = [
  new ProductReceive(
    1,
    'Chocolate Candle',
    'Choco',
    "The utmost utmost beautiful candle of candles.  The burning whick goes far among the skies to become the truly sole king.",
    ['isCute', 'isPopular'],
    10,
    22,
    'assets/images/candle1.png,assets/images/candle2.png,assets/images/candle3.png',
    5,
    [
      new ProductReviewReceive(1, "Neat Product", "DuckDuckGo", 5, "Better than my Browser", "2023-03-18"), 
      new ProductReviewReceive(2, "Chad", "Chad", 5, "Chad", "2023-03-18")
    ]
  ),
  new ProductReceive(
    2, 
    'Cain',
    'Love',
    "The Greatest Goat.",
    ['isTrending', 'isPopular'],
    20,
    19,
    'assets/images/candle2.png,assets/images/candle1.png,assets/images/candle3.png',
    4,
    [
      new ProductReviewReceive(1, "Terrible", "Lola", 5, "10/10 Content", "2023-03-18"), 
      new ProductReviewReceive(2, "Not Chad", "Chad", 1, "Chadz", "2023-03-18")
    ]
  ),
  new ProductReceive(
    3, 
    'Banana Candle',
    'Heavenly',
    "Platinum is Gold.  Gold is Bronze.",
    ['isUnique'],
    5,
    10,
    'assets/images/candle3.png,assets/images/candle1.png,assets/images/candle2.png',
    1.5,
    [
      new ProductReviewReceive(1, "Not Cool", "Opera", 1, "Supertime", "2023-03-18"), 
      new ProductReviewReceive(2, "Corona", "Chad", 2, "Chad", "2023-03-18")
    ]
  ),
  new ProductReceive(
    4, 
    'Empty',
    'Cho',
    "The Sole king.",
    ['isTrending', 'isPopular', 'isForCar'],
    20,
    2,
    'assets/images/candle1.png,assets/images/candle2.png',
    5,
    [
      new ProductReviewReceive(1, "Corona", "Barren", 5, "Seen by Looking", "2023-03-18")
    ]
  ),
  new ProductReceive(
    5, 
    'Look',
    'Bite',
    "COLD BRO",
    ['isPopular'],
    30,
    12,
    'assets/images/candle2.png',
    5,
    [
      new ProductReviewReceive(1, "YESS", "CASINO Mount", 5, "Lots of Poker", "2023-03-18")
    ]
  ),
  new ProductReceive (
    6, 
    'Veteran',
    'Armed Robber',
    "Tinkerer.",
    ['isCute', 'isPopular', 'isTrending', 'isUnique', 'isForCar'],
    7,
    3,
    'assets/images/candle3.png',
    1,
    [
    new ProductReviewReceive(1, "Lol Awful", "Opera", 1, "Browser", "2023-03-18"), 
    new ProductReviewReceive(2, "Medium Chad", "Chad", 1, "Chad the Chad", "2023-03-18")
    ]
  )
];
