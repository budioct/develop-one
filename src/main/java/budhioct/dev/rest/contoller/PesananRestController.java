package budhioct.dev.rest.contoller;

import budhioct.dev.dto.PesananDTO;
import budhioct.dev.service.PesananService;
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
@RequestMapping("/api/v1")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class PesananRestController {

    @Autowired
    PesananService pesananService;

    @GetMapping(
            path = "/pesanans/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyAuthority('user:read', 'admin:read')")
    public ResponseEntity<Map<String, List<PesananDTO.PesananResponse>>> listPesanan(){
        List<PesananDTO.PesananResponse> pesanans = pesananService.listPesanan();
        Map<String, List<PesananDTO.PesananResponse>> response = new HashMap<>();
        response.put("pesanans", pesanans);
        return  ResponseEntity.ok(response);
    }

}
