#include <stdio.h> 
#include <stdlib.h>

int binarySearch(char val, char* alphabet, int start, int end);

int main(){
    char alphabet[26] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
    'q','r','s','t','u','w','x','y','z'};
    char letter = 'r';
    int end = sizeof(alphabet)/sizeof(alphabet[0]) - 1;
    int BinarySearch = binarySearch(letter,alphabet,0,end) + 1;
    printf("Letra '%c' encontrada na posição %d do alfabeto!\n",letter,BinarySearch);
    BinarySearch == -1 ? printf("A letra %c , não foi encontrada no alfabeto!\n", letter) : 
    printf("\nLetra '%c' encontrada na posição %d do alfabeto!\n",letter,BinarySearch);
    
}

/**
 * @brief Este algoritmo efetua uma procura "binária", dividindo o array ao meio recursivamente até encontrar um valor no array igual ao valor inserido, neste caso, letra no alfabeto
 * 
 * @param val caracter que o utilizador deseja procurar no alfabeto
 * @param alphabet Alfabeto latino
 * @param start Posição inicial do array (neste caso alfabeto)
 * @param end Posição final do array (neste caso alfabeto)
 * 
 * @return int 
  */
int binarySearch(char val, char *alphabet, int start, int end){

    int mid = (start + end) / 2;

    if(start > end){
        return -1;
    }

    else if(alphabet[mid] == val){
        return mid;
    }
    else if(alphabet[mid] > val){
        return binarySearch(val,alphabet,start,mid-1);
    }
    else
        return binarySearch(val,alphabet,mid+1,end);
    
    return -1;
}