package pt.pa.adts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This implementation stores mappings in a binary search tree. For this reason
 * elements must be comparable through the {@link Comparable} interface.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class MapBST<K extends Comparable<K>, V> implements Map<K,V> {

    private BSTNode root;

    public MapBST() {
        this.root = null;
    }

    @Override
    public V put(K key, V value) throws NullPointerException {
        if(key == null) throw new NullPointerException("This implementation does not support null keys.");

        if(isEmpty()) {
            this.root = new BSTNode(key, value, null, null, null);
            return null;
        } else {
            return insertBST(key, value, this.root);
        }
    }

    private V insertBST(K key, V value, BSTNode treeRoot) {
        int comparison = key.compareTo( treeRoot.key );
        if( comparison == 0) {
            //key already found, replace value blindly
            V oldValue = treeRoot.value;
            treeRoot.value = value;
            return oldValue;
        }
        else if( comparison < 0 ) { //insert into left sub-tree
            if( treeRoot.left == null) {
                treeRoot.left = new BSTNode(key, value, treeRoot, null, null);
                return null; //no mapping previously existed
            }
            else
                return insertBST(key, value, treeRoot.left); //recursive call
        }
        else { //insert into right sub-tree
            if( treeRoot.right == null) {
                treeRoot.right = new BSTNode(key, value, treeRoot, null, null);
                return null; //no mapping previously existed
            }
            else
                return insertBST(key, value, treeRoot.right); //recursive call
        }
    }

    @Override
    public V get(K key) throws NullPointerException {
        if(key == null) throw new NullPointerException("This implementation does not support null keys.");

        BSTNode nodeForKey = searchNodeWithKey(key, this.root);
        if(nodeForKey == null) return null;
        else return nodeForKey.value;
    }

    @Override
    public V remove(K key) throws NullPointerException {
        if(key == null) throw new NullPointerException("This implementation does not support null keys.");

        BSTNode nodeForKey = searchNodeWithKey(key, this.root);

        if(nodeForKey == null) return null; //mapping does not exist

        //Remove the node based on the three possible scenarios
        BSTNode parent = nodeForKey.parent;
        V removedValue = nodeForKey.value;

        //case 1: external node (no subtrees), just remove the node from the tree
        if( nodeForKey.left == null && nodeForKey.right == null ) {
            if(parent == null) { //its must be the root node, because it has no parent
                this.root = null;
            } else if( parent.left == nodeForKey ) {
                parent.left = null;
            } else { //parent.right == treeRoot
                parent.right = null;
            }
        }
        //case 3: has both sub-tree; two possible methods
        //1 - replace node contents with rightmost contents of the left sub-tree (maximum key)
        //2 - replace node contents with leftmost contents of the right sub-tree (minimum key)
        //Following code uses method #2
        else if( nodeForKey.left != null && nodeForKey.right != null ) {
            BSTNode minimumRight = getLeftmostNode(nodeForKey.right);
            //the following order is critical; remove before replacing
            remove(minimumRight.key);
            nodeForKey.key = minimumRight.key;
            nodeForKey.value = minimumRight.value;

        }
        //case 2: only has one sub-tree; replace node with sub-tree
        else {
            //which exists? left or right sub-tree?
            BSTNode subTree = (nodeForKey.left != null) ? nodeForKey.left : nodeForKey.right;

            if(nodeForKey == this.root) { //it may be the root node
                this.root = subTree;
            } else {
                if( parent.left == nodeForKey ) {
                    parent.left = subTree;
                } else {
                    parent.right = subTree;
                }
            }
        }

        return removedValue; //return previously associated value with removed key
    }

    private BSTNode getLeftmostNode(BSTNode treeRoot) {
        /* if(isEmpty()) return null;

        if(treeRoot.left == null) return treeRoot;

        return getLeftmostNode(treeRoot.left);
        */
        if(treeRoot == null) return null;
        BSTNode curNode = treeRoot;
        while(curNode.left != null){
            curNode = curNode.left;
        }
        return curNode;
    }

    @Override
    public boolean containsKey(K key) throws NullPointerException {
        if(key == null) throw new NullPointerException("This implementation does not support null keys.");

        return searchNodeWithKey(key, this.root) != null;
    }

    private BSTNode searchNodeWithKey(K key, BSTNode treeRoot) {
        if(treeRoot == null) return null;

        if( key.compareTo(treeRoot.key) == 0 ) return treeRoot;

        if( key.compareTo(treeRoot.key) < 0 )
            return searchNodeWithKey(key,treeRoot.left);
        else
            return searchNodeWithKey(key,treeRoot.right);
    }

    @Override
    public Collection<K> keys() {
        List<K> keys = new ArrayList<>();
        appendKeys(this.root, keys);
        return keys;
    }

    private void appendKeys(BSTNode treeRoot, List<K> keys) {
        if(treeRoot == null) return;

        //pre-order traversal
        /*
        keys.add(treeRoot.key);
        appendKeys(treeRoot.left, keys);
        appendKeys(treeRoot.right, keys);
        */

        //em-order traversal
        appendKeys(treeRoot.left, keys);
        keys.add(treeRoot.key);
        appendKeys(treeRoot.right, keys);

        //pos-order traversal
        /*
        appendKeys(treeRoot.left, keys);
        appendKeys(treeRoot.right, keys);
        keys.add(treeRoot.key);
        */

    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        appendValues(this.root, values);
        return values;
    }

    private void appendValues(BSTNode treeRoot, List<V> values) {
        if(treeRoot == null) return;

        //pre-order traversal
        values.add(treeRoot.value);
        appendValues(treeRoot.left, values);
        appendValues(treeRoot.right, values);
    }

    @Override
    public int size() {
        return size(this.root);
    }

    private int size(BSTNode treeRoot) {
        if(treeRoot == null) return 0;
        else return 1 + size(treeRoot.left) + size(treeRoot.right);
    }

    @Override
    public boolean isEmpty() {
        return (this.root == null);
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public String toString() {
        if(isEmpty()) return "Empty binary search tree.";

        StringBuilder sb = new StringBuilder();
        inOrderPrettyString(root, new StringBuilder(), true, sb);

        return String.format("MapBST of size = %d:", size())
                + "\n"
                + sb.toString();
    }

    private void inOrderPrettyString(BSTNode treeRoot, StringBuilder prefix, boolean isTail, StringBuilder sb) {

        if(treeRoot.right != null) {
            inOrderPrettyString(treeRoot.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }

        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(String.format("{key=%s, value=%s", treeRoot.key, treeRoot.value)).append("\n");

        if(treeRoot.left != null) {
            inOrderPrettyString(treeRoot.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
    }

    private class BSTNode {
        private K key;
        private V value;

        private BSTNode parent;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value, BSTNode parent, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
