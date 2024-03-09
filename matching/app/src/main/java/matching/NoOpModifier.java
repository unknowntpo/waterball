package matching;

public class NoOpModifier implements Modifier {
    public int modify(int distance) {
        return distance;
    }
}
