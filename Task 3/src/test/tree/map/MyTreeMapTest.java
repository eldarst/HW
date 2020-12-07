package tree.map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyTreeMapTest {

    @Test
    void TestPutToEmptyMyTreeMap() {
        MyTreeMap<Integer, Integer> mTm = new MyTreeMap<>();
        mTm.put(1, 2);
        Assertions.assertTrue(mTm.containsKey(1));
    }

    @Test
    void TestPutToNotEmptyMyTreeMap() {
        MyTreeMap mTm = new MyTreeMap<>();
        mTm.put(1, 2);
        mTm.put(13, 2);
        mTm.put(12, 2);
        Assertions.assertTrue(mTm.containsKey(12));
    }

    @Test
    void TestGetElem() {
        MyTreeMap mTm = new MyTreeMap<>();
        mTm.put(1, 2);
        mTm.put(13, 2);
        mTm.put(12, 2);
        Assertions.assertEquals(2, mTm.get(12));
    }

    @Test
    void TestContainsElem() {
        MyTreeMap mTm = new MyTreeMap<>();
        mTm.put(1, 2);
        mTm.put(13, 2);
        mTm.put(12, 2);
        Assertions.assertTrue(mTm.containsKey(12));
    }

    @Test
    void TestIsEmptyWithEmptyMap() {
        MyTreeMap mTm = new MyTreeMap<>();
        Assertions.assertTrue(mTm.isEmpty());
    }

    @Test
    void TestIsEmptyWithNotEmptyMap() {
        MyTreeMap mTm = new MyTreeMap<>();
        mTm.put(12, 2);
        Assertions.assertEquals(1, mTm.size());
        Assertions.assertFalse(mTm.isEmpty());
    }

}