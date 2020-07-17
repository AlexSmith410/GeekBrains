package com.geekbrains.java.lesson14;

import java.io.*;

public class MainApp {
    public static void main(String[] args) throws IOException {
        System.out.println(patternOccurrence("aaf", "demo.txt"));
        mergeAllFiles("SomeFiles");
        deleteDir(new File("task3dir"));
    }

//    task 1
    public static int patternOccurrence(String pattern, String pathToFile) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String str;
            while ((str = reader.readLine()) != null) {
                text.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text);

        int m = pattern.length();
        int n = text.length();
        int res = 0;

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }

            if (j == m) {
                res++;
                j = 0;
            }
        }
        return res;
    }

//    task 2
    public static void mergeAllFiles(String directory) throws IOException {
        File dir = new File(directory);
        PrintWriter pw = new PrintWriter("complete.txt");
        String[] fileNames = dir.list();
        for (String fileName : fileNames) {
            File f = new File(dir, fileName);
            if (f.isDirectory()){
                continue;
            }
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            while (line != null) {
                pw.print(line);
                line = br.readLine();
            }
            pw.flush();
        }
        System.out.println("Check complete.txt");
    }

    //task 3
    public static void deleteDir(File dir) {
        File[] files = dir.listFiles();
        if(files != null) {
            for (File file : files) {
                deleteDir(file);
            }
        }
        dir.delete();
    }
}

