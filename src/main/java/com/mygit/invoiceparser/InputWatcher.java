package com.mygit.invoiceparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Component
public class InputWatcher implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private InvoiceParserService invoiceParserService;

    public void watch() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(InvoiceParserConstants.INPUT_FILE_DIRECTORY_PATH);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                File f = new File(InvoiceParserConstants.INPUT_FILE_DIRECTORY_PATH + (event.context()));
                invoiceParserService.processInvoice(f);
            }
            key.reset();
        }
        watchService.close();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        try {
            watch();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
