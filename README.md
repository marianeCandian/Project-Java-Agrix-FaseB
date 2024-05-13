# Agrix - Fase B
Este projeto em Java é uma expansão da aplicação Agrix Fase A, parte do desenvolvimento de um sistema para gestão e monitoramento de fazendas. Esta é a fase B do projeto, que inclui novas funcionalidades e coloca em prática conhecimentos sobre testes com Spring Data, LocalDataTime, buscas customizadas e auditoria.

## Funcionalidades
Na vida de uma pessoa desenvolvedora é muito frequente a necessidade de trabalhamos com persistência de dados e com testes. Em primeiro lugar, este projeto te permitirá colocar em prática os conhecimentos sobre persistência com Spring Data e sobre testes que adquiri.

Este projeto foi desenvolvido focando no comportamento da sua aplicação, sem restringir tanto a forma com que ele será construído e implementado.
A capacidade de pensar e decidir como estruturar uma solução é algo muito valioso para uma pessoa programadora.

O que foi desenvolvido?

Aplicar o conhecimento do ecossistema Spring para criar rotas da API.
Aplicar a injeção de dependência para conectar as camadas de controle, serviço e persistência.
Utilizar o Spring Data JPA para implementar entidades e repositórios para a persistência em banco de dados, bem como implementar buscas customizadas.
Utilizar campos de data nas rotas da API e no banco de dados.
Criar testes unitários para garantir a qualidade e funcionamento correto da implementação, com cobertura de código adequada.

## Conceitos Abordados
Desenvolvimento de rotas da API com Spring Boot.</p>
Injeção de dependências para conectar as camadas de controle, serviço e persistência.</p>
Utilização do Spring Data para persistência em bancos de dados.</p>
Implementação de buscas customizadas com Spring Data.</p>
Utilização de campos de data com LocalDataTime.</p>
Criação de testes unitários para garantir a qualidade do código.</p>

## Requisitos Desenvolvidos

### 1. Migre seu código da Fase A para este projeto (Fase B)

<details>
  <summary>Migre seu código que implementou no "Agrix - Fase A" para este projeto (Fase B)</summary><br />

Neste requisito, você deverá trazer todo o código que você implementou durante o "Agrix - Fase A" para este projeto (Agrix - Fase B).

Tome cuidado especial com:
 - `pom.xml`: o `pom.xml` inicial das Fase B não é igual ao `pom.xml` da Fase A, então você não pode simplesmente substituílo. Cuide para transferir apenas as dependências que você incluiu, sem alterar as outras configurações do projeto.
 - Agora na Fase B já disponibilizamos um pacote com código que você utilizará em um dos requisitos abaixo. Tome cuidado também para não mover nem apagar esse código.

Durante os testes deste requisito, apenas a rota POST `/farms` será validada. No entanto, você precisará trazer todo o código que você implementou na fase anterior.

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição para a rota POST `/farms`:
```json
{
  "name": "Fazendinha",
  "size": 5
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Fazendinha",
  "size": 5
}
```
</details>

</details>


### 2. Escreva testes com cobertura mínima de 80% das linhas da classe PersonService

<details>
  <summary>Escreva testes com cobertura mínima de 80% das linhas da classe PersonService</summary><br />

A Fase A do projeto Agrix deu tão certo que as pessoas inverstidoras decidiram comprar uma base de código existente de outra empresa. Infelizmente, esse código não incluia testes unitários, e você ficou responsável por escrever testes para uma das classes.

O código adquirido está no pacote `com.betrybe.agrix.ebytr.staff`. Por enquanto o código não será refatorado ou integrado à aplicação, então tome cuidado para não alterar ou apagar nada nesse pacote.

A classe que você deverá testar é a `PersonService`, dentro do subpacote `service`. Você deverá garantir uma cobertura dos testes de no mínimo **80%** das linhas dessa classe. Crie seus testes no pacote `com.betrybe.agrix.solution`.

**_Atenção_**: Você pode utilizar as funcionalidades de cobertura de código da sua IDE para te ajudar a identificar o que falta testar. No entanto, lembre-se de que a cobertura que será considerada é a dada pelos testes oficiais do projeto.

</details>

### 3. Ajuste (ou crie) a rota POST /farms/{farmId}/crops para utilizar datas

<details>
  <summary>Ajuste ou crie a rota POST /farms/{farmId}/crops para utilizar campos com datas</summary><br />

Neste requisito, você deverá garantir que a rota para criação de plantações tenha os campos com data definidos abaixo. 

Caso você já tenha implementado esta rota durante a Fase A do projeto, você precisa ajustá-la para incluir os novos campos. Caso contrário, você precisará implementar a rota completa, incluindo os campos antigos e os novos.

A definição original da rota é:
- `/farms/{farmId}/crops` (`POST`)
    - deve receber o `id` da fazenda pelo caminho da rota (representado aqui por `farmId` apenas para diferenciar da plantação)
    - deve receber via corpo do POST os dados da plantação (veja abaixo para os dados de requisição
      e resposta)
    - deve salvar a nova plantação a partir dos dados recebidos, associada à fazenda com o ID
      recebido
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados da plantação criada. A resposta deve incluir o `id` da plantação e
          o `id` da fazenda, mas não deve incluir os dados da fazenda.
    - caso não exista uma fazenda com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

