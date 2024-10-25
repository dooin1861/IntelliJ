package com.example.sb1018;

import com.example.sb1018.entity.Dept;
import com.example.sb1018.entity.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import java.util.List;

@SpringBootTest
class Sb1018ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Test
    void 영속성_find_테스트() {

        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Dept dept = em.find(Dept.class, 10); // find = select, Dept 테이블 내에 값이 10을 찾는다
        dept.setDname("서울"); // 값이 10인 행(레코드)의 Dname을 서울로 변경
        System.out.println(dept);
        transaction.commit();  // DBMS에 update가 발생함.
    }

    @Test
    void 영속성_merge_테스트() {

        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Dept dept = em.find(Dept.class, 10); // find(영속성을 가짐) = select, Dept 테이블 내에 값 10을 찾는다.
        dept.setDname("성남"); // 값이 10인 행(레코드)의 Dname을 서울로 변경.
        System.out.println(dept);
        em.detach(dept); // detach를 만나면 영속성을 잃어서 데이터베이스에 반영이 안된다.
        em.merge(dept);  // 영속성을 잃었다가 merge를 만나서 다시 영속성을 받음, merge = update
        transaction.commit();  // DBMS에 update가 발생함, 커밋이 없으면 롤백된다.
    }

    @Test
    void 영속성_persist_테스트() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Dept 엔티티 객체 생성
        Dept newDept = new Dept();
        newDept.setDeptno(50);
        newDept.setDname("연구소");
        newDept.setLoc("서울");

        // 엔티티를 데이터베이스에 저장, 새로운 행(레코드)을 생성한다.
        em.persist(newDept);

        // 트랜잭션 커밋
        em.getTransaction().commit();
    }

    @Test
    void 영속성_remove_테스트() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 50);
        System.out.println(dept);
        em.remove(dept);    // remove = delete
        transaction.commit();
    }

    @Test
    void dname_Account_바꾸기() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 10);
        dept.setDname("ACCOUNTING");
        System.out.println(dept);

        transaction.commit();
    }

    @Test
    void createQuery_테스트() {
//        Dept dept = em.find(Dept.class, 10);
//        System.out.println(dept);
//        System.out.println("===========================================");
//        TypedQuery<Dept> query = em.createQuery("SELECT d FROM Dept d", Dept.class);
//        List<Dept> depts = query.getResultList();
//        for (Dept d : depts) {
//            System.out.println(d);
//        }
        System.out.println("===========================================");
        TypedQuery<Emp> query2 = em.createQuery("SELECT e FROM Emp e", Emp.class);
        List<Emp> empList = query2.getResultList();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    void createQuery_영속성_테스트() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        System.out.println("========================================");
        TypedQuery<Dept> query = em.createQuery("select d from Dept d", Dept.class);
        List<Dept> depts = query.getResultList();
        for (Dept d : depts) {
            System.out.println(d);
        }
        System.out.println(depts.get(0));
        depts.get(0).setLoc("서울");
        transaction.commit();
    }

    @Test
    void createQuery_테스트2() {
        String jpql = "SELECT d FROM Dept d WHERE d.dname = :aaa";
        TypedQuery<Dept> query = em.createQuery(jpql, Dept.class);
        query.setParameter("aaa", "Accounting");
        List<Dept> depts = query.getResultList();
        Dept dept = depts.get(0);
        System.out.println(dept);
        System.out.println("===========================================");
        String jpql2 = "SELECT d FROM Emp d WHERE d.deptno = :deptNo";
        TypedQuery<Emp> query2 = em.createQuery(jpql2, Emp.class);
        query2.setParameter("deptNo", dept.getDeptno());
        List<Emp> empList = query2.getResultList();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }
}

