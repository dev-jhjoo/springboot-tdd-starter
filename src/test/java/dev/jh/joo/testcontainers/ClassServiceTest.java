package dev.jh.joo.testcontainers;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;



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
@Slf4j // 10.
@ContextConfiguration(initializers = ClassServiceTest.ContainerPropertyInitializer.class)
public class ClassServiceTest {

    // 10.이렇게 생성하는것 대신에 @Slf4j 를 사용할 수 있다.
    Logger LOGGER = LoggerFactory.getLogger(ClassServiceTest.class);

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    Environment environment;

    @Value("${container.port}")
    int port;

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
            .withExposedPorts(5432) // local port 지정
//            .waitingFor(Wait.forListeningPort())
//            .waitingFor(Wait.forHttp("/test/check")) // 이런식으로 원하는 end-point에 요청이 오는지, 요청이 올때까지 기다리는 그런 기능
//            .withEnv("POSTGRES_HOST_AUTH_METHOD" ,"trust")
            .withEnv("POSTGRES_USER", "jhjoo")
            .withEnv("POSTGRES_PASSWORD", "jhjoo")
//            .withEnv("POSTGRES_DB", "tc-test")
    ;

    @BeforeAll
    static void beforeAll(){
        // 10.log 는 @Slf4j annotation에서 기본으로 만들어줌
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log);
        genericContainer.followOutput(logConsumer); // container 안에 생성되는 log도 확인 가능
    }


    // Containers를 static으로 공유하고 해당 자원을 각 테스트 실행시 마다 deleteAll하면 좀 더 효율적으로 테스트 가능
    @BeforeEach
    void beforeEach(){
        log.info("followOutput Before each =================");
        // local 5432 port에 mapping된 docker port 확인
//        System.out.println("getMappedPort=" + genericContainer.getMappedPort(15432));
        System.out.println("getLogs=" + genericContainer.getLogs()); // container log 확인
        System.out.println("============");
        classesRepository.findAll().forEach(System.out::println);
        System.out.println("============");
        classesRepository.deleteAll();
        LOGGER.info("LOGGER   =============================");
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

    @Test
    void applicationContextInitTest(){
        // test-container 생성하고 해당 정보를 ApplicationContextInitializer 에 env 를 통해서 등록하기
        System.out.println("environment.getProperty(\"container.port\")=" + environment.getProperty("container.port"));
        System.out.println("@Value(\"container.port\")=" + port);
    }

    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("container.port=" + genericContainer.getMappedPort(5432))
                    .applyTo(applicationContext.getEnvironment());
        }
    }

}
