import { PipeTransform, Pipe } from '@angular/core';

import { ProductReceive } from '../_core/product';
 

@Pipe({
    name: 'products',
    pure: false
})
export class ProductsPipe implements PipeTransform {
    transform(values: ProductReceive[], filters:any): ProductReceive[]{
        // Return same array if no filter
        if( !filters ) {
            return values;
        }

        // Tag Filters
        for (const [key, value] of Object.entries(filters)) {
            // if filter hasn't been selected, skip
            if (value && !(key.substring(0, 4) == 'price') ) {
                values = values.filter(item => item.tagNames.includes(key));
            }
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
