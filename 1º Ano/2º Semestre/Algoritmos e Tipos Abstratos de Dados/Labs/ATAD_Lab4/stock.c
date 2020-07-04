#include <stdio.h> 
#include <stdlib.h>
#include "stock.h"


/**
 * @brief Este metodo permite criar uma nova instância de stock
 * 
 * @param designation Nome do produto presente no stock
 * @param reference Número de referência do produto presente no stock
 * @param price Preço do produto presente no stock
 * @param quantity Quantidade de cada produto no stock
 * 
 * @return Stock
 */
Stock stockCreate(char *designation, int reference, float price, int quantity){
    Product product;
    product.designation = designation;
    product.reference = reference;
    product.price = price;
    Stock stock;
    stock.product = product;
    stock.quantity = quantity;
    return stock;
}

/**
 * @brief Este metodo permite gerar um output de um determinado stock
 * 
 * @param s Stock que o utilizador deseja consultar
 */
void stockPrint(Stock s){
    printf("Product[ %s - ref: %d - price %.2f € ], qtt: %d\n", s.product.designation,
    s.product.reference,s.product.price,s.quantity);
}