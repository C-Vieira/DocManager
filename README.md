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
- **Model:** Camada de acesso aos dados, geralmente via um DAO (Data Access Object)

Desse modo, as classes `DocView` e `DocDatabase` são mantidas desacopladas e a comunicação é feita somente via um objeto mediador `DocController`.

Os dados exibidos pela View mantêm-se atualizados reagindo indiretamente à notificações de modificação ocorridas nos dados do Model.

A figura a seguir ilustra as dependências descritas entre as camadas da aplicação:

<img src="https://github.com/C-Vieira/DocManager/blob/master/images/app-architecture.png" />

# Padrões Aplicados
## Singleton


## Mediator


## Observer


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
