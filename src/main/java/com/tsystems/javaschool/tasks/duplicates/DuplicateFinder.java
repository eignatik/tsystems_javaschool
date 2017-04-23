package com.tsystems.javaschool.tasks.duplicates;

import java.io.File;
import java.util.Map;

public class DuplicateFinder {

    private IDataGetter dataGetter;

    public IDataGetter getDataGetter() {
        return dataGetter;
    }

    public void setDataGetter(IDataGetter dataGetter) {
        this.dataGetter = dataGetter;
    }

    /**
     * Processes the specified file and puts into another sorted and unique
     * lines each followed by number of occurrences.
     *
     * @param sourceFile file to be processed
     * @param targetFile output file; append if file exist, create if not.
     * @return <code>false</code> if there were any errors, otherwise
     * <code>true</code>
     */
    public boolean process(File sourceFile, File targetFile) {
        setStrategy(sourceFile, targetFile);
        Map<String, Integer> sortedData = dataGetter.getSortedAndCountedMap(dataGetter.getData());
        return dataGetter.writeData(sortedData);
    }

    private void setStrategy(File sourceFile, File targetFile) {
        if (dataGetter instanceof FileSystemDataGetter) {
            if (sourceFile == null || targetFile == null) {
                throw new IllegalArgumentException();
            }
            ((FileSystemDataGetter) dataGetter).setSource(sourceFile);
            ((FileSystemDataGetter) dataGetter).setTarget(targetFile);
        }
    }
}