package pt.sofco.graha.module.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pt.sofco.graha.module.keranjangs.KeranjangsEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@Entity
@Table(name = "items")
public class ItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_name")
    private String name;
    @Column(name = "category")
    private String category;
    @CreatedDate
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Column(name = "update_modified_at", insertable = false)
    private LocalDateTime updateAt;

    @ManyToMany(mappedBy = "itemsList", cascade = CascadeType.ALL)
    private List<KeranjangsEntity> keranjangsList;
}
