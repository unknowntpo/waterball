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
                "Coor 1"));
        individuals.add(new Individual(2, Individual.Gender.FEMALE, 30, "Intro of individual 2",
                "Habits of individual 2", "Coor 2"));
        individuals.add(new Individual(3, Individual.Gender.MALE, 22, "Intro of individual 3", "Habits of individual 3",
                "Coor 3"));
        individuals.add(new Individual(4, Individual.Gender.FEMALE, 28, "Intro of individual 4",
                "Habits of individual 4", "Coor 4"));

        List<Pair> pairs = strategy.doMatch(individuals);
        Assertions.assertEquals(individuals.size() / 2, pairs.size());
    }
}
