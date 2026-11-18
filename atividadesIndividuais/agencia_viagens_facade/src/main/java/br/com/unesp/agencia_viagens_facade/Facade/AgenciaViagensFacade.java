package br.com.unesp.agencia_viagens_facade.Facade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import br.com.unesp.agencia_viagens_facade.Models.ServicoHospedagem;
import br.com.unesp.agencia_viagens_facade.Models.ServicoPassagemAerea;
import br.com.unesp.agencia_viagens_facade.Models.ServicoTransporte;

/**
 * Facade: oferece ao cliente uma interface simplificada para contratar,
 * consultar e cancelar um pacote de viagem completo, delegando cada etapa
 * aos serviços do subsistema.
 */
// facade, o cliente só conversa com ela e não precisa saber quais serviços existem nem a ordem das chamadas
public class AgenciaViagensFacade {

    private static final String LINHA = "----------------------------------------";
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final ServicoPassagemAerea passagemAerea = new ServicoPassagemAerea();
    private final ServicoHospedagem hospedagem = new ServicoHospedagem();
    private final ServicoTransporte transporte = new ServicoTransporte();

    private final Random random;
    private final Set<Integer> codigos = new HashSet<>();

    /**
     * Cria a facade gerando os códigos de reserva com um {@link Random} comum.
     */
    public AgenciaViagensFacade() {
        this(new Random());
    }

    /**
     * Cria a facade com o {@link Random} informado.
     *
     * @param random gerador dos códigos de reserva
     */
    // recebe o Random de fora pra o teste conseguir prever o código gerado
    public AgenciaViagensFacade(Random random) {
        this.random = random;
    }

    /**
     * Contrata o pacote completo: passagem, hospedagem e transporte, nessa ordem.
     *
     * @param destino destino da viagem
     * @param dataIda data de ida
     * @param dataRetorno data de retorno
     * @param passageiros quantidade de passageiros
     * @return o código da reserva, com 4 dígitos
     * @throws IllegalArgumentException se o destino for vazio, as datas forem nulas,
     *                                  o retorno for antes da ida ou não houver passageiros
     */
    public int contratarPacote(String destino, LocalDate dataIda, LocalDate dataRetorno, int passageiros) {

        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("O destino não pode ser vazio.");
        }

        if (dataIda == null || dataRetorno == null) {
            throw new IllegalArgumentException("As datas de ida e retorno são obrigatórias.");
        }

        if (dataRetorno.isBefore(dataIda)) {
            throw new IllegalArgumentException("A data de retorno não pode ser antes da data de ida.");
        }

        if (passageiros <= 0) {
            throw new IllegalArgumentException("A quantidade de passageiros deve ser maior que zero.");
        }

        int codigo = gerarCodigo();

        System.out.println("Destino: " + destino);
        System.out.println("Data de ida: " + dataIda.format(FORMATO));
        System.out.println("Data de retorno: " + dataRetorno.format(FORMATO));
        System.out.println("Quantidade de passageiros: " + passageiros);

        passagemAerea.reservar(codigo, destino, passageiros);
        hospedagem.reservar(codigo, destino, dataIda, dataRetorno, passageiros);
        transporte.reservar(codigo, passageiros);

        System.out.println(LINHA);
        System.out.println("PACOTE DE VIAGEM CONTRATADO COM SUCESSO!");
        System.out.println("Código da reserva: " + codigo);
        return codigo;
    }

    /**
     * Consulta os serviços contratados na reserva.
     *
     * @param codigo código da reserva
     * @throws IllegalArgumentException se a reserva não existir
     */
    public void consultarPacote(int codigo) {
        validar(codigo);

        System.out.println("Código da reserva: " + codigo);
        passagemAerea.consultar(codigo);
        hospedagem.consultar(codigo);
        transporte.consultar(codigo);
    }

    /**
     * Cancela a reserva acionando os três serviços, na ordem inversa da contratação.
     *
     * @param codigo código da reserva
     * @throws IllegalArgumentException se a reserva não existir
     */
    // cancela do último para o primeiro, transporte e hotel dependem da passagem
    public void cancelarPacote(int codigo) {
        validar(codigo);

        System.out.println("Código da reserva: " + codigo);
        transporte.cancelar(codigo);
        hospedagem.cancelar(codigo);
        passagemAerea.cancelar(codigo);
        codigos.remove(codigo);

        System.out.println(LINHA);
        System.out.println("PACOTE DE VIAGEM CANCELADO COM SUCESSO!");
    }

    /**
     * Gera um código de 4 dígitos (1000 a 9999) que ainda não esteja em uso.
     *
     * @return o código gerado
     */
    // sorteia de novo se cair num código que já existe, senão duas reservas ficariam com o mesmo código
    private int gerarCodigo() {
        int codigo;

        do {
            codigo = 1000 + random.nextInt(9000);
        } while (codigos.contains(codigo));

        codigos.add(codigo);
        return codigo;
    }

    /**
     * Garante que a reserva exista.
     *
     * @param codigo código da reserva
     * @throws IllegalArgumentException se a reserva não existir
     */
    private void validar(int codigo) {

        if (!codigos.contains(codigo)) {
            throw new IllegalArgumentException("Reserva " + codigo + " não encontrada.");
        }
    }
}
