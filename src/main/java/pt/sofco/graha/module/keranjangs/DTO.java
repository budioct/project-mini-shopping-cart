package pt.sofco.graha.module.keranjangs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pt.sofco.graha.module.items.ItemsEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DTO {

    @Getter
    @Setter
    @Builder
    public static class respKeranjangs {
        private Long id;
        private Set<respItems> items;
        private Integer jumlah_unit_pemesanan;
        private String keterangan;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
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
    public static class reqKeranjangs {
        @NotBlank
        private String keterangan;
        List<reqItems> items;
    }

    @Getter
    @Setter
    public static class reqItems {
        @NotNull
        private Long itemId;
        @NotNull
        private Integer countItem;
    }

    @Getter
    @Setter
    public static class reqrmvKeranjangs {
        @NotNull
        private Long id;
    }

    public static respKeranjangs toReqKeranjangs(KeranjangsEntity entity) {
        return respKeranjangs.builder()
                .id(entity.getId())
                .keterangan(entity.getKeterangan())
                .jumlah_unit_pemesanan(entity.getJumlahPesanan())
                .items(entity.getItemsList().stream().map(DTO::toRespItems).collect(Collectors.toSet()))
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
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
