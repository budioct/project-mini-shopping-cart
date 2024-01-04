package pt.sofco.graha.rest.handler;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class RestResponse {

    @Getter
    @Setter
    @Data
    @Builder
    public static class list<T>{
        private Integer status_code;
        private String message;
        private restPagingResponse paging;
        private T list;
        private String errors;
    }

    @Getter
    @Setter
    @Data
    @Builder
    public static class object<T> {
        private Integer status_code;
        private String message;
        private T data;
        private String errors;
    }

    @Getter
    @Setter
    @Data
    @Builder
    public static class restError<T> {
        private Integer status_code;
        private String message;
        private String errors;
    }

    @Getter
    @Setter
    @Data
    @Builder
    public static class restPagingResponse {
        private Integer currentPage;
        private Integer totalPage;
        private Integer sizePage;

    }

}
