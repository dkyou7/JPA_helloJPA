package hellojpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Member {
        @Id @GeneratedValue
        @Column(name = "MEMBER_ID")
        private Long id;

        @Column(name = "MEMBER_NAME")
        private String name;

//        @Column(name = "TEAM_ID")
//        private Long teamId;


        @ManyToOne      // 관계가 뭐야.
        @JoinColumn(name = "TEAM_ID")   // 조인을 어떤걸로 할꺼야.
        private Team team;

        public void changeTeam(Team team){
                this.team = team;
                team.getMembers().add(this);
        }
}
