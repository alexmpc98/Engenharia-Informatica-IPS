/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa.adts;



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
        Position<String> posCao = myTree.insert(posMamifero, "Cao");
        Position<String> posGato = myTree.insert(posMamifero, "Gato");
        myTree.insert(posMamifero, "Vaca");
        myTree.insert(posAve, "Papagaio");
        Position<String> posAguia = myTree.insert(posAve, "Aguia");
        Position<String> aguiaReal = myTree.insert(posAguia, "Aguia Real");


        myTree.insert(posCao, "Labrador");
        //System.out.println("TREE " + myTree);
        System.out.println(myTree);

        System.out.println("Elemento de posAve? " + posAve.element());

        System.out.println("IsExternal (aguia real)? " + myTree.isExternal(aguiaReal));
        System.out.println("IsExternal (aguia) ? " + myTree.isExternal(posAguia));
        System.out.println("IsInternal (aguia) ? " + myTree.isInternal(posAguia));
        System.out.println("IsInternal (root) ? " + myTree.isInternal(root));

        System.out.println("Tree size? " + myTree.size()); //expected?: 10
        System.out.println("Tree height? " + myTree.height()); //expected?: 10


        System.out.println(myTree.elements());
        //pre-order:
        //[Animal, Mamifero, Cao, Labrador, Gato, Vaca, Ave, Papagaio, Aguia, Aguia Real]

        Tree<String> myTree2 = new TreeLinked("Disks");
        Position<String> root2 = myTree2.root();


        try {
            myTree.remove(root2); //expected: InvalidPositionException
            System.out.println(myTree);
        } catch (InvalidPositionException e) {
            System.out.println(e.getMessage());
        }
    }
}