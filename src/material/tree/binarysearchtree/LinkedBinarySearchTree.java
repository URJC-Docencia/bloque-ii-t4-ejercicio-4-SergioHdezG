
package material.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import material.Position;
import material.tree.binarytree.InorderBinaryTreeIterator;
import material.tree.binarytree.LinkedBinaryTree;

/**
 *
 * @author mayte
 * @param <E>
 */
public class LinkedBinarySearchTree<E> implements BinarySearchTree<E> {
    
    private LinkedBinaryTree<E> t = new LinkedBinaryTree<>();
    private Comparator<E> comp;
    private int size = 0;
    
    public LinkedBinarySearchTree(Comparator<E> c){
        this.comp = c;
    }
    
   public LinkedBinarySearchTree(){
       this(new DefaultComparator<>());
   }

    @Override
    public Position<E> find(E value) {
        if (t.isEmpty())
            return null;
        Position<E> p = t.root();
        boolean stop = p==null || comp.compare(p.getElement(), value)==0;
        while (!stop){
            if ((comp.compare(value,p.getElement()) > 0) && t.hasRight(p))
                p = t.right(p);
            else if (comp.compare(value,p.getElement()) < 0 && t.hasLeft(p))
                    p = t.left(p);
            else 
                stop = true;        
        }
        if (comp.compare(value, p.getElement()) == 0)
            return p;
        return null;
    }

    @Override
    public Iterable<? extends Position<E>> findAll(E value) {
        LinkedList<Position<E>> l = new LinkedList<>();
        Position<E> p = find(value);
        if (p != null && comp.compare(value, p.getElement())==0){
            l.add(p);
            boolean sigue = t.hasLeft(p);
            while (sigue){
                p = t.left(p);
                if (comp.compare(value, p.getElement())==0){
                    l.add(p);
                    sigue = t.hasLeft(p);
                }else{
                    sigue = !sigue;
                }
            }
        }
        
        return l;
    }

    @Override
    public Position<E> insert(E value) {
        size++;
        if (t.isEmpty()){//es la raiz
            return t.addRoot(value);
        }else{//se debe insertar en el lugar adecuado
            Position<E> p = t.root();
            return sigueBuscando(value, p);
        }
    }

    private Position<E> sigueBuscando(E value, Position<E> p) {
        if (comp.compare(value,p.getElement()) > 0){//Si el valor es mayor que la raiz a la derecha
            if (t.hasRight(p)){
                return sigueBuscando(value,t.right(p));
            }else{//ese es el lugar
                return t.insertRight(p, value);
            }
        }else{//por la izquierda
            if (t.hasLeft(p)){
                return sigueBuscando(value,t.left(p));
            }else{//ese es el lugar
                return t.insertLeft(p, value);
            }
        }
    }
    
     private Position<E> encuentraPos(E value, Position<E> p) {
        if (comp.compare(value,p.getElement()) == 0){//Si coincide 
            return p;
        }
        else if (comp.compare(value,p.getElement()) > 0){//el valor es mayor que la raiz a la derecha
                if (t.hasRight(p))
                    return encuentraPos(value,t.right(p));
                else//no puede continuar con la búsqueda
                    return p;
             }else{//por la izquierda, puede ser igual o menor
                if (t.hasLeft(p))
                    return encuentraPos(value,t.left(p));
                else//no puede continuar con la búsqueda
                return p;
            }
        
    }
     
    @Override
    public boolean isEmpty() {
        return t.isEmpty();
    }

    @Override
    public void remove(Position<E> pos) {
        size--;
        if (t.hasLeft(pos) && t.hasRight(pos)){
            Position<E> sucesor = succesor(pos);
            t.swap(pos, sucesor);
            t.remove(pos);
        }else
            t.remove(pos);
    }
    
    public Position<E> succesor(Position<E> pos){
        if (t.hasRight(pos))
            return minimum(t.right(pos));
        if (t.isRoot(pos))
            return null;
        Position<E> aux = t.parent(pos);
        while (!t.isRoot(aux) && t.hasRight(aux) && pos == t.right(aux)){
            pos = aux;
            aux = t.parent(aux);
        }
        return aux;
    }
    private Position<E> minimum(Position<E> pos) {
        while (t.hasLeft(pos))
            pos = t.left(pos);
        return pos;
    }

