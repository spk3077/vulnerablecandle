import { PipeTransform, Pipe } from '@angular/core';

import { Product } from '../_core/product';
 

@Pipe({
    name: 'products',
    pure: false
})
export class ProductsPipe implements PipeTransform {
    transform(values: Product[], filters:any): Product[]{
        // Return same array if no filter
        if( !filters )
            return values;
        
        // Tag Filters
        if( filters.popular ){
            values = values.filter(item => item.tagNames.includes("isPopular"));
        } 
        if( filters.cute ){
            values = values.filter(item => item.tagNames.includes("isCute"));
        } 
        if( filters.trending ){
            values = values.filter(item => item.tagNames.includes("isTrending"));
        }
        if( filters.car ){
            values = values.filter(item => item.tagNames.includes("isForCar"));
        }
        if( filters.unique ){
            values = values.filter(item => item.tagNames.includes("isUnique"));
        }

        // Price Filters
        if( !filters.price1 && !filters.price2 && !filters.price3 && !filters.price4 ){
            return values;
        }
        if( !filters.price1 ){
            values = values.filter(item => !(item.price < 10) );
        }
        if( !filters.price2 ){
            values = values.filter(item => !(item.price >= 10 && item.price < 20) );
        }
        if( !filters.price3 ){
            values = values.filter(item => !(item.price >= 20 && item.price <= 30) );
        }
        if( !filters.price4 ){
            values = values.filter(item => !(item.price > 30) );
        }
        return values;
      }
}
