package br.unesp.loja_virtual.model;

/**
 * Singleton com as configurações globais da loja (nome e desconto padrão).
 */
public class Configuracao {

    /**
     * Única instância da classe.
     */
    private static Configuracao instancia;
    private String nomeLoja;
    private double descontoPadrao;

    /**
     * Construtor privado, impede a criação de instâncias fora da classe.
     */
    private Configuracao() {
    }

    /**
     * Retorna a instância única, criando-a no primeiro acesso.
     *
     * @return a instância única de {@link Configuracao}
     */
    public static Configuracao getInstance() {

        if (instancia == null) {
            instancia = new Configuracao();
        }

        return instancia;
    }

    /**
     * Retorna o nome da loja.
     *
     * @return o nome da loja
     */
    public String getNomeLoja() {
        return nomeLoja;
    }

    /**
     * Define o nome da loja.
     *
     * @param nomeLoja novo nome da loja
     */
    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    /**
     * Retorna o desconto padrão da loja.
     *
     * @return o desconto padrão, em porcentagem
     */
    public double getDescontoPadrao() {
        return descontoPadrao;
    }

    /**
     * Define o desconto padrão da loja.
     *
     * @param descontoPadrao novo desconto padrão, em porcentagem
     */
    public void setDescontoPadrao(double descontoPadrao) {
        this.descontoPadrao = descontoPadrao;
    }
}