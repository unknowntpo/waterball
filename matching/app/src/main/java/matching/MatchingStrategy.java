package matching;

import javax.annotation.meta.Exclusive;
import java.lang.IllegalArgumentException;
import java.util.List;

public abstract class MatchingStrategy {
    public List<Pair> match(List<Individual> individuals) throws IllegalArgumentException {
        validateArgs(individuals);
        return doMatch(individuals);
    }

    abstract List<Pair> doMatch(List<Individual> individuals);

    public void validateArgs(List<Individual> individuals) throws IllegalArgumentException {
        if (!(individuals.size() > 0 && individuals.size() % 2 == 0)) {
            throw new IllegalArgumentException("length of individuals should be at least 2");
        }
    }
}
