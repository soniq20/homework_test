package org.sonia.huma;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\SHuma\\IdeaProjects\\HomeWork\\src\\test\\resources\\downloads.txt");
        new PodcastStats().stats(file);
    }
}

