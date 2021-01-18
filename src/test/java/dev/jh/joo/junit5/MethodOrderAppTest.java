package dev.jh.joo.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


// integration test, functional test 에 경우 순서가 필요한 경우가 있다.
// 회원가입에 경우 순서가 필요할 수 있다.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MethodOrderAppTest {

    @Order(2) // Spring에도 @Order annotation 이 있다. 햇갈리지 말자.
    @DisplayName("Order = 2")
    @Test
    void orderOne(){
        System.out.println("one");
    }

    @Order(1)
    @DisplayName("Order = 1")
    @Test
    void orderTwo(){
        System.out.println("two");
    }

}