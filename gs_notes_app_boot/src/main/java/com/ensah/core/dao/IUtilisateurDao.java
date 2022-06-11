package com.ensah.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUtilisateurDao extends JpaRepository<Utilisateur, Long>, GenericJpa<Utilisateur, Long> {


    @Query("SELECT t FROM Utilisateur t where t.nom = :name and t.prenom =:last")
    Utilisateur findByName(@Param("name")String name,@Param("last") String Prenom);
}
