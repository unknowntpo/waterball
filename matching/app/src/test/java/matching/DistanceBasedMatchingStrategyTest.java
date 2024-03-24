package matching;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistanceBasedMatchingStrategyTest {
    @Test
    void testDoMatch() {
        Modifier modifier = new NoOpModifier();
        DistanceBasedMatchingStrategy strategy = new DistanceBasedMatchingStrategy(modifier);

        List<Individual> individuals = new ArrayList<Individual>();

        individuals.add(new Individual(1, Individual.Gender.MALE, 25, "Intro of individual 1", "Habits of individual 1",
                "(1,3)"));
        individuals.add(new Individual(2, Individual.Gender.FEMALE, 30, "Intro of individual 2",
                "Habits of individual 2", "(2,4)"));
        individuals.add(new Individual(3, Individual.Gender.MALE, 22, "Intro of individual 3", "Habits of individual 3",
                "(4,5)"));
        individuals.add(new Individual(4, Individual.Gender.FEMALE, 28, "Intro of individual 4",
                "Habits of individual 4", "(5,6)"));

        List<Pair> wantPairs = new ArrayList<Pair>();
        wantPairs.add(new Pair(individuals.get(0), individuals.get(1)));
        wantPairs.add(new Pair(individuals.get(2), individuals.get(3)));

        List<Pair> pairs = strategy.doMatch(individuals);
        Assertions.assertEquals(individuals.size() / 2, pairs.size());
        Assertions.assertTrue(wantPairs.containsAll(pairs));
    }
}
