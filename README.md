#  Consulta Tabela FIPE

Aplicação Java desenvolvida com Spring Boot que consome a API pública da [Tabela FIPE](https://parallelum.com.br/fipe/api/v1/) para consultar valores de mercado de veículos (carros, motos e caminhões) diretamente pelo terminal.

##  Sobre o projeto

O usuário escolhe o tipo de veículo (carro, moto ou caminhão), navega pela lista de marcas disponíveis, seleciona uma marca, filtra os modelos por nome e visualiza os valores de avaliação para cada ano de fabricação do veículo escolhido.

##  Funcionalidades

- Consulta de marcas por tipo de veículo (carros, motos, caminhões)
- Listagem de modelos de uma marca, ordenados alfabeticamente
- Filtro de modelos por trecho do nome
- Consulta de valores FIPE por ano de fabricação do modelo selecionado

##  Tecnologias utilizadas

- **Java 17+**
- **Spring Boot** — estrutura e ciclo de vida da aplicação
- **Maven** — gerenciamento de dependências e build do projeto
- **HttpClient (java.net.http)** — consumo da API REST da Tabela FIPE
- **Jackson** — deserialização de JSON, com uso de `@JsonAlias` e `@JsonIgnoreProperties` para mapear os dados retornados pela API
- **Java Streams e Lambdas** — ordenação e filtragem de dados
- **Records** — representação imutável dos modelos de dados

##  Arquitetura

O projeto é organizado em camadas, separando responsabilidades:

```
src/main/java/br/com/nicolas/fipe/
├── FipeApplication.java        # Ponto de entrada da aplicação Spring Boot
├── Principal/
│   └── Main.java                # Fluxo principal e interação com o usuário
├── service/
│   ├── ConsumoApi.java           # Responsável por consumir a API da FIPE
│   ├── IConverteDados.java       # Interface para conversão de dados (inversão de dependência)
│   └── Convertedados.java        # Implementação da conversão de JSON usando Jackson
└── model/
    ├── Dados.java                 # Representa marca/modelo (código e nome)
    ├── Modelos.java                # Lista de modelos de uma marca
    └── Veiculo.java                # Dados de avaliação de um veículo (valor, marca, modelo, ano, combustível)
```

## ️ Como executar

### Pré-requisitos
- Java 17 ou superior
- Maven instalado

### Passos

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd fipe
   ```

2. Compile e execute o projeto com Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Siga as instruções exibidas no terminal:
    - Escolha o tipo de veículo (Carros, Motos ou Caminhões)
    - Informe o código da marca desejada
    - Digite um trecho do nome do veículo para filtrar os modelos
    - Informe o código do modelo para consultar os valores por ano

##  Exemplo de uso

```
===== TABELA FIPE =====

- Carros
- Motos
- Caminhoes

Escolha o tipo de veiculo:
Carros

Informe o codigo da marca para consulta:
59

Digite um trecho do nome do veiculo a ser buscado:
Civic

Digite o codigo do modelo para buscar os valores de avaliacao:
...

Todos os veiculos filtrados com avaliacoes por ano:
Veiculo[valor=R$ 120.000,00, marca=Honda, modelo=Civic, ano=2022, combustivel=Gasolina]
```

##  API utilizada

Este projeto consome a [API Parallelum FIPE](https://parallelum.com.br/fipe/api/), uma API pública e gratuita para consulta de dados da Tabela FIPE.

