package br.com.unesp.acesso_documentos_proxy.Interface;

import br.com.unesp.acesso_documentos_proxy.Models.Usuario;

/**
 * Subject do Proxy: contrato comum ao documento real e ao seu substituto,
 * então o cliente usa os dois do mesmo jeito.
 */
public interface Documento {
    /**
     * Retorna o nome do documento.
     *
     * @return o nome do documento
     */
    String getNome();
    /**
     * Informa se o documento é confidencial.
     *
     * @return {@code true} se só administradores podem acessar
     */
    boolean isConfidencial();
    /**
     * Exibe o conteúdo do documento para o usuário.
     *
     * @param usuario usuário que está acessando o documento
     */
    void exibir(Usuario usuario);
}
