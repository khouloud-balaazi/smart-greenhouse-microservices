package com.smartgreenhouse.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartgreenhouse.Enum.ParametreType;
import com.smartgreenhouse.entities.Parametre;
import com.smartgreenhouse.repositories.ParametreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParametreService {
	private final ParametreRepository parametreRepository;

	public Parametre createOrUpdateParametre(Parametre parametre) {
	    // Vérifie si un paramètre du même type existe déjà
	    boolean exists = parametreRepository.findByType(parametre.getType()).isPresent();
	    if (exists) {
	        // Lève une exception pour bloquer la création d'un doublon
	        throw new IllegalArgumentException(
	            "Erreur : un paramètre avec le type '" + parametre.getType() + "' existe déjà."
	        );
	    }

	    // Sinon, sauvegarde le paramètre
	    return parametreRepository.save(parametre);
	}

    public List<Parametre> getAllParametres() {
        return parametreRepository.findAll();
    }

    public Parametre getByType(ParametreType type) {
        return parametreRepository.findByType(type)
                .orElseThrow(() ->
                        new IllegalArgumentException("Parametre not found for type: " + type));
    }
    public Parametre getById(Long id) {
        return parametreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parametre not found"));
    }

}
