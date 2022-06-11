package com.ensah.core.dao;

import com.ensah.core.bo.InscriptionModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface inscriptionModule extends JpaRepository<InscriptionModule,Long> {
    boolean existsByModule_IdModule(long id);
    boolean existsByInscriptionAnnuelle_IdInscription(long id);
    List<InscriptionModule> findByInscriptionAnnuelle_IdInscription(long id);
}
