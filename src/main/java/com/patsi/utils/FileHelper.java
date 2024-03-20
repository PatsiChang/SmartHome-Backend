package com.patsi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {
    public static void newFile(String pathname, String profilePictureID, byte[] image)
        throws IOException {
        File f = new File(pathname + profilePictureID + ".jpg");
        if(f.exists()) {
            throw new IOException("File already exist.");
        }
        try (FileOutputStream outputStream = new FileOutputStream(f)) {
            outputStream.write(image);
                outputStream.write(image);
        }
    }
}
