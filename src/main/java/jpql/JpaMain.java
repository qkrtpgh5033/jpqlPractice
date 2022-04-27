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

            Test test = new Test();
            test.setTestName("member100");
            em.persist(test);

            Test test2 = new Test();
            test2.setTestName("member2");
            em.persist(test2);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);


            em.persist(member2);

            em.flush();
            em.clear();

            List<Member> resultList = em.createQuery("select m from Member m left join Test t on m.username = t.testName", Member.class)
                    .getResultList();

            System.out.println("resultList.size() = " + resultList.size());


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
