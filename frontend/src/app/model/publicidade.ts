export interface Publicidade {
  id: number;
  titulo: string;
  imagemUrl: string;
  linkDestino: string;
  dataInicio: string; // ou Date, dependendo do back-end
  dataFim: string;    // ou Date
  ativo: boolean;
  posicao: 'topo' | 'lateral' | string;
  categorias: string[];
}
