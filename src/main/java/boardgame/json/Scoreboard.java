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
 *
 */
public class Scoreboard {

    private static Scoreboard scoreboard = null;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    private static final File scoreboardFile = new File("./scoreboard.json");

    private List<ScoreboardElements> scoreboardElements;

    public static Scoreboard getInstance() {
        if (scoreboard == null)
            scoreboard = new Scoreboard();

        return scoreboard;
    }

    private Scoreboard() {
        try {
            if (!scoreboardFile.exists()) {
                scoreboardFile.createNewFile();
                FileWriter fw = new FileWriter(scoreboardFile);
                fw.write("[]");
                fw.close();
                scoreboardElements = List.of();
            } else
                loadScoreboard(new FileInputStream(scoreboardFile));
        } catch (IOException e) {
            e.printStackTrace();
            Logger.warn("An I/O Exception has been occurred!");
        }
    }

    private void loadScoreboard(InputStream inputStream){
        try {
            scoreboardElements = OBJECT_MAPPER.readValue(inputStream, new TypeReference<List<ScoreboardElements>>() {}); {
            }
        } catch (IOException e) {
            e.printStackTrace();
            Logger.warn("An I/O Exception has been occurred!");
        }
    }

    public List<ScoreboardElements> getScoreboard() {
        try {
            loadScoreboard(new FileInputStream(scoreboardFile));
            return scoreboardElements.stream()
                    .sorted(Comparator.comparing(ScoreboardElements::getDate)).collect(Collectors.toList());
        }catch(IOException e) {
            e.printStackTrace();
            Logger.warn("An I/O Exception has been occurred!");
        }
        throw new IllegalArgumentException();
    }

    public void saveScoreboardElement(ScoreboardElements element) {
        try {
            FileWriter fileWriter = new FileWriter(scoreboardFile);

            SequenceWriter sequenceWriter = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValuesAsArray(fileWriter);
            for(ScoreboardElements scoreboardElement: scoreboardElements){
                sequenceWriter.write(scoreboardElement);
            }
            sequenceWriter.write(element);
            sequenceWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.warn("An I/O Exception has been occurred!");
        }
    }

}
