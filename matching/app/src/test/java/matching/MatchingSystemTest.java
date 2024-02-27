package matching;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver.Match;

public class MatchingSystemTest {
    @Test
    void shouldGreet() {
        MatchingSystem sys = new MatchingSystem();
        assertEquals("Hello", sys.greeting(), "should be the same");
    }
}
