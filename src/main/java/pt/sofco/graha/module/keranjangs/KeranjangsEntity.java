package pt.sofco.graha.module.keranjangs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pt.sofco.graha.module.items.ItemsEntity;
import pt.sofco.graha.module.pesanans.PesanansEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@Entity
@Table(name = "keranjangs")
public class KeranjangsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "jumlah_pemesanan")
    private Integer jumlahPesanan;
    @CreatedDate
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Column(name = "update_modified_at", insertable = false)
    private LocalDateTime updateAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "keranjangs_like_items",
            joinColumns = @JoinColumn(name = "keranjang_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private Set<ItemsEntity> itemsList;

    @OneToMany(mappedBy = "keranjang", cascade = CascadeType.ALL)
    private List<PesanansEntity> pesanansList;

}
