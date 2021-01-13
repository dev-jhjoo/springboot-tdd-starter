package dev.jh.joo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesAppTest {


    // src/test/resources/junit-platform.properties 를 참조하여 설정 관리.
    // junit.jupiter.displayname.generator.default = 를 통해 메소드 이름 형식을 일괄적으로 적용 가능
    @Test
    void name_test(){
        System.out.println("name test");
    }

}