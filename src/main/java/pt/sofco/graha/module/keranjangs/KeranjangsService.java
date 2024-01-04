package pt.sofco.graha.module.keranjangs;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface KeranjangsService {
    Page<DTO.respKeranjangs> list(Map<String, Object> filter);

    DTO.respKeranjangs create(DTO.reqKeranjangs request);

    DTO.respKeranjangs detail(DTO.reqrmvKeranjangs request);

    void remove(DTO.reqrmvKeranjangs request);
}
