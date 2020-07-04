#include <stdio.h>
#include <math.h>
#include "vector.h"
#include <stdbool.h>

/**
 * @brief Cria e inicializa uma nova instância de Vector.
 * 
 * @param x Integer
 * @param y Integer
 * @return Vector 
 */
Vector vectorCreate(int x, int y)
{
    Vector vector;
    vector.x = x;
    vector.y = y;
    return vector;
};

/**
 * @brief Imprime na consola a instância do Vector.
 * 
 * @param v Vector
 */
void vectorPrint(Vector v)
{
    printf("\nVector: X = %d - Y = %d", v.x, v.y);
};

/**
 * @brief Calcula a magnitude de um  Vector.
 * 
 * @param v Vector
 * @return Double 
 */
double vectorLength(Vector v)
{
    double vectorSize = 0;
    vectorSize = sqrt(pow(v.x, 2) + pow(v.y, 2));
    return vectorSize;
};

/**
 * @brief Calcula o produto escalar entre dois vetores.
 * 
 * @param v1 Primeiro Vector
 * @param v2 Segundo Vector
 * @return Integer 
 */
int vectorDotProduct(Vector v1, Vector v2)
{
    int scalarProduct = 0;
    scalarProduct = (v1.x * v2.x) + (v1.y * v2.y);
    return scalarProduct;
};

/**
 * @brief Verifica se existe algum par de vectores ortogonais.
 * 
 * @param list Vector Array
 * @param listSize Integer
 * @return Boolean 
 */
bool existOrthogonals(Vector list[], int listSize)
{
    // Sem ter em conta a complexidade de vectorDotProduct:
    // Complexidade Algoritmica: O(n²)
    // Levando em conta a complexidade de vectorDotProduct:
    // Complexidade Algoritmica: O(n²)
    bool value = false;
    for (int i = 0; i < listSize; i++)
    {
        for (int e = 0; e < listSize; e++)
        {
            if (list[i].x != list[e].x && list[i].y != list[e].y) {
                if (vectorDotProduct(list[i], list[e]) == 0) {
                   value = true; 
                }
            }
        }
    }
    return value;
};