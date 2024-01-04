package pt.sofco.graha.module.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pt.sofco.graha.common.Models;
import pt.sofco.graha.module.validation.ValidationService;

import java.util.List;
import java.util.Map;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    ValidationService validation;

    @Autowired
    ItemsRepository repo;

    @Transactional(readOnly = true)
    public Page<DTO.respItems> list(Map<String, Object> filter) {
        Models<ItemsEntity> models = new Models<>();
        Page<ItemsEntity> itemsPage = repo.findAll(models.where(filter), models.pageableSort(filter));
        List<DTO.respItems> respItems = itemsPage.getContent().stream().map(DTO::toRespItems).toList();
        if (respItems.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "list items not found!");
        }

        return new PageImpl<>(respItems, itemsPage.getPageable(), itemsPage.getTotalElements());
    }

    @Transactional
    public DTO.respItems create(DTO.reqItems request) {
        validation.validate(request);
        ItemsEntity items = new ItemsEntity();
        items.setName(request.getName());
        items.setCategory(request.getCategory());
        repo.save(items);

        return DTO.toRespItems(items);
    }

    @Transactional
    public void remove(DTO.reqrmvItems request) {
        validation.validate(request);
        ItemsEntity items = repo.findFirstById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "items not found!"));

        repo.delete(items);
    }
}
