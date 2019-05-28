package util.staticFunctions;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public class FileRead {
    /**
     * @return Returns the content of the help.txt
     */
    public static String helpText(){
            try {
                URL path = FileRead.class.getResource("help.txt");
                File f = new File(path.getFile());
                byte[] bytes = Files.readAllBytes(f.toPath());
                return new String(bytes,"UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
