package budhioct.dev.service.impl;

import budhioct.dev.dto.KeranjangDTO;
import budhioct.dev.repository.KeranjangRepository;
import budhioct.dev.service.KeranjangService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeranjangServiceImpl implements KeranjangService {

    private final KeranjangRepository keranjangRepository;

    @Transactional(readOnly = true)
    public List<KeranjangDTO.KeranjangResponse> listKeranjangs() {

        List<KeranjangDTO.KeranjangResponse> list = keranjangRepository.findAll()
                .stream()
                .map(KeranjangDTO::toKeranjangResponse)
                .toList();

        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "keranjang not found");
        }

        return list;
    }
}
