package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            //entityManager를 자바 컬렉션처럼 이해하면된다. -> 내 객체를 대신 저장해주는 애
            Member findMember = em.find(Member.class,1L); //pk가 1인 애를 조회
            //em.remove(findMember); //삭제

            //수정
            findMember.setName("HelloJPA");
            //자바 객체에서 값만 수정했는데도, Update 쿼리가 나간다.
            // JPA를 통해서 entity를 가져오면 JPA가 관리한다. 트랜잭션 commit 시점에 확인하고, 바뀌어있으면 Update 쿼리가 나간다.


            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close(); //실제 애플리케이션이 끝나면 entityManagerFactory를 닫아줘야한다.

    }
}
