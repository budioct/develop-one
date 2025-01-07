package budhioct.dev.service;

import budhioct.dev.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO.ProductResponse> listProduct();

}
