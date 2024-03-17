package com.patsi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHelper {
    public static File newFile(String pathname) {
        return new File(pathname);
    }
    public static FileOutputStream newFileOutputStream(File f) throws IOException {
        try{
            return new FileOutputStream(f);
        } catch (Exception e){
            return null;
        }
    }
}
