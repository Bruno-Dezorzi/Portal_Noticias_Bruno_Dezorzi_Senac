import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NoticiaService } from '../../../service/noticia.service';
import { CategoriaService } from '../../../service/categoria.service';
import { Noticia } from '../../../model/noticia';

@Component({
  standalone: true,
  selector: 'app-noticia-form',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  templateUrl: './cadastro-noticias.component.html',
  styleUrl: './cadastro-noticias.component.css'
})
export class NoticiaFormComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private noticiaService = inject(NoticiaService);
  private categoriaService = inject(CategoriaService);

  categorias: any[] = [];
  noticiaId: number | null = null;
  isEdicao = false;
  salvando = false;

  form = new FormGroup({
    id: new FormControl<number | null>(null),
    titulo: new FormControl<string>('', [Validators.required, Validators.minLength(3)]),
    descricao: new FormControl<string>('', [Validators.required, Validators.minLength(10)]),
    dataPublicacao: new FormControl<string>('', Validators.required),
    categoria: new FormControl<number | null>(null, Validators.required)
  });

  ngOnInit(): void {
    this.noticiaId = this.route.snapshot.params['id'] ? +this.route.snapshot.params['id'] : null;
    this.isEdicao = !!this.noticiaId;
    
    this.carregarCategorias();
    
    if (this.isEdicao) {
      this.carregarNoticia();
    } else {
      // Para novos registros, define data atual
      this.form.patchValue({
        dataPublicacao: new Date().toISOString().split('T')[0]
      });
    }
  }

  private async carregarCategorias(): Promise<void> {
    try {
      this.categorias = await this.categoriaService.get().toPromise() || [];
    } catch (error) {
      console.error('Erro ao carregar categorias:', error);
      this.mostrarErro('Erro ao carregar categorias');
    }
  }

  private async carregarNoticia(): Promise<void> {
    if (!this.noticiaId) return;

    try {
      const noticia = await this.noticiaService.getById(this.noticiaId).toPromise();
      
      if (noticia) {
        this.form.patchValue({
          id: noticia.id,
          titulo: noticia.titulo || '',
          descricao: noticia.descricao || '',
          dataPublicacao: this.formatarDataParaInput(noticia.dataPublicacao),
          categoria: noticia.categoria?.id || null
        });
      }
    } catch (error) {
      console.error('Erro ao carregar notícia:', error);
      this.mostrarErro('Erro ao carregar notícia');
      this.router.navigate(['/noticia']);
    }
  }

  private formatarDataParaInput(data: any): string {
    if (!data) return '';
    
    const date = new Date(data);
    return date.toISOString().split('T')[0];
  }

  async salvar(): Promise<void> {
    if (this.form.invalid) {
      this.marcarCamposComoTocados();
      return;
    }

    this.salvando = true;

    try {
      const formData = this.form.value;
      
      const noticia: Noticia = {
        id: this.isEdicao ? this.noticiaId : null,
        titulo: formData.titulo!,
        descricao: formData.descricao!,
        dataPublicacao: new Date(formData.dataPublicacao!),
        categoria: {
          id: formData.categoria!,
          nome: null,
          descricao: null,
          categoria: null
        },
        autor: {
          id: 1, // Você pode ajustar isso conforme sua lógica de autenticação
          nome: null,
          email: null,
          biografia: null
        }
      };

      await this.noticiaService.salvar(noticia).toPromise();
      
      this.mostrarSucesso(this.isEdicao ? 'Notícia atualizada com sucesso!' : 'Notícia criada com sucesso!');
      this.router.navigate(['/noticia']);
      
    } catch (error) {
      console.error('Erro ao salvar notícia:', error);
      this.mostrarErro('Erro ao salvar notícia. Tente novamente.');
    } finally {
      this.salvando = false;
    }
  }

  private marcarCamposComoTocados(): void {
    Object.keys(this.form.controls).forEach(key => {
      this.form.get(key)?.markAsTouched();
    });
  }

  private mostrarSucesso(mensagem: string): void {
    // Implementar notificação de sucesso (toast/alert)
    console.log('Sucesso:', mensagem);
  }

  private mostrarErro(mensagem: string): void {
    // Implementar notificação de erro (toast/alert)
    console.error('Erro:', mensagem);
  }
}