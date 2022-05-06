package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        /**
         * EntityManager : 고객의 요청에 따라 DB 작업을 할 때 필요
         */
        EntityManager em = emf.createEntityManager();
        /**
         * 모든 데이터 변경은 "트랜잭션이 필요"
         */
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        //code
        try{
            Team teamA = new Team();
            teamA.setName("TeamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setAge(10);
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAge(20);
            member2.setTeam(teamB);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setAge(30);
            member3.setTeam(teamB);
            em.persist(member3);


            em.flush();
            em.clear();

            String query = "select t from Team t join fetch t.memberList";
            List<Team> resultList = em.createQuery(query, Team.class)
                    .getResultList();

            for (Team t : resultList) {
                System.out.println(t.getName() +" | member : " + t.getMemberList().size());
            }


            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
