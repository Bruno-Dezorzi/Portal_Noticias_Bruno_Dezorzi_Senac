import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterModule } from '@angular/router';
import { Categoria } from '../../../model/categoria';

@Component({
  selector: 'app-gerenciar-categorias',
  imports: [CommonModule, RouterModule,RouterLink],
  templateUrl: './gerenciar-categorias.component.html',
  styleUrl: './gerenciar-categorias.component.css'
})
export class GerenciarCategoriasComponent implements OnInit {
  categoria$: Categoria[] = [
    {
      id: 1,
      nome: 'Tecnologia',
      categoria: null
    },
    {
      id: 2,
      nome: 'Negócios',
      categoria: null
    },
    {
      id: 3,
      nome: 'Esportes',
      categoria: null
    },
    {
      id: 4,
      nome: 'Programação',
      categoria: {
        id: 1,
        nome: 'Tecnologia',
        categoria: null
      }
    },
    {
      id: 5,
      nome: 'Futebol',
      categoria: {
        id: 3,
        nome: 'Esportes',
        categoria: null
      }
    },
    {
      id: 6,
      nome: 'Marketing Digital',
      categoria: {
        id: 2,
        nome: 'Negócios',
        categoria: null
      }
    }
  ];

  constructor() { }

  ngOnInit(): void {
  }

  editar(id: number | null): void {
    if (id) {
      console.log('Editando categoria:', id);
      // Navegar para edição: this.router.navigate(['/categorias/editar', id]);
    }
  }

  remover(id: number | null): void {
    if (id && confirm('Tem certeza que deseja excluir esta categoria?')) {
      // Verificar se existem subcategorias antes de remover
      const hasSubcategorias = this.categoria$.some(cat => cat.categoria?.id === id);

      if (hasSubcategorias) {
        alert('Não é possível excluir esta categoria pois existem subcategorias vinculadas a ela.');
        return;
      }

      this.categoria$ = this.categoria$.filter(categoria => categoria.id !== id);
      console.log('Categoria removida:', id);
    }
  }
}
