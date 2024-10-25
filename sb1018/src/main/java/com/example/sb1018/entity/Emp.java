package com.example.sb1018.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@ToString
@Getter
@Setter

public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empno;
    private String ename;
    private String job;
    private String mgr;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 날짜 형식 지정
    private Date hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;
}
