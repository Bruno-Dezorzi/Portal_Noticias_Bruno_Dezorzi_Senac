import { Component, ElementRef, ViewChild, AfterViewInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

interface Stats {
  totalNews: number;
  activeCategories: number;
  registeredUsers: number;
  todayViews: number;
}

interface Activity {
  title: string;
  description: string;
  time: string;
}

interface News {
  id: number;
  title: string;
  category: string;
  date: Date;
  status: string;
  views: number;
  image?: string;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard-adm.component.html',
  styleUrls: ['./dashboard-adm.component.css']
})
export class DashboardAdmComponent implements AfterViewInit, OnDestroy {
  @ViewChild('viewsChart', { static: false }) viewsChartRef!: ElementRef<HTMLCanvasElement>;
  @ViewChild('categoriesChart', { static: false }) categoriesChartRef!: ElementRef<HTMLCanvasElement>;

  private viewsChart?: Chart;
  private categoriesChart?: Chart;
  private updateInterval?: number;

  stats: Stats = {
    totalNews: 127,
    activeCategories: 8,
    registeredUsers: 1847,
    todayViews: 15742
  };

  recentActivities: Activity[] = [
    {
      title: 'Nova notícia publicada',
      description: 'Artigo "Copa do Mundo 2024" foi publicado na categoria Esportes',
      time: 'Há 2 horas'
    },
    {
      title: 'Usuário registrado',
      description: 'João Silva se registrou no portal',
      time: 'Há 4 horas'
    },
    {
      title: 'Comentário moderado',
      description: 'Comentário removido por violação das regras',
      time: 'Há 6 horas'
    },
    {
      title: 'Categoria criada',
      description: 'Nova categoria "Tecnologia" foi adicionada',
      time: 'Ontem'
    },
    {
      title: 'Backup realizado',
      description: 'Backup automático do sistema executado com sucesso',
      time: 'Ontem'
    }
  ];

  latestNews: News[] = [
    {
      id: 1,
      title: 'Copa do Mundo 2024 inicia amanhã',
      category: 'Esportes',
      date: new Date('2024-05-22'),
      status: 'Publicado',
      views: 1250,
      image: '/api/placeholder/50/50'
    },
    {
      id: 2,
      title: 'Novos investimentos em tecnologia',
      category: 'Economia',
      date: new Date('2024-05-21'),
      status: 'Rascunho',
      views: 0,
      image: '/api/placeholder/50/50'
    },
    {
      id: 3,
      title: 'Festival de cinema internacional',
      category: 'Lazer',
      date: new Date('2024-05-20'),
      status: 'Publicado',
      views: 890,
      image: '/api/placeholder/50/50'
    }
  ];

  ngAfterViewInit(): void {
    this.initializeCharts();
    this.startAutoUpdate();
  }

  ngOnDestroy(): void {
    if (this.updateInterval) {
      clearInterval(this.updateInterval);
    }
    if (this.viewsChart) {
      this.viewsChart.destroy();
    }
    if (this.categoriesChart) {
      this.categoriesChart.destroy();
    }
  }

  private initializeCharts(): void {
    this.createViewsChart();
    this.createCategoriesChart();
  }

  private createViewsChart(): void {
    const ctx = this.viewsChartRef.nativeElement.getContext('2d');
    if (ctx) {
      this.viewsChart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: ['16/05', '17/05', '18/05', '19/05', '20/05', '21/05', '22/05'],
          datasets: [{
            label: 'Visualizações',
            data: [12000, 14500, 13200, 15800, 16200, 14900, 15742],
            borderColor: '#0d6efd',
            backgroundColor: 'rgba(13, 110, 253, 0.1)',
            tension: 0.4,
            fill: true
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          scales: {
            y: {
              beginAtZero: true
            }
          },
          plugins: {
            legend: {
              display: false
            }
          }
        }
      });
    }
  }

  private createCategoriesChart(): void {
    const ctx = this.categoriesChartRef.nativeElement.getContext('2d');
    if (ctx) {
      this.categoriesChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['Esportes', 'Política', 'Economia', 'Lazer', 'Internacional'],
          datasets: [{
            data: [25, 20, 18, 15, 12],
            backgroundColor: [
              '#28a745',
              '#dc3545',
              '#ffc107',
              '#17a2b8',
              '#6f42c1'
            ]
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              position: 'bottom'
            }
          }
        }
      });
    }
  }

  private startAutoUpdate(): void {
    this.updateInterval = window.setInterval(() => {
      console.log('Atualizando dashboard...');
      // Aqui você pode implementar a lógica para atualizar os dados
      // Por exemplo, fazer uma chamada HTTP para buscar novos dados
    }, 30000); // Atualiza a cada 30 segundos
  }

  getCategoryBadgeClass(category: string): string {
    const classes: { [key: string]: string } = {
      'Esportes': 'bg-success',
      'Economia': 'bg-info',
      'Lazer': 'bg-primary',
      'Política': 'bg-warning',
      'Internacional': 'bg-secondary'
    };
    return classes[category] || 'bg-secondary';
  }

  getStatusBadgeClass(status: string): string {
    const classes: { [key: string]: string } = {
      'Publicado': 'bg-success',
      'Rascunho': 'bg-warning',
      'Rejeitado': 'bg-danger'
    };
    return classes[status] || 'bg-secondary';
  }

  editNews(id: number): void {
    console.log('Editando notícia:', id);
    // Implementar lógica de edição
  }

  deleteNews(id: number): void {
    console.log('Excluindo notícia:', id);
    // Implementar lógica de exclusão
  }
}
