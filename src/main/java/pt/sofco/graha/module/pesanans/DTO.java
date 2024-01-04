package pt.sofco.graha.module.pesanans;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pt.sofco.graha.config.converter.StringToDateConverter;
import pt.sofco.graha.module.customers.CustomersEntity;
import pt.sofco.graha.module.items.ItemsEntity;
import pt.sofco.graha.module.keranjangs.KeranjangsEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DTO {

    @Getter
    @Setter
    @Builder
    public static class respPesanans{
        private Long id;
        private String noRegister;
        private String tanggalPemesanan;
        private respKeranjangs keranjang;
        private respCustomers customer;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }

    @Getter
    @Setter
    @Builder
    public static class respCustomers {
        private Long id;
        private String name;
        private String address;
    }

    @Getter
    @Setter
    @Builder
    public static class respKeranjangs {
        private Long id;
        private Set<respItems> items;
        private Integer jumlah_unit_pemesanan;
        private String keterangan;
    }

    @Getter
    @Setter
    @Builder
    public static class respItems {
        private Long id;
        private String name;
        private String category;
    }

    @Getter
    @Setter
    public static class reqPesanans{
        @NotNull
        private Long keranjangId;
        @NotNull
        private Long customerId;
    }

    @Getter
    @Setter
    public static class reqrmvPesanans{
        @NotBlank
        private String noregister;
    }

    @Getter
    @Setter
    public static class reqUpdtPesanans{
        @NotBlank
        private String noRegister;
        private reqUpdtKeranjang updateKeranjang;
    }

    @Getter
    @Setter
    public static class reqUpdtKeranjang{
        @NotNull
        private Long keranjangId;
        @NotBlank
        private String keterangan;
        private List<reqUpdtItem> updateItem;
    }

    @Getter
    @Setter
    public static class reqUpdtItem{
        @NotNull
        private Long itemId;
        @NotNull
        private Integer countItem;
    }

    public static respPesanans toResponsePesanans(PesanansEntity entity){
        return respPesanans.builder()
                .id(entity.getId())
                .noRegister(entity.getNoRegister())
                .tanggalPemesanan(StringToDateConverter.convert(entity.getCreateAt()))
                .customer(toRespCustomers(entity.getCustomer()))
                .keranjang(toReqKeranjangs(entity.getKeranjang()))
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .build();
    }

    public static respCustomers toRespCustomers(CustomersEntity entity) {
        return respCustomers.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .build();
    }

    public static respKeranjangs toReqKeranjangs(KeranjangsEntity entity) {
        return respKeranjangs.builder()
                .id(entity.getId())
                .keterangan(entity.getKeterangan())
                .jumlah_unit_pemesanan(entity.getJumlahPesanan())
                .items(entity.getItemsList().stream().map(DTO::toRespItems).collect(Collectors.toSet()))
                .build();
    }

    public static respItems toRespItems(ItemsEntity entity) {
        return respItems.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .build();
    }

}
