package br.com.unesp.acesso_documentos_proxy.Proxy;

import br.com.unesp.acesso_documentos_proxy.Interface.Documento;
import br.com.unesp.acesso_documentos_proxy.Models.DocumentoReal;
import br.com.unesp.acesso_documentos_proxy.Models.Usuario;

/**
 * Proxy: substituto do {@link DocumentoReal} que verifica as permissões do usuário
 * (proxy de proteção) e só carrega o documento no primeiro acesso autorizado,
 * reutilizando a mesma instância depois (proxy virtual).
 */
// proxy, implementa a mesma interface do documento real então o cliente nem percebe que está falando com um substituto
public class DocumentoProxy implements Documento {

    private final String nome;
    private final boolean confidencial;

    // fica null até o primeiro acesso autorizado
    private DocumentoReal documentoReal;

    /**
     * Cria o proxy sem carregar o documento.
     *
     * @param nome nome do documento
     * @param confidencial se o documento é confidencial
     * @throws IllegalArgumentException se o nome for nulo ou vazio
     */
    public DocumentoProxy(String nome, boolean confidencial) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do documento não pode ser vazio.");
        }

        this.nome = nome;
        this.confidencial = confidencial;
    }

    /**
     * {@inheritDoc}
     */
    // nome e tipo ficam no proxy, então dá pra consultar sem carregar o documento
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
     * Verifica a permissão do usuário e, se autorizado, carrega o documento
     * (apenas no primeiro acesso) e delega a exibição ao documento real.
     *
     * @param usuario usuário que está acessando o documento
     * @throws IllegalArgumentException se o usuário for nulo
     */
    @Override
    public void exibir(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }

        System.out.println("[PROXY] Verificando permissões...");

        // documento confidencial só para administrador
        if (confidencial && usuario.getPerfil() != Usuario.Perfil.ADMINISTRADOR) {
            System.out.println("[PROXY] Acesso negado!");
            System.out.println("O usuário não possui autorização para acessar este documento.");
            return;
        }

        System.out.println("[PROXY] Acesso autorizado.");

        if (documentoReal == null) {
            System.out.println("[PROXY] Carregando documento...");
            System.out.println();
            documentoReal = new DocumentoReal(nome, confidencial);
        } else {
            System.out.println("[PROXY] Documento já carregado. Reutilizando instância.");
            System.out.println();
        }

        documentoReal.exibir(usuario);
    }
}
