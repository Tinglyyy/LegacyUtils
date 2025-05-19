package de.unverwunderbar.legacy.legacyutils.scoreboard;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UScoreBoard {
    @Getter
    @Setter
    private List<String> lines;

    private final Scoreboard board;

    private final DisplaySlot displaySlot;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private char prefix = ' ';

    private Objective objective;

    public UScoreBoard(DisplaySlot displaySlot, String title, String... lines) {
        this(displaySlot, title, Arrays.asList(lines));
    }

    protected UScoreBoard(DisplaySlot displaySlot, String title, List<String> lines) {
        this.lines = lines;

        Collections.reverse(this.lines);

        this.displaySlot = displaySlot;
        this.title = title;

        board = Bukkit.getScoreboardManager().getNewScoreboard();
        generate();
    }

    protected void generate() {
        generate(true);
    }

    protected void generate(boolean register) {
        objective = objective == null ?
                board.registerNewObjective(title, "dummy") : objective;
        objective.setDisplaySlot(displaySlot);

        if(!register)
            board.resetScores(title);

        int i = 0;
        for(String line : lines) {
            Score score;
            if(line.endsWith("\n")) {
                score = objective.getScore("-----------------------");
                score.setScore(i);
                i++;

                line = line.substring(0, line.length()-1);
            }

            score = objective.getScore(prefix + line);
            score.setScore(i);
            i++;


        }
    }

    public void show(Player player) {
        player.setScoreboard(board);
    }

    public void setLine(int i, String line) {
        lines.set(i, line);
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public void removeLine(int i) {
        lines.remove(i);
    }

    public void removeLine(String line) {
        lines.remove(line);
    }

    public void clear() {
        for(String s : board.getEntries()) {
            board.resetScores(s);
        }
        //lines.clear();
    }
}
