import org.junit.jupiter.api.Test;
import org.sonia.huma.PodcastStats;
import org.sonia.huma.data.MostPopularShowResult;
import org.sonia.huma.data.MostPrerollShow;
import org.sonia.huma.data.MostUsedDeviceResult;
import org.sonia.huma.data.PodcastDownloadData;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PodcastStatsTest {

    private static final File FILE = new File(
            "C:\\Users\\SHuma\\IdeaProjects\\HomeWork\\src\\test\\resources\\downloads.txt");

    @Test
    void testFindMostPopularShowInSanFrancisco() throws Exception {
        PodcastStats podcastStats = new PodcastStats();
        List<PodcastDownloadData> podcastDownloadData = podcastStats.readInput(FILE);
        MostPopularShowResult result = podcastStats.findMostPopularShowInSanFrancisco(podcastDownloadData);

        assertEquals(24, result.getMax());
        assertEquals(List.of("Who Trolled Amber"), result.getShowNames());
    }
    @Test
    void testMostUsedDevice() throws  Exception{
        PodcastStats podcastStats = new PodcastStats();
        List<PodcastDownloadData> podcastDownloadData = podcastStats.readInput(FILE);
        MostUsedDeviceResult result = podcastStats.mostUsedDevice(podcastDownloadData);

        assertEquals("mobiles & tablets", result.getMaxDeviceUsed());
        assertEquals(60, result.getMaxDevice());
    }
    @Test
    void testPreroll() throws Exception{
        PodcastStats podcastStats = new PodcastStats();
        List<PodcastDownloadData> podcastDownloadData = podcastStats.readInput(FILE);
        MostPrerollShow result = podcastStats.preroll(podcastDownloadData);
        LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<>();
        resultMap.put("Show Id: Stuff You Should Know", 40);
        resultMap.put("Show Id: Who Trolled Amber", 40);
        resultMap.put("Show Id: Crime Junkie",30);
        resultMap.put("Show Id: The Joe Rogan Experience",10);

        assertEquals(resultMap,result);



    }
}
