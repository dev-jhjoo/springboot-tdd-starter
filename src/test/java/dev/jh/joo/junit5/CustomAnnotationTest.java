package dev.jh.joo.junit5;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD }) // 어디에 사용가능한가: 메소드에 사용 가능하다.
@Retention(RetentionPolicy.RUNTIME) // 전략: RUNTIME까지 유지한다.
@Test
@Tag("custom")
public @interface CustomAnnotationTest {
}
