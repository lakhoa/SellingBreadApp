package com.example.SellingBreadApp.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Temporal(TemporalType.DATE)
  @CreationTimestamp
  @Column(name = "createDate")
  private Date createDate;

  @Temporal(TemporalType.TIME)
  @CreationTimestamp
  @Column(name = "createTime")
  private Date createdTime;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "createDateTime")
  private Date createDateTime;

  @Column(name = "totalPrice")
  private Double totalPrice;


  @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems;

}


