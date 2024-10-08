public class pilha<T> {
    private Node<T> pilha;

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public pilha() {
        pilha = null;
    }

    public void empilhar(T valor) {
        Node<T> newNode = new Node<>(valor);
        newNode.next = pilha; 
        pilha = newNode; 
    }

    public T desempilhar() {
        if (vazia()) {
            return null;
        }
        T data = pilha.data;
        pilha = pilha.next; 
        return data;
    }

    public void limpar() {
        pilha = null;  
    }

    public boolean vazia() {
        return pilha == null;
    }
}
