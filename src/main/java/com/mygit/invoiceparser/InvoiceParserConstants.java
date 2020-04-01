package com.mygit.invoiceparser;

public final class InvoiceParserConstants {

    public static final String DIRECTORY_PATH = System.getProperty("user.dir") + "\\data\\";

    public static final String INPUT_FILE_DIRECTORY_PATH = DIRECTORY_PATH + "in\\";

    public static final String OUTPUT_FILE_DIRECTORY_PATH = DIRECTORY_PATH + "out\\";

    public static final String OUTPUT_FILE_NAME = "output_" + System.currentTimeMillis() + ".txt";
}
