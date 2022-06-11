package com.ensah.core.dao;

import com.ensah.core.bo.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iNiveau extends JpaRepository<Niveau,Long> {

    Niveau getNiveauByIdNiveau(long id);

}
