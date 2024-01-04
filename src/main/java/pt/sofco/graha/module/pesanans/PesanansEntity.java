package pt.sofco.graha.module.pesanans;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pt.sofco.graha.module.customers.CustomersEntity;
import pt.sofco.graha.module.keranjangs.KeranjangsEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@Entity
@Table(name = "pesanans")
public class PesanansEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "no_register")
    private String noRegister;
    @CreatedDate
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Column(name = "update_modified_at", insertable = false)
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomersEntity customer;

    @ManyToOne
    @JoinColumn(name = "keranjang_id", referencedColumnName = "id")
    private KeranjangsEntity keranjang;

}
