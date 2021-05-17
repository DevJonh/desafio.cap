# Desafio Capgemini

Sistema de Cadastro e Gerenciamento de Anúncios.
Suas funcionalidades são:
  - Cadastro unitários de Anúncios
  - Cadastro multiplos de Anúncios
  - Exclusão multipla de Anuncios
  - Geração de um arquivo txt com um relatório da campanha
  - Calculo para projeção de views, cliques e compartilhamentos por real investido

## Para Rodar o Projeto

### 1º PASSO

 - Baixar/Clonar o projeto
 - Copiar o script do arquivo *scripts.txt* e executar no banco de dados (Postegresql preferencialmente)

### 2º PASSO

Após baixar o projeto e criar o banco de dados você precisará:
 
 - Abrir o projeto em sua IDE
 - Editar o arquivo que se encontra em *anuncios/src/main/java/desafio/capgemini/conexao/SingleConnection.java*

```
Você só precisará modificar as variavéis para a configuração do seu banco de dados:

  private static String URL_BANCO = "jdbc:postgresql://localhost:5432/anuncios";
	private static String PASSWORD = "admin";
	private static String USER = "postgres";
  
```
### 3º PASSO

Agora é somente executar o programa na IDE
