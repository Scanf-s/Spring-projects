import org.junit.jupiter.api.*;

public class JUnitCycleTest {

    @BeforeAll // 전체 테스트 시작 전, 1회 실행하는 메서드 -> static으로 선언
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach // 테스트케이스 실행 전마다 실행
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() {
        System.out.println("@Test1");
    }

    @Test
    public void test2() {
        System.out.println("@Test2");
    }

    @AfterAll // 전체 테스트를 마치고 종료하기 전에 1회 실행하는 메서드 -> static으로 선언
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach // 테스트케이스 하나 끝날때마다 실행
    public void afterEach() {
        System.out.println("@AfterEach");
    }

}
