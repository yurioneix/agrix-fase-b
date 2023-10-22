## Agrix Fase B 

Continuação do projeto Agrix Fase A. 

<br> 

## Premissa do projeto 

A Fase A do projeto foi um sucesso! Sua equipe agora ficou responsável por expandir a aplicação e incluir algumas funcionalidades extra. Inclusive, vocês receberam uma base de código pronta que conseguiram adquirir de outra empresa, e que precisará ser testado.

<br>

## Habilidades desenvolvidas

- Aplicar o conhecimento do ecossistema Spring para criar rotas da API.
- Aplicar a injeção de dependência para conectar as camadas de controle, serviço e persistência.
- Utilizar o Spring Data JPA para implementar entidades e repositórios para a persistência em banco
  de dados, bem como implementar buscas customizadas.
- Utilizar campos de data nas rotas da API e no banco de dados
- Criar testes unitários para garantir a qualidade e funcionamento correto da implementação, com
  cobertura de código adequada. 

<br>

## Instalação

1. Clone o repositório

- Use o comando: `git clone git@github.com:yurioneix/agrix-fase-b.git`
- Entre na pasta do repositório que você acabou de clonar:
    - `cd agrix-fase-b`

2. Instale as dependências

- `mvn install -DskipTests`

3. Suba os containeres

- `docker-compose up -d`

<br> 

## Endpoints 

- <strong> POST `/farms/{farmId}/crops` </strong>

<details>
  <summary> Associa uma plantação à uma fazenda, através do id da fazenda </summary>

  - Ajusta a rota da fase A para receber 2 novos campos com datas.

  - Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

    ```json
      {
        "name": "Couve-flor",
        "plantedArea": 5.43,
        "plantedDate": "2022-12-05",
        "harvestDate": "2023-06-08"
      }
    ```

  - Exemplo de resposta com `status 201`:
  
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
</details>

<br>

- <strong> GET `/farms/{farmId}/crops` </strong>

<details>
  <summary>Retorna uma plantação pelo id da fazenda em que ela está associada</summary>

  Exemplo de resposta, com `status 200`, para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

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

<br>

- <strong> GET `/crops` </strong>

<details>
  <summary>Retorna todas as plantações cadastradas</summary>

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

<br>

- <strong> GET `/crops/{id}` </strong>

<details>
  <summary>Retorna uma plantação pelo seu id</summary>

  - Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

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

<br>

- <strong> GET `/crops/search` </strong>

<details>
  <summary>Busca uma plantação pela data de colheita</summary>

  - Deve receber dois parâmetros por query string para busca:
    - `start`: data de início
    - `end`: data de fim
  - Deve retornar uma lista com as plantações nas quais o campo `harvestDate` esteja entre as data de início e de fim.

  - Exemplo de resposta para a rota `/crops/search?start=2023-01-07&end=2024-01-10`:

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

<br>

- <strong> POST `/fertilizers` </strong>

<details>
  <summary> Cria um fertilizante </summary>

  - Exemplo de requisição:

    ```json
      {
        "name": "Compostagem",
        "brand": "Feita em casa",
        "composition": "Restos de alimentos"
      }
    ```

  - Exemplo de resposta:
  
    ```json
    {
      "id": 1,
      "name": "Compostagem",
      "brand": "Feita em casa",
      "composition": "Restos de alimentos"
    }
    ```
</details>

<br>

- <strong> GET `/fertilizers` </strong>

<details>
  <summary>Lista todos os fertilizantes cadastrados</summary>

  - Exemplo de resposta:
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

<br> 

- <strong> GET `/fertilizers/{id}` </strong>

<details>
  <summary>Retorna uma fertilizante pelo seu id</summary>

  - Exemplo de resposta da rota `/fertilizers/3` (supondo que exista um fertilizante com `id = 3`):

```json
{
  "id": 3,
  "name": "Adubo",
  "brand": "Feito pelas vaquinhas",
  "composition": "Esterco"
}
```

</details> 
