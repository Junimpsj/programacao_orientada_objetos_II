package br.com.unesp.mapa_parque_flyweight.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * FlyweightFactory: cria e guarda os {@link TipoArvore}, devolvendo o objeto já
 * existente quando as características se repetem.
 */
// factory do flyweight, é ela que garante que não existam dois TipoArvore iguais
public class ArvoreFactory {

    private final Map<String, TipoArvore> tipos = new HashMap<>();

    /**
     * Retorna o tipo de árvore com as características informadas, criando-o só se ainda não existir.
     *
     * @param especie espécie da árvore
     * @param formato formato da copa
     * @param cor cor predominante
     * @return o tipo de árvore compartilhado
     * @throws IllegalArgumentException se algum dos dados for nulo ou vazio
     */
    // a chave junta as três características, mesma espécie com outra cor vira outro objeto
    public TipoArvore obterTipo(String especie, String formato, String cor) {
        String chave = especie + "|" + formato + "|" + cor;
        TipoArvore tipo = tipos.get(chave);

        if (tipo == null) {
            tipo = new TipoArvore(especie, formato, cor);
            tipos.put(chave, tipo);
            System.out.println("[NOVA ÁRVORE] " + tipo);
        } else {
            System.out.println("[REUTILIZANDO] " + tipo);
        }

        return tipo;
    }

    /**
     * Retorna quantos objetos de tipo de árvore foram efetivamente criados.
     *
     * @return a quantidade de objetos criados
     */
    public int getQuantidadeTipos() {
        return tipos.size();
    }
}
