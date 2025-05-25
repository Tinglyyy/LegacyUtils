package de.unverwunderbar.legacy.legacyutils.scoreboard;

import lombok.Getter;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.List;

public class AnimatedScoreBoard extends UScoreBoard{
    @Getter
    private final List<String> original;

    public AnimatedScoreBoard(DisplaySlot displaySlot, String title, String... lines) {
        super(displaySlot, title, lines);
        original = getLines();
    }

    public void animate(int steps) {
        new ScoreboardAnimationRunnable(this, steps).startAsync();
    }


}
