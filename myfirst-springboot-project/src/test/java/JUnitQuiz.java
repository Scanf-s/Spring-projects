import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitQuiz {

    @Test
    public void junitTest() {
        String name1 = "Kim";
        String name2 = "Kim";
        String name3 = "Yang";

        Assertions.assertThat(name1).isNotNull();
        Assertions.assertThat(name2).isNotNull();
        Assertions.assertThat(name3).isNotNull();

        Assertions.assertThat(name1).isEqualTo(name2);
        Assertions.assertThat(name1).isNotEqualTo(name3);
    }

    @Test
    public void junitTest2() {
        int num1 = 15;
        int num2 = 0;
        int num3 = -4;

        Assertions.assertThat(num1).isPositive();
        Assertions.assertThat(num2).isZero();
        Assertions.assertThat(num3).isNegative();
        Assertions.assertThat(num1).isGreaterThan(num2);
        Assertions.assertThat(num3).isLessThan(num2);
    }
}
