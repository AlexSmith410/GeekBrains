package com.geekbrains.java.lesson15;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) throws IOException {
        System.out.println(patternOccurrence("aaf", "complete.txt"));
        mergeAllFiles("complete.txt", "SomeFiles");
        ArrayList<Path> result = findSmallFiles(Paths.get("task3"));
        for (Path path : result
        ) {
            System.out.println(path);
        }
    }

    //    task 1
    public static int patternOccurrence(String pattern, String fileName) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(fileName, "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(3);
        int res = 0;
        int patLen = pattern.length();
        int currPos;
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                currPos = buf.position();
                if (currPos <= buf.limit() - patLen) {
                    for (int i = 0; i < patLen; i++) {
                        if (pattern.charAt(i) != buf.get()) {
                            break;
                        }
                        if (i == patLen - 1) {
                            res++;
                        }
                    }
                    buf.position(currPos + 1);
                } else {
                    break;
                }
            }
            buf.compact();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
        return res;
    }

    //    task 2
    public static void mergeAllFiles(String outputFile, String pathToDir) throws IOException {
        Path output = Paths.get(outputFile);
        Path directory = Paths.get(pathToDir);
        Stream<Path> filesToProcess = Files.list(directory);

        filesToProcess.forEach(path -> {
            if (Files.isDirectory(path))
                return;
            Stream<String> lines = null;
            try {
                lines = Files.lines(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            lines.forEach(line -> {
                try {
                    Files.write(output, line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    //    task 3
    public static ArrayList<Path> findSmallFiles(Path catalog) {
        ArrayList<Path> smallFiles = new ArrayList<>();
        int sizeLim = 102400;
        try {
            Files.walkFileTree(catalog, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (Files.size(file) < sizeLim) {
                        smallFiles.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return smallFiles;
    }
}
