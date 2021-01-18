package dev.jh.joo.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// 2. 이렇게 하면 해당 class는 공유됨
// 또한 @BeforeAll, @AfterAll에 경우에도 static을 붙히지 않아도 된다.
class TestInstanceTest {

    private int value = 0;

    // 1. 이런식으로 진행 했을 경우 value는 공유되지 않는다.
    // this 또한 다른 객체를 의미한다.
    // 기본적으로 JUnit 테스트간의 의존성을 없애기 위함
    @Test
    void add01(){
        System.out.println(this);
        System.out.println(value++);
    }

    @Test
    void add02(){
        System.out.println(this);
        System.out.println(value++);
    }




}