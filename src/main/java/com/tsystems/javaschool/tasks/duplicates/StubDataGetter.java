package com.tsystems.javaschool.tasks.duplicates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StubDataGetter implements IDataGetter {
    private List<String> sourceData = new ArrayList<>();
    private Map<String, Integer> mapOfCounts;
    private Random random = new Random();

    public Map<String, Integer> getMapOfCounts() {
        return mapOfCounts;
    }

    @Override
    public List<String> getData() {
        return generateTestData();
    }

    @Override
    public boolean writeData(Map<String, Integer> dataMap) {
        mapOfCounts = dataMap;
        return true;
    }

    private List<String> generateTestData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
         list.add(generateString());
        }
        return list;
    }

    private String generateString() {
        String generatedData = "test";
        String result = "";
        char[] charSeq = generatedData.toCharArray();
        for (char ch : charSeq) {
            ch += random.nextInt(3);
            result += ch;
        }
        return result;
    }
}
