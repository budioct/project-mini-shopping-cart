package pt.sofco.graha.module.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ItemsRepository extends JpaRepository<ItemsEntity, Long>, JpaSpecificationExecutor<ItemsEntity> {

    Optional<ItemsEntity> findFirstById(Long id);

}
