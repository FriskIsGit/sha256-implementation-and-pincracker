import org.junit.Test;
import static org.junit.Assert.*;

public class SHA256Test{
    @Test
    public void test1(){
        long st = System.currentTimeMillis();
        assertEquals("c4f9244023afa6ac67ac044545cfa19528221791f1521a9c62bcb177de645664", SHA256.hashString("brate"));
        long en = System.currentTimeMillis();
        System.out.println(en-st);
    }
    @Test
    public void test2(){
        String str = "5727";
        String actual = SHA256.hashString(str);
        assertEquals("226912ab42a1c1a4ff34aec12dcc58df1865371d40213f37a6da4ba46c728b95", actual);
    }
    @Test
    public void test3(){
        String str = "hello world";
        String actual = SHA256.hashString(str);
        assertEquals("b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9", actual);
    }
    @Test
    public void test4(){
        String str = "latte";
        String actual = SHA256.hashString(str);
        assertEquals("201c8cbbff58fbfa7cc259eb37e08f7eb8eaf5d2b2fa50046bd44fc4dd7eff2f", actual);
    }
    @Test
    public void test5(){
        String str = "password12345678";
        String actual = SHA256.hashString(str);
        assertEquals("aafeeba6959ebeeb96519d5dcf0bcc069f81e4bb56c246d04872db92666e6d4b", actual);
    }
}
