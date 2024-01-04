package pt.sofco.graha.module.pesanans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pt.sofco.graha.module.customers.CustomersEntity;
import pt.sofco.graha.module.customers.CustomersRepository;
import pt.sofco.graha.module.items.ItemsEntity;
import pt.sofco.graha.module.items.ItemsRepository;
import pt.sofco.graha.module.keranjangs.KeranjangsEntity;
import pt.sofco.graha.module.keranjangs.KeranjangsRepository;
import pt.sofco.graha.module.validation.ValidationService;

import java.util.*;

@Service
public class PesananServiceImpl implements PesananService {

    @Autowired
    ValidationService validation;

    @Autowired
    PesanansRepository repoPesanan;

    @Autowired
    KeranjangsRepository repoKeranjang;

    @Autowired
    ItemsRepository repoItem;

    @Autowired
    CustomersRepository repoCustomer;

    @Transactional
    public DTO.respPesanans create(DTO.reqPesanans request) {
        validation.validate(request);
        CustomersEntity customers = repoCustomer.findFirstById(request.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
        KeranjangsEntity keranjangs = repoKeranjang.findFirstById(request.getKeranjangId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "keranjang not found"));
        PesanansEntity pesanan = new PesanansEntity();
        pesanan.setNoRegister(UUID.randomUUID().toString());
        pesanan.setCustomer(customers);
        pesanan.setKeranjang(keranjangs);
        repoPesanan.save(pesanan);

        return DTO.toResponsePesanans(pesanan);
    }

    @Transactional(readOnly = true)
    public DTO.respPesanans detail(DTO.reqrmvPesanans request) {
        validation.validate(request);
        PesanansEntity pesanans = repoPesanan.findFirstByNoRegister(request.getNoregister())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pesanan not found"));

        return DTO.toResponsePesanans(pesanans);
    }

    @Transactional
    public DTO.respPesanans update(DTO.reqUpdtPesanans request) {
        validation.validate(request);
        List<Integer> countItems = new LinkedList<>();
        Set<ItemsEntity> listItems = new HashSet<>();
        KeranjangsEntity keranjangsEntity = new KeranjangsEntity();
        PesanansEntity pesanansEntity = new PesanansEntity();
        KeranjangsEntity save = null;
        PesanansEntity pesanans = repoPesanan.findFirstByNoRegister(request.getNoRegister())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pesanan not found"));
        pesanansEntity.setNoRegister(pesanans.getNoRegister());
        pesanansEntity.setCustomer(pesanans.getCustomer());
        repoPesanan.deleteById(pesanans.getId());
        if (Objects.nonNull(request.getUpdateKeranjang())) {
            DTO.reqUpdtKeranjang updateKeranjang = request.getUpdateKeranjang();
            validation.validate(updateKeranjang);
            KeranjangsEntity keranjangs = repoKeranjang.findFirstById(updateKeranjang.getKeranjangId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "keranjang not found"));
            repoKeranjang.removeKeranjangs(keranjangs.getId()); // delete pivot from id keranjangs
            for (DTO.reqUpdtItem reqUpdtItem : updateKeranjang.getUpdateItem()) {
                validation.validate(reqUpdtItem);
                ItemsEntity item = repoItem.findFirstById(reqUpdtItem.getItemId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found"));
                listItems.add(item);
                countItems.add(reqUpdtItem.getCountItem());
            }
            Integer reduce = countItems.stream().reduce(0, Integer::sum);
            keranjangsEntity.setKeterangan(updateKeranjang.getKeterangan());
            keranjangsEntity.setJumlahPesanan(reduce);
            keranjangsEntity.setItemsList(listItems);
            save = repoKeranjang.save(keranjangsEntity);
        }
        pesanansEntity.setKeranjang(save);
        repoPesanan.save(pesanansEntity);

        return DTO.toResponsePesanans(pesanansEntity);
    }

}
