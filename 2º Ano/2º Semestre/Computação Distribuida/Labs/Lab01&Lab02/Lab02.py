#1 - Ciclo for
#1.1 - Faça uma função que retorne a soma de todos os elementos de uma lista. Use o ciclo for para iterar sobre os elementos da lista
def sumelements(n):
    sum = 0;
    for value in range(len(n)):
        sum = sum + n[value];
    return sum;

b = sumelements([1,3,4,5]);
print("Sum of all elements : ",b);

#1.2 - Faça uma função que indique o valor máximo de uma lista e a sua posição na lista.
def maxvalue(n):
    max = 0;
    for value in range(len(n)):
        if max < n[value]:
            max = n[value]

    return max;

MaxVal = maxvalue([1,3,11,5,10]);
print("Max Value: ",MaxVal);

#1.3 Faça uma função que retorne o reverso de uma lista
def reverselist(n):
    reversedList = [];
    for value in list(reversed(n)):
        reversedList.append(value);
    print("Reversed List: ", reversedList);

ReverseList = reverselist([1,3,11,5,10]);

#1.4 - is_sorted
def is_sorted(n):
    for value in range(len(n) - 1):
        if(n[value] > n[value+1]):
            return "Not Sorted"
    return "Sorted"

sortListASC = is_sorted([2,3,4,5]);
print(sortListASC);

#1.5 - is_sorted_desc
def is_sorted_desc(n):
    for value in range(len(n) - 1):
        if(n[value] < n[value+1]):
            return "Not Sorted"
    return "Sorted"

sortListDESC = is_sorted_desc([5,4,3,2]);
print(sortListDESC);

#1.6 - has duplicates
def has_duplicates(n):
    for i in range(len(n)):
        for v in range(len(n)):
            if n[i] == n[v] and i != v:
                return "Has duplicates";
    return "Doesnt have duplicates";

HasDuplicates = has_duplicates([4,2,3,5]);
print(HasDuplicates);

#2.Ciclo While

#2.1 - Faça uma função que receba um número n e imprima no ecrã, por ordem decrescente, que números são pares
def printPairs(n):
    while n != 0:
        if n % 2 == 0:
           print(n);
        n = n-1;

printedPairs = printPairs(10);

#2.2 - Faça uma nova função que receba um número n e imprima todos os múltiplos de 3 entre 0 e n, por ordem crescente
def multipleby3(n):
    array = []
    while n!= 0:
        if n % 3 == 0:
            array.append(n);
        n = n-1;
    array.reverse();
    print(array);

Multiple = multipleby3(12);

#3- Dicionários
ages = {
    "Pedro" : 10,
    "Isabel": 11,
    "Ana": 9,
    "Tomas":10,
    "Manuel":10,
    "José":11,
    "Maria": 12,
    "Gabriel": 10,
}
#3.1 - Obtenha o número de alunos no dicionário
print("Number of students: ",len(ages));

#3.2 - Faça uma função que receba o dicionario como parametro e devolva a media de idade dos alunos
def avgage(dict):
    sum = 0;
    for i, n in dict.items():
        sum = sum + n;
    return sum/len(dict);

AvgAge = avgage(ages);
print(AvgAge);

#3.3 - Faça uma função que receba o dicionário e retorne o nome do aluno mais velho
def oldest_student(dict):
    oldest = 0;
    name = '';
    for i,n in dict.items():
        if n > oldest:
            oldest = n;
            name = i;
    return name;

OldestStudent = oldest_student(ages);
print(OldestStudent);


#3.4 Faça uma função que receba o dicionário e um número n e retorne um novo dicionário em que os alunos estão n anos mais velhos
def new_ages(dict,newAge):
    for i,n in dict.items():
         n = n + newAge;
         dict[i] = n;
    return dict;

NewAge = new_ages(ages,10);
print(NewAge);


#4 - Sub-dicionários
students = {
    "Pedro": {"idade": 10, "morada": "Setúbal"},
    "Isabel": {"idade": 11, "morada": "Azeitão"},
    "Ana": {"idade": 9, "morada": "Setúbal"},
}

#4.1 - Quantos estudantes estão no dicionário students? Utilize a função apropriada
print("Number of students: ",len(students));

#4.2 - Faça uma função que receba como argumento o dicionario e calcule a média de idades dos alunos
def avg_age_students(dict):
    sum = 0;
    for i, n in dict.items():
        sum = sum + n["idade"];
    return sum/len(dict);

AvgAge = avg_age_students(students);
print(AvgAge);


# 4.3 - Faça uma função que receba o dicionário, o nome de uma morada e retorne o nome de todos os alunos cuja morada
# seja a morada passada por agumento
def find_student(dict,address):
    students_with_address = []
    for i, n in dict.items():
        if n["morada"] == address:
            students_with_address.append(i);
    return students_with_address;

FindStudent = find_student(students,"Setúbal");
print("Estudantes que vivem na localidade introduzida: ",FindStudent);



















