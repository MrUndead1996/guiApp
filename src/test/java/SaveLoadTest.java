import org.logic.SaveLoadController;
import org.logic.Tasks;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class SaveLoadTest {

    @Test
    public void saveTest() throws IOException {
        SaveLoadController saveLoadController = new SaveLoadController();
        saveLoadController.save(Tasks.NumberInExpandedForm,"7806");
        File dir = new File("./saves/");
        assertEquals(1,dir.list().length);
    }


    @Test
    public void loadTest() throws IOException {
        SaveLoadController saveLoadController = new SaveLoadController();
        File dir = new File("./save");
        File file = new File("./save/test.txt");
        dir.mkdir();
        file.createNewFile();
        FileOutputStream fs = new FileOutputStream(file);
        fs.write(Tasks.NumberInExpandedForm.name().getBytes());
        fs.write("\n42".getBytes());
        List<String> result = saveLoadController.load(file);
        assertEquals(Tasks.NumberInExpandedForm.name(),result.get(0));
        assertEquals(42,Integer.parseInt(result.get(1)));
    }
}
