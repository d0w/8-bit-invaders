package com.example.a8_bitinvader;

/** Creates interface writer that can write and read files */
public interface Writer {
    void writeToFile(String str);
    String[] readFromFile();
}
