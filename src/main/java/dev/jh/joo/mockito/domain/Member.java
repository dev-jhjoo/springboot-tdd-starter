package dev.jh.joo.mockito.domain;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private long id;
    private String email;
}
