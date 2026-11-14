# API REST - Atividade Prática (Mr Robot Store)

API desenvolvida em **Java** com **Spring Boot** para a atividade prática da disciplina de Desenvolvimento Web Back-End (Análise e Desenvolvimento de Sistemas - UNINTER). O sistema gerencia o controle de clientes, produtos e pedidos de uma loja de eletrônicos fictícia.

---

## 🚀 Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot** (Web, Data JPA, Validation)
* **Bean Validation (Jakarta)** para validação de dados
* **Banco de dados MySQL** para persistência relacional
* **Maven** para gerenciamento de dependências

---

## 📋 Entidades e Atributos

1. **Cliente**
   * `id` (Long)
   * `nome` (String)
   * `clienteDesde` (LocalDate)

2. **Produto**
   * `id` (Long)
   * `nome` (String)
   * `preco` (BigDecimal)
   * `estoque` (Boolean)

3. **Pedido**
   * `id` (Long)
   * `clienteId` (Long)
   * `clienteNome` (String)
   * `produtoId` (Long)
   * `produtoNome` (String)
   * `quantidade` (Integer)

---

## 🔌 Endpoints Disponíveis

### 👤 Clientes (`/clientes`)
* `POST /clientes` - Cadastra um novo cliente.
* `GET /clientes` - Lista todos os clientes cadastrados.
* `GET /clientes/{id}` - Consulta um cliente específico por ID.
* `PUT /clientes/{id}` - Atualiza os dados de um cliente.
* `DELETE /clientes/{id}` - Remove um cliente por ID.

### 📦 Produtos (`/produtos`)
* `POST /produtos` - Cadastra um novo produto.
* `GET /produtos` - Lista todos os produtos cadastrados.
* `GET /produtos/{id}` - Consulta um produto específico por ID.
* `PUT /produtos/{id}` - Atualiza os dados de um produto.
* `DELETE /produtos/{id}` - Remove um produto por ID.

### 🛒 Pedidos (`/pedidos`)
* `POST /pedidos` - Realiza um novo pedido (com validação de cliente e produto).
* `GET /pedidos` - Lista todos os pedidos cadastrados.
* `GET /pedidos/{id}` - Consulta um pedido específico por ID.
* `PUT /pedidos/{id}` - Atualiza um pedido.
* `DELETE /pedidos/{id}` - Remove um pedido por ID.

---

## ⚠️ Configuração Importante (Variáveis de Ambiente)

Para que a API consiga se comunicar com o banco de dados MySQL, **é obrigatório** que você defina as variáveis de ambiente correspondentes na sua IDE ou no seu sistema operacional antes de executar a aplicação. Caso contrário, a API não conseguirá estabelecer conexão e falhará ao rodar.

Certifique-se de configurar as seguintes variáveis de ambiente com os dados do seu MySQL local:
* `DB_USER` (seu usuário do MySQL, ex: `root`)
* `DB_PASSWORD` (sua senha do MySQL)

---

## ⚙️ Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone <URL_DO_SEU_REPOSITORIO_GITHUB>
