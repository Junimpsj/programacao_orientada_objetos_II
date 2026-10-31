# Avaliações Parciais

Aqui estarão os código de algumas "avaliações parciais" da disciplina que descobrimos da melhor forma possível (contém ironia) que seriam "avaliações supresas". Me senti na Codecon.

## Conteúdo

- [`avalParcial1/compras_mercado`](avalParcial1/compras_mercado/): pedido de compras de mercado com produtos, itens e formas de pagamento
- [`avalParcial1/concurso_vendas`](avalParcial1/concurso_vendas/): apuração de vendas de um concurso com participantes padrão e sênior
- [`revisaoFinalPOOII/`](revisaoFinalPOOII/): enunciado (PDF) da revisão final, sem projeto entregue (veja a `observacao.txt`)

## Como executar

Cada projeto é Maven; a execução é feita pelos testes (veja abaixo).

## Como testar

Todos os projetos já declaram o JUnit 5 e o plugin Surefire no `pom.xml`:

```bash
mvn test
```

## Requisitos

- JDK 17+
- Maven 3.6.3+