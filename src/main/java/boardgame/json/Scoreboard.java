package boardgame.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.tinylog.Logger;
import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class for managing the JSON file and the Scoreboard.
 */
public class Scoreboard {

    private static Scoreboard leaderboard = null;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    private static final File leaderboardFile = new File(System.getProperty("user.home")+File.separator+"scoreboard.json");

    private List<Element> scoreboardElements;

    /**
     * Returns the {@code Scoreboard} instance.
     *
     * @return {@code Scoreboard} object
     */
    public static Scoreboard getInstance(){
        if(leaderboard == null){
            leaderboard = new Scoreboard();
        }
        return leaderboard;
    }

    private Scoreboard() {
        try {
            if (!leaderboardFile.exists()) {
                leaderboardFile.createNewFile();
                FileWriter fileWriter = new FileWriter(leaderboardFile);
                fileWriter.write("[]");
                fileWriter.close();
                scoreboardElements = List.of();
            } else {
                loadLeaderboard(new FileInputStream(leaderboardFile));
            }
        } catch (IOException e) {
            Logger.warn("An I/O Exception has been occurred!");
            e.printStackTrace();
        }
    }

    private void loadLeaderboard(InputStream is){
        try {
            scoreboardElements = OBJECT_MAPPER.readValue(is, new TypeReference<List<Element>>() {}); {
            }
        } catch (IOException e) {
            Logger.warn("An I/O Exception has been occurred!");
            e.printStackTrace();
        }
    }

    /**
     * Gets all the elements of the Scoreboard from the scoreboard.json file.
     *
     * @return the scoreboard elements ordered by date
     */
    public List<Element> getLeaderboard() {
        try {
            loadLeaderboard(new FileInputStream(leaderboardFile));
            return scoreboardElements.stream()
                    .sorted(Comparator.comparing(Element::getDate)).collect(Collectors.toList());
        }catch(IOException e) {
            Logger.warn("An I/O Exception has been occurred!");
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    /**
     *
     *
     * @param Element
     */
    public void saveScoreboardElement(Element Element){
        try {
            FileWriter fileWriter = new FileWriter(leaderboardFile);

            SequenceWriter sequenceWriter = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValuesAsArray(fileWriter);
            for(boardgame.json.Element leaderboardElement: scoreboardElements){
                sequenceWriter.write(leaderboardElement);
            }
            sequenceWriter.write(Element);
            sequenceWriter.close();
        } catch (IOException e) {
            Logger.warn("An I/O Exception has been occurred!");
            e.printStackTrace();
        }
    }

}