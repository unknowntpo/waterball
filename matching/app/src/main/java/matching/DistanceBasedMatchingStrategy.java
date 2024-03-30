package matching;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;

public class DistanceBasedMatchingStrategy extends MatchingStrategy {
	private Modifier modifier;

	private static final Logger LOGGER = Logger.getLogger(DistanceBasedMatchingStrategy.class.getClass().getName());

	@Override
	List<Pair> doMatch(List<Individual> individuals) {
		// TODO Auto-generated method stub
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

			// select right
			float[] candidateDistances = new float[individuals.size()];
			for (int i = 0; i < individuals.size(); i++) {
				if ((i == leftIdx) || selected.containsKey(i)) {
					candidateDistances[i] = Float.MAX_VALUE;
					continue;
				}
				candidateDistances[i] = left.getCoordinate()
						.distance(individuals.get(i).getCoordinate());
			}
			int rightIndex = IntStream.range(0, individuals.size())
					.reduce((i, j) -> candidateDistances[i] < candidateDistances[j] ? i : j).orElse(-1);
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

	DistanceBasedMatchingStrategy(Modifier modifier) {
		this.modifier = modifier;
	}
}
