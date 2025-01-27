package budhioct.dev.service;

import budhioct.dev.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO.ProductResponse> listProduct(String search);
    ProductDTO.ProductResponse detailProduct(Long id);
    ProductDTO.ProductResponse createProduct(ProductDTO.ProductRequest request);
    void removeProduct(long id);

}
