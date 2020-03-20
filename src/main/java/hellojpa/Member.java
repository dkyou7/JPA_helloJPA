package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
// DB의 테이블 명이 다를경우
// @Table(name = "USER")
public class Member {

        @Id
        private Long id;

        @Column(name = "name", nullable = false)
        private String username;

        private Integer age;

        @Enumerated(EnumType.STRING)
        private RoleType roleType;

        @Temporal(TemporalType.TIMESTAMP)
        private Date createdDate;

        @Temporal(TemporalType.TIMESTAMP)
        private Date lastModifiedDate;

        // 최신 버전인 경우 이렇게 하면 대체 가능하다.
        // 굳이 어노테이션 붙일 필요 없음.
        private LocalDate test1;
        private LocalDateTime test2;

        @Lob
        private String description;
        //Getter, Setter…
}
