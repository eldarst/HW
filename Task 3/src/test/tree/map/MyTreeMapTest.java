package tree.map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyTreeMapTest {

    @Test
    void TestPutToEmptyMyTreeMap() {
        MyTreeMap<Integer, Integer> tree = new MyTreeMap<>();
        tree.put(1, 2);
        Assertions.assertTrue(tree.containsKey(1));
    }

    @Test
    void TestPutToNotEmptyMyTreeMap() {
        MyTreeMap tree = new MyTreeMap<>();
        tree.put(1, 2);
        tree.put(13, 2);
        tree.put(12, 2);
        Assertions.assertTrue(tree.containsKey(12));
    }

    @Test
    void TestGetElem() {
        MyTreeMap tree = new MyTreeMap<>();
        tree.put(1, 2);
        tree.put(13, 2);
        tree.put(12, 2);
        Assertions.assertEquals(2, tree.get(12));
    }

    @Test
    void TestContainsElem() {
        MyTreeMap tree = new MyTreeMap<>();
        tree.put(1, 2);
        tree.put(13, 2);
        tree.put(12, 2);
        Assertions.assertTrue(tree.containsKey(12));
    }

    @Test
    void TestIsEmptyWithEmptyMap() {
        MyTreeMap tree = new MyTreeMap<>();
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void TestIsEmptyWithNotEmptyMap() {
        MyTreeMap tree = new MyTreeMap<>();
        tree.put(12, 2);
        Assertions.assertEquals(1, tree.size());
        Assertions.assertFalse(tree.isEmpty());
    }

}