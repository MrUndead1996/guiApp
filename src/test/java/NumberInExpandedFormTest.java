import org.logic.NumberInExpandedForm;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberInExpandedFormTest {

    @Test
    public void NumberInExpandedFormTest(){
        NumberInExpandedForm numberInExpandedForm = new NumberInExpandedForm();
        assertEquals("10 + 2",numberInExpandedForm.getNumber(12));
        assertEquals("40 + 2",numberInExpandedForm.getNumber(42));
        assertEquals("400 + 20",numberInExpandedForm.getNumber(420));
        assertEquals("70000 + 300 + 4",numberInExpandedForm.getNumber(70304));
        assertEquals("4",numberInExpandedForm.getNumber(4));
    }
}
