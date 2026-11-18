package br.com.unesp.acesso_documentos_proxy.Models;

import br.com.unesp.acesso_documentos_proxy.Interface.Documento;

/**
 * RealSubject do Proxy: o documento de fato, cujo carregamento é custoso.
 * Não verifica permissões, isso é responsabilidade do proxy.
 */
public class DocumentoReal implements Documento {

    private final String nome;
    private final boolean confidencial;

    /**
     * Carrega o documento. É a operação custosa que o proxy adia.
     *
     * @param nome nome do documento
     * @param confidencial se o documento é confidencial
     */
    // o carregamento pesado acontece no construtor, então criar o objeto já é o custo que o proxy quer evitar
    public DocumentoReal(String nome, boolean confidencial) {
        this.nome = nome;
        this.confidencial = confidencial;
        System.out.println("[DOCUMENTO REAL] Carregamento concluído.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConfidencial() {
        return confidencial;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exibir(Usuario usuario) {
        System.out.println("[DOCUMENTO REAL] Exibindo conteúdo do documento.");
    }
}
