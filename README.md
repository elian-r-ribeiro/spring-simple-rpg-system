
# Simple RPG System

Olá! Esse projeto se trata nada mais nada menos do que de uma simples API que simula um sistema BEM SIMPLES de RPG. Existe um player, um inimigo, um combate e itens para serem utilizados.


## Rodando o projeto

Para começar, você precisa ter o Java, o Maven, o Docker com o Postgres instalado (ou então o Postgres intalado diretamente em sua máquina, XAMPP ou qualquer ferramenta do gênero), uma forma de enviar requisições HTTP para a A.P.I. (como Postman ou Insomnia), uma forma de gerenciar o Postgres e uma I.D.E. de sua preferência com suporte a Java (opcional). Caso precise, ha um docker-compose.yml presente entre os arquivos, basta abrir o terminal na pasta do projeto e executar 

```bash
  docker-compose up
```

Após se certificar de que tudo acima está de acordo, você precisa abrir seu console na pasta do projeto e rodar o comando:

```bash
  maven clean install
```

Isso irá instalar todas as dependências necessárias no projeto caso você não tenha uma I.D.E. que faça isso automaticamente.

Inicie o Postgres e crie um banco de dados para o projeto, como "simple-rpg-system". Após isso, vá até "customer-microservice/src/main/resources/application.yml". Lá, você irá fazer os seguintes ajustes nas variáveis de ambiente. Você pode criar um arquivo dentro da pasta do projeto com o nome .env.local, por exemplo:

```bash
  spring.datasource.url=url
```

Aqui você irá colocar a URL do seu banco de dados.

```bash
  spring.datasource.username=nome
```

Aqui é onde você irá colocar o nome de usuário para acessar seu banco de dados. Para o postgres sem alterações, geralmente é "postgres".

```bash
  spring.datasource.password=senha
```

Aqui é a senha para acessar seu banco de dados. Se você seguiu o arquivo docker-compose.yml presente no projeto, a senha será "root".

```bash
  spring.datasource.password=senha
```

```bash
  spring.datasource.driver-class-name=driver
```

Aqui é o Driver do banco de dados o qual você está utilizando. No caso desse projeto, é o driver do Postgres, presente mais abaixo.

Opcional: 

```bash
  server.port=porta
```

Caso haja algum clonflito de portas, você pode alterar a porta do projeto aqui.

Exemplos de variáveis de ambiente: 

```bash
  SERVER_PORT=3040;
```

```bash
  DATABASE_URL=jdbc:postgresql://localhost:5432/simple-rpg-system;
```

```bash
  DATABASE_USERNAME=postgres;
```

```bash
  DATABASE_USERNAME=postgres;
```

```bash
  DATABASE_PASSWORD=root;
```

```bash
  DATABASE_DRIVER=org.postgresql.Driver;
```

Após realizar essas configurações, seu projeto deve estar pronto para rodar. Se estiver em uma I.D.E., basta utilizar o runner dela, caso não, abra o console na pasta do projeto e digite: 

```bash
  mvn spring-boot:run
```

Caso nenhuma mensagem de erro apareça, o projeto está funcionando corretamente.
## End-points

Abra o aplicativo que irá utilizar para realizar requisições HTTP e teste:

ATENÇÃO: Todas as requisições precisam ter no header a seguinte informação:

```env
  apiVersion: versão-api
```

Isso dirá ao projeto qual versão do end-point chamar. Atualmente estão presentes apenas: 1.0.

### Jogador

#### Criar novo jogador

```http
  POST http://localhost:porta/players/new-player
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `String` | **Obrigatório**. O nome do jogador |

#### Obter a lista de todos os jogadores

```http
  GET http://localhost:porta/players
```

#### Obter jogador

```http
  GET http://localhost:porta/players/id
```

#### Editar jogador

```http
  PUT http://localhost:porta/players/update-player/id
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `String` | O nome do jogador |

#### Deletar jogador

```http
  DELETE http://localhost:porta/players/delete-player/id
```

