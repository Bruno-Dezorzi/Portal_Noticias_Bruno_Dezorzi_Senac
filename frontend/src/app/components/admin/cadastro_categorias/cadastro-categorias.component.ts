import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../../service/categoria.service';
import { Router, RouterModule } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-categoria',
  standalone: true,
  imports: [NgFor, RouterModule],
  templateUrl: './../cadastro_categorias/cadastro-categorias.component.html',
  styleUrl: './../cadastro_categorias/cadastro-categorias.component.css'
})
export class CategoriaComponent implements OnInit {
  categoria$: any;

  constructor(
    private categoriaService: CategoriaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCategoria();
  }

  public async getCategoria(): Promise<void> {
    try {
      this.categoria$ = await lastValueFrom(this.categoriaService.get());
    } catch (erro) {
      console.error('Erro ao buscar categorias:', erro);
    }
  }

  public editar(id: number): void {
    this.router.navigate(['categoria/editar', id]);
  }

  public async remover(id: number): Promise<void> {
    try {
      await lastValueFrom(this.categoriaService.remover(id));
      this.getCategoria(); // atualiza a lista após remover
    } catch (erro) {
      console.error('Erro ao remover categoria:', erro);
    }
  }
}
