package pt.pa.adts;


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

    private int size(TreeNode treeNode) {
        if(treeNode == null) return 0;

        //return 1 + size(treeNode.left) + size(treeNode.right); //BST

        int count = 1;
        for(TreeNode childTreeRoot : treeNode.children) {
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
        return root;

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

        TreeNode treeNode = checkPosition(position);

        return treeNode.children.isEmpty();

    }

    @Override
    public boolean isRoot(Position<E> position) throws InvalidPositionException {
        TreeNode treeNode = checkPosition(position);

        return (treeNode == this.root);
    }


    //insert(null, ELEMENT)
    public Position<E> insert(Position<E> parent, E elem)
            throws BoundaryViolationException, InvalidPositionException {

        if(isEmpty()){
            if( parent!= null) throw new InvalidPositionException("Pai não é nulo");
            this.root = new TreeNode(elem);
            return root;
        }

        TreeNode  parentNode = checkPosition(parent); //checkPosition: "converte" Position -> TreeNode + validação!
        TreeNode node = new TreeNode(elem, parentNode);
        parentNode.children.add(node); //List<TreeNode> children
        return node;

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
        TreeNode treeNodeRemove = checkPosition(position);

        //treeNodeRemove.parent.children.remove(treeNodeRemove);

        TreeNode parent = treeNodeRemove.parent;
        int nodeIndex = -1;
        for(int i=0; i<parent.children.size(); i++) {
            if(parent.children.get(i) == treeNodeRemove) {
                nodeIndex = i;
            }
        }
        if(nodeIndex != -1) parent.children.remove(nodeIndex);

        return treeNodeRemove.element;
    }

    @Override
    public int height() {
        throw new UnsupportedOperationException();
    }



    /*
        auxiliary method to check if Position is valid and cast to a treeNode
     */
    private TreeNode checkPosition(Position<E> position) //main: Person implements Position
            throws InvalidPositionException {
        if (position == null) {
            throw new InvalidPositionException();
        }

        try {
            TreeNode treeNode = (TreeNode) position;
            if (treeNode.children == null) {
                throw new InvalidPositionException("The position is invalid");
            }
            return treeNode;
        } catch (ClassCastException e) {
            throw new InvalidPositionException();
        }
    }

        @Override
    public Iterable<Position<E>> positions() {
        throw new UnsupportedOperationException();
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

        //breadth-order
        Queue<TreeNode> queue = new LinkedList<>();
        //enqueue: queue.offer()
        //dequeue: queue.poll()

        queue.offer(this.root);
        while(!queue.isEmpty()) {
            TreeNode n = queue.poll();
            lista.add(n.element);
            for(TreeNode child : n.children) {
                queue.offer(child);
            }
        }

        //recursive pre-order
        /*
        if (!isEmpty()) {
            elements(root, lista);
        }
         */

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

    @Override
    public Iterator<E> iterator() {
        //ArrayList<E> elementos = new ArrayList<>();
        //--> colocar em 'elementos' os elementos em pre-ordem
        //elements(this.root, elementos);
        //return elementos.iterator();

        return new MyIteratorDepthFirst();
    }

    //TODO: implementar iterador breadth-first
    private class MyIteratorBreadhFirst implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

    private class MyIteratorDepthFirst implements Iterator<E> {

        /*
        System.out.println("Travessia Depth-First:");
        Stack<Position<String>> queue = new Stack<>();
        queue.push(tree.root());
        while(!queue.isEmpty()) {
            Position<String> n = queue.pop();
            System.out.println(n.element());

            for(Position<String> f : tree.children(n)) {
                queue.push(f);
            }
        }
         */

        private Stack<Position<E>> stack = new Stack<>();

        public MyIteratorDepthFirst() {
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            Position<E> n = stack.pop();

            for(Position<E> f : children(n)) {
                stack.push(f);
            }

            return n.element();
        }
    }

    /**
     *  inner class - represent a node of a tree. Each node have a list of children, that can be empty.
     */
    private class TreeNode implements Position<E> {

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

        public E element() {
            if (element == null) {
                throw new InvalidPositionException();
            }
            return element;
        }
    }

}
