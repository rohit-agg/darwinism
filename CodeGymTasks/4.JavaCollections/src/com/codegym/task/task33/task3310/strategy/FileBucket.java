package com.codegym.task.task33.task3310.strategy;

import com.codegym.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {

    Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("bucket", "strategy");
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public long getFileSize() {

        try {
            return Files.size(path);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return 0;
    }

    public void putEntry(Entry entry) {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path));
            objectOutputStream.writeObject(entry);
            objectOutputStream.close();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {

        if (getFileSize() == 0) {
            return null;
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path));
            Entry entry = (Entry) objectInputStream.readObject();
            return entry;
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }

        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
}
