package budhioct.dev.dto;

import budhioct.dev.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ProductDTO {

    @Getter
    @Setter
    @Builder
    public static class ProductResponse {
        private long id;
        private String kode;
        private String nama;
        private Integer harga;
        @JsonProperty("is_ready")
        private boolean is_ready;
        private String gambar;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    public static ProductResponse toProductResponse(Product prod) {
        return ProductResponse.builder()
                .id(prod.getId())
                .kode(prod.getKode())
                .nama(prod.getNama())
                .harga(prod.getHarga())
                .is_ready(prod.isReady())
                .gambar(prod.getGambar())
                .createdAt(prod.getCreatedAt())
                .updatedAt(prod.getUpdatedAt())
                .build();
    }

}
