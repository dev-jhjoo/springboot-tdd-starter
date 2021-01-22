package dev.jh.joo.testcontainers;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Classes {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
