package pt.sofco.graha.module.items;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class DTO {

    @Getter
    @Setter
    @Builder
    public static class respItems {
        private Long id;
        private String name;
        private String category;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }

    @Getter
    @Setter
    public static class reqItems {
        @NotBlank
        private String name;
        @NotBlank
        private String category;
    }

    @Getter
    @Setter
    public static class reqrmvItems {
        @NotNull
        private Long id;
    }

    public static respItems toRespItems(ItemsEntity entity) {
        return respItems.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .build();
    }

}