Você precisará incluir dois atributos novos (descritos no diagrama atualizado das tabelas):
- `plantedDate`, representando a data em que a plantação foi semeada
- `harvestDate`, representando a data em qua a plantação foi ou está prevista para ser colhida

As datas devem ser recebidas e retornadas no formato ISO (`YYYY-MM-DD`). Sugerimos que você use o tipo `LocalDate`.

Nota: dependendo de como você fez sua implementação, é possível que ao resolver este requisito você também resolva automaticamente os próximos requisitos relacionados a plantações. Caso isso aconteça, não se assuste :)

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
{
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "plantedDate": "2022-12-05",
  "harvestDate": "2023-06-08"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "plantedDate": "2022-12-05",
  "harvestDate": "2023-06-08",
  "farmId": 1
}
```

Note que o `id` da resposta se refere à plantação, e que o da fazenda está em `farmId`.

</details>

</details>

### 4. Ajuste (ou crie) a rota GET /farms/{farmId}/crops para utilizar datas

<details>
  <summary>Ajuste ou crie a rota GET /farms/{farmId}/crops para utilizar campos com datas</summary><br />

Da mesma forma que no requisito 2, você deve incluir os campos com datas na resposta deste requisito.

A definição original da rota é:
- `/farms/{farmId}/crops` (`GET`):
    - deve receber o `id` de uma fazenda pelo caminho
    - deve retornar uma lista com todas as plantações associadas à fazenda
    - caso não exista uma fazenda com esse `id`, a rota retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

Os campos novos a serem incluídos são os mesmos do requisito anterior.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-12-05",
    "harvestDate": "2023-06-08",
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  }
]
```

</details>

</details>

### 5. Ajuste (ou crie) a rota GET /crops para utilizar datas

<details>
  <summary>Ajuste ou crie a rota GET /crops para utilizar campos com datas</summary><br />

A definição original da rota é:
- `/crops` (`GET`)
    - deve retornar uma lista de todas as plantações cadastradas. A resposta deve incluir o `id` de
      cada plantação e o `id` da fazenda associada, mas não deve incluir os dados da fazenda.

Os campos novos a serem incluídos são os mesmos do requisito anterior.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "plantedDate": "2023-05-22",
    "harvestDate": "2024-01-10",
    "farmId": 2
  }
]
```

</details>

</details>

### 6. Ajuste (ou crie) a rota GET /crops/{id} para utilizar datas

<details>
  <summary>Ajuste ou crie a rota GET /crops/{id} para utilizar campos com datas</summary><br />

A definição original da rota é:
- `/crops/{id}` (`GET`):
    - deve receber o `id` de uma plantação pelo caminho da rota
    - caso exista a plantação com o `id` recebido, deve retornar os dados da plantação. A resposta
      deve incluir o `id` de cada plantação e o `id` da fazenda associada, mas não deve incluir os
      dados da fazenda.
    - caso não exista uma plantação com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Plantação não encontrada!` no corpo da resposta.

Os campos novos a serem incluídos são os mesmos do requisito anterior.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

```json
{
  "id": 3,
  "name": "Tomate",
  "plantedArea": 1.9,
  "plantedDate": "2023-05-22",
  "harvestDate": "2024-01-10",
  "farmId": 2
}
```

</details>

</details>


### 7. Crie a rota GET /crops/search para busca de plantações

<details>
  <summary>Crie a rota GET /crops/search para busca de plantações a partir da data de colheita</summary><br />

A rota a ser criada é:
- `/crops/search` (`GET`)
  - deve receber dois parâmetros por query string para busca:
    - `start`: data de início
    - `end`: data de fim
  - deve retornar uma lista com as plantações nas quais o campo `harvestDate` esteja entre as data de início e de fim.
    - a comparação das datas deve ser inclusiva (ou seja, deve incluir datas que sejam iguais à de início ou à de fim)
  - a resposta deve incluir o `id` de cada plantação e o `id` da fazenda associada, mas não deve incluir os dados da fazenda.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/search?start=2023-01-07&end=2024-01-10`:

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "plantedDate": "2022-02-15",
    "harvestDate": "2023-02-20",
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "plantedDate": "2023-05-22",
    "harvestDate": "2024-01-10",
    "farmId": 2
  }
]
```

</details>

</details>


### 8. Crie a rota POST /fertilizers

<details>
  <summary>Crie a rota POST /fertilizers para criação de um novo fertilizante</summary><br />

Neste requisito, você deverá criar a primeira rota para gerenciamento de fertilizantes. 

Lembre-se que os fertilizantes estão em um relacionamento `n:n` com plantações, então considere isso na hora de implementar sua solução deste e dos próximos requisitos.

A rota a ser criada é:
- `/fertilizers` (`POST`)
    - deve receber via corpo do POST os dados de um fertilizante
    - deve salvar um novo fertilizante a partir dos dados recebidos
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados do fertilizante criado, incluindo seu `id`

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição:

