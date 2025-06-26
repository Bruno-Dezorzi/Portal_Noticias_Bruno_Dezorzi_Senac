import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'smartSlice',
  standalone: true
})
export class SmartSlicePipe implements PipeTransform {

  transform(value: string | null | undefined, limit: number): string {
    if (!value || value.length <= limit) {
      return value ?? '';
    }

    // Procura o primeiro ponto após o limite
    const afterLimit = value.slice(limit);
    const pontoIndex = afterLimit.indexOf('.');

    if (pontoIndex !== -1) {
      // Retorna até o primeiro ponto encontrado após o limite
      return value.slice(0, limit + pontoIndex + 1);
    }

    // Se não houver ponto, retorna só até o limite
    return value.slice(0, limit) + '...';
  }
}
