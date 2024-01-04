package pt.sofco.graha.rest.enpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.sofco.graha.module.keranjangs.DTO;
import pt.sofco.graha.module.keranjangs.KeranjangsService;
import pt.sofco.graha.rest.handler.RestResponse;
import pt.sofco.graha.utilities.Constants;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/keranjangs")
public class KeranjangsRest {

    @Autowired
    KeranjangsService service;

    @PostMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.list<List<DTO.respKeranjangs>> list(@RequestBody Map<String, Object> filter){

        Page<DTO.respKeranjangs> respKeranjangs = service.list(filter);
        return RestResponse.list.<List<DTO.respKeranjangs>>builder()
                .list(respKeranjangs.getContent())
                .paging(RestResponse.restPagingResponse.builder()
                        .currentPage(respKeranjangs.getNumber())
                        .totalPage(respKeranjangs.getTotalPages())
                        .sizePage(respKeranjangs.getSize())
                        .build())
                .status_code(Constants.OK)
                .message(Constants.ITEM_EXIST_MESSAGE)
                .build();

    }

    @PostMapping(
            path = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.object<DTO.respKeranjangs> create(@RequestBody DTO.reqKeranjangs request){

        DTO.respKeranjangs respKeranjangs = service.create(request);
        return RestResponse.object.<DTO.respKeranjangs>builder()
                .data(respKeranjangs)
                .status_code(Constants.OK)
                .message("Items telah masuk ke dalam keranjang")
                .build();

    }

    @PostMapping(
            path = "/detail",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.object<DTO.respKeranjangs> detail(@RequestBody DTO.reqrmvKeranjangs request){

        DTO.respKeranjangs respKeranjangs = service.detail(request);
        return RestResponse.object.<DTO.respKeranjangs>builder()
                .data(respKeranjangs)
                .status_code(Constants.OK)
                .message("Detail Items yang ada di keranjang anda!")
                .build();
    }

    @PostMapping(
            path = "/remove",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.object<String> remove(@RequestBody DTO.reqrmvKeranjangs request){

        service.remove(request);
        return RestResponse.object.<String>builder()
                .data("")
                .status_code(Constants.OK)
                .message("yahhh items yang ada di keranjang anda telah di hapus!!")
                .build();

    }

}
