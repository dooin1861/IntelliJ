package com.example.sb1018.service;

import com.example.sb1018.entity.Dept;
import com.example.sb1018.entity.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
@Slf4j
public class EmService {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    public Emp updateEmp(int empNo, String empName) {

        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Emp emp = em.find(Emp.class, empNo);
        if (emp != null) {
            emp.setEname(empName);
            log.info("update emp {} with name {}", empNo, empName);
        } else {
            log.info("해당 {} 부서가 없음.", empNo);
        }
        transaction.commit();  // DBMS에 update가 발생함, commit
        return emp;
    }


    public List<Emp> findAllEmps() {
        TypedQuery<Emp> query = em.createQuery("SELECT e FROM Emp e", Emp.class);
        return query.getResultList();
    }

    public Emp findByEmpno(Integer empno) {
        return em.find(Emp.class, empno); // empno로 직원 정보를 조회
    }
}
