package com.ensah.core.services.impl;

import java.util.List;

import com.ensah.core.bo.Element;
import com.ensah.core.bo.Module;
import com.ensah.core.bo.Niveau;
import com.ensah.core.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Utilisateur;
import com.ensah.core.services.IPersonService;
import com.ensah.core.utils.ExcelExporter;

@Service
@Transactional
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IUtilisateurDao personDao;
	@Autowired
	private iEnseignant enseignant;
	@Autowired
	private iModule module;
	@Autowired
	private Imatiere imatiere;
	@Autowired
	private iNiveau niveau;

	public List<Utilisateur> getAllPersons() {

		return personDao.findAll();
	}

	public void deletePerson(Long id) {
		personDao.deleteById(id);

	}

	public Utilisateur getPersonById(Long id) {
		return personDao.getById(id);

	}

	public void addPerson(Utilisateur pPerson) {
		personDao.save(pPerson);

	}

	public void updatePerson(Utilisateur pPerson) {
		personDao.save(pPerson);

	}

	public ExcelExporter preparePersonExport(List<Utilisateur> persons) {
		String[] columnNames = new String[] { "Nom", "Prénom", "CIN", "Email", "Télé" };
		String[][] data = new String[persons.size()][5];

		int i = 0;
		for (Utilisateur u : persons) {
			data[i][0] = u.getNom();
			data[i][1] = u.getPrenom();
			data[i][2] = u.getCin();
			data[i][3] = u.getEmail();
			data[i][4] = u.getTelephone();
			i++;
		}

		return new ExcelExporter(columnNames, data, "persons");

	}

	@Override
	public Integer getid(String name) {
		return null;
	}

	@Override


	public Utilisateur getPersonByCin(String cin) {

		return personDao.getEntityByColValue(Utilisateur.class, "cin", cin);

	}

	@Override
	public Utilisateur getPersonByName(String Name,String Last) {
		Utilisateur ul=(Utilisateur) personDao.findByName(Name,Last);
		if(ul!=null) {
			long id = ul.getIdUtilisateur();
			if (enseignant.existsByIdUtilisateur(id)) {
				System.out.println("oui c est un enseignat");
			} else {
				System.out.println("ce ne st pas un enseignat");
			}
		}




		return ul ;
	}

}
