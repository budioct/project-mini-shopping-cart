package pt.sofco.graha.module.items;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface ItemsService {

    Page<DTO.respItems> list(Map<String, Object> filter);

    DTO.respItems create(DTO.reqItems request);

    void remove(DTO.reqrmvItems request);

}
