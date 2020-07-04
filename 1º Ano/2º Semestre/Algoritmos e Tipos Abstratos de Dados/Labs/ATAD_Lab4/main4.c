#include <stdio.h> 
#include <stdlib.h>
#include "stock.h"

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

}