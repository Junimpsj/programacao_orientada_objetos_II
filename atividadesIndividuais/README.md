# Atividades Individuais

Sobre essa pasta, contrariando o que o README principal diz, aqui será desenvolvido apenas algumas atividades/trabalhos individuais que aparecerem durante a disciplina. Tendo apenas eu como responsável.

## Projetos

| Projeto | Descrição | Padrão |
|---|---|---|
| [`acesso_documentos_proxy`](acesso_documentos_proxy/) | Acesso a documentos públicos e confidenciais com controle de permissão e carregamento sob demanda | Proxy |
| [`agencia_viagens_facade`](agencia_viagens_facade/) | Contratação, consulta e cancelamento de pacotes de viagem | Facade |
| [`checkout_ecommerce_abstract_factory`](checkout_ecommerce_abstract_factory/) | Checkout com imposto e frete por país | Abstract Factory |
| [`editor_documento_command`](editor_documento_command/) | Substituição de texto em documento com botões de executar e desfazer | Command |
| [`jogo_bridge`](jogo_bridge/) | Personagens e armas combináveis em tempo de execução | Bridge |
| [`loja_virtual_decorator`](loja_virtual_decorator/) | Serviços adicionais combináveis sobre o preço de um produto | Decorator |
| [`loja_virtual_factory`](loja_virtual_factory/) | Criação de formas de pagamento por tipo | Factory |
| [`loja_virtual_singleton_prototype`](loja_virtual_singleton_prototype/) | Configuração única da loja e clonagem de produtos | Singleton e Prototype |
| [`mapa_parque_flyweight`](mapa_parque_flyweight/) | Árvores de um parque compartilhando espécie, formato e cor | Flyweight |
| [`pagamento_adapter`](pagamento_adapter/) | Integração da API SuperPay ao contrato de pagamento do sistema | Adapter |
| [`sistema_arquivos_composite`](sistema_arquivos_composite/) | Hierarquia de arquivos e pastas com cálculo de tamanho | Composite |
| [`sistema_relatorios_factory_method`](sistema_relatorios_factory_method/) | Geração de relatórios em PDF e Excel | Factory Method |
| [`validacao_pedidos_chain`](validacao_pedidos_chain/) | Validação de estoque, pagamento e endereço antes de liberar o pedido | Chain of Responsibility |
| [`validador_codigos_interpreter`](validador_codigos_interpreter/) | Validação de códigos pela gramática `A(B\|C)*J` | Interpreter |
| [`exemplo_interface`](exemplo_interface/) | Contas corrente e poupança com interface de operações bancárias | - |
| [`logistica_commerce`](logistica_commerce/) | Bônus de entregadores de moto, carro e bike | - |
| [`polinomio`](polinomio/) | Termos e polinômios com `BigDecimal` | - |

Projetos que implementam um design pattern seguem o nome `nome_do_projeto_padrao`.

## Como executar

Cada pasta é um projeto Maven; a classe principal fica no pacote `Aplication`/`application`/`app` (ex.: `App`).

```bash
cd <pasta_do_projeto>
mvn compile exec:java -Dexec.mainClass=<pacote.App>
```

## Como testar

Todos os projetos já declaram o JUnit 5 e o plugin Surefire no `pom.xml`:

```bash
mvn test
```

## Requisitos

- JDK 17+
- Maven 3.6.3+