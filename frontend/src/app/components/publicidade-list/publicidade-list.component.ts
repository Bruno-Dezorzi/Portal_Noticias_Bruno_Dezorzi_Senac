import { Component, OnInit, OnDestroy } from '@angular/core';
import { Publicidade } from './../../model/publicidade';
import { PublicidadeService } from './../../service/publicidade.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-publicidade-list',
  templateUrl: './publicidade-list.component.html',
  styleUrls: ['./publicidade-list.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class PublicidadeListComponent implements OnInit, OnDestroy {
  publicidades: Publicidade[] = [];
  private subscription: Subscription = new Subscription();

  constructor(
    private publicidadeService: PublicidadeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getPublicidades();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  public getPublicidades(): void {
    const sub = this.publicidadeService.get().subscribe({
      next: (publicidades) => {
        this.publicidades = publicidades;
      },
      error: (error) => {
        console.error('Erro ao carregar publicidades:', error);
        this.publicidades = [];
      }
    });
    
    this.subscription.add(sub);
  }

  // Método para filtrar publicidades por posição
  public getPublicidadesPorPosicao(posicao: string): Publicidade[] {
    return this.publicidades.filter(pub => 
      pub.posicao === posicao && 
      this.isPublicidadeVigente(pub) && 
      pub.ativo
    );
  }

  // Método para verificar se a publicidade está vigente
  private isPublicidadeVigente(publicidade: Publicidade): boolean {
    const hoje = new Date();
    const dataInicio = new Date(publicidade.dataInicio);
    const dataFim = new Date(publicidade.dataFim);
    
    return hoje >= dataInicio && hoje <= dataFim;
  }

  // Método para navegação segura para links externos
  public abrirLink(url: string): void {
    if (url) {
      window.open(url, '_blank', 'noopener,noreferrer');
    }
  }
}