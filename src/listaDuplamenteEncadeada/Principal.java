package listaDuplamenteEncadeada;

public class Principal {
    public static void main(String[] args) {
        ListaDuplamenteEncadeada<Integer> lista = new ListaDuplamenteEncadeada<>();

        // Testando o método adiciona
        lista.adiciona(10);
        lista.adiciona(20);
        lista.adiciona(30);
        System.out.println("Após adicionar elementos: " + lista);

        // Testando o método adicionaInicio
        lista.adicionaInicio(5);
        System.out.println("Após adicionar no início: " + lista);

        // Testando o método adiciona em posição específica
        lista.adiciona(2, 15);
        System.out.println("Após adicionar na posição 2: " + lista);

        // Testando o método removeInicio
        lista.removeInicio();
        System.out.println("Após remover do início: " + lista);

        // Testando o método removeFinal
        lista.removeFinal();
        System.out.println("Após remover do final: " + lista);

        // Testando o método remove em posição específica
        lista.remove(1);
        System.out.println("Após remover da posição 1: " + lista);

        // Testando o método buscaPorPosicao
        int elemento = lista.buscaPorPosicao(1);
        System.out.println("Elemento na posição 1: " + elemento);

        // Testando o método busca
        int posicao = lista.busca(20);
        System.out.println("Posição do elemento 20: " + posicao);

        // Testando o método inverte
        lista.inverte();
        System.out.println("Após inverter a lista: " + lista);

        // Testando o método limpa
        lista.limpa();
        System.out.println("Após limpar a lista: " + lista);
    }
}