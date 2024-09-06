import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {

    @DisplayName("1 + 2 is 3")
    @Test
    public void test() {
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertThat(a+b).isEqualTo(sum);
    }
}
