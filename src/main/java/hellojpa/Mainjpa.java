package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Mainjpa {
    public static void main(String[] args) {
        // persistence.xml 에서 데이터베이스 정보를 불러온다.
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        // 공장을 바탕으로 매니저를 찍어낸다.
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // code start
        EntityTransaction tx = entityManager.getTransaction();  // 트랜잭션 내부에서 쿼리를 실행해야 한다.
        tx.begin();         // 트랜잭션 시작
        try{

            Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.changeTeam(team);    // setTeam 보다 명확한 메서드 지정을 해준다.
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            Member findMember = entityManager.find(Member.class, member.getId());
//            Team findTeam = entityManager.find(Team.class, team.getId());
            List<Member> members = findMember.getTeam().getMembers();
            for(Member m : members){
                System.out.println("m.getName() = " + m.getName());
            }
            Team findMemberTeam = findMember.getTeam();

            tx.commit();        // 트랜잭션 실행
        }catch (Exception e){
            // 에러가 발생하면 롤백하기.
            tx.rollback();
        }finally {
            // 어찌되었던 매니저를 닫아주어야 한다.
            entityManager.close();
        }
        entityManagerFactory.close();
        // code end
    }
}
