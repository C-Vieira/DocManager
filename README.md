# Trabalho Padrões de Projeto de Software - JDoc: Document Manager

Simples aplicação para o gerenciamento e edição de documentos de texto. Possui as funcionalidades básicas para criação, listagem, edição e salvar documentos.

Software desenvolvido para obtenção de nota parcial para a disciplina "Padrões de Projeto de Software" do curso de Engenharia de Software da Universidade de Ribeirão Preto - UNAERP.

---

# Tecnologias Utilizadas
- **Java 21**
- **Biblioteca Java Swing**

# Arquitetura Geral

**MVC: Model, View, Controller**

O MVC é um padrão de arquitetura de software focado no reuso de código e separação de conceitos em três camadas de abstração interconcetadas:
- **View:** Camada de exibição e entrada de dados, visível ao usuário final
- **Controller:** Camada de tratamento de dados, faz a comunicação direta com o model
- **Model:** Camada de acesso aos dados, geralmente via um Data Access Object (DAO)

Desse modo, as classes `DocView` e `DocDatabase` são mantidas desacopladas e a comunicação é feita somente via um objeto mediador `DocController`.

Os dados exibidos pela View mantêm-se atualizados reagindo indiretamente à notificações de modificação ocorridas nos dados do Model.

A figura a seguir ilustra as dependências descritas entre as camadas da aplicação:

<img src="https://github.com/C-Vieira/DocManager/blob/master/images/app-architecture.png" />

# Padrões Aplicados
## Singleton
O padrão Singleton é empregado pela classe `ServiceLocator`, a qual mantém uma única instância de si mesma e a expõe para uso por outras classes em escopo global.

Tem por função unificar e centralizar o processo de criação de novos objetos dos tipos `DocView`, `DocController` e `DocDatabase`, gerindo suas dependências.

## Mediator
O padrão Mediator é aplicado por meio da introdução da classe `DocController`, que reduz a dependência entre as camadas de dados e exibição.

Esta classe recebe requisições de uma classe do tipo `DocView` e as propaga para uma outra do tipo `DocDatabase`, mantendo uma comunicação unidirecional e desacoplada.

## Observer
O padrão Observer é utilizado entre classes `DocView` e `DocDatabase` para proporcionar uma comunicação indireta de forma reativa entre as camadas da aplicação.

Por meio da definição das interfaces `DocListener` e `DocPublisher`, é possível manter os dados da camada de exibição sempre atualizados via notificações de modificação emitidas pela camada de dados.

# Diagrama de Classes
<img src="https://github.com/C-Vieira/DocManager/blob/master/images/docmanager_classuml.png" />

# Telas
## Tela Principal
<img src="https://github.com/C-Vieira/DocManager/blob/master/images/tela_principal.png" />
<img src="https://github.com/C-Vieira/DocManager/blob/master/images/tela_principal_newdoc.png" />

## Tela de Edição
<img src="https://github.com/C-Vieira/DocManager/blob/master/images/tela_edicao2.png" />
<img src="https://github.com/C-Vieira/DocManager/blob/master/images/tela_edicao.png" />

# Considerações Finais
Em suma, a aplicação dos padões de projeto **Mediator**, **Observer** e **ServiceLocator(Singleton)** unidos ao padrão arquitetural **MVC** e boas práticas de progamação, tais como os **Princípios SOLID**, proporcionam uma maior eficiência e modularidade ao desenvolvimento de aplicações sob o paradigma da orientação a objetos, resultando em software de mais fácil manutenção, extensão e colaboração.
