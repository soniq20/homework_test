import org.junit.jupiter.api.Test;
import org.sonia.huma.PodcastStats;
import org.sonia.huma.data.MostPopularShowResult;
import org.sonia.huma.data.MostUsedDeviceResult;
import org.sonia.huma.data.PodcastDownloadData;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PodcastStatsTest {

    //private static final File FILE = new File(
         //"C:\\Users\\SHuma\\IdeaProjects\\HomeWork\\src\\test\\resources\\downloads.txt");
    File file = new File("src/test/resources/downloads.txt");
    PodcastStats podcastStats = new PodcastStats();

    @Test
    void testFindMostPopularShowInSanFrancisco() throws Exception {
        //PodcastStats podcastStats = new PodcastStats();
        List<PodcastDownloadData> podcastDownloadData = podcastStats.readInput(file);
        MostPopularShowResult result = podcastStats.findMostPopularShowInSanFrancisco(podcastDownloadData);

        assertEquals(24, result.getMax());
        assertEquals(List.of("Who Trolled Amber"), result.getShowNames());
    }
    @Test
    void testMostUsedDevice() throws  Exception{
       //PodcastStats podcastStats = new PodcastStats();
        List<PodcastDownloadData> podcastDownloadData = podcastStats.readInput(file);
        MostUsedDeviceResult result = podcastStats.mostUsedDevice(podcastDownloadData);

        assertEquals("mobiles & tablets", result.getMaxDeviceUsed());
        assertEquals(60, result.getMaxDevice());
    }
    @Test
    void testPreroll() throws Exception{
        //PodcastStats podcastStats = new PodcastStats();
        List<PodcastDownloadData> podcastDownloadData = podcastStats.readInput(file);
        Map<String, Integer> expectedResult = new HashMap<>();
        Map<String, Integer> result = podcastStats.preroll(podcastDownloadData);
        expectedResult.put("Stuff You Should Know", 40);
        expectedResult.put("Who Trolled Amber", 40);
        expectedResult.put("Crime Junkie",30);
        expectedResult.put("The Joe Rogan Experience",10);

        assertEquals(expectedResult,result);



    }
}
