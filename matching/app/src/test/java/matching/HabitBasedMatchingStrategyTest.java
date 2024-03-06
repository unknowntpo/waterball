package matching;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver.Match;

public class HabitBasedMatchingStrategyTest {
    private MatchingStrategy strategy;

    @Test
    void shouldMatch() {
        List<Individual> individuals = new ArrayList<>();

        individuals.add(new Individual(1, Individual.Gender.MALE, 25, "Intro of individual 1", "Habits of individual 1",
                "Coor 1"));
        individuals.add(new Individual(2, Individual.Gender.FEMALE, 30, "Intro of individual 2",
                "Habits of individual 2", "Coor 2"));
        individuals.add(new Individual(3, Individual.Gender.MALE, 22, "Intro of individual 3", "Habits of individual 3",
                "Coor 3"));
        individuals.add(new Individual(4, Individual.Gender.FEMALE, 28, "Intro of individual 4",
                "Habits of individual 4", "Coor 4"));

        this.strategy = new HabitBasedMatchingStrategy();
        assertTrue(this.strategy.match(individuals).size() / 2 == individuals.size(),
                "length of pairs should be individuals.size() / 2");
        // assertEquals("Hello", this.strategy.match(individuals), "should be the
        // same");
    }
}
