package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //emf는 애플리케이션 로딩시점에 딱 한번만 만든다.

        EntityManager em = emf.createEntityManager();
        //트랜잭션 단위별로 em을 꼭 만들어야 한다.
        //code 작성 부분
        EntityTransaction tx = em.getTransaction();
        //JPA에서는 트랜잭션 단위가 매우 중요하다.
        //모든 데이터를 변경하는 모든 작업은 꼭 트랜잭션 안에서 작업한다.

        tx.begin();
        try{
            //영속 상태란?
            
            //비영속상태
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            System.out.println("==Before==");
            em.persist(member); //영속성 컨텍스트에 저장
            System.out.println("==After==");

            Member findMember = em.find(Member.class, 101L); //출력문을 보면, select 쿼리가 안나간다.
            System.out.println("findMember.id " + findMember.getId());
            System.out.println("findMember.name" + findMember.getName());
            // 조회를 했는데, select 쿼리가 안날라간 것은
            // 영속성 컨텍스트에 저장을 하면서, 1차 캐시에 저장이 되므로.. 똑같은 pk로 값을 가져오면 1차캐시를 먼저 조회한 것.

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close(); //실제 애플리케이션이 끝나면 entityManagerFactory를 닫아줘야한다.

    }
}
