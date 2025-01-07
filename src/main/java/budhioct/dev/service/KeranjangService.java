package budhioct.dev.service;

import budhioct.dev.dto.KeranjangDTO;

import java.util.List;

public interface KeranjangService {

    List<KeranjangDTO.KeranjangResponse> listKeranjangs();
}
