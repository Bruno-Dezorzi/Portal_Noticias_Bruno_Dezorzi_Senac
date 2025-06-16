// cadastro-categorias.component.ts
import { Component, inject, OnInit } from '@angular/core';
import { CategoriaService } from '../../../service/categoria.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { Categoria } from '../../../model/categoria';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgFor, CommonModule } from '@angular/common';

@Component({
    selector: 'app-categoria-form',
    imports: [
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        CommonModule // Inclui NgFor e outras diretivas comuns
    ],
    templateUrl: './cadastro-categorias.component.html',
    styleUrl: './cadastro-categorias.component.css'
})
export class CategoriaFormComponent implements OnInit {

    categoria$: Categoria[] = [];
    categoria: Categoria | null = null;
    private activateRoute = inject(ActivatedRoute);
    id: number | null = null;

    form = new FormGroup({
        id: new FormControl<number | null>(null),
        nome: new FormControl<string>('', [Validators.required, Validators.minLength(2)]),
        descricao: new FormControl<string>('', [Validators.required]),
        categoria: new FormControl<number | null>(null)
    });

    constructor(
        private categoriaService: CategoriaService, 
        private router: Router
    ) {}

    async ngOnInit(): Promise<void> {
        await this.getCategorias();
        
        const idParam = this.activateRoute.snapshot.params['id'];
        if (idParam) {
            this.id = parseInt(idParam, 10);
            await this.getCategoriaById();
        }
    }

    public async getCategorias(): Promise<void> {
        try {
            this.categoria$ = await lastValueFrom(this.categoriaService.get());
        } catch (error) {
            console.error('Erro ao carregar categorias:', error);
        }
    }

    public async getCategoriaById(): Promise<void> {
        if (!this.id) return;
        
        try {
            this.categoria = await lastValueFrom(this.categoriaService.getById(this.id));
            
            if (this.categoria) {
                this.form.patchValue({
                    id: this.categoria.id,
                    nome: this.categoria.nome,
                    descricao: this.categoria.descricao,
                    categoria: this.categoria.categoria?.id || null
                });
            }
        } catch (error) {
            console.error('Erro ao carregar categoria:', error);
        }
    }

    public salvar(): void {
        if (this.form.invalid) {
            this.form.markAllAsTouched();
            return;
        }

        const formValues = this.form.value;
        const categoria: Categoria = {
            id: this.id,
            nome: formValues.nome || '',
            descricao: formValues.descricao || '',
            categoria: formValues.categoria ? {
                id: formValues.categoria,
                nome: null,
                descricao: null,
                categoria: null
            } : undefined
        };

        console.log('Salvando categoria:', categoria);

        this.categoriaService.salvar(categoria).subscribe({
            next: (response) => {
                console.log('Categoria salva com sucesso:', response);
                this.router.navigate(['/admin']); // Navegação consistente
            },
            error: (erro) => {
                console.error('Erro ao salvar categoria:', erro);
                // Aqui você pode adicionar uma notificação de erro para o usuário
            }
        });
    }

    public cancelar(): void {
        this.router.navigate(['/admin']);
    }

    // Método auxiliar para verificar erros de validação
    public hasError(controlName: string, errorType: string): boolean {
        const control = this.form.get(controlName);
        return !!(control?.hasError(errorType) && control?.touched);
    }
}