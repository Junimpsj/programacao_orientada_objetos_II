package br.com.unesp.validacao_pedidos_chain.Models;

/**
 * Produto de um pedido da loja virtual. Guarda as informações que cada etapa
 * da cadeia de validação verifica: estoque, pagamento e endereço de entrega.
 */
// é o "request" do padrão, o objeto que vai passando de etapa em etapa
public class Produto {

    private String nome;
    private boolean emEstoque;
    private boolean pagamentoAprovado;
    private boolean enderecoInformado;

    /**
     * Cria um produto.
     *
     * @param nome nome do produto
     * @param emEstoque se o produto está disponível em estoque
     * @param pagamentoAprovado se o pagamento foi aprovado
     * @param enderecoInformado se o endereço de entrega foi informado corretamente
     * @throws IllegalArgumentException se o nome for nulo ou vazio
     */
    public Produto(String nome, boolean emEstoque, boolean pagamentoAprovado, boolean enderecoInformado) {
        setNome(nome);
        this.emEstoque = emEstoque;
        this.pagamentoAprovado = pagamentoAprovado;
        this.enderecoInformado = enderecoInformado;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return o nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome do produto.
     *
     * @param nome novo nome do produto
     * @throws IllegalArgumentException se o nome for nulo ou vazio
     */
    public void setNome(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }

        this.nome = nome;
    }

    /**
     * Informa se o produto está disponível em estoque.
     *
     * @return {@code true} se há estoque
     */
    public boolean isEmEstoque() {
        return emEstoque;
    }

    /**
     * Altera a disponibilidade em estoque.
     *
     * @param emEstoque se o produto está disponível
     */
    public void setEmEstoque(boolean emEstoque) {
        this.emEstoque = emEstoque;
    }

    /**
     * Informa se o pagamento foi aprovado.
     *
     * @return {@code true} se o pagamento foi aprovado
     */
    public boolean isPagamentoAprovado() {
        return pagamentoAprovado;
    }

    /**
     * Altera a situação do pagamento.
     *
     * @param pagamentoAprovado se o pagamento foi aprovado
     */
    public void setPagamentoAprovado(boolean pagamentoAprovado) {
        this.pagamentoAprovado = pagamentoAprovado;
    }

    /**
     * Informa se o endereço de entrega foi informado corretamente.
     *
     * @return {@code true} se o endereço foi informado
     */
    public boolean isEnderecoInformado() {
        return enderecoInformado;
    }

    /**
     * Altera a situação do endereço de entrega.
     *
     * @param enderecoInformado se o endereço foi informado
     */
    public void setEnderecoInformado(boolean enderecoInformado) {
        this.enderecoInformado = enderecoInformado;
    }
}
