package listaDuplamenteEncadeada;

public class ListaDuplamenteEncadeada<T> {

    private NoDuplo<T> inicio;
    private NoDuplo<T> ultimo;
    private int tamanho = 0;

    private final int NAO_ENCONTRADO = -1;
    private final String NAO_EXISTE = "Posição não existe.";
    private final String LISTA_VAZIA = "Lista está vazia.";

    public void adiciona(T elemento) {
        NoDuplo<T> novoNo = new NoDuplo<>(elemento);
        if (this.tamanho == 0) {
            this.inicio = novoNo;
        } else {
            this.ultimo.setProximo(novoNo);
            novoNo.setAnterior(this.ultimo);
        }
        this.ultimo = novoNo;
        this.tamanho++;
    }

    public void adicionaInicio(T elemento) {
        NoDuplo<T> novoNo = new NoDuplo<>(elemento);
        if (this.tamanho == 0) {
            this.inicio = novoNo;
            this.ultimo = novoNo;
        } else {
            novoNo.setProximo(this.inicio);
            this.inicio.setAnterior(novoNo);
            this.inicio = novoNo;
        }
        this.tamanho++;
    }

    public void adiciona(int posicao, T elemento) {
        if (this.posicaoNaoExiste(posicao)) {
            throw new IllegalArgumentException(NAO_EXISTE);
        }

        if (posicao == 0) {
            this.adicionaInicio(elemento);
        } else if (posicao == this.tamanho) {
            this.adiciona(elemento);
        } else {
            NoDuplo<T> noAtual = this.buscaNo(posicao);
            NoDuplo<T> noAnterior = noAtual.getAnterior();
            NoDuplo<T> novoNo = new NoDuplo<>(elemento, noAtual, noAnterior);

            noAtual.setAnterior(novoNo);
            noAnterior.setProximo(novoNo);
            this.tamanho++;
        }
    }

    public T removeInicio() {
        if (this.tamanho == 0) {
            throw new RuntimeException(LISTA_VAZIA);
        }

        T removido = this.inicio.getElemento();
        this.inicio = this.inicio.getProximo();

        if (this.inicio != null) {
            this.inicio.setAnterior(null);
        } else {
            this.ultimo = null;
        }

        this.tamanho--;
        return removido;
    }

    public T removeFinal() {
        if (this.tamanho == 0) {
            throw new RuntimeException(LISTA_VAZIA);
        }

        T removido = this.ultimo.getElemento();
        this.ultimo = this.ultimo.getAnterior();

        if (this.ultimo != null) {
            this.ultimo.setProximo(null);
        } else {
            this.inicio = null;
        }

        this.tamanho--;
        return removido;
    }

    public T remove(int posicao) {
        if (this.posicaoNaoExiste(posicao)) {
            throw new IllegalArgumentException(NAO_EXISTE);
        }

        if (posicao == 0) {
            return this.removeInicio();
        }

        if (posicao == this.tamanho - 1) {
            return this.removeFinal();
        }

        NoDuplo<T> noRemover = this.buscaNo(posicao);
        NoDuplo<T> noAnterior = noRemover.getAnterior();
        NoDuplo<T> noProximo = noRemover.getProximo();

        noAnterior.setProximo(noProximo);
        noProximo.setAnterior(noAnterior);

        this.tamanho--;
        return noRemover.getElemento();
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public void limpa() {
        NoDuplo<T> atual = this.inicio;
        while (atual != null) {
            NoDuplo<T> proximo = atual.getProximo();
            atual.setElemento(null);
            atual.setProximo(null);
            atual.setAnterior(null);
            atual = proximo;
        }
        this.inicio = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    private NoDuplo<T> buscaNo(int posicao) {
        if (this.posicaoNaoExiste(posicao)) {
            throw new IllegalArgumentException(NAO_EXISTE);
        }

        NoDuplo<T> noAtual = this.inicio;
        for (int i = 0; i < posicao; i++) {
            noAtual = noAtual.getProximo();
        }

        return noAtual;
    }

    private boolean posicaoNaoExiste(int posicao) {
        return !(posicao >= 0 && posicao < this.tamanho);
    }

    public T buscaPorPosicao(int posicao) {
        return this.buscaNo(posicao).getElemento();
    }

    public int busca(T elemento) {
        NoDuplo<T> noAtual = this.inicio;
        int pos = 0;

        while (noAtual != null) {
            if (noAtual.getElemento().equals(elemento)) {
                return pos;
            }
            pos++;
            noAtual = noAtual.getProximo();
        }

        return NAO_ENCONTRADO;
    }

    public void inverte() {
        NoDuplo<T> atual = this.inicio;
        NoDuplo<T> temp = null;

        while (atual != null) {
            temp = atual.getAnterior();
            atual.setAnterior(atual.getProximo());
            atual.setProximo(temp);
            atual = atual.getAnterior();
        }

        if (temp != null) {
            this.inicio = temp.getAnterior();
        }
    }

    @Override
    public String toString() {
        if (this.tamanho == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder("[");
        NoDuplo<T> atual = this.inicio;

        while (atual != null) {
            builder.append(atual.getElemento());
            atual = atual.getProximo();
            if (atual != null) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}