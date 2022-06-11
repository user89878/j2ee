package com.ensah.core.dao;

import com.ensah.core.bo.InscriptionAnnuelle;
import com.ensah.core.bo.InscriptionMatiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface inscriptionmatiere extends JpaRepository<InscriptionMatiere,Long> {
    boolean existsByInscriptionAnnuelle_IdInscription(long id);
    boolean existsByMatiere_IdMatiere(long id);
    List<InscriptionMatiere> findByInscriptionAnnuelle_IdInscription(long id);




}
