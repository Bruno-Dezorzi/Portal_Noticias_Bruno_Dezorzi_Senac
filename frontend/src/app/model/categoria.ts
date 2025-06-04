export class Categoria {
    id!: number|null;
    nome!: string|null;
    descricao!: string|null;
    categoria?: Categoria|null;
}