### Inimigos

#### Criar novo inimigo

```http
  POST http://localhost:porta/enemies/new-enemy
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `String` | **Obrigatório**. O nome do inimigo |
| `hp` | `Long` | **Obrigatório**. A vida do inimigo |
| `ap` | `Long` | **Obrigatório**. A Attack Power do  inimigo |
| `dp` | `Long` | **Obrigatório**. A Defense Power do  inimigo |
| `xpDrop` | `Long` | **Obrigatório**. A quantidade de XP dropada pelo inimigo |

#### Obter lista de todos os inimigos

```http
  GET http://localhost:porta/enemies
```

#### Obter inimigo

```http
  GET http://localhost:porta/enemies
```

#### Atualizar inimigo

```http
  PUT http://localhost:porta/enemies/update-enemy/id
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `String` | O nome do inimigo |
| `hp` | `Long` | A vida do inimigo |
| `ap` | `Long` | A Attack Power do  inimigo |
| `dp` | `Long` | A Defense Power do  inimigo |
| `xpDrop` | `Long` | A quantidade de XP dropada pelo inimigo |

#### Deletar inimigo

```http
  DELETE http://localhost:porta/enemies/delete-enemy/id
```

### Itens

#### Criar novo item

```http
  POST http://localhost:porta/items/new-item
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `itemName` | `String` | **Obrigatório**. O nome do item |
| `itemUsage` | `ENUM` | **Obrigatório**. Qual a utilidade / uso do item. (Lista abaixo) |
| `effectValue` | `Long` | **Obrigatório**. O valor de efeito do uso do item |

Os usos do item podem ser os seguintes:

| Nome   | Descrição |
| :---------- | :---------| 
| `RECOVER_HP` | No uso, recupera HP do jogador até o limite máximo |
| `INCREASE_AP` | No uso, aumenta a Attack Power do jogador |
| `INCREASE_DP` | No uso, aumenta a Defense Power do jogador |
| `INCREASE_XP` | No uso, aumenta o XP do jogador |
| `INCREASE_LEVEL` | No uso, aumenta o nível do jogador |

#### Obter lista de todos os itens

```http
  GET http://localhost:porta/items
```

#### Obter item

```http
  GET http://localhost:porta/items/id
```

#### Atualizar item

```http
  PUT http://localhost:porta/items/update-item/id
```

| Body(JSON)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `itemName` | `String` | O nome do item |
| `itemUsage` | `ENUM` | Qual a utilidade / uso do item. (Lista abaixo) |
| `effectValue` | `Long` | O valor de efeito do uso do item |

Os usos do item podem ser os seguintes:

| Nome   | Descrição |
| :---------- | :---------| 
| `RECOVER_HP` | No uso, recupera HP do jogador até o limite máximo |
| `INCREASE_AP` | No uso, aumenta a Attack Power do jogador |
| `INCREASE_DP` | No uso, aumenta a Defense Power do jogador |
| `INCREASE_XP` | No uso, aumenta o XP do jogador |
| `INCREASE_LEVEL` | No uso, aumenta o nível do jogador |

### Deletar item

```http
  DELETE http://localhost:porta/items/delete-item/id
```

### Gerenciamento de itens

#### Dar item a um jogador

```http
  POST http://localhost:porta/players-items/add-item-to-player/playerId/itemId
```

#### Fazer um jogador usar um de seus itens

```http
  POST http://localhost:porta/players-items/use-item/playerId/itemId/itemPositionInList
```

itemPositionInList é a posição do item na lista de itens do jogador, não o ID do item.

#### Obter lista de itens de um jogador

```http
  GET http://localhost:porta/players-items/get-player-items/id
```

### Combate

```http
  POST http://localhost:porta/combats/begin-combat/playerId/enemyId
```

Isso irá executar um combate automático entre um jogador e um inimigo. Certifique-se de criar inimigos cujo AP, DP e HP sejam menores ou quase idênticos ao do jogador (para baixo), caso contrário o combate será sempre perdido.