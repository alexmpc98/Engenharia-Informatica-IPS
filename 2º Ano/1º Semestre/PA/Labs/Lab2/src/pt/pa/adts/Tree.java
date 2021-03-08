package pt.pa.adts;


import java.util.Collection;

/**
 * A tree is made up of a root that is a tree node that can have an arbitrary number of children. Each children is a root tree node too.
 *
 * @param <E> Type of element stored at an tree node
 *
 */
public interface Tree<E> {
  /**
   * @return the number of nodes in the tree.
   */
  public int size();

  /**
   * @return whether the tree is empty.
   */
  public boolean isEmpty();

  /**
   * @return an iterable collection of the elements.
   */
  public Iterable<Position<E>> positions();

  /**
   * @return an iterable collection of the postions of the elements of the tree.
   */
  public Iterable<E> elements();

  /** Replaces the element stored at a given node. */
  /**
   * @param position of the treeNode to replace the element
   * @param elem     the new element
   * @return the elemnet replaced
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  public E replace(Position<E> position, E elem)
          throws InvalidPositionException;

  /**
   * @return the position of root of the tree
   * @throws EmptyTreeException if root is null
   */
  public Position<E> root() throws EmptyTreeException;


  /**
   * @param position of the tree node to determine the parent
   * @return the parent of the given position
   * @throws InvalidPositionException   if position is null or is not referring to a treeNode
   * @throws BoundaryViolationException if there is no parent
   */
  public Position<E> parent(Position<E> position)
          throws InvalidPositionException, BoundaryViolationException;

  /**
   * @param position the position of the tree node to calculate the children tree nodes
   * @return an iterable collection of the children of a given node.
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  public Iterable<Position<E>> children(Position<E> position)
          throws InvalidPositionException;

  /** Returns whether a given node is internal. */
  /**
   * @param position of the tree node
   * @return true if the treenode is a internal node
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  public boolean isInternal(Position<E> position)
          throws InvalidPositionException;

  /**
   * @param position of the tree node
   * @return true if the treenode is a external node
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  public boolean isExternal(Position<E> position)
          throws InvalidPositionException;
  /** Returns whether a given node is the root of the tree. */
  /**
   * @param position of the tree node
   * @return true if the treenode is a root
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */
  public boolean isRoot(Position<E> position) throws InvalidPositionException;

  /**
   * @param parent - Position of the ascendent node
   * @param elem   - elem to insert in the tree
   * @param order  - order of the child
   * @return
   * @throws InvalidPositionException   position of parent is null or is not referring to a treeNode
   * @throws BoundaryViolationException if order is greater then the number of children of the ascenent node, or less than 0.
   */
  public Position<E> insert(Position<E> parent, E elem, int order) throws InvalidPositionException, BoundaryViolationException;

  /**
   * @param parent - Position of the ascendent node
   * @param elem   - elem to insert in the tree
   * @return
   * @throws InvalidPositionException position of parent is null or is not referring to a treeNode
   */

  public Position<E> insert(Position<E> parent, E elem) throws InvalidPositionException;

  /**
   * @param position of the treeNode to be removed
   * @return the element removed
   * @throws InvalidPositionException if position is null or is not referring to a treeNode
   */

  public E remove(Position<E> position) throws InvalidPositionException;

  /**
   * @return the height of the tree ( -1  if is empty, 0 if has just the root) that is equal to the higher level of the tree
   */
  public int height();

  /**
   * @param existingPosition Actual position of the node
   * @param newParent        New parent of the node in the new position
   */
  public void move(Position<E> existingPosition, Position<E> newParent);

}

