package budhioct.dev.service.impl;

import budhioct.dev.dto.PesananDTO;
import budhioct.dev.repository.PesananRepository;
import budhioct.dev.service.PesananService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PesananServiceImpl implements PesananService {

    private final PesananRepository pesananRepository;

    @Transactional
    public List<PesananDTO.PesananResponse> listPesanan() {

        List<PesananDTO.PesananResponse> list = pesananRepository.findAll()
                .stream()
                .map(PesananDTO::toPesananResponse)
                .toList();

        if (list.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "keranjang not found");
        }

        return list;
    }
}
