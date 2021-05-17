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

    private static Scoreboard scoreboard = null;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    private static final File scoreboardFile = new File(System.getProperty("user.home")+File.separator+"scoreboard.json");

    private List<Element> scoreboardElements;

    /**
     * Returns the {@code Scoreboard} instance.
     *
     * @return {@code Scoreboard} object
     */
    public static Scoreboard getInstance(){
        if(scoreboard == null){
            scoreboard = new Scoreboard();
        }
        return scoreboard;
    }

    private Scoreboard() {
        try {
            if (!scoreboardFile.exists()) {
                scoreboardFile.createNewFile();
                FileWriter fileWriter = new FileWriter(scoreboardFile);
                fileWriter.write("[]");
                fileWriter.close();
                scoreboardElements = List.of();
            } else {
                loadLeaderboard(new FileInputStream(scoreboardFile));
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
            loadLeaderboard(new FileInputStream(scoreboardFile));
            return scoreboardElements.stream()
                    .sorted(Comparator.comparing(Element::getDate)).collect(Collectors.toList());
        }catch(IOException e) {
            Logger.warn("An I/O Exception has been occurred!");
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    /**
     * Writing a new element into the scoreboard.json file.
     *
     * @param Element the element which is needed to be saved
     */
    public void saveScoreboardElement(Element Element){
        try {
            FileWriter fileWriter = new FileWriter(scoreboardFile);

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