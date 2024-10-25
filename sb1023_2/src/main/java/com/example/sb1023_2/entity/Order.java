package com.example.sb1023_2.entity;

import com.example.sb1023_2.entity.OrderStatus;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

  @Id @GeneratedValue
  @Column(name = "order_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @Setter
  @OneToMany(mappedBy = "order")
  private List<com.example.sb1023_2.entity.OrderItem> orderItems = new ArrayList<>();

  @Temporal(TemporalType.TIMESTAMP)
  private Date orderDate;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  // 연관관계 편의 메서드
  public void setMember(final Member member) {
    if (this.member != null) {
      this.member.getOrders().remove(this);
    }
    this.member = member;
    member.getOrders().add(this);
  }

  public void addOrderItem(final com.example.sb1023_2.entity.OrderItem orderItem) {
    orderItems.add(orderItem);
    orderItem.setOrder(this);
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public Member getMember() {
    return member;
  }

  public List<com.example.sb1023_2.entity.OrderItem> getOrderItems() {
    return orderItems;
  }

    public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(final Date orderDate) {
    this.orderDate = orderDate;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(final OrderStatus status) {
    this.status = status;
  }

}
