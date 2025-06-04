import { Component, inject, OnInit } from '@angular/core';
import { CategoriaService } from '../../../service/categoria.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { Categoria } from '../../../model/categoria';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';


@Component({
    selector: 'app-categoria-form',
    imports: [FormsModule,
        ReactiveFormsModule,
        NgFor,
        RouterModule,
      HttpClientModule ],
    templateUrl: './cadastro-categorias.component.html',
    styleUrl: './cadastro-categorias.component.css'
})
export class CategoriaFormComponent implements OnInit{

  categoria$: any;
  categoria: any;
  private activateRoute = inject(ActivatedRoute);
  id: any;

  form = new FormGroup({
    id: new FormControl<number|null>(null),
    nome: new FormControl<string>(''),
    descricao: new FormControl<string>(''),
    categoria: new FormControl<number|null>(null)

  });

  constructor(private categoriaService: CategoriaService, private router: Router){

  }
  ngOnInit(): void {
    this.getCategorias();
    this.id = this.activateRoute.snapshot.params['id'];
    if(this.id){
      this.getCategoriaById();
    }

  }

  public async getCategorias(){
    this.categoria$ = await lastValueFrom(this.categoriaService.get());
  }

  public async getCategoriaById(){
    this.categoria = await lastValueFrom(this.categoriaService.getById(this.id));
    console.log(this.categoria);
    this.form.controls.id.setValue(this.categoria.id);
    this.form.controls.nome.setValue(this.categoria.nome);
    this.form.controls.descricao.setValue(this.categoria.descricao);
    this.form.controls.categoria.setValue(this.categoria.categoria?.id);
  }



  public salvar(){
    //todo
    let id_ = null;
    if(this.id){
      id_ = this.id;
    }
    let nome = this.form.controls.nome.value;
    let descricao = this.form.controls.descricao.value;
    let categoria_pai = this.form.controls.categoria.value;
    console.log(categoria_pai);
    let categoria: Categoria;

    if(categoria_pai){
      categoria = {
        "id": id_,
        "nome": nome,
        "descricao": descricao,
        "categoria": {
          "id": categoria_pai,
          "nome": null,
          "descricao": null,
          "categoria": null
        }
      };
    }else {
      categoria = {
        "id": id_,
        "nome": nome,
        "descricao": descricao
      };
    }


    console.log(categoria);


    this.categoriaService.salvar(categoria).subscribe(
      categoria => {
        this.router.navigate(['categoria']);
        console.log(categoria);
      },
      erro => {
        console.log(erro);
      }
    );
  }

}
