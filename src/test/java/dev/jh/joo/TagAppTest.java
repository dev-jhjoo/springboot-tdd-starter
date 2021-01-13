package dev.jh.joo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagAppTest {
    // tag의 좋은점은 run configuration에서 Tags로 설정하여 해당 tag들만 실행이 가능

    @Test
    @Tag("fast")
    void tagTest01(){
        System.out.println("tagTest01");
    }

    @Test
    @Tag("fast")
    void tegTest02(){
        System.out.println("tagTest02");
    }

    @Test
    @Tag("slow")
    void tagTest03(){
        System.out.println("tagTest03");
    }

    @Test
    @Tag("slow")
    void tagTest04(){
        System.out.println("tagTest04");
    }

    @CustomAnnotationTest
    void customAnnotationTest(){
        System.out.println("customAnnotationTest");
    }
}