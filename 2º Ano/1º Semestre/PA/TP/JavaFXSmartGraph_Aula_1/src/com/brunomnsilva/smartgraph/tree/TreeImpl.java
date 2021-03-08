package com.brunomnsilva.smartgraph.tree;

import java.util.*;

public class TreeImpl<E> implements Tree<E> {

    private TreeNode root;
    private int size;

    /**
     * Creates an empty tree.
     */
    public TreeImpl() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Creates a tree with an initial root element.
     * @param rootElement element to store at root.
     */
    public TreeImpl(E rootElement) {
        this.root = new TreeNode(rootElement);
        this.size = 1;
    }

    private TreeImpl(TreeNode treeRoot) {
        if(treeRoot == null) throw new NullPointerException("Cannot copy a null tree.");

        /* We're traversing the original tree and copying the tree starting at this.root */
        Queue<TreeNode> queueTree = new LinkedList<>();
        Queue<TreeNode> queueThis = new LinkedList<>();

        queueTree.offer(treeRoot);

        this.root = new TreeNode(treeRoot.element);
        this.size = 1;

        queueThis.offer(this.root);

        while(!queueTree.isEmpty()) {
            TreeNode nodeTree = queueTree.poll();
            TreeNode currentThis = queueThis.poll();

            for(TreeNode childNode : nodeTree.children) {
                queueTree.offer(childNode);

                TreeNode copy = new TreeNode(childNode.element, currentThis);
                currentThis.children.add( copy );
                this.size++;

                queueThis.offer(copy);
            }
        }

    }

    @Override
    public TreePosition<E> root() {
        return this.root;
    }

    @Override
    public TreePosition<E> insert(TreePosition<E> parent, E elem) throws InvalidTreePositionException, NullPointerException {
        if(elem == null) throw new NullPointerException("This implementation does not allow for nulls.");

        /* if the tree is empty and parent == null then we insert the element at the root node */
        TreeNode insertedNode = null;
        if(this.root == null && parent == null) {
            this.root = insertedNode = new TreeNode(elem);
        } else {
            TreeNode treeNode = checkPosition(parent);
            treeNode.children.add( insertedNode = new TreeNode(elem, treeNode) );
        }

        this.size++;

        return insertedNode;
    }

    @Override
    public TreePosition<E> insert(TreePosition<E> parent, E elem, int order) throws InvalidTreePositionException, NullPointerException, IndexOutOfBoundsException {
        if(elem == null) throw new NullPointerException("This implementation does not allow for nulls.");

        TreeNode treeNode = checkPosition(parent);

        if(order < 0 || order > treeNode.childCount())
            throw new IndexOutOfBoundsException(String.format("Specified order is out of bounds. Allowed: [0, %d]", treeNode.childCount()));

        TreeNode insertedNode = new TreeNode(elem, treeNode);
        treeNode.children.add(order, insertedNode);
        this.size++;

        return insertedNode;
    }

    @Override
    public E replace(TreePosition<E> position, E elem) throws InvalidTreePositionException, NullPointerException {
        if(elem == null) throw new NullPointerException("This implementation does not allow for nulls.");

        TreeNode treeNode = checkPosition(position);

        E old = treeNode.element;
        treeNode.element = elem;
        return old;
    }

    @Override
    public E remove(TreePosition<E> position) throws InvalidTreePositionException {
        TreeNode treeNode = checkPosition(position);

        if(treeNode == this.root) {
            this.root = null;
            this.size = 0;
        } else {
            int sizeTree = sizeRecursive(treeNode);
            treeNode.parent.children.remove(treeNode);
            this.size -= sizeTree;
        }

        return treeNode.element;
    }

    @Override
    public void move(TreePosition<E> position, TreePosition<E> newParent) throws InvalidTreePositionException {
        TreeNode treeNode = checkPosition(position);
        if(treeNode == this.root) throw new InvalidTreePositionException("Cannot move the root.");

        TreeNode treeNodeParent = checkPosition(newParent);

        treeNode.parent.children.remove(treeNode);
        treeNodeParent.children.add(treeNode);
        treeNode.parent = treeNodeParent;
    }

    @Override
    public TreePosition<E> parent(TreePosition<E> position) throws InvalidTreePositionException {
        TreeNode treeNode = checkPosition(position);
        return treeNode.parent;
    }

