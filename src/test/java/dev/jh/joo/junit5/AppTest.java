package dev.jh.joo.junit5;

import dev.jh.joo.junit5.App;
import dev.jh.joo.junit5.AppStatus;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AppTest {

    @Test
    void create_test(){
        App app = new App(1);
        assertNotNull(app);
        System.out.println("createTest");
    }

    @Test
    @DisplayName("App 생성시 status값이 DRAFT 인지 확인")
    void createStatusConfirmTest(){
        App app = new App(1);
        assertNotNull(app); // app null check


//        3가지 방식으로 표현 가능
//        3번째 인자는 실행이 실패했을때 넘기게되는데
//        1번째 방식처럼 message만 넘기게 될경우 문자열 연산을 성공이든 실패든 실행하기 때문에
//        연산 시간을 줄이기 위해서는 lambda 또는 Supplier를 생성해서 표현하는게 좋다.
//        lambda식 추천

//        assertEquals(AppStatus.DRAFT, app.getStatus(), "App 생성시 status default값은 DRAFT여야 한다.");
        assertEquals(AppStatus.DRAFT, app.getStatus(), () -> "App 생성시 status default값은 DRAFT여야 한다.");
//        assertEquals(AppStatus.DRAFT, app.getStatus(), new Supplier<String>() {
//            @Override
//            public String get() {
//                return "App 생성시 status default값은 DRAFT여야 한다.";
//            }
//        });

        // assertEquals parameter -> 1. 예상, 2. 실제
    }


    @Test
    @Disabled
    void assertAllTest(){
        App app = new App(-10);

        // assertAll로 묶어주면 중간에 error가 나서 테스트가 실패해도 그 다음 assert를 진행해서
        // 일단 모든 assert의 결과를 알 수 있다.
        assertAll(
                () -> assertNotNull(app),
                () -> assertEquals(AppStatus.STARTED, app.getStatus(), () -> "app's status default DRAFT"),
                () -> assertTrue(app.getLimit() > 0, () -> "app의 limit은 0보다 커야한다.")
        );
    }

    @Test
    void assertThrowTest(){
        assertThrows(
                IllegalArgumentException.class, () -> new App(-1)
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new App(-1)
        );

        assertEquals("limit은 0보다 커야한다.", exception.getMessage());
    }

    @Test
    @DisplayName("time out test")
    void assertTimeOutTest(){
        assertTimeout(Duration.ofSeconds(1), () -> new App(10)); // 성공

//        assertTimeout(Duration.ofMillis(100), () -> {
//            new App(10);
//            Thread.sleep(300);
//        });
        // 이렇게 할 경우 기대한 100밀리세컨드가 아닌 테스트 하는 블럭내에 모든 프로세스가 종료되기까지 대기함
        // 이미 100이 지났지만 300을 기다리는 낭비를 한다. 그래서 그걸 방지하는게 assertTimeoutPreemptively

        // 하지만 assertTimeoutPreemptively는 thread관련 작업을 할 경우 잘 사용해야함.
        // 관련 keyword=ThreadLocal
//        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
//            new App(10);
//            Thread.sleep(300);
//        });
    }


    @DisplayName("실행 안하는 테스트 😆")
    @Test
    @Disabled // 실행하지 않는다.
    void createTest01(){
        System.out.println("createTest01");
    }

    @Test
    void createTest02(){
        System.out.println("createTest02");
    }

    @BeforeAll // 모든 테스트 실행 전 한번
    static void beforeAll(){
        // 무조건 static
        System.out.println("Before All");
    }

    @AfterAll // 모든 테스트 실행 후 한번
    static void afterAll(){
        // 무조건 static
        System.out.println("After All");
    }

    @BeforeEach // 각 테스트가 실행하기번 전에 한번
    void beforeEach(){
        System.out.println("Before Each");
    }

    @AfterEach // 각 테스트가 끝난뒤 한번
    void afterEach(){
        System.out.println("After Each");
    }
}