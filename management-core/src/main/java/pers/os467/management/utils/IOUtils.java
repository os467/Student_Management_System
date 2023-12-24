package pers.os467.management.utils;


import java.io.FileInputStream;
import java.io.IOException;

public class IOUtils {

    public static byte[] toByteArray(FileInputStream fileInputStream){

        int available;
        byte[] bytes = null;
        try {
            available = fileInputStream.available();
            bytes = new byte[available];

            fileInputStream.read(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

}
