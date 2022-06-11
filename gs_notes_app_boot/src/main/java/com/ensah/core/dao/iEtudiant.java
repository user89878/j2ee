package com.ensah.core.dao;

import com.ensah.core.bo.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iEtudiant extends JpaRepository<Etudiant,Long> {
    Etudiant findByIdUtilisateur(long id);
}
