import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../../service/categoria.service';
import { Router, RouterModule } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { NgFor } from '@angular/common';

@Component({
    selector: 'app-categoria',
    imports: [NgFor, RouterModule],
    templateUrl: './gerenciar-categorias.component.html',
    styleUrl: './gerenciar-categorias.component.css'
})
export class CategoriaComponent implements OnInit{

  categoria$: any;
  categoria: any;

  constructor(private categoriaService: CategoriaService, private router: Router){

  }
  ngOnInit(): void {
    this.getCategoria();
  }

  public async getCategoria(){
    this.categoria$ = await lastValueFrom(this.categoriaService.get());

  }


  public editar(id: number){
    //this.categoria = await lastValueFrom(this.categoriaService.getCategoriaById(id));
    this.router.navigate(['categoria/editar/', id]);
    console.log(id);
  }

  public async remover(id: number){
    let ret = await lastValueFrom(this.categoriaService.remover(id));
    this.categoria$ = await lastValueFrom(this.categoriaService.get());
  }

}
