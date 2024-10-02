package org.sonia.huma;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sonia.huma.data.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class PodcastStats {

    public List<PodcastDownloadData> readInput(File file) throws IOException {
        Scanner myReader = new Scanner(file);
        List<PodcastDownloadData> readObjects = new ArrayList<>();

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            PodcastDownloadData podcastDownloadData = parseJsonEntry(line);
            readObjects.add(podcastDownloadData);
        }
        myReader.close();

        return readObjects;
    }

    private PodcastDownloadData parseJsonEntry(String line) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PodcastDownloadData data = mapper.readValue(line, PodcastDownloadData.class);
        return data;
    }


    public void stats(File file) throws IOException {
        List<PodcastDownloadData> podcastDownloadData = readInput(file);

        findMostPopularShowInSanFrancisco(podcastDownloadData);
        mostUsedDevice(podcastDownloadData);
        preroll(podcastDownloadData);


    }

    public MostPopularShowResult findMostPopularShowInSanFrancisco(List<PodcastDownloadData> podcastDownloadData) {
        MostPopularShowResult result = new MostPopularShowResult();
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
        result.setMax(max);
        result.setShowNames(showNames);

        System.out.println("The number of downloads for the most downloaded show is " + max);
        System.out.println("The most downloaded shows is " + showNames);

        return result;
    }

    public MostUsedDeviceResult mostUsedDevice(List<PodcastDownloadData> podcastDownloadData) {
        MostUsedDeviceResult result = new MostUsedDeviceResult();
        Integer device1Count = 0;
        Integer device2Count = 0;
        Integer device3Count = 0;
        String device1 = "mobiles & tablets";
        String device2 = "desktops & laptops";
        String device3 = "smart speakers";
        List<String> devicesList = new ArrayList<>();
        for (PodcastDownloadData podcastDownloadData1 : podcastDownloadData) {
            String device = podcastDownloadData1.getDeviceType();
            devicesList.add(device);

        }
        for (String device : devicesList) {
            if ("mobiles & tablets".equalsIgnoreCase(device)) {
                device1Count++;
            } else if ("desktops & laptops".equalsIgnoreCase(device)) {
                device2Count++;
            } else device3Count++;
        }
        Integer maxDevice = 0;
        String maxDeviceUsed = "";
        Map<String, Integer> deviceTypesAndValues = new HashMap<>();
        deviceTypesAndValues.put(device1, device1Count);
        deviceTypesAndValues.put(device2, device2Count);
        deviceTypesAndValues.put(device3, device3Count);
        for (Map.Entry<String, Integer> entry : deviceTypesAndValues.entrySet()) {
            if (maxDevice < entry.getValue()) {
                maxDevice = entry.getValue();
                maxDeviceUsed = entry.getKey();
            } else if (maxDevice.equals(entry.getValue())) {
                maxDeviceUsed = entry.getKey();

            }

        }
        result.setMaxDeviceUsed(maxDeviceUsed);
        result.setMaxDevice(maxDevice);
        System.out.println("Most used device is " + maxDeviceUsed + " and the number of downloads for this device is " + maxDevice);
        return result;

    }

    public Map<String, Integer> preroll(List<PodcastDownloadData> podcastDownloadData) {
        LinkedHashMap<String,Integer> result = new LinkedHashMap<>();
        Map<String, Integer> listOfShowsAndIndex = new HashMap<>();
        Integer counter = 0;
        String showId = "";
        List<String> breakIndex = new ArrayList<>();
        for (PodcastDownloadData data : podcastDownloadData) {
            showId = data.getDownloadIdentifier().getShowId();
            counter = listOfShowsAndIndex.getOrDefault(showId, 0);
            List<Opportunity> opportunitiesList = data.getOpportunities();
            for (Opportunity opp : opportunitiesList) {
                breakIndex = opp.getPositionUrlSegments().getAdBreakIndex();
                for (String bi : breakIndex) {
                    if (bi.contains("preroll")) {
                        counter++;
                    }
                }
            }

            listOfShowsAndIndex.put(showId, counter);
        }
        //display them in descending order

        result = listOfShowsAndIndex.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue,NewValue)->oldValue,
                                LinkedHashMap::new));

        listOfShowsAndIndex.entrySet()
                .stream()
                .sorted((e1,e2)->e2.getValue().compareTo(e1.getValue()))
                .forEach(entry->System.out.println("Show Id: " + entry.getKey() + ", Preroll Opportunity Number: " + entry.getValue()));

        return result;
    }
//    public void findWeeklyShows(List<PodcastDownloadData>podcastDownloadData) {
//        WeeklyShowsResult result = new WeeklyShowsResult();
//        Map<String, Long> listOfShows = new HashMap<>();
//        String showId = "";
//        Long date = null;
//        Date dateConverted;
//        List<Long> dates = new ArrayList<>();
//        for (PodcastDownloadData data : podcastDownloadData) {
//            showId = data.getDownloadIdentifier().getShowId();
//            List<Opportunity> opportunitiesList = data.getOpportunities();
//            for(Opportunity opportunity:opportunitiesList){
//                date = opportunity.getOriginalEventTime();
//
//            }
//            listOfShows.put(showId, date);
//            System.out.println(listOfShows);
//
//
//        }
//        String show1 = "Who Trolled Amber", show2="Crime Junkie", show3 ="Stuff You Should Know", show4="The Joe Rogan Experience";
//        List<Long>show1List= new ArrayList<>();
//        List<Long>show2List= new ArrayList<>();
//        List<Long>show3List= new ArrayList<>();
//        List<Long>show4List= new ArrayList<>();
//        for(Map.Entry<String, Long>entry : listOfShows.entrySet()){
//            if(entry.getKey().equals(show1)){
//                show1List.add(entry.getValue());
//            } else if (entry.getKey().equals(show2)) {
//                show2List.add(entry.getValue());
//            } else if (entry.getKey().equals(show3)) {
//                show3List.add(entry.getValue());
//            }else{
//                show4List.add(entry.getValue());
//            }
//        }
//        Collections.sort(show1List);
//        Collections.sort(show2List);
//        Collections.sort(show3List);
//        Collections.sort(show4List);
//
//
//    }
}
