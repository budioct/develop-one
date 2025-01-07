package budhioct.dev.service.impl;

import budhioct.dev.dto.ProductDTO;
import budhioct.dev.entity.Product;
import budhioct.dev.repository.ProductRepository;
import budhioct.dev.service.ProductService;
import budhioct.dev.utilities.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ValidationService validation;

    @Transactional(readOnly = true)
    public List<ProductDTO.ProductResponse> listProduct() {

        List<ProductDTO.ProductResponse> list = productRepository.findAll()
                .stream()
                .map(ProductDTO::toProductResponse)
                .toList();

        if (list.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }

        return list;
    }

    @Transactional
    public ProductDTO.ProductResponse createProduct(ProductDTO.ProductRequest request) {
        validation.validate(request);

        if (productRepository.findFirstByKode(request.getKode()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product code is already exist");
        }

        Product product = new Product();
        product.setKode(request.getKode());
        product.setNama(request.getNama());
        product.setHarga(request.getHarga());
        product.setIsReady(request.getIsReady());
        product.setGambar(request.getGambar());
        productRepository.save(product);

        return ProductDTO.toProductResponse(product);
    }

    @Transactional
    public void removeProduct(long id) {
        Product product = productRepository.findFirstById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));

        productRepository.delete(product);
    }
}
