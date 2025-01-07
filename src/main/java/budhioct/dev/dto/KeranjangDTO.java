package budhioct.dev.dto;

import budhioct.dev.entity.Keranjang;
import budhioct.dev.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class KeranjangDTO {

    @Getter
    @Setter
    @Builder
    public static class KeranjangResponse {
        private Long id;
        @JsonProperty("jumlah_pemesanan")
        private Integer jumlahPemesanan;
        private String keterangan;
        private ProductResponse products;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

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
    }

    public static KeranjangDTO.KeranjangResponse toKeranjangResponse(Keranjang ker) {
        return KeranjangDTO.KeranjangResponse.builder()
                .id(ker.getId())
                .jumlahPemesanan(ker.getJumlahPemesanan())
                .keterangan(ker.getKeterangan())
                .products(toProductResponse(ker.getProduct()))
                .createdAt(ker.getCreatedAt())
                .updatedAt(ker.getUpdatedAt())
                .build();
    }

    public static KeranjangDTO.ProductResponse toProductResponse(Product prod) {
        return KeranjangDTO.ProductResponse.builder()
                .id(prod.getId())
                .kode(prod.getKode())
                .nama(prod.getNama())
                .harga(prod.getHarga())
                .is_ready(prod.getIsReady())
                .gambar(prod.getGambar())
                .build();
    }

}
