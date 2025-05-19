package de.unverwunderbar.legacy.legacyutils.scoreboard;

import de.unverwunderbar.legacy.legacyutils.Legacyutils;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AnimationRunnable extends BukkitRunnable {

    private final AnimatedScoreBoard scoreBoard;

    private final int steps;

    private final AtomicInteger i = new AtomicInteger(0);

    public AnimationRunnable(AnimatedScoreBoard scoreBoard, int steps) {

        this.scoreBoard = scoreBoard;
        this.steps = steps > 0 ? steps : 1;
    }

    public void startAsync() {
        runTaskTimerAsynchronously(Legacyutils.main, 0, 20L);
    }

    public void start() {
        runTaskTimer(Legacyutils.main, 0, 20L / steps);
    }

    @Deprecated(since = "Use start() or startAsync()")
    @Override
    public void run() {
        List<String> lines = scoreBoard.getLines();

        scoreBoard.clear();

        for(int j = 0; j < lines.size(); j++) {
            //scoreBoard.removeLine(j);
            scoreBoard.setLine(j, rotateString(lines.get(j), lines.get(j).length()/steps));

        }

        scoreBoard.generate(false);

        int current = i.incrementAndGet();

        if(current > steps) {
//            scoreBoard.clear();
//            scoreBoard.setLines(scoreBoard.getOriginal());

            this.cancel();
        }
    }

    private static String rotateString(String source, int steps) {
        List<Character> chars = source.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        Collections.rotate(chars, steps);

        StringBuilder res = new StringBuilder();
        for(Character c : chars)
            res.append(c.charValue());

        return res.toString();
    }
}
