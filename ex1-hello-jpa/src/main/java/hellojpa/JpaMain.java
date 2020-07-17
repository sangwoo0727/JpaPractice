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
            Member member = em.find(Member.class,150L);
            member.setName("AAA");
            //원래대로면, member는 영속성 컨텍스트에 올라간 상태이고, commit 시점에 더티 체킹을 통하여, 차이가 있으면, update 쿼리를 날린다.

            em.detach(member);
            //하지만 이렇게 준영속상태로 만들어버리면, 더 이상 영속성 컨텍스트에서 관리를 안하게 된다.
            //그러면 commit 시점에 update 쿼리 나가지 않는다.

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close(); //실제 애플리케이션이 끝나면 entityManagerFactory를 닫아줘야한다.

    }
}
