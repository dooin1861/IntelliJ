package com.example.sb1023;

import com.example.sb1023.entity.Member;
import com.example.sb1023.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.model.IModel;

import javax.persistence.*;
import java.util.List;

@SpringBootTest
class Sb1023ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void test_Save() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        // 멤버1 저장
        Member member1 = new Member("member1", "고양이1");
        member1.setTeam(team1);
        em.persist(member1);

        // 팀2 저장
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        // 멤버2 저장
        Member member2 = new Member("member2", "고양이2");
        member2.setTeam(team2);
        em.persist(member2);

        System.out.println(member1);
        System.out.println(member2);
        transaction.commit();
    }

    @Test
    void test_find() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // 조회
        String jpql = "select m from Member m join m.team t where t.name =: teamName";
        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();
        for (Member member : resultList) {
            System.out.println(member);
        }

        transaction.commit();
    }

    @Test
    void test_find2() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Member member = em.find(Member.class, "member1");
        System.out.println(member);
        System.out.println("팀이름 : " + member.getTeam().getName());

        transaction.commit();
    }

    @Test
    void test_update() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // 새로운 팀2
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        em.find(Member.class, "member1").setTeam(team2);

        transaction.commit();
    }

    @Test
    void test_연관관계제거() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);

        transaction.commit();
    }

    @Test
    void test_연관된_엔티티_삭제() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // 팀2 삭제
//        Team team = em.find(Team.class, "team2");
//        em.remove(team);
        
        // 팀1 삭제
        Team team = em.find(Team.class, "team1");
        Member member2 = em.find(Member.class, "member2");
        member2.setTeam(null);
        em.remove(team);

        transaction.commit();
    }

    @Test
    void test_양방향_탐색() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Team team = em.find(Team.class, "team1");

        for (Member member : team.getMembers()) {
            System.out.println(member.getTeam().getName());
        }

//        team.getMembers().forEach(t -> System.out.println("member.username=" + t.getUsername()));

        transaction.commit();
    }

    @Test
    void test_순수객체() {
        Member member1 = new Member("member1", "고양이1");
        Member member2 = new Member("member2", "고양이2");
        Team team = new Team("team1", "팀1");

        member1.setTeam(team);
        member2.setTeam(team);
        System.out.println(member1);
        System.out.println(member2);
    }

}