```json
{
  "name": "Compostagem",
  "brand": "Feita em casa",
  "composition": "Restos de alimentos"
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Compostagem",
  "brand": "Feita em casa",
  "composition": "Restos de alimentos"
}
```

</details>

</details>


### 9. Crie a rota GET /fertilizers

<details>
  <summary>Crie a rota GET /fertilizers para listar todos os fertilizantes cadastrados</summary><br />

Neste requisito, você deverá criar a rota para listar todos os fertilizantes cadastrados. A rota a ser criada é:
- `/fertilizers` (`GET`):
    - deve retornar uma lista de todos os fertilizantes cadastrados, incluindo o `id` de cada.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Compostagem",
    "brand": "Feita em casa",
    "composition": "Restos de alimentos"
  },
  {
    "id": 2,
    "name": "Húmus",
    "brand": "Feito pelas minhocas",
    "composition": "Muitos nutrientes"
  },
  {
    "id": 3,
    "name": "Adubo",
    "brand": "Feito pelas vaquinhas",
    "composition": "Esterco"
  }
]
```
</details>

</details>


### 10. Crie a rota GET /fertilizers/{id}

<details>
  <summary>Crie a rota GET /fertilizers/{id} para pegar as informações de um fertilizante</summary><br />

Neste requisito, você deverá criar a rota para pegar as informações de um fertilizante. A rota a ser criada é:
- `/fertilizers/{fertilizerId}` (`GET`):
    - deve receber o `id` de um fertilizante pelo caminho da rota
    - caso exista o fertilizante com o `id` recebido, deve retornar seus dados, incluindo seu `id`
    - caso não exista um fertilizante com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Fertilizante não encontrado!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta da rota `/fertilizers/3` (supondo que exista um fertilizante com `id = 3`):

```json
{
  "id": 3,
  "name": "Adubo",
  "brand": "Feito pelas vaquinhas",
  "composition": "Esterco"
}
```

</details>

</details>


### 11. Crie a rota POST /crops/{cropId}/fertilizers/{fertilizerId}

<details>
  <summary>Crie a rota POST /crops/{cropId}/fertilizers/{fertilizerId} associar uma plantação com um fertilizante</summary><br />

Neste requisito, você deverá criar a rota para criar a associação entre uma plantação e um fertilizante. A rota a ser criada é:
- `/crops/{cropId}/fertilizers/{fertilizerId}` (`POST`)
    - deve receber tanto o `id` da plantação quanto o `id` do fertilizante pelo caminho da rota
    - o corpo da requisição será vazio
    - deve fazer a associação entre o fertilizante e a plantação
    - em caso de sucesso, deve retornar o status HTTP 201 (CREATED) com a mensagem `Fertilizante e plantação associados com sucesso!` no corpo da resposta
    - caso não exista uma plantação com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Plantação não encontrada!` no corpo da resposta.
    - caso não exista um fertilizante com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Fertilizante não encontrado!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de resposta para a rota `/crops/1/fertilizers/2` (supondo que exista uma plantação com `id = 1` e um fertilizante com `id = 2`):

```text
Fertilizante e plantação associados com sucesso!
```

</details>

</details>


### 12. Crie a rota GET /crops/{cropId}/fertilizers

<details>
  <summary>Crie a rota GET /crops/{cropId}/fertilizers para listar os fertilizante associados a uma plantação</summary><br />

Neste requisito, você deverá criar a rota para listar os fertilizante associados a uma plantação. A rota a ser criada é:
- `/crops/{cropId}/fertilizers` (`GET`):
    - deve receber o `id` de uma plantação pelo caminho
    - deve retornar uma lista com todas os fertilizantes associados à plantação
    - caso não exista uma plantação com o `id` recebido, a rota deve retornar o status HTTP 404 com a mensagem `Plantação não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/2/fertilizers` (supondo que exista uma plantação com `id = 2`):

```json
[
  {
    "id": 2,
    "name": "Húmus",
    "brand": "Feito pelas minhocas",
    "composition": "Muitos nutrientes"
  },
  {
    "id": 3,
    "name": "Adubo",
    "brand": "Feito pelas vaquinhas",
    "composition": "Esterco"
  }
]
```

</details>

</details>

## Por que isso é importante?
Este projeto expande o conhecimento adquirido sobre o ecossistema Spring, especialmente no que diz respeito à criação de rotas da API, injeção de dependências e utilização do Spring Data para persistência em bancos de dados. Além disso, permite praticar a criação de testes unitários para garantir a qualidade do código e o funcionamento correto da aplicação.

## Como usar
1-Clone o repositório para o seu ambiente local:
git clone https://github.com/marianeCandian/Project-Java-Agrix-FaseB.git

2-Abra o projeto em sua IDE Java preferida.

3-Execute o programa principal para iniciar a aplicação.

4-Utilize ferramentas como Postman ou curl para enviar requisições para as rotas disponíveis na API.

5-Consulte a documentação da API ou os testes unitários para obter informações detalhadas sobre como usar cada rota.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests para melhorar este projeto.
