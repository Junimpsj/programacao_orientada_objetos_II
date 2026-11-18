package br.com.unesp.acesso_documentos_proxy.Models;

/**
 * Usuário do sistema de documentos, identificado pelo nome e pelo perfil de acesso.
 */
public class Usuario {

    /**
     * Perfis de acesso, cada um com a descrição exibida no sistema.
     */
    public enum Perfil {
        /** Acessa apenas documentos públicos. */
        COMUM("Usuário comum"),
        /** Acessa documentos públicos e confidenciais. */
        ADMINISTRADOR("Administrador");

        private final String descricao;

        Perfil(String descricao) {
            this.descricao = descricao;
        }

        /**
         * Retorna a descrição do perfil.
         *
         * @return a descrição exibida no sistema
         */
        public String getDescricao() {
            return descricao;
        }
    }

    private final String nome;
    private final Perfil perfil;

    /**
     * Cria um usuário.
     *
     * @param nome nome do usuário
     * @param perfil perfil de acesso
     * @throws IllegalArgumentException se o nome for nulo/vazio ou o perfil nulo
     */
    public Usuario(String nome, Perfil perfil) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }

        if (perfil == null) {
            throw new IllegalArgumentException("O perfil do usuário não pode ser nulo.");
        }

        this.nome = nome;
        this.perfil = perfil;
    }

    /**
     * Retorna o nome do usuário.
     *
     * @return o nome do usuário
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o perfil do usuário.
     *
     * @return o perfil de acesso
     */
    public Perfil getPerfil() {
        return perfil;
    }
}
