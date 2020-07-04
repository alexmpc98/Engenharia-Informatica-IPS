#include <stdio.h> 
#include <stdlib.h>
#include "stock.h"

int orderedSearch(int val, Stock *stockVetor,int start, int end);

int main(){
    Stock s = stockCreate("Cerveja", 112348654, 0.90, 30);
    stockPrint(s);
    printf("\n\n");

    char* productsDesignation[9] = {"Cafe","Agua","Limonada","Cha","Vinho","Sumo","Leite","Galao","Agua com gas"};
    int productsReferences[9] = {126789345,306982361,401872229,500034544,521222345,603481993,
                                 706787878,800045663,910226773};
    float productsPrices[9] = {0.40,0.50,1.50,0.90,1.30,2.10,0.60,0.85,1.65};
    int productsQuantity[9] = {25,52,3,1,16,14,8,15,22};
    

    Stock stockVetor[10];
    stockVetor[0] = s;
    for(int i=1;i<10; i++){
        stockVetor[i] = stockCreate(productsDesignation[i],productsReferences[i], 
        productsPrices[i], productsQuantity[i]);
    }
    printf("All products in stock:\n");
    for(int i=0;i<9;i++){
        stockPrint(stockVetor[i]);
    }
    int reference = 603481993;
    printf("\n\n");
    int qty = orderedSearch(reference,stockVetor,0,10);
    qty == -1 ? printf("O producto com referencia %d , não foi encontrado em stock!\n", reference) : 
    printf("Producto com referencia %d encontrado em stock \n    - Existem disponiveis %d unidades\n",reference,qty);
}

/**
 * @brief Este algoritmo efetua uma procura ordenada, dividindo o array ao meio recursivamente até encontrar um valor no array igual ao valor inserido, neste caso, uma referencia de um produto de um stock, num array de stocks! Posteriormente retorna a quantidade desse produto no stock.
 * 
 * @param val Número de referencia de produto no stock que o utilizador deseja procurar num conjunto de stocks 
 * @param stockVetor Array de stocks
 * @param start Posição inicial do array de stocks
 * @param end Posição final do array de stocks
 * 
 * @return int
 */
int orderedSearch(int val, Stock *stockVetor,int start, int end){
    int mid;

    Stock newVetor;
    for(int i=0; i<end-1;i++){
        for(int j=i+1; j<end;j++){
            if(stockVetor[i].product.reference > stockVetor[j].product.reference){
                newVetor = stockVetor[i];
                stockVetor[i] = stockVetor[j];
                stockVetor[j] = newVetor;
            }
        }
    }

    while(start <= end){
        mid = (start + end)/2;
    
    if(stockVetor[mid].product.reference == val)
        return stockVetor[mid].quantity;
    else if(stockVetor[mid].product.reference > val)
        end = mid - 1;
    else
        start = mid + 1;
    }
    return -1;
}