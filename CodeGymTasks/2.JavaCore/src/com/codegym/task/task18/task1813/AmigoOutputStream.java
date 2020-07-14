package com.codegym.task.task18.task1813;

import java.io.FileOutputStream;
import java.io.IOException;

/* 
AmigoOutputStream

*/

public class AmigoOutputStream extends FileOutputStream {
    
    public static String fileName = "C:/tmp/result.txt";
    private FileOutputStream fStream;
    
    public AmigoOutputStream(FileOutputStream fs) throws IOException {
        super(fs.getFD());
        fStream = fs;
    }

    public static void main(String[] args) throws IOException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }
    
    public void write(byte[] bytes) throws IOException {
        this.fStream.write(bytes);
    }

    public void write(byte[] bytes, int off, int len) throws IOException {
        this.fStream.write(bytes, off, len);
    }

    public void write(int b) throws IOException {
        this.fStream.write(b);
    }
    
    public void flush() throws IOException {
        this.fStream.flush();
    }

    public void close() throws IOException {
        
        this.fStream.flush();
        this.write("CodeGym © All rights reserved.".getBytes());
        this.fStream.close();
    }
}
