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
        newNode.next = pilha; // O novo nó aponta para o nó atual no topo
        pilha = newNode; // Atualiza o topo da pilha para o novo nó
    }

    public T desempilhar() {
        if (vazia()) {
            return null;
        }
        T data = pilha.data;
        pilha = pilha.next; // O topo agora aponta para o próximo nó
        return data;
    }

    public void limpar() {
        pilha = null;  
    }

    public boolean vazia() {
        return pilha == null;
    }
}
