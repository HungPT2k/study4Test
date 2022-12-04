//package com.example.study4test.entity;
//
//
//import lombok.Data;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//@Data
//public abstract class Auditable<U> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
////    @CreationTimestamp
////    @Column(name = "created_at")
////    private LocalDateTime createdAt;
////    @UpdateTimestamp
////    @Column(name = "updated_at")
////    private LocalDateTime updatedAt;
////    @ManyToOne
////    @JoinColumn(name = "created_by")
////    @ManyToOne
////    @JoinColumn(name = "created_by_id")
//
////    @Column(name = "created_by")
////    @CreatedBy
////    private String createdBy;
//
////    @ManyToOne
////    @JoinColumn(name = "updated_by")
////    @ManyToOne
////    @JoinColumn(name = "updated_by_id")
//
////    @Column(name = "updated_by")
////    @LastModifiedBy
////    private String updatedBy;
//    @Column(name = "created_by")
//    @CreatedBy
//    protected U createdBy;
//
//    @CreatedDate
//    protected LocalDateTime creationDate;
//
//    @Column(name = "u_by")
//    @LastModifiedBy
//    protected U lastModifiedBy;
//
//    @LastModifiedDate
//    protected LocalDateTime lastModifiedDate;
//
//
//    }
//
//
//
//
