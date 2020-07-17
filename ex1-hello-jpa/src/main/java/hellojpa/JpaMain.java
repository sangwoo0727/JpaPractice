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
            member.setId(100L);
            member.setName("HelloJPA");
            
            //여기부터 영속상태
            em.persist(member); //entityManager 안에있는 영속성 컨텍스트에서 관리를 받기 시작한다는 것.
            // 사실은 이때 db에 저장되지 않는다. -> 커밋때 쿼리문이 날아간다.
            
            em.detach(member); //영속성 컨턱스트에서 분리, 준영속 상태
            em.remove(member); //실제 디비 삭제를 요청하는 상태

            tx.commit(); // 커밋 시점에 영속성 컨텍스트에 있는애가 디비로 쿼리가 날라간다.

            //어플리케이션이랑 데이터베이스 사이에 중간계층이 있는 것이다. 영속성 컨텍스트라는..
            // 이걸 사용하면 얻는 이점은 다음 시간에 고고
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close(); //실제 애플리케이션이 끝나면 entityManagerFactory를 닫아줘야한다.

    }
}
