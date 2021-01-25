package dev.jh.joo.testcontainers;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Testcontainers
@Slf4j
public class DockerComposeTest {
    // docker compose 를 통해 여러개 docker container 를 사용 가능하다.
    // test-containers 보다는 테스트가 느릴 수 있다.
    // Application의 docker-compose.yml 에는 local:docker port를 맵핑 했다.
    // 테스트에서는 Application에서 사용하는 docker와 port충돌이 있을 수 있기때문에 테스트 설정에는 docker측 port를 설정하지 않는다.

    @Container
    static DockerComposeContainer composeContainer =
            new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"));

    @Test
    void compseTest(){
        System.out.println("Compose test...");
    }


}
