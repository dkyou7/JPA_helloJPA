package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Mainjpa {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // code start
        EntityTransaction tx = entityManager.getTransaction();  // 트랜잭션 내부에서 쿼리를 실행해야 한다.
        tx.begin();         // 트랜잭션 시작
        try{
            // 삽입
            // Member member = new Member();
            // member.setId(1L);
            // member.setName("유동관");

            // entityManager.persist(member);
            // 조회
            // Member findMember = entityManager.find(Member.class,1L);
            List<Member> result = entityManager.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(1)  // 1번째부터 시작해서
                    .setMaxResults(10)  // 10개 가져와라. 페이징 처리할 때 유용 (선택)
                    .getResultList();

            for(Member mem: result){
                System.out.println("mem.getName() = " + mem.getName());
            }
            // System.out.println("findMember = " + findMember.getId());
            // System.out.println("findMember.id = " + findMember.getName());

            // 수정
            // Member findMember = entityManager.find(Member.class,1L);
            // findMember.setName("helloJPA");

            // 삭제
            // Member findMember = entityManager.find(Member.class,1L);
            // entityManager.remove(findMember);
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
