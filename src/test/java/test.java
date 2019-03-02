import org.junit.Test;
import static org.junit.Assert.*;
import static junit.framework.TestCase.fail;

public class test {
    @Test
    public void test1() {
        tree d = new tree();

        d.addInTree(5);
        d.addInTree(6);
        d.addInTree(3);
        d.addInTree(4);
        d.addInTree(7);
    }

    @Test
    public void test2() {
        tree d = new tree();

        d.addInTree(5);
        d.addInTree(6);
        d.addInTree(3);
        d.addInTree(4);
        d.addInTree(7);
        d.Delete(3);
        assertEquals("Number: 4, parent: 5, left: null, right: null", d.toString(4));
    }

    @Test
    public void test3() {
        tree d = new tree();

        d.addInTree(8);
        d.addInTree(9);
        d.addInTree(10);
        d.addInTree(11);
        d.addInTree(1);
        d.Delete(9);
        d.Delete(10);
        assertEquals("Number: 8, parent: null, left: 1, right: 11", d.toString(8));
        assertEquals("Number: 11, parent: 8, left: null, right: null", d.toString(11));
    }
}