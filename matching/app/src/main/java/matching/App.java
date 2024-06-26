/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package matching;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class App {
	private static final Logger LOGGER = Logger.getLogger(DistanceBasedMatchingStrategy.class.getClass().getName());

	public String getGreeting() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		List<Individual> individuals = new ArrayList<Individual>();

		individuals.add(new Individual(1, Individual.Gender.MALE, 25, "Intro of individual 1", "Habits of individual 1",
				"(1,3)"));
		individuals.add(new Individual(2, Individual.Gender.FEMALE, 30, "Intro of individual 2",
				"Habits of individual 2", "(2,4)"));
		individuals.add(new Individual(3, Individual.Gender.MALE, 22, "Intro of individual 3", "Habits of individual 3",
				"(4,5)"));
		individuals.add(new Individual(4, Individual.Gender.FEMALE, 28, "Intro of individual 4",
				"Habits of individual 4", "(5,6)"));

		// match with distance (reversed)
		var distanceBasedStrategy = new DistanceBasedMatchingStrategy(new ReverseModifier());
		var pairs = distanceBasedStrategy.doMatch(individuals);

		LOGGER.info(pairs.toString());

		var habitBasedStrategy = new HabitBasedMatchingStrategy();
		pairs = habitBasedStrategy.doMatch(individuals);
		LOGGER.info(pairs.toString());
	}
}
