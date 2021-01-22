package dev.jh.joo.testcontainers;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

/*****************************************************************
 * spring은 기본적으로 각각의 DB의 정책을 따라가기때문에 ex.isolation level...
 * 테스트를 위해 알파는 로컬디비 베타는 h2 이런식으로 DB를 섞어 쓰게되면 prod에서 문제가 생길 수 있다.
 *
 * 만약 /scripts.sh 처럼 개발용 DB, 테스트용 DB sh을 만드는것 또한 관리포인트가 늘어나는 것이다.
 *
 * ## 이런점을 도와주는 라이브러리가 test-containers 이다.
 * 장점. 테스트 실행시 DB를 설정하거나 별도의 프로그램 또는 스크립트를 실행할 필요 없다.
 * 장점. 보다 prod에 가까운 테스트를 만들 수 있다.
 *
 * 단점. 테스트가 느려질 수 있다.
 *****************************************************************/


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Testcontainers
public class ClassServiceTest {

    @Autowired
    ClassesRepository classesRepository;

//    @Container
//    static PostgreSQLContainer postgreSQLContainer =
//            new PostgreSQLContainer("postgres").withDatabaseName("tc-test");
//    PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();
    // static 이면 beforeAll 처럼 모든 테스트가 공유 가능
    // 인스턴스로 생성시 각 테스트마다 생성되기 때문에 자원낭비일 수 있다.

    // 이런식으로 test-containers에서 기본적으로 제공하지 않는 모듈도 public한 저장소에서 이미지 이름만 있으면 사용가능.
    // local 저장소를 먼저 검색 후 없으면 download 후 사용
    @Container
    static GenericContainer genericContainer = new GenericContainer("postgres")
            .withEnv("POSTGRES_DB","gc-test");


    // Containers를 static으로 공유하고 해당 자원을 각 테스트 실행시 마다 deleteAll하면 좀 더 효율적으로 테스트 가능
    @BeforeEach
    void beforeEach(){
        System.out.println("Before each");
        classesRepository.deleteAll();
    }

    @Test
    void create() {
        System.out.println("CREATE TEST");

        Classes classes = new Classes(1L, "TEST-CONTAINERS");
        classesRepository.save(classes);

        System.out.println("============");
        classesRepository.findAll().forEach(System.out::println);
        System.out.println("============");

        Classes findClasses = classesRepository.findById(1L).orElseThrow(IllegalArgumentException::new);

        System.out.println(findClasses.toString());
    }
}
