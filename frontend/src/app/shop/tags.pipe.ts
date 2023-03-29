import { Pipe, PipeTransform } from '@angular/core';
import { TagReceive } from '@app/_core/tag';

@Pipe({
  name: 'tags'
})
export class TagsPipe implements PipeTransform {

  transform(values: TagReceive[], tagHead: string): TagReceive[] {
    return values.filter(item => item.type == tagHead);
  } 

}
