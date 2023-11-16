
package material.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import material.Position;

/**
 *
 * @author mayte
 */
public class RBTree<E> implements BinarySearchTree<E> {

    private RBInfo<E> checkPosition(Position<E> v) {
        if (v != null && v instanceof RBInfo)
            return (RBInfo<E>) v;
        throw new RuntimeException("This kind of information is invalid");
    }

    //Esta clase es necesaria para guardar el valor de la altura AVL en los nodos BTNodes
    private class RBInfo<T> implements Comparable<RBInfo<T>>, Position<T> {

        private boolean isRed; // we add a color field to a BTNode
        private final T element;
        private Position<RBInfo<T>> pos;

        public RBInfo(T element) {
            this.element = element;
            this.isRed = true; //los nodos recien insertados son rojos excepto la raíz
            this.pos = null;
        }

        public void setTreePosition(Position<RBInfo<T>> pos) {
            this.pos = pos;
        }

        public Position<RBInfo<T>> getTreePosition() {
            return this.pos;
        }

        public boolean isRed() {
            return isRed;
        }

        public void setRed(boolean color) {
            isRed = color;
        }

        @Override
        public T getElement() {
            return element;
        }

        @Override
        public int compareTo(RBInfo<T> o) {
            if (element instanceof Comparable && o.element instanceof Comparable) {
                Comparable<T> c1 = (Comparable<T>) element;
                return c1.compareTo(o.element);

            } else {
                throw new ClassCastException("Element is not comparable");
            }
        }
    }

    private class RBTreeIterator<T> implements Iterator<Position<T>> {

        private final Iterator<Position<RBInfo<T>>> it;

        public RBTreeIterator(Iterator<Position<RBInfo<T>>> iterator) {
            this.it = iterator;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Position<T> next() {
            Position<RBInfo<T>> aux = it.next();
            return aux.getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
    
    private final LinkedBinarySearchTree<RBInfo<E>> t = new LinkedBinarySearchTree<>();
    private final Reestructurator r = new Reestructurator();
    
    @Override
    public Position<E> find(E value) {
        throw new RuntimeException("Not implemented.");
    }

    @Override
    public Iterable<? extends Position<E>> findAll(E value) {//no tienen sentido porque no se permiten repeticiones
        throw new RuntimeException("Not implemented.");
    }

    @Override
    public Position<E> insert(E value) {
        throw new RuntimeException("Not implemented.");
    }

    @Override
    public void remove(Position<E> pos) {
        throw new RuntimeException("Not implemented.");
    }

    
    @Override
    public Iterable<? extends Position<E>> rangeIterator(E m, E M) {
        RBInfo<E> ini = new RBInfo<>(m);
        RBInfo<E> fin = new RBInfo<>(M);
        List<RBInfo<E>> l = new ArrayList<>();
        for (Position<RBInfo<E>> p: t.rangeIterator(ini, fin)){
            l.add(p.getElement());
        }
        return l;
    }
    
    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        RBInfo<E> n = checkPosition(v);
        List<RBInfo<E>> l = new ArrayList<>();
        for (Position<RBInfo<E>> p: t.children(n.getTreePosition())){
            l.add(p.getElement());
        }
        return l;
    }
    
    @Override
    public boolean isEmpty() {
        return t.isEmpty();
    }
    
    @Override
    public int size() {
        return t.size();
    }


    @Override
    public Position<E> left(Position<E> v) {
        RBInfo<E> n = checkPosition(v);
        return t.left(n.getTreePosition()).getElement();
    }

    @Override
    public Position<E> right(Position<E> v) {
        RBInfo<E> n = checkPosition(v);
        return t.right(n.getTreePosition()).getElement();
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        RBInfo<E> n = checkPosition(v);
        return t.hasLeft(n.getTreePosition());
    }

    @Override
    public boolean hasRight(Position<E> v) {
        RBInfo<E> n = checkPosition(v);
        return t.hasRight(n.getTreePosition());
    }

    @Override
    public Position<E> root() {
        return t.root().getElement();
    }

    @Override
    public Position<E> parent(Position<E> v) {
        RBInfo<E> n = checkPosition(v);
        return t.parent(n.getTreePosition()).getElement();
    }

    @Override
    public boolean isInternal(Position<E> v) {
        RBInfo<E> n = checkPosition(v);
        return t.isInternal(n.getTreePosition());
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        RBInfo<E> n = checkPosition(v);
        return t.isLeaf(n.getTreePosition());
    }

    @Override
    public boolean isRoot(Position<E> v) {
        RBInfo<E> n = checkPosition(v);
        return t.isRoot(n.getTreePosition());
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new RBTreeIterator<E>(t.iterator());
    }
    
}
