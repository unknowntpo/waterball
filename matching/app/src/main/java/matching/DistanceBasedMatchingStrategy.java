package matching;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;

public class DistanceBasedMatchingStrategy extends MatchingStrategy {
    private Modifier modifier;

    private static final Logger LOGGER = Logger.getLogger(DistanceBasedMatchingStrategy.class.getClass().getName());

    @Override
    List<Pair> doMatch(List<Individual> individuals) {
        // TODO Auto-generated method stub
        HashMap<Integer, Boolean> selected = new HashMap<Integer, Boolean>();
        Random rand = new Random();
        ArrayList<Pair> pairs = new ArrayList<Pair>();
        while (selected.size() < individuals.size() / 2) {
            Individual left = null;
            Individual right = null;
            int leftIdx = rand.nextInt(individuals.size());
            while (!selected.containsKey(leftIdx)) {
                left = individuals.get(leftIdx);
                selected.put(leftIdx, true);
            }
            int rightIdx = rand.nextInt(individuals.size());
            while (!selected.containsKey(rightIdx)) {
                right = individuals.get(rightIdx);
                selected.put(rightIdx, true);
            }
            pairs.add(new Pair(left, right));
        }
        // A . B C D
        // pick random
        return pairs;
    }

    DistanceBasedMatchingStrategy(Modifier modifier) {
        this.modifier = modifier;
    }
}
