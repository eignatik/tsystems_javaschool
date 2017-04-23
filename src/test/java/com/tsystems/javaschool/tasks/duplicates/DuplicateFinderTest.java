package com.tsystems.javaschool.tasks.duplicates;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class DuplicateFinderTest {

    private DuplicateFinder duplicateFinder = new DuplicateFinder();

    @Before
    public void initClasses() {
        duplicateFinder.setDataGetter(new StubDataGetter());
    }

    @Test
    public void testWithoutIO() {
        boolean result = duplicateFinder.process(null, null);
        List<String> data = duplicateFinder.getDataGetter().getData();
        StubDataGetter getter = (StubDataGetter) duplicateFinder.getDataGetter();

        assertTrue(result);
        assertTrue(!getter.getMapOfCounts().isEmpty());
        assertTrue(!getter.getSortedAndCountedMap(data).isEmpty());
        assertTrue(getter.getSortedAndCountedMap(null).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test() {
        //run
        DuplicateFinder duplicateFinder = setDuplicateFinderWithFiles();
        duplicateFinder.process(null, new File("a.txt"));

        //assert : exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void test1() {
        //run
        DuplicateFinder duplicateFinder = setDuplicateFinderWithFiles();
        duplicateFinder.process(new File("a.txt"), null);

        //assert : exception
    }

    private DuplicateFinder setDuplicateFinderWithFiles() {
        DuplicateFinder duplicateFinder = new DuplicateFinder();
        duplicateFinder.setDataGetter(new FileSystemDataGetter());
        return duplicateFinder;
    }

    private DuplicateFinder setDuplicateFinderWithStubs() {
        DuplicateFinder duplicateFinder = new DuplicateFinder();
        duplicateFinder.setDataGetter(new StubDataGetter());
        return duplicateFinder;
    }
}