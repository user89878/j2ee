package com.ensah.core.dao;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ensah.core.bo.InscriptionAnnuelle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface inscriptionAnuelle extends JpaRepository<InscriptionAnnuelle,Long> {
  boolean existsByEtudiant_IdUtilisateur(long id);
  InscriptionAnnuelle findByEtudiant_IdUtilisateur(long id);

}
