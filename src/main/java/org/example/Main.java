package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;


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
        return data;
    }


    public static void main(String[] args) throws IOException {
        List<PodcastDownloadData> podcastDownloadData = readInput();

        findMostPopularShowInSanFrancisco(podcastDownloadData);
        mostUsedDevice(podcastDownloadData);

    }
    public static void mostUsedDevice(List<PodcastDownloadData> podcastDownloadData){
        Integer device1Count = 0;
        Integer device2Count = 0;
        Integer device3Count = 0;
        String device1 = "mobiles & tablets";
        String device2 = "desktops & laptops";
        String device3 = "smart speakers";
        List<String> devicesList = new ArrayList<>();
        for(PodcastDownloadData podcastDownloadData1 : podcastDownloadData){
            String device = podcastDownloadData1.getDeviceType();
            devicesList.add(device);

        }
        for(String device : devicesList){
            if("mobiles & tablets".equalsIgnoreCase(device)){
                device1Count++;
            } else if ("desktops & laptops".equalsIgnoreCase(device)) {
                device2Count++;
            }else device3Count++;
        }
        Integer maxDevice=0;
        String maxDeviceUsed = "";
        Map<String, Integer> deviceTypesAndValues = new HashMap<>();
        deviceTypesAndValues.put(device1, device1Count);
        deviceTypesAndValues.put(device2, device2Count);
        deviceTypesAndValues.put(device3, device3Count);
        for(Map.Entry<String, Integer> entry : deviceTypesAndValues.entrySet()){
            if(maxDevice<entry.getValue()){
                maxDevice = entry.getValue();
                maxDeviceUsed = entry.getKey();
            } else if (maxDevice.equals(entry.getValue())) {
                maxDeviceUsed= entry.getKey();

            }

        }

        System.out.println("Most used device is " + maxDeviceUsed + " and the number of downloads for this device is " + maxDevice);

    }
    private static void preroll(List<PodcastDownloadData>podcastDownloadData){
        Map<String, String>listOfShowsAndIndex = new HashMap<>();
        for(PodcastDownloadData downloadData : podcastDownloadData){
            for(PodcastDownloadData data : podcastDownloadData){
                String showId = data.getDownloadIdentifier().getShowId();
                String breakIndex = data.getPositionUrlSegments().getAdBreakIndex();

            }
        }

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
        // multiple shows could have same number of downloads
        List<String> showNames = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sanFranciscoShowsMap.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
                showNames.clear();
                showNames.add(entry.getKey());
            } else if (max.equals(entry.getValue())) {
                showNames.add(entry.getKey());
            }
        }
        System.out.println("The number of downloads for the most downloaded show is " + max);
        System.out.println("The most downloaded shows is " + showNames);
        assert max == 24 : "Well done!";
        assert showNames.equals("Who Trolled Amber"): "Very well done";;

    }


        }

