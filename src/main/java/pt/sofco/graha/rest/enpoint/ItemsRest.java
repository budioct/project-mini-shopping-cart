package pt.sofco.graha.rest.enpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.sofco.graha.module.items.DTO;
import pt.sofco.graha.module.items.ItemsService;
import pt.sofco.graha.rest.handler.RestResponse;
import pt.sofco.graha.utilities.Constants;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/items")
public class ItemsRest {

    @Autowired
    ItemsService service;

    @PostMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.list<List<DTO.respItems>> list(@RequestBody Map<String, Object> filter) {

        Page<DTO.respItems> respItems = service.list(filter);
        return RestResponse.list.<List<DTO.respItems>>builder()
                .list(respItems.getContent())
                .paging(RestResponse.restPagingResponse.builder()
                        .currentPage(respItems.getNumber())
                        .sizePage(respItems.getSize())
                        .totalPage(respItems.getTotalPages())
                        .build())
                .status_code(Constants.OK)
                .message(Constants.ITEM_EXIST_MESSAGE)
                .build();
    }

    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.object<DTO.respItems> create(@RequestBody DTO.reqItems request) {

        DTO.respItems respItems = service.create(request);
        return RestResponse.object.<DTO.respItems>builder()
                .data(respItems)
                .status_code(Constants.OK)
                .message(Constants.CREATE_MESSAGE)
                .build();
    }

    @PostMapping(
            path = "/remove",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.object<String> remove(@RequestBody DTO.reqrmvItems request){

        service.remove(request);
        return RestResponse.object.<String>builder()
                .data("")
                .status_code(Constants.OK)
                .message(Constants.DELETE_MESSAGE)
                .build();
    }


}
