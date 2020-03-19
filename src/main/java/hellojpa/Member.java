package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// DB의 테이블 명이 다를경우
// @Table(name = "USER")
public class Member {

    @Id
    private Long id;
    // DB의 컬럼 명이 다를 경우
    // @Column(name = "USERNAME")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
