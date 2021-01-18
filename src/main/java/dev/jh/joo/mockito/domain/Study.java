package dev.jh.joo.mockito.domain;

import dev.jh.joo.mockito.service.StudyStatus;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@ToString
@Getter
@Entity
public class Study {

    @Id
    @GeneratedValue
    private Long id;

    private int limitCount;
    private String name;
    private StudyStatus status;
    private LocalDateTime openedDateTime;
    @ManyToOne
    private Member owner;

    public Study(int limitCount, String name) {
        this.limitCount = limitCount;
        this.name = name;
        this.status = StudyStatus.DRAFT;
    }

    public Study(int limitCount) {
        if(limitCount < 0) throw new IllegalArgumentException("limit 은 0보다 커야 한다.");
        this.limitCount = limitCount;
    }

    public void open(){
        this.status = StudyStatus.OPENED;
        this.openedDateTime = LocalDateTime.now();
    }

//    public void setStatus(StudyStatus status) {
//        this.status = status;
//    }
//    public void setOpenedDateTime(LocalDateTime openedDateTime) {
//        this.openedDateTime = openedDateTime;
//    }

    public void setOwner(Member member) {
        this.owner = member;
    }

}
