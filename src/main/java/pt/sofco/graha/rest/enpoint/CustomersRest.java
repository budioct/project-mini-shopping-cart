package pt.sofco.graha.rest.enpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.sofco.graha.module.customers.CustomersService;
import pt.sofco.graha.module.customers.DTO;
import pt.sofco.graha.rest.handler.RestResponse;
import pt.sofco.graha.utilities.Constants;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersRest {

    @Autowired
    private CustomersService service;

    @PostMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.list<List<DTO.respCustomers>> list(@RequestBody Map<String, Object> filter) {

        Page<DTO.respCustomers> respCustomers = service.list(filter);
        return RestResponse.list.<List<DTO.respCustomers>>builder()
                .list(respCustomers.getContent())
                .paging(RestResponse.restPagingResponse.builder()
                        .currentPage(respCustomers.getNumber())
                        .sizePage(respCustomers.getSize())
                        .totalPage(respCustomers.getTotalPages())
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
    public RestResponse.object<DTO.respCustomers> create(@RequestBody DTO.reqCustomers request) {

        DTO.respCustomers respCustomers = service.create(request);
        return RestResponse.object.<DTO.respCustomers>builder()
                .data(respCustomers)
                .status_code(Constants.OK)
                .message(Constants.CREATE_MESSAGE)
                .build();
    }

    @PostMapping(
            path = "/remove",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.object<String> remove(@RequestBody DTO.reqrmvCustomers request) {

        service.remove(request);
        return RestResponse.object.<String>builder()
                .data("")
                .status_code(Constants.OK)
                .message(Constants.DELETE_MESSAGE)
                .build();

    }


}
