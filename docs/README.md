# Game: Berzerk

Information on:
- the usage of design patterns in your project;
- which code smells are still present at the end, and
- which refactorings could be used to fix them.

# LDTS_1007 - Berzerk

Pretendemos recriar o jogo Berzerk do Atari 2600, com algumas mudanças, nomeadamente, utilizar mais do que 1 Heroi (Recruit, Tanky, Expert), e dar-lhe um protótipo de história.
Berzerk é um jogo de ação e tiro em que o jogador tem por objetivo passar todos os niveis sem ser morto, podendo disparar e destruir monstros e tendo que ter sempre cuidado para não bater nas paredes porque são eletrificadas. 
A cada nível corresponde um mapa diferente e os monstros vao ficando também mais fortes e desenvolvendo capacidade de tiro. 
Além disso, apesar de uma abordagem pacifista ser possivel, não há muito espaço para o sucesso, visto que os inimigos são uma ameaça constante.

Este projeto foi desenvolvido por *Catarina Canelas* (*up202103631*@fe.up.pt), *Diogo Gomes* (*up201505676*@fe.up.pt) e *Rui Soares* (*up202103631*@fe.up.pt) para LDTS 2021/22


### IMPLEMENTED FEATURES

- **Menus** - A navegação é feita através das setas do teclado e a opção a selecionar com Enter.
    - PLAY;
    - SETTINGS;
    - LEADERBOARD;
    - EXIT.
- **Leaderboard** - Possibilidade de ver o ‘top’ 5 de melhores jogadores.
- **Escolha de Herói** - Possibilidade de escolher o herói com que se pretende jogar, na opção 'SETTINGS'. Cada herói tem as suas particularidades.
    - Herói Recruit - Herói com Estatisticas Básicas.
    - Herói Tanky - Herói com pontos Extra de Vida.
    - Herói Expert - Herói com cadência de tiro mais elevada.
- **Movimentação do Herói** - O herói movimenta-se para os quatro sentidos usando as setas do teclado.
- **Morte do Herói** - O herói morre se tiver contacto com as paredes eletrizadas, com os monstros ou com as balas destes.
- **Movimentação dos Monstros** - Os monstros movimentam-se sozinhos. Com o avançar dos niveis, podem movimentar-se mais rapidamente.


### PLANNED FEATURES
- **Disparos do Herói** - O herói pode disparar usando o espaço do teclado.
- **Disparos dos Monstros** - Os monstros podem disparar balas a partir do nivel 2.
- **Morte dos Monstros** - Um monstro atingido por uma bala morre. Monstros em diferentes níveis podem necessitar de mais do que uma bala para morrerem.
- **Passagem de niveis** - A passagem de niveis dá-se com a chegada do herói à parte aberta do mapa. No último nivel, o objetivo é apanhar a taça do jogo.
- **Interrupção do jogo** - Usando a tecla 'esc' do teclado é possivel parar o jogo e aceder a um Menu.
- **Menu de Fim de Jogo** - Se o heroi morrer ou chegar ao fim do jogo aparece GAME OVER e o SCORE conseguido e é-lhe permitido registar o nome, e de seguida um menu com as opções TRY AGAIN e EXIT.


### DESIGN

