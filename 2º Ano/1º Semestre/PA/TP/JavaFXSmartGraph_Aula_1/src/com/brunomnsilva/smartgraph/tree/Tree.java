package com.brunomnsilva.smartgraph.tree;

import java.util.Collection;

/**
 *
 * @param <E>
 */
public interface Tree<E> {

    /**
     *
     * @return
     */
    TreePosition<E> root();

    /**
     *
     * @param parent
     * @param elem
     * @return
     * @throws InvalidTreePositionException
     */
    TreePosition<E> insert(TreePosition<E> parent, E elem) throws InvalidTreePositionException, NullPointerException;

    /**
     *
     * @param parent
     * @param elem
     * @param order
     * @return
     * @throws InvalidTreePositionException
     */
    TreePosition<E> insert(TreePosition<E> parent, E elem, int order)
            throws InvalidTreePositionException, NullPointerException, IndexOutOfBoundsException;

    /**
     *
     * @param position
     * @param elem
     * @return
     * @throws InvalidTreePositionException
     */
    E replace(TreePosition<E> position, E elem) throws InvalidTreePositionException, NullPointerException;

    /**
     *
     * @param position
     * @return
     * @throws InvalidTreePositionException
     */
    E remove(TreePosition<E> position) throws InvalidTreePositionException;

    /**
     *
     * @param position
     * @param newParent
     * @throws InvalidTreePositionException
     */
    void move(TreePosition<E> position, TreePosition<E> newParent) throws InvalidTreePositionException;

    /**
     *
     * @param position
     * @return
     * @throws InvalidTreePositionException
     */
    TreePosition<E> parent(TreePosition<E> position) throws InvalidTreePositionException;

    /**
     *
     * @param position
     * @return
     * @throws InvalidTreePositionException
     */
    Collection<TreePosition<E>> children(TreePosition<E> position) throws InvalidTreePositionException;

    /**
     *
     * @param position
     * @return
     * @throws InvalidTreePositionException
     */
    boolean isRoot(TreePosition<E> position) throws InvalidTreePositionException;

    /**
     *
     * @param position
     * @return
     * @throws InvalidTreePositionException
     */
    boolean isInternal(TreePosition<E> position) throws InvalidTreePositionException;

    /**
     *
     * @param position
     * @return
     * @throws InvalidTreePositionException
     */
    boolean isExternal(TreePosition<E> position) throws InvalidTreePositionException;

    /**
     *
     * @return
     */
    Collection<E> elements();

    /**
     *
     * @return
     */
    Collection<TreePosition<E>> positions();

    /**
     *
     * @param rootPosition
     * @return
     * @throws InvalidTreePositionException
     */
    Tree<E> subTree(TreePosition<E> rootPosition) throws InvalidTreePositionException;;

    /**
     *
     * @return
     */
    int height();

    /**
     *
     * @return
     */
    int order();

    /**
     *
     * @return
     */
    int size();

    /**
     *
     * @return
     */
    boolean isEmpty();

    /**
     *
     */
    void clear();













}
