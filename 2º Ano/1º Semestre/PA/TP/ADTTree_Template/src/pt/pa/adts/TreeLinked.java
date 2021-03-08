package pt.pa.adts;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * @author patricia.macedo
 * @param <E> type of elements of the tree
 */
public class TreeLinked<E> implements Tree<E> {

    //** TreeNode implemented as a inner class at the end **/
    
    private TreeNode root; 


    public TreeLinked() {
     this.root=null;
    }
   
    public TreeLinked(E root) {
        this.root = new TreeNode(root);
    }

    @Override
    public int size() {
        return size(this.root);
    }

    private int size(TreeNode treeRoot) {
        //impl. recursiva

        //BST
        //if(treeRoot == null) return 0;
        //return 1 + size(treeRoot.left) + size(treeRoot.right);

        if(treeRoot == null) return 0;
        //return 1 + size(treeRoot.children.get(0) + size(treeRoot.children.get(1))) + ...

        int count = 1;
        for(TreeNode childTreeRoot : treeRoot.children) {
            count += size(childTreeRoot);
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public E replace(Position<E> position, E e) throws InvalidPositionException {
        TreeNode node = checkPosition(position);
        E replacedElem= node.element;
        node.element= e;
        return replacedElem;
    }

    @Override
    public Position<E> root() throws EmptyTreeException {
        return this.root;

    }

    @Override
    public Position<E> parent(Position<E> position) throws InvalidPositionException, BoundaryViolationException {
        TreeNode node = checkPosition(position);
        return node.parent;

    }

    @Override
    public Iterable<Position<E>> children(Position<E> position) throws InvalidPositionException {
        TreeNode node = checkPosition(position);
        ArrayList<Position<E>> list = new ArrayList<>();
        for (Position<E> pos : node.children) {
            list.add(pos);
        }
        return list;
    }

    @Override
    public boolean isInternal(Position<E> position) throws InvalidPositionException {
        TreeNode aux = checkPosition(position);
        return !aux.children.isEmpty() && aux != this.root;
    }

    @Override
    public boolean isExternal(Position<E> position) throws InvalidPositionException {

        //converter position -> treenode?
        TreeNode treeNode = checkPosition(position);

        return treeNode.children.isEmpty();
    }

    @Override
    public boolean isRoot(Position<E> position) throws InvalidPositionException {

        //converter position -> treenode?
        TreeNode treeNode = checkPosition(position);

        return (treeNode == this.root);

    }


    public Position<E> insert(Position<E> parent, E elem)
            throws BoundaryViolationException, InvalidPositionException {

        if(isEmpty()){
            if( parent!= null) throw new InvalidPositionException("Pai não é nulo");
            this.root = new TreeNode(elem);
            return root;
        }

        TreeNode  parentNode = checkPosition(parent); //checkPosition converte Position -> TreeNode + validações
        TreeNode node = new TreeNode(elem, parentNode);
        parentNode.children.add(node);
        return node; //TreeNode implements Position

    }


    @Override
    public Position<E> insert(Position<E> parent, E elem, int order)
            throws BoundaryViolationException, InvalidPositionException {
        if(isEmpty()){
            if( parent!= null) throw new InvalidPositionException("Pai não é nulo");
            if (order != 0 ) throw new BoundaryViolationException("Fora de limites");
            this.root = new TreeNode(elem);
            return root;
        }
        TreeNode parentNode = checkPosition(parent);
        if (order < 0 || order > parentNode.children.size()) {
            throw new BoundaryViolationException("Fora de limites");
        }
        TreeNode node = new TreeNode(elem, parentNode);
        parentNode.children.add(order, node);
        return node;

    }


    @Override
    public E remove(Position<E> position) throws InvalidPositionException {

        TreeNode treeNode = checkPosition(position);
        if(treeNode == this.root) {
            this.root = null;
            return treeNode.element;
        }

        TreeNode parent = treeNode.parent;
        parent.children.remove(treeNode);

        return treeNode.element;

    }

    @Override
    public int height() {
        //throw new NotImplementedException();
        //TODO: TPC
        return height(this.root);
    }

    private int height(TreeNode treeRoot) {
        if(treeRoot == null) return -1;
        if(treeRoot.children.isEmpty()) return 0; /* importante */

        //int treeHeight = 1;
        /* Algoritmo de seleção de máximo: */
        int childMaxHeight = Integer.MIN_VALUE;
        for(TreeNode childRoot : treeRoot.children) {
            int childHeight = height(childRoot);
            if(childHeight > childMaxHeight) {
                childMaxHeight = childHeight;
            }
        }
        return 1 + childMaxHeight;
    }



    /*
        auxiliary method to check if Position is valid and cast to a treeNode
     */
    private TreeNode checkPosition(Position<E> position)
            throws InvalidPositionException {
        if (position == null) {
            throw new InvalidPositionException();
        }

        try {
            TreeNode treeNode = (TreeNode) position;
            if (treeNode.children == null) {
                throw new InvalidPositionException("The position is invalid");
            }

            if(!belongs(treeNode))
                throw new InvalidPositionException("This position does not belong to this tree.");

            return treeNode;
        } catch (ClassCastException e) {
            throw new InvalidPositionException();
        }
    }

    private boolean belongs(TreeNode node) {
        if(node == null) return false;
        TreeNode current = node;
        while(current.parent != null) {
            current = current.parent;
        }

        return current == this.root;
    }

        @Override
    public Iterable<Position<E>> positions() {
        //throw new NotImplementedException();
        ArrayList<Position<E>> lista = new ArrayList<>();
        if (!isEmpty()) {
            positions(root, lista);
        }
        return lista;
    }

    /** auxiliary recursive method for positions() method**/
    private void positions(Position<E> position, ArrayList<Position<E>> lista) {

        for (Position<E> w : children(position)) {
            positions(w, lista);
        }
        lista.add(lista.size(), position); // visit (position)
    }

    /** auxiliary recursive method for elements() method**/
    private void elements(Position<E> position, ArrayList<E> lista) {

        lista.add(lista.size(), position.element()); // visit (position) primeiro, pre-order
        for (Position<E> w : children(position)) {
            elements(w, lista);
        }

    }

    @Override
    public Iterable<E> elements() {
        ArrayList<E> lista = new ArrayList<>();
        //if (!isEmpty()) {
        //    elements(root, lista);

        //breadth-first (iterativo)
        Queue<TreeNode> queue = new LinkedList<>();
        //queue.enqueue -> queue.offer
        //queue.dequeue -> queue.poll

        queue.offer(this.root);
        while(!queue.isEmpty()) {
            TreeNode n = queue.poll();
            lista.add(n.element);
            for(TreeNode f : n.children) {
                queue.offer(f);
            }
        }

        //TPC: substituir Queue por Stack (java) e ver o resultado!!
        return lista;
    }

    private String toStringPreOrder(Position<E> position) {
        String str = position.element().toString(); // visit (position)
        for (Position<E> w : children(position)) {
            str += "," + toStringPreOrder(w);
        }
        return str;
    }

    public String toString() {
        String str = "";
        if (!isEmpty()) {
            str = toStringPreOrderLevels(root, 1);
        }

        return str;
    }

    /* auxiliary method to write Tree, using preorder aproach */

    private String toStringPreOrderLevels(Position<E> position, int level) {
        String str = position.element().toString(); // visit (position)
        for (Position<E> w : children(position)) {
            str += "\n" + printLevel(level) + toStringPreOrderLevels(w, level + 1);
        }
        return str;
    }

    /** auxiliary method to format a level of the tree*/

    private String printLevel(int level) {
        String str = "";
        for (int i = 0; i < level; i++) {
            str += "  ";
        }
        return str + "-";
    }

    /**
     *  inner class - represent a node of a tree. Each node have a list of children, that can be empty.
     */
    private class TreeNode implements Position<E> {  // TreeNode node = ...; node.instanceof(Position) -> true

        private E element;  // element stored at this node
        private TreeNode parent;  // adjacent node
        private List<TreeNode> children;  // children nodes

        TreeNode(E element) {
            this.element = element;
            parent = null;
            children = new ArrayList<>();
        }

        TreeNode(E element, TreeNode parent) {
            this.element = element;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        @Override
        public E element() {
            return element;
        }
    }

}
