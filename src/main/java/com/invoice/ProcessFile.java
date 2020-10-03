package com.invoice;

import com.invoice.service.RegisterService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class ProcessFile {

    public static final String USER_HOME = "user.home";
    public static final String DATA = "data";
    public static final String IN = "in";
    public static final String OUT = "out";
    public static final String FORMAT_FILE_DAT = ".dat";
    public static final String FORMAT_FILE_DONE_DAT = ".done.dat";
    public static final int FORMAT_SIZE = 4;

    public static void main(String[] args) throws IOException, InterruptedException {

        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path inPath = Paths.get(System.getProperty(USER_HOME).concat(File.separator).concat(DATA).concat(File.separator).concat(IN));
        Path outPath = Paths.get(System.getProperty(USER_HOME).concat(File.separator).concat(DATA).concat(File.separator).concat(OUT));

        inPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        RegisterService service = new RegisterService();
        WatchKey watchKey;

        while ((watchKey = watchService.take()) != null) {
            for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {

                String filename = watchEvent.context().toString();

                if (FORMAT_FILE_DAT.equalsIgnoreCase(filename.substring(filename.length() - FORMAT_SIZE))) {

                    Path inputFilePath = inPath.resolve((Path) watchEvent.context());
                    Path outputFilePath = outPath.resolve(filename.replace(FORMAT_FILE_DAT, FORMAT_FILE_DONE_DAT));
                    List<String> lines = Files.readAllLines(inputFilePath);

                    service.registerAll(lines);

                    try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath)) {
                        writer.write(service.getaAllDataReport());
                    }
                }
            }

            watchKey.reset();
        }
    }
}
