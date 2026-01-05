package com.smartgreenhouse.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartgreenhouse.Enum.ParametreType;
import com.smartgreenhouse.entities.Mesure;
import com.smartgreenhouse.entities.Parametre;
import com.smartgreenhouse.repositories.MesureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MesureService {
	private final MesureRepository mesureRepository;
    private final ParametreService parametreService;

    public Mesure enregistrerMesure(ParametreType type, Double valeur) {

        Parametre parametre = parametreService.getByType(type);

        Mesure mesure = Mesure.builder()
                .parametre(parametre)
                .valeur(valeur)
                .dateMesure(LocalDateTime.now())
                .build();

        return mesureRepository.save(mesure);
    }

    public List<Mesure> getMesuresByParametre(Long parametreId) {
        return mesureRepository.findByParametreId(parametreId);
    }
}
