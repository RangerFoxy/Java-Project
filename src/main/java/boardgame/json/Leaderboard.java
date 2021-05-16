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

public class Leaderboard {

    private static Leaderboard leaderboard = null;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final File leaderboardFile = new File("./leaderboard.json");

    private List<LeaderboardElement> leaderboardElements;

    public static Leaderboard getInstance(){
        if(leaderboard == null){
            leaderboard = new Leaderboard();
        }
        return leaderboard;
    }

    private Leaderboard() {
        try {
            if (!leaderboardFile.exists()) {
                leaderboardFile.createNewFile();
                FileWriter fileWriter = new FileWriter(leaderboardFile);
                fileWriter.write("[]");
                fileWriter.close();
                leaderboardElements = List.of();
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
            leaderboardElements = OBJECT_MAPPER.readValue(is, new TypeReference<List<LeaderboardElement>>() {}); {
            }
        } catch (IOException e) {
            Logger.warn("An I/O Exception has been occurred!");
            e.printStackTrace();
        }
    }

    public List<LeaderboardElement> getLeaderboard() {
        try {
            loadLeaderboard(new FileInputStream(leaderboardFile));
            return leaderboardElements.stream()
                    .sorted(Comparator.comparing(LeaderboardElement::getDate)).collect(Collectors.toList());
        }catch(IOException e) {
            Logger.warn("An I/O Exception has been occurred!");
            e.printStackTrace();
        }
        throw new IllegalArgumentException("An Exception has been occurred!");
    }

    public void saveLeaderboardElement(LeaderboardElement Element){
        try {
            FileWriter fileWriter = new FileWriter(leaderboardFile);

            SequenceWriter sequenceWriter = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValuesAsArray(fileWriter);
            for(LeaderboardElement leaderboardElement: leaderboardElements){
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