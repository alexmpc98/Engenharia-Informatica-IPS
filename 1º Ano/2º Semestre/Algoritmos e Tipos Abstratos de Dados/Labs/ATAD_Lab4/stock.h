typedef struct {
     char* designation;
     int reference;
     float price;
} Product;

typedef struct {
    Product product;
    int quantity;
} Stock;

Stock stockCreate(char *designation, int reference, float price, int quantity);
void stockPrint(Stock s);