    @Override
    public int size() {
       return this.size;
    }

    @Override
    public Iterable<? extends Position<E>> rangeIterator(E m, E M) {
        
        List<Position<E>> l = new ArrayList<>();
        Iterator<Position<E>> ite = iterator();
        boolean seguir = ite.hasNext();
        while (seguir){
            Position<E> p = ite.next();
            
            if ((comp.compare(p.getElement(),m) >=0) && (comp.compare(p.getElement(), M)<=0))
                l.add(p);
            seguir = ite.hasNext() && !(comp.compare(p.getElement(), M)>0);
        }
        return l;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new InorderBinaryTreeIterator<>(t);
    }

    @Override
    public Position<E> root() {
        return t.root();
    }

    @Override
    public Position<E> parent(Position<E> v) {
        return t.parent(v);
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        return t.children(v);
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return t.isInternal(v);
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        return t.isLeaf(v);
    }

    @Override
    public boolean isRoot(Position<E> v) {
        return t.isRoot(v);
    }

    @Override
    public Position<E> left(Position<E> v) {
        return t.left(v);
    }

    @Override
    public Position<E> right(Position<E> v) {
        return t.right(v);
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        return t.hasLeft(v);
    }

    @Override
    public boolean hasRight(Position<E> v) {
        return t.hasRight(v);
    }
    
    

    /**
     * Create un new tree from node v.
     *
     * @param v new root node
     * @return  The new tree.
     */
    public LinkedBinarySearchTree<E> subTree(Position<E> v) {
        //E newRoot = checkPosition(v);
        LinkedBinaryTree<E> subTree = t.subTree(v);
                                       
        LinkedBinarySearchTree<E> tree = new LinkedBinarySearchTree<>();
        tree.t = subTree;
        tree.comp = this.comp;
        
        tree.size = numeroNodos(tree.t);
        this.size = this.size - tree.size;
        return tree;
    }

    private int numeroNodos(LinkedBinaryTree<E> t) {
        int cont = 0;
        for (Position<E> position : t) {
            cont++;
        }
        return cont;
    }

    /**
     * Attach the tree lbt at the root of this tree 
     * @param lbt
    */  
    public void attach(LinkedBinarySearchTree<E> lbt) {
        if (lbt == this)
            throw new RuntimeException("Cannot attach a tree over himself");
        if (!isEmpty())
            throw new RuntimeException("Cannot attach root to a not empty tree");
        
        if (lbt != null && !lbt.isEmpty()) {
            this.t = lbt.t;
            this.comp = lbt.comp;
            this.size = lbt.size;
            lbt = null;
        }
    }
    
    
    /**
     * Attach tree t as left children of node p
     * @param p 
     * @param lbt 
    */ 
    
    public void attachLeft(Position<E> p, LinkedBinarySearchTree<E> lbt) {
                      
        if (lbt == this) {
            throw new RuntimeException("Cannot attach a tree over himself");
        }

        if (hasLeft(p))
            throw new RuntimeException("Cannot attach a tree in a non empty method");            
        
        if (lbt != null && (!lbt.isEmpty()) && (lbt.comp.getClass() == this.comp.getClass()) && (this.comp.compare(p.getElement(), lbt.t.root().getElement())>=0)) {
            
            t.attachLeft(p, lbt.t);
            this.size = this.size + lbt.size;
            lbt = null;
        }
    }

    /**
     * Attach tree t as right children of node p
     * @param p
     * @param lbt 
    */ 
    
    public void attachRight(Position<E> p, LinkedBinarySearchTree<E> lbt) {
        
        if (lbt == this)
            throw new RuntimeException("Cannot attach a tree over himself");

        if (hasRight(p))
            throw new RuntimeException("Cannot attach a tree in a non empty method");            
        
        if (lbt != null && (!lbt.isEmpty()) && (lbt.comp.getClass() == this.comp.getClass()) && this.comp.compare(p.getElement(), lbt.t.root().getElement())<0)  {
            t.attachRight(p, lbt.t);
            this.size = this.size + lbt.size;
            lbt = null;
            
        }
    }
   
    
}
