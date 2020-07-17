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
            Member member = em.find(Member.class , 150L);
            member.setName("ZZZ");
            //JPA 목적이 자바 컬렉션 다루듯이 다루는 것이 목적이기 때문에,
            //컬렉션에선 값 꺼낸다음에 값 변경한 후 다시 값을 집어넣지 않는다.
            //때문에, JPA 역시도 똑같이 작동하게 되어있다.
            //비밀은 영속성 컨텍스트 안에 있다.
            //커밋할때 엔티티와 스냅샷을 비교한다.
            // 비교를 해서 바뀐게 있으면, Update 쿼리를 날린다.
            tx.commit(); //커밋하는 시점에 데이터베이스에 쿼리가 날아간다.
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close(); //실제 애플리케이션이 끝나면 entityManagerFactory를 닫아줘야한다.

    }
}
