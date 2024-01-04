package pt.sofco.graha;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.sofco.graha.module.customers.CustomersEntity;
import pt.sofco.graha.module.customers.CustomersRepository;
import pt.sofco.graha.module.items.ItemsEntity;
import pt.sofco.graha.module.items.ItemsRepository;
import pt.sofco.graha.module.keranjangs.KeranjangsEntity;
import pt.sofco.graha.module.keranjangs.KeranjangsRepository;
import pt.sofco.graha.module.pesanans.PesanansEntity;
import pt.sofco.graha.module.pesanans.PesanansRepository;

import java.util.*;

@SpringBootTest
public class DBTest {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    KeranjangsRepository keranjangsRepository;

    @Autowired
    PesanansRepository pesanansRepository;

    @Test
    void testConnection() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        try {
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Connection Fail!");
        } finally {
            transaction.rollback();
        }

        entityManager.close();
        entityManagerFactory.close();

    }

    @Test
    void insert() {

        CustomersEntity customers = new CustomersEntity();
        customers.setName("Budhi Octaviansyah");
        customers.setAddress("Tangerang, Indonesia");

        Assertions.assertNotNull(customers);

        customersRepository.save(customers);

    }

    @Test
    void getList() {

        List<CustomersEntity> list = customersRepository.findAll();

        Assertions.assertNotNull(list);
        Assertions.assertEquals(1, list.size());

    }

    @Test
    void detail() {

        CustomersEntity customers = customersRepository.findFirstById(1L)
                .orElseThrow(() -> new RuntimeException("customers not found!"));

        Assertions.assertNotNull(customers);
        Assertions.assertEquals("Budhi Octaviansyah", customers.getName());

    }

    @Test
    void update() {

        CustomersEntity customers = customersRepository.findFirstById(1L)
                .orElseThrow(() -> new RuntimeException("customers not found!"));

        customers.setName("Budhi Octaviansyah UPDATED");
        customers.setAddress("Tangerang, Indonesia UPDATED");

        Assertions.assertNotNull(customers);

        customersRepository.save(customers);

    }

    @Test
    void delete() {

        CustomersEntity customers = customersRepository.findFirstById(1L)
                .orElseThrow(() -> new RuntimeException("customers not found!"));

        Assertions.assertNotNull(customers);

        customersRepository.delete(customers);

    }

    @Test
    void insertItems() {

        ItemsEntity items = new ItemsEntity();
        items.setName("es teh manis");
        items.setCategory("minuman");

        Assertions.assertNotNull(items);

        itemsRepository.save(items);

    }

    @Test
    void insertManyItemsToKeranjangs() {

        KeranjangsEntity keranjangs = new KeranjangsEntity();
        keranjangs.setKeterangan("10 Kuah nya banyakin");
        keranjangs.setJumlahPesanan(10);

        ItemsEntity esTehManis = new ItemsEntity();
        esTehManis.setName("Es teh manis Banget");
        esTehManis.setCategory("minuman");
//        esTehManis.setKeranjangs(keranjangs);

        ItemsEntity bakso = new ItemsEntity();
        bakso.setName("Bakso Jumbo");
        bakso.setCategory("makanan");
//        bakso.setKeranjangs(keranjangs);

        List<ItemsEntity> list = new ArrayList<>();
        list.add(esTehManis);
        list.add(bakso);

        keranjangsRepository.save(keranjangs);
        itemsRepository.saveAll(list);

    }

    @Test
    void insertPesanan(){

        CustomersEntity customers = customersRepository.findFirstById(1L)
                .orElseThrow(() -> new RuntimeException("customer not found"));

        Assertions.assertNotNull(customers);
        Assertions.assertEquals(1L, customers.getId());

        KeranjangsEntity keranjangs = keranjangsRepository.findFirstById(3L)
                .orElseThrow(() -> new RuntimeException("pesanan not found"));

        Assertions.assertNotNull(keranjangs);
        Assertions.assertEquals(3L, keranjangs.getId());

        PesanansEntity pesanans = new PesanansEntity();
        pesanans.setNoRegister(UUID.randomUUID().toString());
        pesanans.setCustomer(customers);
        pesanans.setKeranjang(keranjangs);

        Assertions.assertNotNull(pesanans);

        pesanansRepository.save(pesanans);


    }

    @Test
    void compareList(){

        List<Integer> newList = Arrays.asList(102, 103, 105, 108);
        List<Integer> oldList = Arrays.asList(102, 104, 105, 108, 109);

        List<Integer> deleted = new ArrayList<>(oldList);
        deleted.removeAll(newList);
        System.out.println(deleted);

        /**
         * result:
         * [104, 109]
         */

    }

    @Test
    void compareList2(){
        List<Integer> newList = Arrays.asList(102,103,105,108);
        List<Integer> exstList = Arrays.asList(102,104,105,108,109);
        List<Integer> deleList = new LinkedList<Integer>();
        for(int a : exstList){
            if(!newList.contains(a)){
                deleList.add(a);
            }
        }
        System.out.println(newList); //as it is new list.
        System.out.println(deleList);// diff list

        /**
         * result:
         * [102, 103, 105, 108]
         * [104, 109]
         */

    }


}
