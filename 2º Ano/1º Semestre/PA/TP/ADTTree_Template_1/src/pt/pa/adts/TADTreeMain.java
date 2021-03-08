/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa.adts;


import java.util.*;

/**
 *
 * @author patricia.macedo
 */
public class TADTreeMain {

    public static void main(String[] args) {
        Tree<String> myTree = new TreeLinked("Animal");
        Position<String> root = myTree.root();
        Position<String> posMamifero = myTree.insert(root, "Mamifero");
        Position<String> posAve = myTree.insert(root, "Ave");
        myTree.insert(posMamifero, "Cao");
        Position<String> posGato = myTree.insert(posMamifero, "Gato");
        myTree.insert(posMamifero, "Vaca");
        myTree.insert(posAve, "Papagaio");
        Position<String> posAguia = myTree.insert(posAve, "Aguia");
        myTree.insert(posAguia, "Aguia Real");
        //System.out.println("TREE " + myTree);
        System.out.println(myTree);

        System.out.println("IsExternal (gato)? " + myTree.isExternal(posGato));
        System.out.println("IsExternal (aguia)? " + myTree.isExternal(posAguia));
        System.out.println("IsInternal (aguia)? " + myTree.isInternal(posAguia));
        System.out.println("IsInternal (root)? " + myTree.isInternal(root));
        System.out.println("Tree size? " + myTree.size());

        for(String s : myTree) {
            System.out.println(s);
        }

        //System.out.println("Elements: ");
        //for(String s : myTree.elements()) {
        //    System.out.println(s);
        //}

        //System.out.println("-----");
        //myTree.remove(posAve);
        //System.out.println(myTree);

        //System.out.println("Tree size? " + myTree.size());
        //BFS(myTree);
        //DFS(myTree);

        //uso abusivo de iterador e modificacao de colecao
        /*
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3); list.add(4); list.add(5);
        for(Integer i : list) {
            if(i == 3) {
                list.remove(4);
            }
        }
         */
    }

    public static void BFS(Tree<String> tree) {
        System.out.println("Travessia Breadth-First:");
        /* Visitar os nós da árvore em tavessia breadth-first*/
        Queue<Position<String>> queue = new LinkedList<>();
        queue.offer(tree.root());
        while(!queue.isEmpty()) {
            Position<String> n = queue.poll();
            /* Processar o nó (pode variar consoante o problema) */
            System.out.println(n.element());

            for(Position<String> f : tree.children(n)) {
                queue.offer(f);
            }
        }
    }

    public static void DFS(Tree<String> tree) {
        System.out.println("Travessia Depth-First:");
        /* Visitar os nós da árvore em tavessia depth-first*/
        Stack<Position<String>> queue = new Stack<>();
        queue.push(tree.root());
        while(!queue.isEmpty()) {
            Position<String> n = queue.pop();
            /* Processar o nó (pode variar consoante o problema) */
            System.out.println(n.element());

            for(Position<String> f : tree.children(n)) {
                queue.push(f);
            }
        }
    }
}