    @Override
    public Collection<TreePosition<E>> children(TreePosition<E> position) throws InvalidTreePositionException {
        TreeNode treeNode = checkPosition(position);

        List<TreePosition<E>> children = new LinkedList<>();

        for(TreeNode node : treeNode.children) {
            children.add(node);
        }

        return children;
    }

    @Override
    public boolean isRoot(TreePosition<E> position) throws InvalidTreePositionException {
        TreeNode treeNode = checkPosition(position);
        return (treeNode == this.root);
    }

    @Override
    public boolean isInternal(TreePosition<E> position) throws InvalidTreePositionException {
        TreeNode treeNode = checkPosition(position);
        return (treeNode != this.root && !treeNode.children.isEmpty());
    }

    @Override
    public boolean isExternal(TreePosition<E> position) throws InvalidTreePositionException {
        TreeNode treeNode = checkPosition(position);
        return (treeNode.children.isEmpty());
    }

    @Override
    public Collection<E> elements() {
        List<E> elements = new LinkedList<>();
        for(TreePosition<E> p : positions()) {
            elements.add(p.element());
        }
        return elements;
    }

    @Override
    public Collection<TreePosition<E>> positions() {
        List<TreePosition<E>> positions = new LinkedList<>();

        /* Breadth-first traversal */
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this.root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            positions.add(node);
            for(TreeNode childNode : node.children) {
                queue.offer(childNode);
            }
        }

        return positions;
    }

    @Override
    public Tree<E> subTree(TreePosition<E> rootPosition) throws InvalidTreePositionException {
        TreeNode rootNode = checkPosition(rootPosition);
        return new TreeImpl<>(rootNode);
    }

    @Override
    public int height() {
        return height(this.root);
        //TODO: convert to iterative version? https://www.geeksforgeeks.org/iterative-method-to-find-height-of-binary-tree/
    }



    private int height(TreeNode treeRoot) {
        if (treeRoot == null) return -1;
        if (treeRoot.children.isEmpty()) return 0;

        int childMaxHeight = Integer.MIN_VALUE;
        for (TreeNode childRoot : treeRoot.children) {
            int childHeight = height(childRoot);
            if (childHeight > childMaxHeight) {
                childMaxHeight = childHeight;
            }
        }
        return 1 + childMaxHeight;
    }

    @Override
    public int order() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public int size() {
        return this.size;
    }

    private int sizeRecursive(TreeNode treeRoot) {
        if(treeRoot == null) return 0;

        int descendentCount = 0;
        for(TreeNode child : treeRoot.children) {
            descendentCount += sizeRecursive(child);
        }

        return 1 + descendentCount;
    }

    @Override
    public boolean isEmpty() {
        return (this.root == null);
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        String str = "";
        if (!isEmpty()) {
            str = toStringPreOrderLevels(root, 1);
        }

        return str;
    }

    private TreeNode checkPosition(TreePosition<E> position) throws InvalidTreePositionException {

        if (position == null)  throw new InvalidTreePositionException("The tree position cannot be null.");

        try {
            TreeNode treeNode = (TreeNode) position;

            /* Check in position belongs to this tree.
            The easiest method is to check if the ascending root is this one */
            if(findRoot(treeNode) != this.root)
                throw new InvalidTreePositionException("The tree position does not belong to this tree.");

            return treeNode;
        } catch (ClassCastException e) {
            throw new InvalidTreePositionException("The tree position is of an invalid type.");
        }
    }

    private TreeNode findRoot(TreeNode treeNode) {
        /* Already the root */
        if(treeNode.parent == null) return treeNode;

        TreeNode current = treeNode;
        while(current.parent != null) {
            current = current.parent;
        }
        return current;
    }

    private String toStringPreOrderLevels(TreeNode treeNode, int level) {
        String str = treeNode.element.toString();
        for (TreeNode w : treeNode.children) {
            str += "\n" + levelPadding(level) + toStringPreOrderLevels(w, level + 1);
        }
        return str;
    }

    private String levelPadding(int level) {
        return new String(new char[level]).replace("\0", "\t");
    }

    private class TreeNode implements TreePosition<E> {
        E element;
        TreeNode parent;
        List<TreeNode> children;

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

        int childCount() {
            return this.children.size();
        }

        @Override
        public E element() {
            return element;
        }

    }
}
