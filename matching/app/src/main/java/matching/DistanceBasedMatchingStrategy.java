package matching;

import java.util.List;

public class DistanceBasedMatchingStrategy extends MatchingStrategy {
    private Modifier modifier;

    @Override
    List<Pair> doMatch(List<Individual> individuals) {
        // TODO Auto-generated method stub
        return null;
    }

    DistanceBasedMatchingStrategy(Modifier modifier) {
        this.modifier = modifier;
    }
}
