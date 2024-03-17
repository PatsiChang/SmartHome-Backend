package com.patsi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {
    public static void newFile(String pathname, String profilePictureID, byte[] image)
        throws IOException {
        File f = new File(pathname + profilePictureID + ".jpg");
        try (FileOutputStream outputStream = new FileOutputStream(f)) {
            if (outputStream != null) {
                outputStream.write(image);
            } else {
                throw new IOException("Failed to create file output stream");
            }
        }

    }
}
