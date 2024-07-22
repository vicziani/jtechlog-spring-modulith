package training.mentoringapp;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>("postgres:16.3")
                .withReuse(true);
    }

    @Bean
    @ServiceConnection
    KafkaContainer kafkaContainer() {
        return new KafkaContainer()
                .withReuse(true);
    }

    @Bean
    @ServiceConnection("openzipkin/zipkin")
    GenericContainer<?> zipkinContainer() {
        return new GenericContainer<>("openzipkin/zipkin:3.4.0")
                .withExposedPorts(9411)
                .withReuse(true);
    }
}
