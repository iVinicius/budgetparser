package com.mygit.invoiceparser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Slf4j
public class InvoiceParserService {

    @Autowired
    private FileWriter fileWriter;

    public void processInvoice(File f) {
        String parsedInvoice = readAndParseFile(f);
        fileWriter.writeToFile(parsedInvoice);
    }

    private String readAndParseFile(File inputFile){
        try {
            InputStream inputStream = new FileInputStream(inputFile);
            return readFromInputStream(inputStream);
        } catch (IOException e) {
            log.error("Failed to read file.", e);
        }
        return null;
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = br.readLine()) != null){
                String[] parsedLine = line.split(",");

                // Google Sheet Monthly Budget format (from Nubank invoice csv extract)
                // Date
                resultStringBuilder.append(parsedLine[0]).append("\t");

                // Amount $
                resultStringBuilder.append(parsedLine[3]).append("\t");

                // Description
                resultStringBuilder.append(parsedLine[2]).append("\t");

                resultStringBuilder.append("\n");
            }
        }
        inputStream.close();
        return resultStringBuilder.toString();
    }

}
