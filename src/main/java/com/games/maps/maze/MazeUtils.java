package com.games.maps.maze;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MazeUtils {
    public static Stream<String> getFileStreamFromResources(String fileName) {
        InputStream inputStream = MazeUtils.class.getResourceAsStream(fileName);
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines();
    }

    public static String getFileStringFromResources(String fileName) {
        return getFileStreamFromResources(fileName).collect(Collectors.joining("\n"));
    }
}
