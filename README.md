# Programa de Busca de Filmes usando OMDb API

Este projeto é um programa Java que permite ao usuário buscar informações de filmes na API do OMDb. O programa utiliza a biblioteca Gson para manipular JSON e salva os resultados das buscas em um arquivo `filmes.json`.

## Tecnologias Utilizadas

- **Java**
- **OMDb API**: Para obter informações sobre filmes.
- **Gson**: Para conversão de JSON para objetos Java e vice-versa.
- **HTTP Client**: Para realizar as requisições HTTP à API.

## Funcionalidades

- Busca informações de filmes utilizando o nome fornecido pelo usuário.
- Converte a resposta da API OMDb em um objeto Java.
- Lida com exceções como erros de conversão e formatação de dados.
- Salva os resultados das buscas em um arquivo `filmes.json`.

## Estrutura do Código

### Importações

O código utiliza várias classes e pacotes importantes:

- `br.com.robsonlmds.moviesteam.excecao.ErroDeConversaoDeAnoException`: Classe personalizada para tratamento de erro na conversão de ano.
- `br.com.robsonlmds.moviesteam.modelos.Titulo`: Classe que representa o título do filme.
- `br.com.robsonlmds.moviesteam.modelos.TituloOmdb`: Classe que mapeia a resposta da API OMDb.
- `com.google.gson.*`: Biblioteca para manipulação de JSON.

### Método `main`

O método `main` contém a lógica principal do programa:

1. **Entrada do Usuário**: Solicita ao usuário que digite o nome de um filme para busca ou "sair" para finalizar o programa.
   
2. **Requisição HTTP**: Monta o URL de busca para a API OMDb e envia uma requisição HTTP para obter os dados do filme.
   
3. **Conversão JSON**: Converte a resposta JSON da API em um objeto Java (`TituloOmdb`) usando Gson.

4. **Tratamento de Exceções**:
   - `NumberFormatException`: Captura erros de formatação de números.
   - `IllegalArgumentException`: Captura erros no endereço da API.
   - `ErroDeConversaoDeAnoException`: Captura erros específicos de conversão de ano.

5. **Gravação em Arquivo**: Os resultados são armazenados em uma lista e salvos no arquivo `filmes.json`.

### `Conclusão`

A classe PrincipalComBusca oferece uma interface simples para buscar informações de filmes utilizando a API OMDb. Ao coletar os títulos buscados pelo usuário, converte-os em objetos Java e armazena-os em um arquivo JSON, facilitando o armazenamento e a posterior utilização dos dados. O tratamento de exceções garante que erros durante a busca ou conversão sejam adequadamente comunicados ao usuário.
