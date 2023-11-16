
package material.tree.binarysearchtree;

import java.util.Iterator;
import java.util.TreeSet;
import material.Position;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mayte
 */
public class RBTreeTest {
    
    public RBTreeTest() {
    }

    /**
     * Test of find method, of class RBTree.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        RBTree<Integer> b = new RBTree<>();

        for (int cont = 0; cont < 25; cont += 2) {
            b.insert(cont);
        }

        b.insert(17);

        Position<Integer> p = b.find(17);
        assertEquals(p.getElement().intValue(), 17);
        p = b.find(2);
        assertEquals(p.getElement().intValue(), 2);
        p = b.find(13);
        assertEquals(p, null);
    }

    
    /**
     * Test of insert method, of class RBTree.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        RBTree<Integer> b = new RBTree<>();
        TreeSet<Integer> expected = new TreeSet<>();
        b.insert(5);
        expected.add(5);
        b.insert(3);
        expected.add(3);
        b.insert(1);
        expected.add(1);
        b.insert(7);
        expected.add(7);
        b.insert(6);
        expected.add(6);

        Iterator<Integer> it = expected.iterator();
        for (Position<Integer> e : b) {
            assertEquals(it.next(), e.getElement());
        }
    }
    
    @Test
    public void testInsert2() {
        System.out.println("insert2");
        RBTree<Integer> b = new RBTree<>();
        TreeSet<Integer> expected = new TreeSet<>();
        b.insert(-1);
        expected.add(-1);
        b.insert(1);
        expected.add(1);
        b.insert(3);
        expected.add(3);
        b.insert(5);
        expected.add(5);
        b.insert(-5);
        expected.add(-5);

        Iterator<Integer> it = expected.iterator();
        for (Position<Integer> e : b) {
            assertEquals(it.next(), e.getElement());
        }
    }
    
    @Test
    public void testInsert3() {
        System.out.println("insert3");
        RBTree<Integer> b = new RBTree<>();
        TreeSet<Integer> expected = new TreeSet<>();
        b.insert(4);
        expected.add(4);
        b.insert(7);
        expected.add(7);
        b.insert(12);
        expected.add(12);
        b.insert(15);
        expected.add(15);
        b.insert(3);
        expected.add(3);
        b.insert(5);
        expected.add(5);
        b.insert(14);
        expected.add(14);
        b.insert(18);
        expected.add(18);
        b.insert(16);
        expected.add(16);
        b.insert(17);
        expected.add(17);

        Iterator<Integer> it = expected.iterator();
        for (Position<Integer> e : b) {
            assertEquals(it.next(), e.getElement());
        }
    }

    /**
     * Test of remove method, of class RBTree.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        RBTree<Integer> b = new RBTree<>();
        TreeSet<Integer> expected = new TreeSet<>();

        b.insert(5);
        expected.add(5);
        b.insert(3);
        expected.add(3);
        Position<Integer> p = b.insert(1);
        b.insert(7);
        expected.add(7);
        b.insert(6);
        expected.add(6);

        b.remove(p);

        Iterator<Integer> it = expected.iterator();
        for (Position<Integer> e : b) {
            assertEquals(it.next(), e.getElement());
        }
    }
    
    @Test
    public void testRemove2() {
        System.out.println("remove2");
        RBTree<Integer> b = new RBTree<>();
        TreeSet<Integer> expected = new TreeSet<>();
        Position<Integer> p1, p2, p3, p4, p5, p6;

        b.insert(4); expected.add(4);
        b.insert(7); expected.add(7);
        b.insert(12);
        b.insert(15);
        b.insert(3);
        b.insert(5); expected.add(5);
        b.insert(14); expected.add(14);
        b.insert(18);
        b.insert(16);
        b.insert(17);
             
        p1 = b.find(18);
        b.remove(p1);
        p2 = b.find(3);
        b.remove(p2);
        p3 = b.find(12);
        b.remove(p3);
        p4 = b.find(17);
        b.remove(p4);
        p5 = b.find(15);
        b.remove(p5);
        p6 = b.find(16);
        b.remove(p6);

        Iterator<Integer> it = expected.iterator();
        for (Position<Integer> e : b) {
            assertEquals(it.next(), e.getElement());
        }
    }

    @Test
    public void testRemove3() {
        System.out.println("remove3");
        RBTree<Integer> b = new RBTree<>();
        final int LIMIT = 10;
        final Position [] array = new Position[LIMIT];

        for (int cont = 0; cont < LIMIT; cont++) {            
            Position p = b.insert(cont);
            array[cont] = p;
       }
        
        for (int cont = 0; cont < LIMIT; cont+=2) {
            b.remove(array[cont]);
        }

        int cont = 1;
        for (Position<Integer> e : b) {
            assertEquals(cont,e.getElement().intValue());
            cont+=2;
        }
    }
    /**
     * Test of rangeIterator method, of class RBTree.
     */
    @Test
    public void testRangeIterator() {
        System.out.println("rangeIterator");
        RBTree<Integer> b = new RBTree<>();
        b.insert(4);
        b.insert(7);
        b.insert(12);
        b.insert(15);
        b.insert(3);
        b.insert(14);
        b.insert(18);
        b.insert(16);
        b.insert(17);

        Iterator<? extends Position<Integer>> it = b.rangeIterator(5, 15).iterator();
        assertEquals(7, it.next().getElement().intValue());
        assertEquals(12, it.next().getElement().intValue());
        assertEquals(14, it.next().getElement().intValue());
        assertEquals(15, it.next().getElement().intValue());
    }
    
    /**
     * Test of size method, of class RBTree.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        RBTree<Integer> b = new RBTree<>();
        assertEquals(b.size(), 0);
        b.insert(5);
        assertEquals(b.size(), 1);
        b.insert(10);
        assertEquals(b.size(), 2);

        for (int cont = 0; cont < 25; cont++) {
            b.insert(cont);
        }

        assertEquals(b.size(), 27);
    }

    
    
}
