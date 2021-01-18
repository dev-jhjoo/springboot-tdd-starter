package dev.jh.joo.testcontainers;


import dev.jh.joo.testcontainers.classes.Classes;
import dev.jh.joo.testcontainers.classes.ClassesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClassServiceTest {

    @Autowired
    ClassesRepository classesRepository;

    @Test
    void create(){
        System.out.println("CREATE TEST");

        // given
        Classes classes = new Classes(1L, "TESTCONTAINERS");
        classesRepository.save(classes);

        System.out.println(classesRepository.findById(1L).get());
    }
}
