package br.com.unesp.sistema_arquivos_composite.Aplication;

import br.com.unesp.sistema_arquivos_composite.Composite.Pasta;
import br.com.unesp.sistema_arquivos_composite.Models.Arquivo;

/**
 * Ponto de entrada que demonstra o padrão Composite: monta uma hierarquia
 * de arquivos e pastas, exibe a estrutura, calcula o tamanho e remove um elemento.
 */
public class App {

    /**
     * Monta a estrutura de pastas, exibe e calcula o tamanho antes e depois de uma remoção.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Pasta documentos = new Pasta("documentos");
        Pasta faculdade = new Pasta("faculdade");
        Pasta poo2 = new Pasta("poo2");
        Pasta fotos = new Pasta("fotos");

        // documentos -> faculdade -> poo2, dois níveis de subpastas
        poo2.adicionar(new Arquivo("atividade_composite.pdf", 80));
        poo2.adicionar(new Arquivo("atividade_decorator.pdf", 76));

        faculdade.adicionar(poo2);
        faculdade.adicionar(new Arquivo("calendario.png", 250));

        fotos.adicionar(new Arquivo("praia.jpg", 2048));

        documentos.adicionar(new Arquivo("curriculo.pdf", 120));
        documentos.adicionar(faculdade);
        documentos.adicionar(fotos);

        System.out.println("=== ESTRUTURA ===");
        documentos.exibir(0);
        System.out.println("Tamanho total de " + documentos.getNome() + ": " + documentos.getTamanho() + " KB");

        System.out.println("=== REMOVENDO A PASTA " + fotos.getNome() + " ===");
        documentos.remover(fotos);

        documentos.exibir(0);
        System.out.println("Tamanho total de " + documentos.getNome() + ": " + documentos.getTamanho() + " KB");
    }
}
