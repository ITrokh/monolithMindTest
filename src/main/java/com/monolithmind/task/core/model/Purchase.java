package com.monolithmind.task.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PURCHASES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    private String id;
    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;
    @Column(name = "PRODUCT_ID", nullable = false)
    private String productId;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_PURCHASE_USER_ID"), nullable = false)
    private User user;
}
