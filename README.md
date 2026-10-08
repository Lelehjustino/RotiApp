# 🌱 RotinApp — Rotinas, Metas e Progresso

Aplicativo Android desenvolvido em **Kotlin com Jetpack Compose** como parte do **Trabalho 2 — MAF (Mínimo Aplicativo Funcional)**.

O **RotinApp** tem como objetivo ajudar o usuário a organizar suas rotinas, acompanhar metas e visualizar seu progresso de forma simples e visual.

> 📚 **Projeto acadêmico — Universidade Positivo**

### 🎥 Demonstração

Clique abaixo para assistir à demonstração do aplicativo:

[▶️ Assistir à demonstração do RotinApp](https://drive.google.com/file/d/1ZJ0KFRyBLc90iXeIkImVp8Kb0NQnIrcw/view?usp=sharing)

---

## 📱 Sobre o projeto

O RotinApp surgiu a partir do **Trabalho 1**, no qual foram desenvolvidas as primeiras telas e a identidade visual do aplicativo.

No Trabalho 2, o projeto foi evoluído para um **Mínimo Aplicativo Funcional (MAF)**, passando a utilizar navegação real entre as telas e dados que podem ser adicionados, alterados e removidos durante a execução do aplicativo.

O projeto foi desenvolvido em trio e teve como foco aplicar os conceitos estudados em aula, principalmente:

* Navegação com `NavHost` e `NavController`
* `NavigationBar`
* `LazyColumn`
* `Card`
* `data class`
* `mutableStateListOf`
* `ViewModel`
* Formulários com campos de entrada
* Gerenciamento de estado com Compose
* Organização das rotas e telas
* Componentização da interface

---

# ✨ Funcionalidades

## 📋 Rotinas

A área de rotinas permite visualizar as rotinas cadastradas no aplicativo.

Cada rotina é apresentada em um `Card` dentro de uma `LazyColumn`, contendo informações como:

* Nome da rotina
* Tempo de duração
* Organização visual por cards

A criação de novas rotinas é realizada por meio de um formulário.

Os dados são mantidos em memória durante a execução do aplicativo.

---

## 🎯 Metas

A tela de metas permite ao usuário gerenciar seus objetivos.

É possível:

* Criar uma nova meta
* Informar nome e descrição
* Visualizar as metas cadastradas
* Marcar uma meta como concluída
* Excluir uma meta
* Visualizar informações da meta por meio de um popup

As metas são apresentadas utilizando `LazyColumn` e `Card`.

---

## 📊 Progresso

A tela de progresso apresenta uma visão geral da evolução do usuário.

Entre as informações apresentadas estão:

* Nível atual
* Experiência
* Métricas de progresso
* Atividades
* Acesso às metas
* Acesso ao histórico

Essa tela funciona como uma área central para acompanhar a evolução dentro do aplicativo.

---

## 📅 Histórico

A tela de histórico apresenta informações relacionadas às atividades realizadas anteriormente.

Ela permite visualizar registros relacionados às rotinas e metas concluídas, ajudando o usuário a acompanhar sua evolução ao longo do uso do aplicativo.

---

## 👤 Perfil

A tela de perfil reúne informações relacionadas ao usuário.

Também funciona como uma área de acesso para outras funcionalidades complementares do aplicativo.

---

# 🧭 Navegação

O aplicativo utiliza um **NavHost centralizado** para controlar a navegação entre as telas.

As rotas foram organizadas em um objeto específico, evitando que os nomes das rotas ficassem espalhados pelo código.

```kotlin
object RotaAbas {
    const val Progresso = "Progresso"
    const val TelaCriacao = "TelaCriacao"
    const val TelaListaRotina = "TelaListaRotina"
    const val TelaRotina = "TelaRotina"
    const val TelaHistorico = "TelaHistorico"
    const val TelaMetas = "TelaMetas"
    const val TelaPerfil = "TelaPerfil"
}
```

A navegação entre as telas é realizada utilizando `NavController`.

Exemplo:

```kotlin
navController.navigate(RotaAbas.TelaMetas)
```

Para retornar à tela anterior:

```kotlin
navController.popBackStack()
```

O aplicativo também utiliza uma **NavigationBar**, permitindo acessar as principais áreas do sistema.

---

# 🏗️ Estrutura do aplicativo

A organização das telas foi pensada para separar as diferentes responsabilidades do aplicativo.

Principais telas:

```text
RotinApp
│
├── Progresso
│
├── TelaListaRotina
│
├── TelaCriacao
│
├── TelaRotina
│
├── TelaMetas
│
├── TelaHistorico
│
└── TelaPerfil
```

O projeto também possui estruturas específicas para os modelos e gerenciamento dos dados.

---

# 🧩 Data Classes

O aplicativo utiliza `data classes` para representar os diferentes tipos de informações utilizadas pelo sistema.

### Rotina

```kotlin
data class Rotina(
    val idRotina: Int,
    val nomeRotina: String,
    val tempoMinutosRotina: Int
)
```

A classe `Rotina` representa uma rotina cadastrada pelo usuário.

Também são utilizadas estruturas para representar as atividades e metas utilizadas pelo aplicativo.

---

# 💾 Gerenciamento dos dados

Os dados do aplicativo são mantidos **somente em memória**, conforme permitido pelo enunciado do Trabalho 2.

Isso significa que as informações cadastradas permanecem disponíveis enquanto o aplicativo está sendo executado, mas não são persistidas depois que o aplicativo é fechado.

Para trabalhar com listas reativas, utilizamos `mutableStateListOf`.

Exemplo:

```kotlin
private val _metas = mutableStateListOf<Meta>()

val metas: List<Meta>
    get() = _metas
```

Dessa forma, quando uma informação é adicionada, alterada ou removida, a interface pode ser atualizada automaticamente.

A persistência dos dados em banco ou outro mecanismo não faz parte deste trabalho.

---

# 🧠 Decisões de desenvolvimento

Durante o desenvolvimento do MAF, uma das principais decisões foi centralizar a navegação em um `NavHost` e organizar as rotas em um objeto próprio.

Essa estrutura foi escolhida porque o aplicativo cresceu em relação ao Trabalho 1 e passou a possuir várias telas. Centralizar as rotas facilita a manutenção e deixa o fluxo de navegação mais organizado.

Também optamos por manter os dados em listas reativas na memória, utilizando `mutableStateListOf`, pois essa estrutura atende ao objetivo do trabalho sem a necessidade de implementar persistência.

Outra decisão foi utilizar `ViewModel` para organizar o gerenciamento de informações utilizadas por determinadas telas, evitando concentrar toda a lógica diretamente nos componentes visuais.

---

# 📈 Evolução do Trabalho 1 para o Trabalho 2

No Trabalho 1, o aplicativo possuía três telas estilizadas e os elementos de navegação ainda podiam ser apenas representativos.

No Trabalho 2, essas telas foram evoluídas e o aplicativo passou a possuir:

* Navegação real entre telas
* `NavHost`
* `NavController`
* NavigationBar
* Listas dinâmicas
* Formulários
* Dados em memória
* `ViewModel`
* `mutableStateListOf`
* Novas áreas do aplicativo
* Interações reais com os dados

Assim, o projeto passou de uma representação principalmente visual para uma aplicação funcional dentro do escopo proposto pelo MAF.

---

# 👥 Desenvolvimento dupla

O projeto foi desenvolvido em dupla.

Durante o desenvolvimento, tivemos algumas dificuldades relacionadas à divisão das tarefas. Em determinados momentos, um integrante acabou realizando uma quantidade maior de alterações enquanto outro teve mais dificuldade para avançar em algumas partes.

Essa experiência também mostrou a importância de manter uma comunicação constante entre os integrantes, principalmente porque as telas, rotas e estruturas de dados estão relacionadas entre si.

---

# 🛠️ Tecnologias utilizadas

* **Kotlin**
* **Jetpack Compose**
* **Material 3**
* **Navigation Compose**
* **ViewModel**
* **LazyColumn**
* **Card**
* **NavigationBar**
* **mutableStateListOf**
* **Git**
* **GitHub**
* **Android Studio**

---

# 📂 Organização do projeto

Estrutura simplificada:

```text
app/
└── src/
    └── main/
        └── java/
            └── com.example.myapplication/
                ├── MainActivity.kt
                ├── AppNavegacao.kt
                ├── RotaAbas.kt
                │
                ├── Telas/
                │   ├── TelaListaRotinas.kt
                │   ├── TelaCriacao.kt
                │   ├── TelaMetas.kt
                │   ├── TelaHistorico.kt
                │   ├── Progresso.kt
                │   └── TelaPerfil.kt
                │
                ├── ViewModel/
                │   ├── MetasViewModel.kt
                │   └── HistoricoViewModel.kt
                │
                └── ui/
                    └── theme/
                        ├── Rotina.kt
                        └── Meta.kt
```

> A estrutura acima representa a organização principal do projeto. Os nomes e arquivos podem variar conforme a versão final do código.

---

# ▶️ Como executar o projeto

## Pré-requisitos

Para executar o RotinApp, é necessário possuir:

* Android Studio
* JDK compatível com o projeto
* Android SDK
* Emulador Android ou dispositivo físico

## 1. Clonar o repositório

```bash
git clone URL_DO_REPOSITORIO
```

## 2. Abrir o projeto

Abra o projeto clonado no **Android Studio**.

## 3. Sincronizar o Gradle

Aguarde o Android Studio finalizar a sincronização das dependências.

## 4. Executar

Conecte um dispositivo Android ou inicialize um emulador.

Depois, clique em:

**Run ▶**

O aplicativo será compilado e executado no dispositivo selecionado.

---

# 📸 Documentação do processo
Clique abaixo para ver o progresso do aplicativo:

[▶️ Veja o progresso do RotinApp]([https://drive.google.com/file/d/1ZJ0KFRyBLc90iXeIkImVp8Kb0NQnIrcw/view?usp=sharing](https://docs.google.com/presentation/d/1mJGpCOkL4Ml6ntdr6JviR-vTJbXRlhazem6Y5Pq6kDA/edit?hl=pt-BR&slide=id.h42b499eac98e7b93_0_30#slide=id.h42b499eac98e7b93_0_30))

---

# 🎓 Trabalho acadêmico

**Trabalho 2 — MAF (Mínimo Aplicativo Funcional)**

Disciplina: Desenvolvimento de Aplicativos Android

Universidade Positivo

Projeto desenvolvido para fins acadêmicos.

---

## 🌱 RotinApp

**Organize suas rotinas. Acompanhe suas metas. Visualize seu progresso.**
