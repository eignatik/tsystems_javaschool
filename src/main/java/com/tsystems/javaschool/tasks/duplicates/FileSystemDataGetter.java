package com.tsystems.javaschool.tasks.duplicates;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileSystemDataGetter implements IDataGetter {
    private File source;
    private File target;
    private List<String> sourceText;
    private StringBuilder existedText = new StringBuilder();

    @Override
    public List<String> getData() {
        sourceText = new ArrayList<>();
        try {
            Files.lines(Paths.get(source.getPath()), StandardCharsets.UTF_8).forEach(this::addToList);
        } catch(Exception e) {
            e.getStackTrace();
        }
        return sourceText;
    }

    @Override
    public boolean writeData(Map<String, Integer> dataMap) {
        boolean isSuccess;
        if (dataMap.isEmpty()) {
            return false;
        }
        try {
            if(!target.exists()){ target.createNewFile(); }

            readFile(target);
            try (PrintWriter out = new PrintWriter(target.getAbsoluteFile())) {
                out.print(existedText + "\n\n" + new Date().toString() + "\n" + getText(dataMap));
            }
            isSuccess = true;
        } catch(IOException e) {
            isSuccess = false;
        }
        return isSuccess;
    }

    private void readFile(File file) {
        try {
            Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8).forEach(this::appendText);
        } catch(Exception e) {
            e.getStackTrace();
        }
    }

    private void appendText(String value) {
        existedText
                .append(value)
                .append("\n");
    }

    private String getText(Map<String, Integer> map) {
        StringBuilder dataToFile = new StringBuilder();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            dataToFile.append(entry.getKey())
                    .append("[")
                    .append(entry.getValue())
                    .append("] \n");
        }
        return dataToFile.toString();
    }

    private void addToList(String line) {
        sourceText.add(line);
    }

    public File getSource() {
        return source;
    }

    public void setSource(File source) {
        this.source = source;
    }

    public File getTarget() {
        return target;
    }

    public void setTarget(File target) {
        this.target = target;
    }
}
