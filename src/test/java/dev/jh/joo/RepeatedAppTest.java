package dev.jh.joo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

class RepeatedAppTest {

    @DisplayName("반복 테스트")
    @RepeatedTest(value = 5, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void createTest01(RepetitionInfo info){
        System.out.println("Repeated Test=" + info.getCurrentRepetition() + "/" + info.getTotalRepetitions());
    }

    // 반복마다 다른 값을 사용하고 싶을때
    @DisplayName("Parameterized test")
    @ParameterizedTest(name = "{index}, {displayName}, param={0}")
    @ValueSource(strings = {"one", "two", "three", "four", "five"})
    void parameterizedTest(String param){
        System.out.println("param=" + param);
    }

    @DisplayName("Parameterized test upgrade version")
    @ParameterizedTest(name = "{index}, {displayName}, param={0}")
    @ValueSource(strings = {"one", "two", "three", "four", "five"})
//    @EmptySource // Empty값 추가
//    @NullSource // Null값 추가
//    @NullAndEmptySource // 두개다 추가
    void parameterizedTest02(String param){
        System.out.println("param=" + param);
    }


    @DisplayName("Parameterized test upgrade version")
    @ParameterizedTest(name = "{index}, {displayName}, param={0}")
    @ValueSource(ints = {1,2,3,4,5})
    void parameterizedTest03(Integer param){
        System.out.println("param=" + param);
    }

    // 인자를 객체로 변경해서 반복 테스트 가능
    @DisplayName("Parameterized test upgrade version")
    @ParameterizedTest(name = "{index}, {displayName}, param={0}")
    @ValueSource(ints = {1,2,3,4,5})
    void parameterizedTest04(
            @ConvertWith(AppConvertor.class) App app){
        System.out.println("app=" + app.toString());
    }

    static class AppConvertor extends SimpleArgumentConverter{
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(App.class, targetType, "Can only convert to App");
            return new App(Integer.parseInt(source.toString()));
        }
    }



    // 이렇게 객채 생성 가능
    @DisplayName("Parameterized test upgrade version")
    @ParameterizedTest(name = "{index}, {displayName}, param={0}, {1}")
    @CsvSource({"10, test01" , "20, test02"})
    void parameterizedTest05(Integer limit, String name){
        RepeatedApp repeatedApp = new RepeatedApp(limit, name);
        System.out.println(repeatedApp.toString());
    }
    @DisplayName("Parameterized test upgrade version")
    @ParameterizedTest(name = "{index}, {displayName}, param={0}, {1}")
    @CsvSource({"10, test01" , "20, test02"})
    void parameterizedTest05(ArgumentsAccessor argumentsAccessor){
        RepeatedApp repeatedApp = new RepeatedApp(
                argumentsAccessor.getInteger(0), argumentsAccessor.getString(1)
        );
        System.out.println(repeatedApp.toString());
    }


    
}