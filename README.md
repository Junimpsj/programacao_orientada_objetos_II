# Programação Orientada a Objetos II

Repositório da disciplina Programação Orientada a Objetos II, em Java.

## Sobre o repositório

(ainda vou avaliar a disciplina para ver quais comentários virão aqui...)

Diferente de POO I, a disciplina agora tem um caráter mais prático e será avaliada em equipe. Por isso, a autoria e auditoria dos códigos e trabalhos desenvolvidos aqui serão em geral, coletiva pertencente ao grupo.

## Equipe

**Grupo 03**

- Arthur Vinicius Santos Silveira
- Miguel Garrocini Torres
- Guilherme Gomes da Silva Menezes
- Paulo Celso dos Santos Júnior

## Conteúdo

- [`exerciciosIniciaisTesteSoftware/`](exerciciosIniciaisTesteSoftware/): exercícios iniciais de teste de software propostos em aula, organizados por atividade
- [`atividadesIndividuais/`](atividadesIndividuais/): exercícios que eventualmente podem surgir e que serão individuais para os alunos, esses aqui serão densevolvidos apenas por mim
- [`avaliacoesParciais/`](avaliacoesParciais/): exercícios subitos que surgirão, quase como uma prova surpresa, a serem desenvolvidos pela equipe também

## Como executar

**Arquivos Java soltos** (exercícios e atividades):

```bash
cd <pasta_da_atividade>
javac NomeDoArquivo.java
java NomeDoArquivo
```

**Projetos Maven** (`atividadesIndividuais/` e `avaliacoesParciais/`):

```bash
cd <pasta_do_projeto>
mvn test
```

## Padrões do repositório

- Projetos Maven em Java 17, JUnit 5 (`junit-jupiter`) e `maven-surefire-plugin` declarado no `pom.xml`
- Todas as classes e métodos documentados com Javadoc
- Projetos que implementam um design pattern são nomeados como `nome_do_projeto_padrao` (ex.: `jogo_bridge`)

## Requisitos

- JDK 17+
- Maven 3.6.3+