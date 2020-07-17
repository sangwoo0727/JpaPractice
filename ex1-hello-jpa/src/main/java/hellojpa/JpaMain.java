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
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            //데이터 베이스에는 이미 101L을 pk로 가지고있는 데이터를 넣어둔 상태이다.
            // 1차 캐시는 트랜잭션 단위이므로, 이 경우, 첫번째 find에서는 1차 캐시에 내용이 저장되어있지 않으므로,
            // 데이터베이스에 접근하는 쿼리문(select)가 나가게 되고,
            // 두번째 find에서는 1차 캐시에 저장된 상태이므로 쿼리문이 안날라간다.
            // 즉, 1번만 쿼리문이 수행됨.
            System.out.println(findMember1 == findMember2);
            // 영속 엔티티의 동일성이 보장된다 . 결과값 true
            // 마치 자바 컬렉션에서 꺼내서 하듯이
            // 1차 캐시가 있기 때문에 가능하다.

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close(); //실제 애플리케이션이 끝나면 entityManagerFactory를 닫아줘야한다.

    }
}