[//]: # (> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts:)

[//]: # ()
[//]: # (- **Problem in Context.** The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code]&#40;https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet&#41; in the appropriate version.)

[//]: # (- **The Pattern.** Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.)

[//]: # (- **Implementation.** Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines &#40;these should be [relative links]&#40;https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files&#41;. When doing this, always point to the latest version of the code.)

[//]: # (- **Consequences.** Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.)

[//]: # ()
[//]: # (**Example of one of such subsections**:)

[//]: # ()
[//]: # (------)

[//]: # ()
[//]: # (#### THE JUMP ACTION OF THE KANGAROOBOY SHOULD BEHAVE DIFFERENTLY DEPENDING ON ITS STATE)

[//]: # ()
[//]: # (**Problem in Context**)

[//]: # ()
[//]: # (There was a lot of scattered conditional logic when deciding how the KangarooBoy should behave when jumping, as the jumps should be different depending on the items that came to his possession during the game &#40;an helix will alow him to fly, driking a potion will allow him to jump double the height, etc.&#41;. This is a violation of the **Single Responsability Principle**. We could concentrate all the conditional logic in the same method to circumscribe the issue to that one method but the **Single Responsability Principle** would still be violated.)

[//]: # ()
[//]: # (**The Pattern**)

[//]: # ()
[//]: # (We have applied the **State** pattern. This pattern allows you to represent different states with different subclasses. We can switch to a different state of the application by switching to another implementation &#40;i.e., another subclass&#41;. This pattern allowed to address the identified problems because […].)

[//]: # ()
[//]: # (**Implementation**)

[//]: # ()
[//]: # (The following figure shows how the pattern’s roles were mapped to the application classes.)

[//]: # ()
[//]: # (![img]&#40;https://www.fe.up.pt/~arestivo/page/img/examples/lpoo/state.svg&#41;)

[//]: # ()
[//]: # (These classes can be found in the following files:)

[//]: # ()
[//]: # (- [Character]&#40;https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/Character.java&#41;)

[//]: # (- [JumpAbilityState]&#40;https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/JumpAbilityState.java&#41;)

[//]: # (- [DoubleJumpState]&#40;https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/DoubleJumpState.java&#41;)

[//]: # (- [HelicopterState]&#40;https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/HelicopterState.java&#41;)

[//]: # (- [IncreasedGravityState]&#40;https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/IncreasedGravityState.java&#41;)

[//]: # ()
[//]: # (**Consequences**)

[//]: # ()
[//]: # (The use of the State Pattern in the current design allows the following benefits:)

[//]: # ()
[//]: # (- The several states that represent the character’s hability to jump become explicit in the code, instead of relying on a series of flags.)

[//]: # (- We don’t need to have a long set of conditional if or switch statements associated with the various states; instead, polimorphism is used to activate the right behavior.)

[//]: # (- There are now more classes and instances to manage, but still in a reasonable number.)

#### VIAJAR ENTRE MENUS

**Contexto do Problema**

O jogador deve conseguir viajar entre menus.

**O Padrão**

Aplicamos o Padrão **State**.

Permite-nos representar diferentes estados através de diferentes Subclasses. É possível mudar o estado da aplicação apenas transitando entre implementações (i.e. subclasses). Este padrão foi utilizado por simplificar significativamente a forma como representávamos os menus.


**Implementação**

A seguinte imagem monstra como o Padrão foi aplicado às classes da aplicação.

![img](images/patterns/StatePattern.svg)

Estas Classes podem ser encontradas nos seguintes ficheiros:

- [Controller](../src/main/java/control/Controller.java)
- [ControllerState](../src/main/java/control/state/ControllerState.java)
- [MenuState](../src/main/java/control/state/FactoryState.java)
- [RankingMenuState](../src/main/java/control/state/RankingMenuState.java)
- [SettingsMenuState](../src/main/java/control/state/SettingsMenuState.java)
- [GameOverState](../src/main/java/control/state/GameOverState.java)


**Consequências**

O uso do Padrão State abre caminho aos seguintes benefícios:
- Os vários estados apresentados ao utilizador ficam destacados em código, em vez de ficarem todos na mesma classe.
- É descartada a utilização de um switch de proporções colossais para mudar entre estados; em vez disso, o polimorfismo gere os estados usados no seu devido momento.
- Há mais classes para gerir, mas mais simples e numa quantidade aceitável.


#### VIAJAR ENTRE MENUS SEM SOBRECARREGAR UM TERMINAL

**Contexto do Problema**

O jogador deve conseguir viajar entre menus sem sobre-carregar o terminal. Na solução prévia, utilizamos apenas 1 menu que obrigava a sobre-carregar o terminal com o método "MenuController.run()" quando se mudava do Menu Principal para o Jogo ou para as SETTINGS.

**O Padrão**

Aplicamos o Padrão **Abstract Factory**.

Permite-nos facilitar a transição entre os diferentes estados mencionados no ponto anterior. É possivel criar diferentes instâncias de State para Menus e fases Jogo. Este padrão foi utilizado por tirar um peso de processamento enorme de apenas 1 terminal e redistribui-lo por vários terminais que são abertos conforme as necessidades do utilizador.

Além disso, a aplicação termina quando, resultado da escolha do utilizador, o novo estado é null.

**Implementação**

A seguinte imagem monstra como o Padrão foi aplicado às classes da aplicação.

![img](images/patterns/AbstractFactoryPattern.svg)

Estas Classes podem ser encontradas nos seguintes ficheiros:

- [Controller](../src/main/java/control/Controller.java)
- [FactoryState](../src/main/java/control/state/FactoryState.java)
- [ControllerState](../src/main/java/control/state/ControllerState.java)

**Consequências**

O uso do Padrão Abstract Factory abre caminho aos seguintes benefícios:
- A transição entre Menus passa a ser mais suave, consistente e menos irregular;
- A responsabilidade de criação de novas instâncias de menus é atribuida a apenas uma classe, deixando o código das ;
- O código para instanciar um estado do menu é reduzido a 1 método por estado.

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.

**Example of such a subsection**:

------

#### DATA CLASS

The `PlatformSegment` class is a **Data Class**, as it contains only fields, and no behavior. This is problematic because […].

A way to improve the code would be to move the `isPlatformSegmentSolid()` method to the `PlatformSegment` class, as this logic is purely concerned with the `PlatformSegment` class.

### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

### SELF-EVALUATION

> In this section describe how the work regarding the project was divided between the students. In the event that members of the group do not agree on a work distribution, the group should send an email to the teacher explaining the disagreement.

**Example**:

- Catarina Canelas: 33.3%
- Diogo Gomes: 33.3%
- Rui Soares: 33.3%
