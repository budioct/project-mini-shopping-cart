package pt.sofco.graha.module.pesanans;

public interface PesananService {

    DTO.respPesanans create(DTO.reqPesanans request);

    DTO.respPesanans detail(DTO.reqrmvPesanans request);

    DTO.respPesanans update(DTO.reqUpdtPesanans request);
}
