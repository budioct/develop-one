package budhioct.dev.rest.contoller;

import budhioct.dev.dto.KeranjangDTO;
import budhioct.dev.rest.config.RestResponse;
import budhioct.dev.service.KeranjangService;
import budhioct.dev.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/keranjangs")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class KeranjangRestController {

    @Autowired
    KeranjangService keranjangService;

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyAuthority('user:read', 'admin:read')")
    public ResponseEntity<Map<String, List<KeranjangDTO.KeranjangResponse>>> listKeranjangs() {

        List<KeranjangDTO.KeranjangResponse> keranjangs = keranjangService.listKeranjangs();
        Map<String, List<KeranjangDTO.KeranjangResponse>> response = new HashMap<>();
        response.put("keranjangs", keranjangs);
        return ResponseEntity.ok(response);
    }

    @PostMapping(
            path = "/{id}/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyAuthority('user:create', 'admin:create')")
    public RestResponse.object<KeranjangDTO.KeranjangResponse> createKeranjang(@PathVariable(name = "id") Long id,
                                                                               @RequestBody KeranjangDTO.KeranjangRequest request) {
        request.setProduct_id(id);
        KeranjangDTO.KeranjangResponse keranjang = keranjangService.createKeranjang(request);
        return RestResponse.object.<KeranjangDTO.KeranjangResponse>builder()
                .data(keranjang)
                .status_code(Constants.CREATED)
                .message(Constants.CREATE_MESSAGE)
                .build();
    }

}
