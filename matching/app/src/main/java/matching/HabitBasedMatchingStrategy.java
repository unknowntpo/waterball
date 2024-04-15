package matching;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.logging.Logger;

import matching.MatchingStrategy;

public class HabitBasedMatchingStrategy extends MatchingStrategy {

    private static final Logger LOGGER = Logger.getLogger(DistanceBasedMatchingStrategy.class.getClass().getName());

    @Override
    List<Pair> doMatch(List<Individual> individuals) {
//        HashMap<Integer, Boolean> selected = new HashMap<Integer, Boolean>();
        Map<Integer,Boolean> selected = IntStream.range(0, individuals.size()).boxed().collect(Collectors.toMap(Function.identity(), i -> Boolean.FALSE));
        Random rand = new Random();
        ArrayList<Pair> pairs = new ArrayList<Pair>();
        for (int term = 0; term < individuals.size() / 2; term++) {
            Individual left = null;
            Individual right = null;

            int leftIdx = -1;
            do {
                leftIdx = rand.nextInt(individuals.size());
            } while (selected.get(leftIdx));
            left = individuals.get(leftIdx);
            selected.put(leftIdx, true);

            // Select right
//			int[] habitsMatchCount = new int[individuals.size()];
            Map<Integer, Integer> habitsMatchCount = selected.entrySet().stream()
                    .filter(entry -> entry.getValue() == false)
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> 0));
            int finalLeftIdx = leftIdx;
            habitsMatchCount.forEach((candidateIdx, v) -> {
                habitsMatchCount.put(candidateIdx, countMatchedHabits(individuals.get(finalLeftIdx), individuals.get(candidateIdx)));
            });

            var rightIndex = habitsMatchCount
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey).orElseThrow(()-> new IllegalStateException("No maximum value found"));

            selected.put(rightIndex, true);
            right = individuals.get(rightIndex);

            LOGGER.info(String.format("add new pair %d, %d", left.getId(), right.getId()));
            pairs.add(new Pair(left, right));
        }

        LOGGER.info(selected.toString());
        LOGGER.info(pairs.toString());

        // A . B C D
        // pick random
        return pairs;
    }

    private int countMatchedHabits(Individual left, Individual right) {
        HashSet leftHabits = new HashSet(Arrays.asList(left.getHabits().split(",")));
        HashSet rightHabits = new HashSet(Arrays.asList(right.getHabits().split(",")));
        leftHabits.retainAll(rightHabits);
        return leftHabits.size();
    }

    // @Override
    // List<Pair> doMatch(List<Individual> individuals) {
    // // TODO Auto-generated method stub
    // // throw new UnsupportedOperationException("Unimplemented method 'doMatch'");
    // return new ArrayList<Pair>();
    // }
}
