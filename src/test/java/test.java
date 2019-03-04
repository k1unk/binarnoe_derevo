import org.junit.Test;
import static org.junit.Assert.*;

public class test {
    @Test
    public void test1() {
        Tree d = new Tree();

        d.addInTree(5);
        d.addInTree(6);
        d.addInTree(3);
        d.addInTree(4);
        d.addInTree(7);
    }

    @Test
    public void test2() {
        Tree d = new Tree();

        d.addInTree(5);
        d.addInTree(6);
        d.addInTree(3);
        d.addInTree(45);
        d.addInTree(7);
        d.addInTree(1);
        d.deleteRecurse(3);
        d.addInTree(3);
        assertEquals("Number: 1, parent: 5, left: null, right: 3", d.toString(1));
        assertEquals("Number: 3, parent: 1, left: null, right: null", d.toString(3));
        assertEquals("Number: 5, parent: null, left: 1, right: 6", d.toString(5));
        d.deleteRecurse(5);
        assertEquals("Number: 1, parent: 6, left: null, right: 3", d.toString(1));
        assertEquals("Number: 45, parent: 6, left: 7, right: null", d.toString(45));
        assertEquals("Number: 6, parent: null, left: 1, right: 45", d.toString(6));
    }

    @Test
    public void test3() {
        Tree d = new Tree();

        d.addInTree(8);
        d.addInTree(9);
        d.addInTree(10);
        d.addInTree(11);
        d.addInTree(1);
        d.delete(9);
        d.delete(10);
        assertEquals("Number: 8, parent: null, left: 1, right: 11", d.toString(8));
        assertEquals("Number: 11, parent: 8, left: null, right: null", d.toString(11));
    }
}