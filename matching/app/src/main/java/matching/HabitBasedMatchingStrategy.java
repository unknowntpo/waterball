package matching;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.logging.Logger;
import matching.MatchingStrategy;

public class HabitBasedMatchingStrategy extends MatchingStrategy {

	private static final Logger LOGGER = Logger.getLogger(DistanceBasedMatchingStrategy.class.getClass().getName());

	@Override
	List<Pair> doMatch(List<Individual> individuals) {
		HashMap<Integer, Boolean> selected = new HashMap<Integer, Boolean>();
		Random rand = new Random();
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		while (selected.size() < individuals.size()) {
			Individual left = null;
			Individual right = null;

			int leftIdx = -1;
			do {
				leftIdx = rand.nextInt(individuals.size());
			} while (selected.containsKey(leftIdx));
			left = individuals.get(leftIdx);
			selected.put(leftIdx, true);

			// Select right
			int[] habitsMatchCount = new int[individuals.size()];
			for (int i = 0; i < individuals.size(); i++) {
				if ((i == leftIdx) || selected.containsKey(i)) {
					habitsMatchCount[i] = -1;
					continue;
				}
				habitsMatchCount[i] = countMatchedHabits(left, individuals.get(i));
			}
			int rightIndex = IntStream.range(0, individuals.size())
					.reduce((i, j) -> habitsMatchCount[i] < habitsMatchCount[j] ? i : j).orElse(-1);
			selected.put(rightIndex, true);
			right = individuals.get(rightIndex);

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
