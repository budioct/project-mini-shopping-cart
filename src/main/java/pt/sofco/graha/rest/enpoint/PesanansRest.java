package pt.sofco.graha.rest.enpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.sofco.graha.module.pesanans.DTO;
import pt.sofco.graha.module.pesanans.PesananService;
import pt.sofco.graha.rest.handler.RestResponse;
import pt.sofco.graha.utilities.Constants;

@RestController
@RequestMapping("/api/v1/pesanans")
public class PesanansRest {

    @Autowired
    private PesananService service;

    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.object<DTO.respPesanans> create(@RequestBody DTO.reqPesanans request){

        DTO.respPesanans responsePesanan = service.create(request);
        return RestResponse.object.<DTO.respPesanans>builder()
                .data(responsePesanan)
                .status_code(Constants.OK)
                .message("Pesanan telah diterima dengan nomor register ... " + responsePesanan.getNoRegister())
                .build();
    }

    @PostMapping(
            path = "/detail",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.object<DTO.respPesanans> detail(@RequestBody DTO.reqrmvPesanans request){

        DTO.respPesanans responsePesanan = service.detail(request);
        return RestResponse.object.<DTO.respPesanans>builder()
                .data(responsePesanan)
                .status_code(Constants.OK)
                .message(Constants.ITEM_EXIST_MESSAGE)
                .build();
    }

    @PostMapping(
            path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RestResponse.object<DTO.respPesanans> update(@RequestBody DTO.reqUpdtPesanans request){

        DTO.respPesanans responsePesanan = service.update(request);
        return RestResponse.object.<DTO.respPesanans>builder()
                .data(responsePesanan)
                .status_code(Constants.OK)
                .message(Constants.UPDATE_MESSAGE)
                .build();
    }

}
