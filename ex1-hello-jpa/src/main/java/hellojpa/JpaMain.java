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
            //JPQL 간단한 사용
            //단순한 조회 방법.
            //객체를 대상으로 하는 객체지향 쿼리라고 생각하면 된다.
            //쿼리 날리는게 똑같은데 무슨 이점이 있냐?
            //페이지네이션 같은 부분에서도 여기서 적고, rdb에 따라 dialect만 설정해주면 알아서 다르게 쿼리가 나간다.
            //또한 Member는 테이블이 아닌 Entity 객체
            //뒤에서 아주 자세히 다룰거다. 지금은 이정도로만 넘어가자.
            List<Member> list = em.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(1) //첫번째부터
                    .setMaxResults(10) //10개 가져와라,, 이 두문장이 페이지네이션 부분
                    .getResultList();
            for (Member member : list) {
                System.out.println(member.getName());
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close(); //실제 애플리케이션이 끝나면 entityManagerFactory를 닫아줘야한다.

    }
}
