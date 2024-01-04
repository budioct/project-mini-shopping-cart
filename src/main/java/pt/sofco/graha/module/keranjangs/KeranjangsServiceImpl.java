package pt.sofco.graha.module.keranjangs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pt.sofco.graha.common.Models;
import pt.sofco.graha.module.items.ItemsEntity;
import pt.sofco.graha.module.items.ItemsRepository;
import pt.sofco.graha.module.validation.ValidationService;

import java.util.*;

@Slf4j
@Service
public class KeranjangsServiceImpl implements KeranjangsService {

    @Autowired
    ValidationService validation;

    @Autowired
    KeranjangsRepository repoKeranjang;

    @Autowired
    ItemsRepository repoItems;

    @Transactional(readOnly = true)
    public Page<DTO.respKeranjangs> list(Map<String, Object> filter) {
        Models<KeranjangsEntity> models = new Models<>();
        Page<KeranjangsEntity> keranjangsPage = repoKeranjang.findAll(models.where(filter), models.pageableSort(filter));
        List<DTO.respKeranjangs> respKeranjangs = keranjangsPage.getContent().stream().map(DTO::toReqKeranjangs).toList();
        if (respKeranjangs.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "list keranjang not found!");
        }

        return new PageImpl<>(respKeranjangs, keranjangsPage.getPageable(), keranjangsPage.getTotalElements());
    }

    @Transactional
    public DTO.respKeranjangs create(DTO.reqKeranjangs request) {

        validation.validate(request);
        List<Integer> countItems = new LinkedList<>();
        Set<ItemsEntity> listItems = new HashSet<>();
        KeranjangsEntity keranjangs = new KeranjangsEntity();
        keranjangs.setKeterangan(request.getKeterangan());
        if (Objects.nonNull(request.getItems())) {
            for (DTO.reqItems item : request.getItems()) {
                validation.validate(item);
                ItemsEntity items = repoItems.findFirstById(item.getItemId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "items not found!"));
                countItems.add(item.getCountItem());
                listItems.add(items);
            }
        }
        Integer reduce = countItems.stream().reduce(0, Integer::sum);
        keranjangs.setItemsList(listItems);
        keranjangs.setJumlahPesanan(reduce);
        repoKeranjang.save(keranjangs);

        return DTO.toReqKeranjangs(keranjangs);
    }

    @Transactional(readOnly = true)
    public DTO.respKeranjangs detail(DTO.reqrmvKeranjangs request) {
        validation.validate(request);
        KeranjangsEntity keranjangs = repoKeranjang.findFirstById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "keranjangs not found!"));

        return DTO.toReqKeranjangs(keranjangs);
    }

    @Transactional
    public void remove(DTO.reqrmvKeranjangs request) {
        validation.validate(request);
        KeranjangsEntity keranjangs = repoKeranjang.findFirstById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "keranjangs not found!"));

        repoKeranjang.removeKeranjangs(keranjangs.getId());

    }

}
