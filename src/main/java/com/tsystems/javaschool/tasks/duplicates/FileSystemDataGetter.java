package com.tsystems.javaschool.tasks.duplicates;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileSystemDataGetter implements IDataGetter {
    private File source;
    private File target;
    private List<String> sourceText;

    @Override
    public List<String> getData() {
        sourceText = new ArrayList<>();
        try {
            Files.lines(Paths.get(source.getAbsolutePath()), StandardCharsets.UTF_8).forEach(this::addToList);
        } catch(Exception e) {
            e.getStackTrace();
        }
        return sourceText;
    }

    @Override
    public boolean writeData(Map<String, Integer> dataMap) {
        return false;
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
