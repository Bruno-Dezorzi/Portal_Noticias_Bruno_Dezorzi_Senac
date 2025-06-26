import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { NoticiaService } from '../../../service/noticia.service';
import { lastValueFrom } from 'rxjs';
import { Noticia } from '../../../model/noticia';
import { CategoriaService } from '../../../service/categoria.service';
import { NgFor } from '@angular/common';

@Component({
    standalone: true,
    selector: 'app-noticia-form',
    imports: [
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        NgFor  // Add NgFor to the imports array
    ],
    templateUrl: './cadastro-noticias.component.html',
    styleUrl: './cadastro-noticias.component.css'
})
export class NoticiaFormComponent implements OnInit {
  cat$: any;
  noticia$: any;
  noticia: any;
  private activateRoute = inject(ActivatedRoute);
  id: any;

  form = new FormGroup({
    id: new FormControl<number | null>(null),
    titulo: new FormControl<string>(''),
    corpo: new FormControl<string>(''),
    categoria: new FormControl<number | null>(null)
  });

  constructor(private noticiaService: NoticiaService, private router: Router, private categoriaService: CategoriaService) {
  }

  ngOnInit(): void {
    this.get();
    this.getCategorias();
    this.id = this.activateRoute.snapshot.params['id'];
    if (this.id) {
      this.getById();
    }
  }

  public async get() {
    this.noticia$ = await lastValueFrom(this.noticiaService.get());
  }

  public async getById() {
    this.noticia = await lastValueFrom(this.noticiaService.getById(this.id));
    console.log(this.noticia);
    this.form.controls.id.setValue(this.noticia.id);
    this.form.controls.titulo.setValue(this.noticia.titulo);
    this.form.controls.corpo.setValue(this.noticia.corpo);
    this.form.controls.categoria.setValue(this.noticia.categoria?.id);
  }

  public async getCategorias(){
    this.cat$ = await lastValueFrom(this.categoriaService.get());
  }

  public salvar(): void {
        if (this.form.invalid) {
            this.form.markAllAsTouched();
            return;
        }

        const formValues = this.form.value;
        const noticia: Noticia = {
            id: this.id,
            titulo: formValues.titulo || '',
            corpo: formValues.corpo || '',
            categoria: formValues.categoria ? {
                id: formValues.categoria,
                nome: null,
                descricao: null,
                categoria: null
            } : undefined
        };

        console.log('Salvando notícia:', noticia);

        this.noticiaService.salvar(noticia).subscribe({
            next: (response) => {
                console.log('Notícia salva com sucesso:', response);
                this.router.navigate(['/admin']); // Navegação consistente
            },
            error: (erro) => {
                console.error('Erro ao salvar notícia:', erro);
                // Aqui você pode adicionar uma notificação de erro para o usuário
            }
        });
    }
}