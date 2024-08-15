package com.patsi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHelper {

    public static void newFile(String pathname, String profilePictureID, byte[] image)
        throws IOException {
        File f = new File(pathname + profilePictureID + ".jpg");
        if (f.exists()) {
            throw new IOException("File already exist.");
        }
        try (FileOutputStream outputStream = new FileOutputStream(f)) {
            outputStream.write(image);
            outputStream.write(image);
        }
    }
    public static List<List<String>> readFile(String name, File f) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(f)) {
            Scanner scanner = new Scanner(fileInputStream);
            List<List<String>> listStr = ListHelper.newList();
            while (scanner.hasNext()) {
                listStr.add(Arrays.asList(scanner.nextLine().split(",")));
            }
            return listStr;
        }
    }

    public static void transferFile(File f, String fromPath, String ToPath) throws IOException {
        File sourceFile = new File(fromPath, f.getName());
        File destFile = new File(ToPath, f.getName());
        Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        deleteFile(fromPath, removeFileExtension(f.getName()));
    }

    public static File getFileById(String pathname, String imgUrl) throws IOException {
        return getAllFiles(pathname).stream()
            .filter(file -> removeFileExtension(file.getName()).equals(imgUrl))
            .findFirst()
            .orElse(null);
    }

    public static List<File> getAllFiles(String pathname) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(pathname))) {
            return paths
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        }
    }

    public static String removeFileExtension(String fileName) {
        int idx = fileName.lastIndexOf('.');
        int directoryLastIdx = fileName.lastIndexOf('/');
        return (idx > 0 && directoryLastIdx < idx) ? fileName.substring(0, idx) : fileName;
    }

    public static boolean deleteFile(String pathname, String profilePictureID) {
        File iconFile = new File(pathname + profilePictureID + ".jpg");
        try {
            if (iconFile.exists()) {
                boolean deleted = iconFile.delete();
                return deleted;
            }
        } catch (SecurityException e) {
            // Handle security exception if deletion is not permitted
            e.printStackTrace(); // or log the exception
        }
        return false;
    }
}
