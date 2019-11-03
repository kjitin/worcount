package com.floow.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class DataSplitterTest {



    private DataSplitter dataSplitter;

    @Mock
    MongoDataService mongoDataService;

    @Captor
    private ArgumentCaptor<ArrayList<String>> argumentCaptor;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        dataSplitter = new DataSplitter(mongoDataService);
    }


    @Test
    public void testDataSplitterSplitsFile() throws IOException {

        Path path = Paths.get("src/test/resources/test.txt");

        Stream<String> lines = Files.lines(path);
        List<String> data = lines.collect(Collectors.toList());

        dataSplitter.split(data, "test");

        verify(mongoDataService, times(2))
                .save(argumentCaptor.capture(), anyString());

        assertTrue(argumentCaptor.getValue().size() == 5);



    }



}