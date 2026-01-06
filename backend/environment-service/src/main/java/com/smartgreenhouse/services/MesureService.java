package com.smartgreenhouse.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartgreenhouse.Enum.ParametreType;
import com.smartgreenhouse.entities.Mesure;
import com.smartgreenhouse.entities.Parametre;
import com.smartgreenhouse.event.MeasurementEventPublisher;
import com.smartgreenhouse.repositories.MesureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MesureService {
	private final MesureRepository mesureRepository;
    private final ParametreService parametreService;
    private final MeasurementEventPublisher eventPublisher;

    public Mesure enregistrerMesure(ParametreType type, Double valeur) {

        Parametre parametre = parametreService.getByType(type);

        Mesure mesure = Mesure.builder()
                .parametre(parametre)
                .valeur(valeur)
                .dateMesure(LocalDateTime.now())
                .build();

        Mesure savedMesure = mesureRepository.save(mesure);

        boolean alert = valeur < parametre.getSeuilMin()
                || valeur > parametre.getSeuilMax();

   // 🔔 Publication événement
   eventPublisher.publish(savedMesure, alert);

   return savedMesure;
    }

    public List<Mesure> getMesuresByParametre(Long parametreId) {
        return mesureRepository.findByParametreId(parametreId);
    }
}
