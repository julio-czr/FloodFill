public class fila<T> {
    private int top;
    private int base;
    private int preenchimento;// guarda o fill da fila
    private int tamanho;
    private T[] data;

    public fila(int tamanho){
        this.data = (T[]) new Object[tamanho];
        this.base = 0;
        this.top=-1;
        this.preenchimento=0;
        this.tamanho=tamanho;
    }

    public void adicionar(T item){
    
        top=mover(top);//move o topo da fila
        data[top]=item;//insere o elemento
        if (preenchimento<tamanho) preenchimento++;//adiciona ao fill
    }

    public T remover(){
        if (vazia()) {
            throw new IllegalStateException("Fila vazia.");
        }
        T response = data[base];//salva o elemento
        data[base]=null;//remove da fila
        base=mover(base);//move a base
        preenchimento--;//remove do fill
        return response;//retorna o elemento

    }
    public void limpar(){
        //volta a fila a configuração inicial
        data = (T[]) new Object[tamanho];
        top = -1;
        base = 0;
        preenchimento =0;
    }
    
    public boolean cheia(){
        return preenchimento==tamanho;
    }
    public boolean vazia(){
        return preenchimento==0;
        
    }
    public int mover(int indice){
        indice= (indice+1)%tamanho;
        return indice;
    }

    public T elemento() {
        if (vazia()) {
            System.out.println("Fila vazia.");
            return null;
        }
        return data[base];
    }

    public int tamanho(){
        return tamanho;
    }

    public int fill(){
        return preenchimento;
    }

}