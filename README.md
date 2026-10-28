# Escola — REST API com Spring Boot

API REST para cadastrar e listar **Professores**, **Cursos** e **Alunos**, feita com Spring Boot, JPA e banco H2 em memória.

```
PROFESSOR (1) ───< CURSO (1) ───< ALUNO
```

- Um **curso** pertence a um **professor** (`professorId`)
- Um **aluno** pertence a um **curso** (`cursoId`)

---

## 1. O que você precisa instalar

| Programa | Versão | Onde baixar |
|---|---|---|
| **Java (JDK)** | 17 ou mais novo | https://adoptium.net (escolha a versão 17 ou 21, LTS) |
| **IntelliJ IDEA** | Community (gratuito) | https://www.jetbrains.com/idea/download |
| **Postman** | qualquer | https://www.postman.com/downloads |
| **Git** | qualquer | https://git-scm.com/downloads |

> Não precisa instalar o Maven: o IntelliJ já vem com ele, e o projeto também tem o `mvnw` (Maven Wrapper).

Para conferir se o Java está instalado, abra o **Prompt de Comando** e digite:

```
java -version
```

Tem que aparecer `17` ou um número maior.

---

## 2. Baixar o projeto

### Pelo IntelliJ (mais fácil)

1. Abra o IntelliJ.
2. Na tela inicial, clique em **Clone Repository** (ou **Get from VCS**).
   - Se já tiver um projeto aberto: **File → New → Project from Version Control...**
3. Em **URL**, cole o endereço do repositório no GitHub (o link que te mandaram).
4. Em **Directory**, escolha onde salvar (por exemplo `C:\Users\SEU_USUARIO\projetos\escola`).
5. Clique em **Clone**.
6. Se aparecer **Trust and Open Project**, clique em **Trust Project**.

### Ou pelo terminal

```
git clone URL_DO_REPOSITORIO
```

Depois, no IntelliJ: **File → Open**, escolha a pasta `escola` (a que tem o `pom.xml` dentro) e clique em **Trust Project**.

---

## 3. Configurar o projeto no IntelliJ

1. **Espere o Maven terminar.** Na primeira vez ele baixa as dependências (uns minutos). Acompanhe a barra de progresso no canto de baixo da janela.
2. **Configure o Java.** Se aparecer o aviso amarelo **"Project JDK is not defined"**:
   - Clique em **Setup SDK** e escolha o **Java 17** (ou o que você instalou).
   - Se não aparecer nenhum, clique em **Download JDK** e baixe a versão 17.
3. **Confira o Language level** (evita o código todo vermelho):
   - Vá em **File → Project Structure → Project**.
   - Em **SDK**, deixe o Java 17.
   - Em **Language level**, escolha **SDK default**.
   - Clique em **OK**.
4. Se o código continuar vermelho, abra o painel **Maven** (lado direito da tela) e clique no botão 🔄 **Reload All Maven Projects**.

---

## 4. Rodar a API

1. No painel da esquerda, abra:
   `src → main → java → br.com.aula.escola → EscolaApplication`
2. Clique no **▶ verde** ao lado de `public static void main` e escolha **Run 'EscolaApplication'**.
3. Espere aparecer no console, embaixo:

   ```
   Tomcat started on port 8080 (http)
   Started EscolaApplication in X seconds
   ```

Pronto: a API está rodando em **http://localhost:8080**. Deixe o IntelliJ aberto enquanto usa o Postman.

Para parar a API, clique no **■ quadrado vermelho** no topo ou no console.

---

## 5. Testar no Postman

### Importar a collection

1. Abra o Postman.
2. Clique em **Import** (canto superior esquerdo).
3. Arraste o arquivo **`Escola.postman_collection.json`** (que está na pasta do projeto) ou clique em **files** e selecione ele.
4. Vai aparecer a collection **Escola** na lateral, com 6 requisições.

### Ordem para rodar (importante!)

O curso precisa de um professor, e o aluno precisa de um curso. Então rode **nesta ordem**, clicando em cada uma e depois em **Send**:

| # | Requisição | Método | URL | Resposta esperada |
|---|---|---|---|---|
| 1 | CadastroProfessor | POST | `localhost:8080/professor` | `201 Created` |
| 2 | CadastroCurso | POST | `localhost:8080/curso` | `201 Created` |
| 3 | CadastroAluno | POST | `localhost:8080/aluno` | `201 Created` |
| 4 | RelatorioProfessor | GET | `localhost:8080/professor` | `200 OK` + lista |
| 5 | RelatorioCurso | GET | `localhost:8080/curso` | `200 OK` + lista |
| 6 | RelatorioAluno | GET | `localhost:8080/aluno` | `200 OK` + lista |

### Exemplos de body (aba Body → raw → JSON)

**POST /professor**
```json
{
  "nome": "Carlos Silva",
  "email": "carlos@unasp.edu.br",
  "departamento": "Computação"
}
```

**POST /curso**
```json
{
  "nome": "Sistemas de Informação",
  "professorId": 1
}
```

**POST /aluno**
```json
{
  "nome": "Maria Souza",
  "email": "maria@aluno.unasp.edu.br",
  "cursoId": 1
}
```

> Dica: troque os nomes e e-mails para os seus dados.

### Como tirar os prints

Use **Win + Shift + S** e selecione a janela do Postman mostrando:
- a **URL** e o **método** (POST/GET),
- o **Body** enviado (nos POST),
- o **status** (`201 Created` / `200 OK`) e a **resposta** embaixo.

---

## 6. Problemas comuns

| Problema | Causa | Solução |
|---|---|---|
| `Port 8080 was already in use` | A API já está rodando em outra janela | Pare a outra com o ■ vermelho ou feche o outro IntelliJ |
| POST de curso/aluno dá **404** | O professor/curso com aquele ID não existe | Cadastre na ordem: professor → curso → aluno |
| POST dá **409** | E-mail já cadastrado | Use outro e-mail |
| Os GET voltam `[]` vazio | O banco H2 é em memória e zera a cada vez que a API reinicia | Faça os POST de novo depois de rodar |
| Código todo vermelho | Maven não terminou ou Java errado | Passo 3 (JDK 17, Language level "SDK default", Reload Maven) |
| Postman: `Could not send request` / `ECONNREFUSED` | A API não está rodando | Rode o `EscolaApplication` no IntelliJ antes |

---

## 7. Extra: ver o banco de dados (H2 Console)

Com a API rodando, abra no navegador: http://localhost:8080/h2-console

- **JDBC URL:** `jdbc:h2:mem:escoladb`
- **User Name:** `sa`
- **Password:** `password`

Clique em **Connect** e veja as tabelas `PROFESSOR`, `CURSO` e `ALUNO`.

---

## Estrutura do projeto

```
src/main/java/br/com/aula/escola
├── EscolaApplication.java   → classe principal (o ▶ fica aqui)
├── controller/              → recebe as requisições HTTP (POST e GET)
├── service/                 → regras: busca o professor/curso pelo ID antes de salvar
├── repository/              → acesso ao banco (JpaRepository)
├── entity/                  → tabelas Professor, Curso e Aluno
├── request/                 → formato do JSON que chega nos POST
└── response/                → formato do JSON que a API devolve
```
