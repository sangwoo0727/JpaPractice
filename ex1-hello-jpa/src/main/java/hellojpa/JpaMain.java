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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.changeTeam(team);
            em.persist(member);

            // * team.getMembers().add(member);
            em.flush();
            em.clear();

            //team에 setMembers를 세팅한게 없는데 리스트 돌려보면 값이 출력된다.
            // getMembers 할때 select 쿼리문이 나간다.
            // 근데 * 부분을 안쓰면, 뭔가 객체지향스럽지가 않다.
            // 조회는 되는데, 문제가 생기는 부분이 두가지가 있다.
            // 먼저 이 경우는 em.flush를 한 상태인데, 안할 때 문제가 됨.
            // 안하면 1차캐시에만 있는 상태라서 getMembers하면 아무것도 안들어온다.
            // 두번째는 테스트 케이스 작성할 때 문제가 될 수 있다. -> 테스트 케이스는 jpa를 쓰지 않고, 자바코드만을 이용해서 짜기때문.
            // 결론은 양방향 연관관계를 할땐, 양쪽에 다 값을 세팅하는 게 옳다.
            // * team.getMembers().add(member); 이거 매번 작성하면 까먹으니깐, 연관관계 편의 메소드를 작성하자.
            // Member.class에 작성해놓겠습니다.
            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();


            for (Member m : members) {
                System.out.println(m.getName());
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
