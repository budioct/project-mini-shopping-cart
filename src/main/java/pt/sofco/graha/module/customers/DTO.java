package pt.sofco.graha.module.customers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

public class DTO {

    @Getter
    @Setter
    @Builder
    public static class respCustomers {
        private Long id;
        private String name;
        private String address;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
    }

    @Getter
    @Setter
    public static class reqCustomers {
        @NotBlank
        private String name;
        @NotBlank
        private String address;
    }

    @Getter
    @Setter
    public static class reqrmvCustomers{
        @NotNull
        private Long id;
    }

    public static respCustomers toRespCustomers(CustomersEntity entity) {
        return respCustomers.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .build();
    }

}
