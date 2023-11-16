package material.tree.binarysearchtree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import material.Position;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mayte
 */
public class LinkedBinarySearchTreeTest {
    
    public LinkedBinarySearchTreeTest() {
    }
    private Position<Integer> p0,p1,p3,p5,p6,p7,p8;
    private LinkedBinarySearchTree<Integer> inicia(){
        LinkedBinarySearchTree<Integer> t = new LinkedBinarySearchTree<>();
         p3 = t.insert(3);
         p8 = t.insert(8);
         p6 = t.insert(6);
         p7 = t.insert(7);
         p5 = t.insert(5);
         p1 = t.insert(1);
         p0 = t.insert(0);
         return t;
    }

    /**
     * Test of find method, of class LinkedBinarySearchTree.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        LinkedBinarySearchTree<Integer> instance = inicia();
        
        Position<Integer> result = instance.find(6);
        assertEquals(p6, result);
        result = instance.find(0);
        assertEquals(p0, result);
        result = instance.find(2);
        assertEquals(null, result);
    }

    /**
     * Test of findAll method, of class LinkedBinarySearchTree.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        LinkedBinarySearchTree<Integer> instance = inicia();
        Set<? extends Position<Integer>> expResult = new HashSet<>(Arrays.asList(p0));
        Iterable<? extends Position<Integer>> result = instance.findAll(0);
        int cont = 0;
        for (Position<Integer> p : result) {
            cont++;
            expResult.contains(p);
        }
        assertEquals(expResult.size(),cont);
        Position<Integer> po1 = instance.insert(0);
        Position<Integer> po2 = instance.insert(0);
        List<Position<Integer>> l = Arrays.asList(p0,po1,po2);
        cont = 0;
        result = instance.findAll(0);
        for (Position<Integer> p : result) {
            cont++;
            expResult.contains(p);
        }
        assertEquals(l.size(),cont);
    }

    /**
     * Test of insert method, of class LinkedBinarySearchTree.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        
        LinkedBinarySearchTree<Integer> instance = new LinkedBinarySearchTree<>();
        
        Position<Integer> result1 = instance.insert(9);
        assertEquals(instance.find(9), result1);
        Position<Integer> result2 = instance.insert(2);
        assertEquals(instance.find(2), result2);
    }

    /**
     * Test of isEmpty method, of class LinkedBinarySearchTree.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        LinkedBinarySearchTree<Integer> instance = new LinkedBinarySearchTree<>();
        assertTrue(instance.isEmpty());
        instance.insert(7);
        assertFalse(instance.isEmpty());
    }

    /**
     * Test of remove method, of class LinkedBinarySearchTree.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        LinkedBinarySearchTree<Integer> instance = inicia();
        assertEquals(instance.size(),7);
        instance.remove(p3);
        assertEquals(null,instance.find(3));
        assertEquals(instance.size(),6);
    }

    /**
     * Test of size method, of class LinkedBinarySearchTree.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        LinkedBinarySearchTree<Integer> instance = new LinkedBinarySearchTree<>();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        instance.insert(9);
        instance.insert(12);
        instance.insert(3);
        assertEquals(3, instance.size());
    }

    /**
     * Test of rangeIterator method, of class LinkedBinarySearchTree.
     */
    @Test
    public void testRangeIterator() {
        System.out.println("rangeIterator");
        int m = 2;
        int M = 6;
        LinkedBinarySearchTree<Integer> instance = inicia();
        Iterable<? extends Position<Integer>> expResult = Arrays.asList(p3,p5,p6);
        Iterable<? extends Position<Integer>> result = instance.rangeIterator(m, M);
        
        Iterator<? extends Position<Integer>> it = expResult.iterator();
        for (Position<Integer> position : result) {
            assertEquals(it.next().getElement(),position.getElement());
        }
    }

    /**
     * Test of iterator method, of class LinkedBinarySearchTree.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        LinkedBinarySearchTree<Integer> instance = inicia();
        List<Position<Integer>> asList = Arrays.asList(p0,p1,p3,p5,p6,p7,p8);
        Iterator<Position<Integer>> result = instance.iterator();
        int cont = 0;
        while (result.hasNext()){
            assertEquals(asList.get(cont).getElement(),result.next().getElement());
            cont++;
        }
        
       
    }
    
}
