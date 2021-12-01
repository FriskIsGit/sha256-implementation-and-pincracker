import org.junit.Test;
import static org.junit.Assert.*;

public class HashingTest{
    @Test
    public void test1(){
        long st = System.currentTimeMillis();
        assertEquals("c4f9244023afa6ac67ac044545cfa19528221791f1521a9c62bcb177de645664",HashingAlg.encryptHash("brate"));
        long en = System.currentTimeMillis();
        System.out.println(en-st);
    }
    @Test
    public void test2(){
        assertEquals("226912ab42a1c1a4ff34aec12dcc58df1865371d40213f37a6da4ba46c728b95",HashingAlg.encryptHash("5727"));
    }
}
