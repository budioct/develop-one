package budhioct.dev.rest.contoller;

import budhioct.dev.dto.ProductDTO;
import budhioct.dev.rest.config.RestResponse;
import budhioct.dev.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/products")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyAuthority('user:read', 'admin:read')")
    public ResponseEntity<Map<String, List<ProductDTO.ProductResponse>>> listProduct() {
        List<ProductDTO.ProductResponse> products = productService.listProduct();
        Map<String, List<ProductDTO.ProductResponse>> response = new HashMap<>();
        response.put("products", products);
        return ResponseEntity.ok(response);
    }

}
