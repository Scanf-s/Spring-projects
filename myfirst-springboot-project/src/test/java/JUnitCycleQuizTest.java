import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCycleQuiz {

    @BeforeEach
    public void hello() {
        System.out.println("Hello!");
    }

    @AfterAll
    static void bye() {
        System.out.println("Bye!");
    }

    @Test
    public void test() {
        System.out.println("Test!");
    }

    @Test
    public void test2() {
        System.out.println("Test2!");
    }
}
