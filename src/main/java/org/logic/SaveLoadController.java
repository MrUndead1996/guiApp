package org.logic;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class SaveLoadController {

    /**
     * Save current task and field values to file
     *
     * @param task value of current task
     * @param args values to save
     */
    public void save(Tasks task, String... args) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss"); //create fileName pattern
        File dir = new File("./save/"); // create
        dir.mkdirs();                           //  output directory
        File file = new File("./save/" + task.name() + dateFormat.format(System.currentTimeMillis()) + ".txt");
        file.createNewFile(); // new save file
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(task.name().getBytes());
        for (String s :                                 //write save file
                args) {
            outputStream.write(("\n" + s).getBytes());
        }
        outputStream.flush();
        outputStream.close();
    }

    /**
     * Load task from file
     *
     * @param file TXT file for loading
     * @return List<String> with task type and field values
     */
    public List<String> load(File file) throws IOException {
        List<String> result = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            result.add(reader.readLine());     // get args from file
        }
        reader.close();
        return result;
    }

}
