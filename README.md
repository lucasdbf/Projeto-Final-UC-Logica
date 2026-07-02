# 🖨️ PrintGest - Sistema de Gestão para Gráficas (Projeto Final - UC Lógica)

> **Diga adeus às tabelas de preço manuais.** Orçamentos para rótulos e gestão de produção em segundos.

![Java](https://img.shields.io/badge/java_25.0.1-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)

Projeto desenvolvido como trabalho final para a Unidade Curricular (UC) de **Lógica de Programação** do curso Técnico de Desenvolvimento de Sistemas do **SENAI**.

---

## 📌 O Problema
No dia a dia de gráficas modernas, calcular orçamentos de rótulos e impressos costuma ser um processo manual, demorado e sujeito a erros. É necessário conferir tabelas de preço de papel, calcular área (altura x largura), prever o aproveitamento da folha e aplicar faixas de desconto por quantidade.

## 💡 A Solução
O **PrintGest** foi criado para automatizar e otimizar esse processo. Trata-se de um sistema desenvolvido em **Java** (com interface via `JOptionPane` para foco em performance e lógica de negócios) que realiza cálculos complexos de orçamento instantaneamente, além de gerenciar clientes, funcionários e pedidos (CRUD).

Para apresentar o projeto com excelência, também foi desenvolvida uma **Landing Page** moderna e responsiva.

---

## ⚙️ Principais Funcionalidades

- **🖩 Motor de Orçamento Inteligente:** Insira o tipo de papel, dimensões do rótulo e quantidade. O sistema calcula a área, aproveitamento e valor final baseado em faixas de demanda.
- **👥 Gestão de Clientes:** Lógica de CRUD para cadastro de clientes da gráfica.
- **📦 Gestão de Pedidos:** Lógica de CRUD para registro de orçamentos e acompanhamento.
- **👷 Controle de Funcionários:** Lógica de CRUD para a equipe que opera o sistema.

> **⚠️ Nota sobre o armazenamento de dados:** Como este projeto é focado na UC de Lógica de Programação (1º semestre), não há integração com banco de dados. Todos os dados dos CRUDs são armazenados em **memória** (utilizando listas/arrays na própria execução do Java) e são resetados ao fechar o programa.

---

## 🚀 Tecnologias Utilizadas

**Back-end (Lógica e Sistema):**
- **Java 25.0.1** (Orientação a Objetos, Algoritmos de Cálculo, Estruturas de Repetição e Decisão)
- Interface nativa via `JOptionPane`

**Front-end (Landing Page / Apresentação):**
- **HTML5** (Semântica estrutural)
- **CSS3** (Flexbox, Animações, Variáveis)
- **JavaScript** (Manipulação de DOM, Smooth Scroll)
- Ícones em vetor (SVG)

---

## 📂 Estrutura do Repositório

```text
📁 Projeto-Final-UC-Logica/
│
├── 📁 Landing Page Projeto Final/   # Arquivos da página web de apresentação (HTML, CSS, JS, SVG)
├── 📁 Projeto Final Java/           # Código fonte original em Java e lógica orientada a objetos
├── 📄 LICENSE                       # Licença MIT
├── 📄 ProjetoFinal.jar              # Arquivo executável do sistema pronto para uso
└── 📄 README.md                     # Documentação do projeto
