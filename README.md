JTimeCalculator
===============

Não é uma biblioteca, muito menos um framework. Apenas um conjunto de classes que, aliados ao <a href="http://www.joda.org/joda-time/"> JodaTime </a> permite definir uma janela de cálculo de tempo entre dois períodos.

A interface Janela permite definir a janela da maneira que for mais conveniente, como uma janela de trabalho, onde a jornada é de 08h com intervalo as 12h, retorno as 14h e finalizando as 18h.

Depois basta utilizar a calculadora para obter a diferença de tempo entre dois períodos.


````
Janela janela = new JanelaTrabalho();
janela.adicionarPeriodo(8, 12);
janela.adicionarPeriodo(14, 18);

CalculadoraDeHoras calc = new CalculadoraDeHoras();
DateTime inicial = new DateTime(2010, 07, 16, 8, 0, 0, 0);
DateTime finall = new DateTime(2010, 07, 16, 18, 0, 0, 0);

Tempo labor = calc.quantidadeDeHoras(inicial, finall, janela);
System.out.println(labor.getHoras());//8
System.out.println(labor.getMinutos());//0
                
````


Este projeto também esta disponível em Python neste <a href="https://github.com/geraldox100/PyTimeCalculator">link</a>.
