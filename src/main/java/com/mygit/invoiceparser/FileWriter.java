package com.mygit.invoiceparser;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class FileWriter {

    public void writeToFile(String result) {
        java.io.FileWriter fileWriter = null;
        try {
            fileWriter = new java.io.FileWriter(InvoiceParserConstants.OUTPUT_FILE_DIRECTORY_PATH + InvoiceParserConstants.OUTPUT_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.printf(result);

        printWriter.close();
    }
}
