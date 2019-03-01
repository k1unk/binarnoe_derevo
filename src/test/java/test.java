import org.junit.Test;
import static org.junit.Assert.*;
import static junit.framework.TestCase.fail;

public class test {
    @Test
    public void Insert_Search_Test() {
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
        d.Delete(5);
        assertEquals("Number: 3, parent: 6, left: null, right: null", d.toString(3));

    }
}