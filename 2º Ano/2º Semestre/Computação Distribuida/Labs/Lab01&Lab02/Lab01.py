#2- Strings
#2.1 - Inicialize a string "abc" numa variavel de nome s e:
s = "abc";
# Utilize uma função que calcule o tamanho da string
len(s);
# Escreva a sequencia de operações necessária para transformar a string "abc" na string "aaabbbccc".
newS = s[0:1] + 'aa' + s[1:2] + 'bb' + s[2:3] + 'cc';
print(newS);

#2.2 - Inicialize a string "aaabbbccc" numa váriavel de nome s:
s = "aaabbbccc"
# Utilize uma função que permita encontrar a primeira ocorrência de "b" e a primeira ocorrência de "ccc"
print(s.find('b'));
print(s.find('ccc'));

# Utilize uma função que lhe permita alterar todas as ocorrências de "a" para "X"
print(s.replace('a','X'));

# Apenas a primeira ocorrência de "a" para "X"
print(s.replace('a','X',1));

#2.3 - Partindo da string s com "aaabbbccc" , chegar as seguintes strings
#AAABBBCCC
print(s.upper());

#AAAbbbCCC
s = s.upper()
print(s.replace('B','b'));


#3 - Listas
#3.1 - Escreva a forma de obter as sublistas [4,9] e [10,23] usando slicing
l = [1,4,9,10,23];
print(l[1:3], l[3:5]);

#3.2 - Acrescente o valor 90 ao fim da lista 'l. Utilize concatenação de listas e o metódo append
l.append(90);
print(l);

#3.3 - Calcule a média dos valores da lista
avg = sum(l) / len(l)
print("Média dos valores da lista: ", avg);

#3.4 - Remova a sublista [4,9]
del(l[1:3]);
print("Lista sem [4,9] : ", l);

#4 - Modulos
import math
print(math.sin(0.0));
#Calcule o máximo divisor comum dos seguintes pares: (15, 21), (152, 200), (1988, 9765).
print(math.gcd(15,21));
print(math.gcd(152,200));
print(math.gcd(1988,9765));

#Calcule o logaritmo base 2 dos seguintes números: 0, 1, 2, 6, 9, 15.
#print(math.log2(0));
print(math.log2(1));
print(math.log2(2));
print(math.log2(6));
print(math.log2(9));
print(math.log2(15));

#Funções trigométricas
n = eval(input("Insira um número = "));
print("Coseno : ",math.cos(n));
print("Seno : ",math.sin(n));
print("Tangente : ",math.tan(n));


#5 - Funções
#5.1 - add2

def add2(x,n):
    return x + n

soma_2 = add2(3,4);
print("Soma de dois numeros: ",soma_2);

#add3
def add3(x,i,n):
    return x+i+n

soma_3 = add3(3,4,2);
print("Soma de três numeros: ", soma_3);


#5.2 - Faça uma função que retorne o maior de dois números
def biggest_number(x,n):
    return max(x,n)

biggest_2 = biggest_number(3,4);
print("O maior número é: ", biggest_2);

#5.3 - Faça uma função is_divisable que retorna uma variavel a e uma váriavel be verifique se a é divisivel por b
def is_divisable(a,b):
    if a % b == 0:
        return True;
    else:
        return False;

Divisable = is_divisable(4,2);
if(Divisable):
    print("É divisivel!");
else:
    print("Não é divisivel");

#5.4 - Faça uma função average que calcule a média de uma lista de números passada como parametro
def average(list):
    return sum(list)/len(list)

ListAVG = average([4,4,4]);
print("Média da lista: ", ListAVG);


#6 - Funções recursivas
#6.1 - Implemente a função factorial e teste com vários valores
def factorial(x):
    if x == 0:
        return 1
    else:
        return x * factorial(x-1)

factorialVariable = factorial(1);
print("Factorial 1 - ", factorialVariable);
factorialVariable = factorial(2);
print("Factorial 2 - ", factorialVariable);
factorialVariable = factorial(4);
print("Factorial 3 - ", factorialVariable);

#6.2 - Função recursiva para calcular a soma dos n primeiros números inteiros

def sumfirstnumbers(n):
    if n <= 1:
        return n;
    return n + sumfirstnumbers(n-1);
        

sumFN = sumfirstnumbers(5);
print(sumFN);


#6.3 - Função recursiva Fibonacci
def fib(n):
    if n == 1:
        return 0;
    elif n == 2:
        return 1;
    else:
        return fib(n-1) + fib(n - 2);

print(fib(9));










