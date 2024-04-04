package matching;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HabitBasedMatchingStrategyTest {
	private MatchingStrategy strategy;

	@Test
	void shouldMatch() {
		List<Individual> individuals = new ArrayList<>();

		individuals.add(new Individual(1, Individual.Gender.MALE, 25, "Intro of individual 1",
				"mountain_climbing", "Coor 1"));
		individuals.add(new Individual(2, Individual.Gender.FEMALE, 30, "Intro of individual 2",
				"game,sleep,coffee", "Coor 2"));
		individuals
				.add(new Individual(3, Individual.Gender.MALE, 22, "Intro of individual 3", "game,sleep,coffee,workout",
						"Coor3"));
		individuals.add(new Individual(4, Individual.Gender.FEMALE, 28, "Intro of individual 4",
				"workout,biking,jogging", "Coor 4"));

		this.strategy = new HabitBasedMatchingStrategy();
		var pairs = this.strategy.match(individuals);
		assertTrue(pairs.size() / 2 == individuals.size(),
				"length of pairs should be individuals.size() / 2");

		// assertTrue(this.strategy.match(individuals).size() / 2 == individuals.size(),
		// "length of pairs should be individuals.size() / 2");
		assertTrue(pairs.contains(new Pair(individuals.get(0), individuals.get(3))));
		assertTrue(pairs.contains(new Pair(individuals.get(1), individuals.get(2))));

		// assertEquals("Hello", this.strategy.match(individuals), "should be the
		// same");
	}
}
