package dev.jh.joo.mockito.domain;

import lombok.Getter;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
public class Study {
    private int limitCount;
    private String name;
    private LocalDateTime openedDateTime;

    @ManyToOne
    private Member owner;

    public Study(int limitCount, String name) {
        this.limitCount = limitCount;
        this.name = name;
    }

    public Study(int limitCount) {
        if(limitCount < 0) throw new IllegalArgumentException("limit 은 0보다 커야 한다.");
        this.limitCount = limitCount;
    }

    public void setOwner(Member member) {
        this.owner = member;
    }
}
