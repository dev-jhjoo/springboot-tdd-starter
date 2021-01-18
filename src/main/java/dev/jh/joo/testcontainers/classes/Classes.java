package dev.jh.joo.testcontainers.classes;

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
    private long id;
    private String name;
}
