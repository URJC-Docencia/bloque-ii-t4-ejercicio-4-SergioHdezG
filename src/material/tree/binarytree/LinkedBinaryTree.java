package material.tree.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import material.Position;

/**
 *
 * @author A. Duarte, J. Vélez
 * @param <E>
 * @see BinaryTree
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {

    private LinkedBinaryTree<E> checkBinaryTree(BinaryTree<E> lbt) {
        if (lbt != null && lbt instanceof LinkedBinaryTree)
            return (LinkedBinaryTree<E>)lbt;
        throw new RuntimeException("This is another type of BinaryTree");
    }

    

    protected class BTNode<T> implements Position<T> {

        private T element;
        private BTNode<T> left, right, parent;

        /**
         * Main constructor
         *
         * @param element
         * @param parent
         * @param left
         * @param right
         */
        public BTNode(T element, BTNode<T> parent, BTNode<T> left, BTNode<T> right) {
            setElement(element);
            setParent(parent);
            setLeft(left);
            setRight(right);
        }

        /**
         * Returns the element stored at this position
         *
         * @return
         */
        @Override
        public T getElement() {
            return element;
        }

        /**
         * Sets the element stored at this position
         *
         * @param o
         */
        public final void setElement(T o) {
            element = o;
        }

        /**
         * Returns the left child of this position
         *
         * @return
         */
        public final BTNode<T> getLeft() {
            return left;
        }

        /**
         * Sets the left child of this position
         *
         * @param v
         */
        public final void setLeft(BTNode<T> v) {
            left = v;
        }

        /**
         * Returns the right child of this position
         *
         * @return
         */
        public final BTNode<T> getRight() {
            return right;
        }

        /**
         * Sets the right child of this position
         *
         * @param v
         */
        public final void setRight(BTNode<T> v) {
            right = v;
        }

        /**
         * Returns the parent of this position
         *
         * @return
         */
        public final BTNode<T> getParent() {
            return parent;
        }

        /**
         * Sets the parent of this position
         *
         * @param v
         */
        public final void setParent(BTNode<T> v) {
            parent = v;
        }
    }

    private BTNode<E> root;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        root = null;
    }

    /**
     * Returns whether the tree is empty.
     */
    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * Returns whether a node is internal.
     *
     * @param v
     * @return
     */
    @Override
    public boolean isInternal(Position<E> v) {
        checkPosition(v);
        return (hasLeft(v) || hasRight(v));
    }

    /**
     * Returns whether a node is external.
     *
     * @param p
     * @return
     */
    @Override
    public boolean isLeaf(Position<E> p) {
        return !isInternal(p);
    }

    /**
     * Returns whether a node is the root.
     *
     * @param p
     * @return
     */
    @Override
    public boolean isRoot(Position<E> p) {
        checkPosition(p);
        return (p == root());
    }

    /**
     * Returns whether a node has a left child.
     *
     * @param p
     * @return
     */
    @Override
    public boolean hasLeft(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return (node.getLeft() != null);
    }

    /**
     * Returns whether a node has a right child.
     *
     * @param p
     * @return
     */
    @Override
    public boolean hasRight(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return (node.getRight() != null);
    }

    /**
     * Returns the root of the tree.
     *
     * @return
     */
    @Override
    public Position<E> root() {
        if (root == null)
            throw new RuntimeException("This tree is empty");
        return root;
    }

    /**
     * Returns the left child of a node.
     *
     * @param p
     * @return
     */
    @Override
    public Position<E> left(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        Position<E> leftPos = node.getLeft();
        if (leftPos == null) {
            throw new RuntimeException("No left child");
        }
        return leftPos;
    }

    /**
     * Returns the right child of a node.
     *
     * @param p
     * @return
     */
    @Override
    public Position<E> right(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        Position<E> rightPos = node.getRight();
        if (rightPos == null) {
            throw new RuntimeException("No right child");
        }
        return rightPos;
    }

    /**
     * Returns the parent of a node.
     *
     * @param p
     */
    @Override
    public Position<E> parent(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        Position<E> parentPos = node.getParent();
        if (parentPos == null) {
            throw new RuntimeException("No parent");
        }
        return parentPos;
    }

    /**
     * Returns an iterable collection of the children of a node.
     *
     * @param p
     */
    @Override
    public Iterable<? extends Position<E>> children(Position<E> p) {
        List<Position<E>> children = new ArrayList<>();
        if (hasLeft(p)) {
            children.add(left(p));
        }
        if (hasRight(p)) {
            children.add(right(p));
        }
        return Collections.unmodifiableCollection(children);
    }

    /**
     * Returns an iterator of the elements stored at the nodes.
     */
    @Override
    public Iterator<Position<E>> iterator() {
        return new InorderBinaryTreeIterator<>(this, root);
    }

    /**
     * Replaces the element at a node.
     *
     * @param p updated position.
     * @param e element introduced in position p.
     * @return
     */
    @Override
    public E replace(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        E temp = p.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Return the sibling of a node
     *
     * @param p
     * @return
     */
    @Override
    public Position<E> sibling(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        BTNode<E> parentPos = node.getParent();
        if (parentPos != null) {
            BTNode<E> sibPos;
            BTNode<E> leftPos = parentPos.getLeft();
            if (leftPos == node) {
                sibPos = parentPos.getRight();
            } else {
                sibPos = parentPos.getLeft();
            }
            if (sibPos != null) {
                return sibPos;
            }
        }
        throw new RuntimeException("No sibling");
    }

    /**
     * Adds a root node to an empty tree
     *
     * @param e
     * @return
     */
    @Override
    public Position<E> addRoot(E e) {
        if (!isEmpty()) {
            throw new RuntimeException("Tree already has a root");
        }
        root = new BTNode<>(e, null, null, null);
        return root;
    }

    /**
     * Inserts a left child at a given node.
     *
     * @param p
     * @param e
     * @return
     */
    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        Position<E> leftPos = node.getLeft();
        if (leftPos != null) {
            throw new RuntimeException("Node already has a left child");
        }
        BTNode<E> newNode = new BTNode<>(e, node, null, null);
        node.setLeft(newNode);
        return newNode;
    }

    /**
     * Inserts a right child at a given node.
     *
     * @param p
     * @param e
     * @return
     */
    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        Position<E> rightPos = node.getRight();
        if (rightPos != null) {
            throw new RuntimeException("Node already has a right child");
        }
        BTNode<E> newNode = new BTNode<>(e, node, null, null);
        node.setRight(newNode);
        return newNode;
    }

    /**
     * Removes a node with zero or one child.
     *
     * @param p
     * @return
     */
    @Override
    public E remove(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        BTNode<E> leftPos = node.getLeft();
        BTNode<E> rightPos = node.getRight();
        if (leftPos != null && rightPos != null) {
            throw new RuntimeException("Cannot remove node with two children");
        }
        BTNode<E> child; // the only child of v, if any
        if (leftPos != null) {
            child = leftPos;
        } else if (rightPos != null) {
            child = rightPos;
        } else // v is a leaf
        {
            child = null;
        }
        if (node == root) { // v is the root
            if (child != null) {
                child.setParent(null);
            }
            root = child;
        } else { // v is not the root
            BTNode<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
            if (child != null) {
                child.setParent(parent);
            }
        }
        return p.getElement();
    }


    /**
     * Swap the elements at two nodes
     *
     * @param p1
     * @param p2
     */
    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        BTNode<E> node1 = checkPosition(p1);
        BTNode<E> node2 = checkPosition(p2);
        BTNode<E> parent1,parent2,hi1,hi2,hd1,hd2;
        parent1 = node1.parent; parent2 = node2.parent;
        hi1 = node1.left; hi2 = node2.left;
        hd1 = node1.right; hd2 = node2.right;

        node1.parent = (parent2 == node1) ? node2: parent2;
        node1.left = (hi2 == node1)? node2: hi2;
        node1.right = (hd2 == node1)? node2: hd2;
        node2.parent = (parent1 == node2)?node1:parent1;
        node2.left = (hi1 == node2)?node1:hi1;
        node2.right = (hd1 == node2)?node1:hd1;
 
        //if (!related(node1,node2)){
             if (parent1 != null && parent1 !=node2){
                if (node1 == parent1.left)
                    parent1.left = node2;
                else
                    parent1.right = node2;
            }
            if (parent2 != null && parent2 != node1){
                if (node2 == parent2.left)
                    parent2.left = node1;
                else
                    parent2.right = node1;
            }
            if (hi1 != null && hi1 != node2){
                hi1.parent = node2;
            }
            if (hd1 != null&& hd1 != node2){
                hd1.parent = node2;
            }
            if (hi2 != null && hi2 != node1){
                hi2.parent = node1;
            }
            if (hd2 != null && hd2 != node1){
                hd2.parent = node1;
            }

        //}
    }
    private boolean related(BTNode<E> node1, BTNode<E> node2) {
        return (node1.parent == node2 || node2.parent == node1);
    }

    // Auxiliary methods
    /**
     * If v is a good binary tree node, cast to BTPosition, else throw exception
     */
    private BTNode<E> checkPosition(Position<E> p) {
        if (p == null || !(p instanceof BTNode)) {
            throw new RuntimeException("The position is invalid");
        }
        return (BTNode<E>) p;
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the preorder traversal of the subtree.
     *
     * @param p
     * @param pos
     */
    protected void preorderPositions(Position<E> p, List<Position<E>> pos) {
        pos.add(p);
        if (hasLeft(p)) {
            preorderPositions(left(p), pos); // recurse on left child
        }
        if (hasRight(p)) {
            preorderPositions(right(p), pos); // recurse on right child
        }
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node, ordered
     * according to the inorder traversal of the subtree.
     *
     * @param v
     * @param pos
     */
    protected void inorderPositions(Position<E> v, List<Position<E>> pos) {
        if (hasLeft(v)) {
            inorderPositions(left(v), pos); // recurse on left child
        }
        pos.add(v);
        if (hasRight(v)) {
            inorderPositions(right(v), pos); // recurse on right child
        }
    }


    /**
     * Create un new tree from node v.
     *
     * @param v new root node
     * @return  The new tree.
     */
    public LinkedBinaryTree<E> subTree(Position<E> v) {
        BTNode<E> newRoot = checkPosition(v);
        
        if (newRoot == root) {
            root = null;
        } else {        
            if (newRoot.parent.left == newRoot)
                newRoot.parent.left = null;
            else
                newRoot.parent.right = null;
        }
        
        newRoot.parent = null;
                                
        LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();
        tree.root = newRoot;
        return tree;
    }

    /**
     * Attach the tree t at the root of this tree 
     * @param lbt
     */
    public void attach(BinaryTree<E> lbt) {
        if (lbt == this)
            throw new RuntimeException("Cannot attach a tree over himself");
        if (root != null)
            throw new RuntimeException("Cannot attach root to a not empty tree");
        LinkedBinaryTree<E> t = checkBinaryTree(lbt);
        if (lbt != null && !lbt.isEmpty()) {
            BTNode<E> r = checkPosition(t.root());
            root = r;
            t.root = null;
        }
    }
    
    
    /**
     * Attach tree t as left children of node p
     * @param p 
     * @param lbt 
     */
    @Override
    public void attachLeft(Position<E> p, BinaryTree<E> lbt) {
        BTNode<E> node = checkPosition(p);
                
        if (lbt == this) {
            throw new RuntimeException("Cannot attach a tree over himself");
        }

        if (hasLeft(p))
            throw new RuntimeException("Cannot attach a tree in a non empty method");            
        LinkedBinaryTree<E> t = checkBinaryTree(lbt);
        if (lbt != null && !lbt.isEmpty()) {
            BTNode<E> r = checkPosition(t.root());
            node.setLeft(r);
            r.setParent(node);
            t.root = null;
        }
    }

    /**
     * Attach tree t as right children of node p
     * @param p
     * @param lbt 
     */
    @Override
    public void attachRight(Position<E> p, BinaryTree<E> lbt) {
        BTNode<E> node = checkPosition(p);
               
        if (lbt == this)
            throw new RuntimeException("Cannot attach a tree over himself");

        if (hasRight(p))
            throw new RuntimeException("Cannot attach a tree in a non empty method");            
        LinkedBinaryTree<E> t = checkBinaryTree(lbt);
        if (lbt != null && !lbt.isEmpty()) {
            BTNode<E> r = checkPosition(t.root());
            node.setRight(r);
            r.setParent(node);
            t.root = null;
        }
    }

}
