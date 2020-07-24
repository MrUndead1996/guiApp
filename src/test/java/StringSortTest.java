package javaTest;
import org.logic.StringSort;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringSortTest {

    @Test
    public void stringSortTest(){
        StringSort ss = new StringSort();
        String[] strings1 = {"arp", "live", "strong"};
        String[] strings2 = {"lively", "alive", "harp", "sharp", "armstrong"};
        assertArrayEquals(new String[]{"arp", "live", "strong"}, ss.sort(strings2, strings1));
    }
    @Test
    public void stringSortNegativeTest(){
        StringSort ss = new StringSort();
        String[] strings1 = {"tarp", "mice", "bull"};
        String[] strings2 = {"lively", "alive", "harp", "sharp", "armstrong"};
        assertArrayEquals(new String[]{},ss.sort(strings2,strings1));
    }
    @Test
    public void stringSortTest2(){
        StringSort ss = new StringSort();
        String[] strings1 = {"strong", "live", "arp", "arp"};
        String[] strings2 = {"lively", "alive", "harp", "sharp", "armstrong"};
        assertArrayEquals(new String[]{"arp", "live", "strong"}, ss.sort(strings2, strings1));
    }
}
