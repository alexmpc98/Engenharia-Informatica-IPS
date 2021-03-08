# Laboratório 3

## Introdução

Este trabalho laboratorial cobre o padrão de software *Strategy*.


## Nível Básico (Acompanhamento em Aula ou Autónomo)

### Cálculo de Estatísticas

Pretende-se poder calcular diversas estatísticas sobre um conjunto de notas, 
utilizando este padrão.

1. Crie a interface `Statistic` com o método:

    `double compute(List<StudentGrade> grades)`

2. Crie três concretizações desta interface por forma a poder calcular três
    estatísticas, nomeadamente **média aritmética**, **nota mais baixa** e **nota mais alta**; 

   * No caso de o conjunto de notas ser vazio, deve ser devolvido o valor -1.
   
   * Para os alunos fornecidos no projeto, os resultados esperados são:
     * Média: `13.16`
     * Nota mais baixa: `6`
     * Nota mais alta: `20`

3. Adicione um atributo do tipo `Statistic` à classe **CourseGrades** e os métodos `void changeStatistic(Statistic s)` e `double computeStatistic()`; a estatística por "omissão" calcula a média.

4. Altere o método main por forma a poder mostrar o valor das três estatísticas para o
    conjunto de alunos existentes na grelha.

### Ordenação

Adicionalmente, pretende-se dar uso ao padrão *Strategy* para permitir alterar a ordenação das nota devolvidas pelo método `CourseGrades.list()`. 

5. Faça a aplicação do padrão *Strategy* nesta funcionalidade.
	
	* Utilize a interface `GradeSorting` com o método `void sort(List<StudentGrade> list)`.

	* Implemente estratégias para ordenar por **nome**, **número (id)** e **nota**.

6. Sabendo que o método `CourseGrades.toString()` utiliza internamente o método `list()`, altere o método de ordenação no main e imprima a pauta das notas.

## Nível Avançado (Autónomo)

Pretende-se implementar o padrão de software **DAO**, que é uma versão de padrão *Strategy* utilizado na persistência de dados.

**Nota:** Este padrão será lecionado durante as aulas TPs. Assim que o aprender, tente aplicá-lo a este problema.

1. Aplique o padrão **DAO** na persistência de informação relativamente às notas contidas na classe `CourseGrades`.

2. Elabore outro método `main` que permita verificar a persistência dos dados entre execuções do programa.