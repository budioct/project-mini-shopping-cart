package pt.sofco.graha.module.customers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pt.sofco.graha.module.pesanans.PesanansEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@Entity
@Table(name = "customers")
public class CustomersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @CreatedDate
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Column(name = "update_modified_at", insertable = false)
    private LocalDateTime updateAt;
    @OneToMany(mappedBy = "customer")
    private List<PesanansEntity> pesanansList;

}
