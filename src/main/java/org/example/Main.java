package org.example;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import  com.fasterxml.jackson.databind.ObjectMapper;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static List<PodcastDownloadData> readInput() throws IOException {
         File myFile = new File("C:\\Users\\SHuma\\IdeaProjects\\HomeWork\\src\\test\\resources\\downloads.txt");
            Scanner myReader = new Scanner(myFile);
            List<PodcastDownloadData> readObjects = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                PodcastDownloadData podcastDownloadData = parseJsonEntry(line);
                readObjects.add(podcastDownloadData);
            }
            myReader.close();

            return readObjects;
    }

    private static PodcastDownloadData parseJsonEntry(String line) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PodcastDownloadData data = mapper.readValue(line, PodcastDownloadData.class);

        // System.out.println("json:");
        // System.out.println(data);

        return  data;
    }


    public static void main(String[] args) throws IOException {
        List<PodcastDownloadData> podcastDownloadData = readInput();

        findMostPopularShowInSanFrancisco(podcastDownloadData);
    }

    private static void findMostPopularShowInSanFrancisco(List<PodcastDownloadData> podcastDownloadData) {
        Map<String, Integer> sanFranciscoShowsMap = new HashMap<>();

        for (PodcastDownloadData downloadData : podcastDownloadData) {
            if ("san francisco".equalsIgnoreCase(downloadData.getCity())) {
                String showId = downloadData.getDownloadIdentifier().getShowId();

                if (sanFranciscoShowsMap.containsKey(showId)) {
                    Integer currentCount = sanFranciscoShowsMap.get(showId);
                    sanFranciscoShowsMap.put(showId, currentCount + 1);
                } else {
                    sanFranciscoShowsMap.put(showId, 1);
                }
            }
        }

        System.out.println(sanFranciscoShowsMap);

        Integer max = 0;
        String maxDownloadedShow = "";
        for(Map.Entry<String, Integer> entry : sanFranciscoShowsMap.entrySet()){
            if(max< entry.getValue()){
                max = entry.getValue();
                maxDownloadedShow = entry.getKey();
            }
        }
        System.out.println("The number of most downloaded show is " + max);
        System.out.println("The most downloaded show is " + maxDownloadedShow);
    }
}