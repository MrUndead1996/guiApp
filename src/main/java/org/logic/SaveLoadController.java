package org.logic;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SaveLoadController {

    public void save(Tasks task,String ... args) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyyHH:mm:ss");
        File dir = new File("./save/");
        dir.mkdirs();
        File file = new File("./save/" + task.name() + dateFormat.format(System.currentTimeMillis()) + ".txt");
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(task.name().getBytes());
        for (String s :
                args) {
        outputStream.write(("\n" + s).getBytes());
        }
        outputStream.flush();
        outputStream.close();
    }

    public List<String> load(File file) throws IOException {
        List<String> result = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()){
            result.add(reader.readLine());
        }
        return result;
    }

    public List<String> getFileList() {
        File dir = new File("./save/");
        return Arrays.asList(Objects.requireNonNull(dir.list()));
    }
}
