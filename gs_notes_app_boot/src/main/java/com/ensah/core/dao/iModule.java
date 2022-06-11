package com.ensah.core.dao;
import com.ensah.core.bo.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface iModule extends JpaRepository<Module,Long>,GenericJpa<Module, Long> {



      Module findByTitre(String name);
      boolean existsByTitre(String titre);

}
