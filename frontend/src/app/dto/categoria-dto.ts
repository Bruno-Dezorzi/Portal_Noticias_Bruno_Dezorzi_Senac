export class CategoriaDTO {
    nome!: string;
    descricao!: string;
    categoriaPaiId?: number | null;

    constructor(nome?: string, descricao?: string, categoriaPaiId?: number | null) {
        this.nome = nome || '';
        this.descricao = descricao || '';
        this.categoriaPaiId = categoriaPaiId || null;
    }
}