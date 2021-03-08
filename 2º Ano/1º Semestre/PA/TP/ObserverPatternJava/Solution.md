#Exercícios - Soluções
===
1. Possível Implementação

    ```java
        public class ShoppingCartListView implements Observer {
        
            @Override
            public void update(Object arg) {
                if(arg instanceof ShoppingCart) {
                    
                    ShoppingCart cart = (ShoppingCart)arg;
                    
                    String name = cart.getName();
                    Collection<Product> products = cart.getProducts();
                    
                    double total = 0;
                    System.out.printf("\n%s\n ", cart.getName());
                    int count=1;
                    for (Product p : products) {
                        System.out.printf("\n%d : %s : %f euros ", count++, p.getName(), p.getCost());
                    }
                    System.out.println("\n");
        
                }
            }       
        }
    ```
2. Possível Implementação - alterações na main class
    ```java
       ShoppingCartListView listView = new ShoppingCartListView(); // new observer
       cart2.addObservers(costView, listView);// add the observer to cart2
    ```

3. Possível Implementação

    ```java
    public class ShoppingCartAllert implements Observer {
    private double maxValue;
    
        public ShoppingCartAllert(double maxValue) {
            this.maxValue = maxValue;
        }
    
        @Override
        public void update(Object o) {
            if(o instanceof ShoppingCart) {
                ShoppingCart cart = (ShoppingCart)o;
                Product product=cart.lastProductAdded();
                if (product.getCost()>maxValue)
                    System.out.println("ALLERT!!! - The product" + product.getName() +
                            " has exceeded the maximum value configured :"  + this.maxValue);
    
                
            }
        }
    ```

4. Possível Implementação - alterações na main class

    ```java
    ShoppingCartAllert allert= new ShoppingCartAllert(50);// new observer
    cart1.addObservers(costView,allert);// add the observer to cart2
    ```



