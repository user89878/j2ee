package com.ensah.core.services.impl;

import com.ensah.core.bo.*;
import com.ensah.core.bo.Module;
import com.ensah.core.dao.*;
import com.ensah.core.services.IExcelService;
import com.ensah.core.services.IPersonService;
import com.ensah.core.utils.NoteExcelImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ExcelServiceImpl implements IExcelService {
    @Autowired
    private IPersonService iPersonService;
    @Autowired
    private iEnseignant enseignant;
    @Autowired
    private iModule module;
    @Autowired
    private Imatiere imatiere;
    @Autowired
    private iNiveau iniveau;
    @Autowired
    private inscriptionAnuelle is;
    @Autowired
    private iEtudiant iE;
    @Autowired
    private inscriptionmatiere iA;
    @Autowired
    private inscriptionModule ib;



    @Override
    public boolean excelImport(Model model,MultipartFile inputfile) throws IOException {

        String nomE= NoteExcelImporter.getEnseignat(inputfile.getInputStream());
        String module1=NoteExcelImporter.getModule(inputfile.getInputStream());
        String classe=NoteExcelImporter.getClass(inputfile.getInputStream());
        List<String>listElemnt=NoteExcelImporter.getElements(inputfile.getInputStream(),4);
        String fullName=nomE;
        String surName=fullName.split(" ")[fullName.split(" ").length-1];
        String firstName = fullName.substring(0, fullName.length() - surName.length());
       int k=1;
       int cmp=1;
        Utilisateur u=iPersonService.getPersonByName(firstName,surName);
        if(enseignant.existsByIdUtilisateur(u.getIdUtilisateur())==false){
            System.out.println("l enseignat n'exist");
            return false;
        }
        else{
            System.out.println("l enseignat exist dans la base de donnes");
            if(module.existsByTitre(module1)==false){
                System.out.println("l element n'exist");
                model.addAttribute("message","le module  n'exist pas ");
            return false;
            }
            else{
                System.out.println("le module  exist ");
                Module ms=module.findByTitre(module1);
                long id=ms.getIdModule();
                List<Element> listE=imatiere.getAllByModule_IdModule(id);
                Niveau nv=iniveau.getNiveauByIdNiveau(ms.getNiveau().getIdNiveau());
                if(nv==null){
                  System.out.println("la class n est pas conforme avec la classe du module");
                    model.addAttribute("message","a class n est pas conforme avec la classe du module");
                  return false;
                }else{
                    if(listE.size()!=listElemnt.size()){

                        System.out.println("le nombre des elements dans le fichier n est pas conforme avec le nombre d elements");
                        model.addAttribute("message","le nombre des elements dans le fichier n est pas conforme avec le nombre d elements");

                        return false;
                    }else{
                        System.out.println("le niveaux est le meme");
                        for(int i=0;i<listE.size();i++){
                            if(k==1){
                            if(imatiere.existsByNom(listElemnt.get(i))){
                                System.out.println("l'element"+ listElemnt.get(i)+ "exsit");
                                k=1;


                            }else{
                                System.out.println("le element n esxy pas");
                                k=0;
                            }}}
                            if(k==0){
                                System.out.println("il ya une erreur dans les nom de l excel");
                                model.addAttribute("message","il ya une erreur dans les nom des elements de l excel");

                                return false;
                            }else{
                                System.out.println("les nome des matiere sont conforme");
                                List<List<Double>> listNotes=new ArrayList<>();
                                for(int i=0;i<listE.size();i++){
                                    listNotes.add(NoteExcelImporter.getnoteelemnt(inputfile.getInputStream(),i+4));
                                }

                                for(int j=0;j<listNotes.size();j++){
                                    for(int q=0;q<listE.size();q++){
                                        if(cmp==1){
                                        if(listNotes.get(j).get(q)>0.0 && listNotes.get(j).get(q)<20.0){
                                            cmp=1;
                                        }
                                        else if(listNotes.get(j).get(q)<0.0 && listNotes.get(j).get(q)>20.0){
                                            cmp=0;
                                        }
                                        }

                                    }
                                }
                                if(cmp==0){
                                    System.out.println("hors norme");
                                }else{
                                    System.out.println("bien effectue");
                                }

                            }



                    }


                }


                }

        }

        return true;



    }



    @Override
    public void insererBo( MultipartFile inputfile) throws IOException{
        String nomE= NoteExcelImporter.getEnseignat(inputfile.getInputStream());
        String module1=NoteExcelImporter.getModule(inputfile.getInputStream());
        String classe=NoteExcelImporter.getClass(inputfile.getInputStream());
        List<String>listElemnt=NoteExcelImporter.getElements(inputfile.getInputStream(),4);
        List<Integer>idE=NoteExcelImporter.getid(inputfile.getInputStream(),0);

        Module ms=module.findByTitre(module1);
        long id=ms.getIdModule();
        List<Element> listE=imatiere.getAllByModule_IdModule(id);

        Niveau nv=iniveau.getNiveauByIdNiveau(ms.getNiveau().getIdNiveau());
        int nbr=listElemnt.size();
        List<Double> Moyene=NoteExcelImporter.getnoteelemnt(inputfile.getInputStream(),nbr+4);
        List<String>listv=NoteExcelImporter.getvalidation(inputfile.getInputStream(),nbr+5);
        List<InscriptionAnnuelle> insA = new ArrayList<>();
        List<List<Double>> listNotes=new ArrayList<>();
        for(int i=0;i<listE.size();i++){
            listNotes.add(NoteExcelImporter.getnoteelemnt(inputfile.getInputStream(),i+4));
        }
        for(int i=0;i<idE.size();i++){
            Etudiant e=null;
            InscriptionAnnuelle as=null;
            InscriptionAnnuelle as1=null;
            e=iE.findByIdUtilisateur(idE.get(i));
            if(is.existsByEtudiant_IdUtilisateur(idE.get(i))){
                as1=is.findByEtudiant_IdUtilisateur(idE.get(i));
                Long id2=as1.getIdInscription();
                as=new InscriptionAnnuelle(id2,2102,"hey",5,5,listv.get(i),"bien","kjbghj",e,nv);
                is.save(as);
                insA.add(as);
            }else{
                as=new InscriptionAnnuelle(2102,"holla",5,5,listv.get(i),"bien","kjbghj",e,nv);
                is.save(as);
                insA.add(as);
            }}
        List<Object> insAN = new ArrayList<>();
        List<InscriptionMatiere> insm = new ArrayList<>();
        InscriptionMatiere as2=null;
        List<InscriptionMatiere> insAN2 = new ArrayList<>();
        String session=NoteExcelImporter.getSession(inputfile.getInputStream());
        System.out.println(session);
        for(int j=0;j<listElemnt.size();j++){
            Element as=null;
            for(int z=0;z<idE.size();z++) {
                if("Normal".equals(session)){
                if(iA.existsByMatiere_IdMatiere(listE.get(j).getIdMatiere()) && iA.existsByInscriptionAnnuelle_IdInscription(insA.get(z).getIdInscription())){
                    insAN2=(iA.findByInscriptionAnnuelle_IdInscription(insA.get(z).getIdInscription()));
                    for(int p=0;p<insAN2.size();p++){
                        as=imatiere.findByNom(listElemnt.get(p));
                        Long id2=insAN2.get(p).getIdInscriptionMatiere();

                        insm.add(new InscriptionMatiere(id2,listNotes.get(p).get(z),insAN2.get(p).getNoteSR(),0 , listv.get(z), insAN2.get(p).getPlusInfos(), as.getCurrentCoefficient(), as, insA.get(z)));}
                }else{

                    insm.add(new InscriptionMatiere(listNotes.get(j).get(z),0 ,0 , listv.get(z), "c qoui",  listE.get(j).getCurrentCoefficient(), listE.get(j), insA.get(z)));

                }}
                if("Rattrapage".equals(session)){
                    if(iA.existsByMatiere_IdMatiere(listE.get(j).getIdMatiere()) && iA.existsByInscriptionAnnuelle_IdInscription(insA.get(z).getIdInscription())){
                        insAN2=(iA.findByInscriptionAnnuelle_IdInscription(insA.get(z).getIdInscription()));
                        for(int p=0;p<insAN2.size();p++){
                            as=imatiere.findByNom(listElemnt.get(p));
                            Long id2=insAN2.get(p).getIdInscriptionMatiere();


                            insm.add(new InscriptionMatiere(id2,insAN2.get(p).getNoteSN(), listNotes.get(p).get(z),0 , listv.get(z), insAN2.get(p).getPlusInfos(), as.getCurrentCoefficient(),as, insA.get(z)));}
                    }else{
                        insm.add(new InscriptionMatiere(0, listNotes.get(j).get(z),0 , listv.get(z), "c qui", listE.get(j).getCurrentCoefficient(), listE.get(j), insA.get(z)));

                    }
                }
            }

            for(int u=0;u<insm.size();u++){
                iA.save(insm.get(u));
            }
            List<InscriptionModule> insAN4 =new ArrayList<>();
            List<InscriptionModule> insAN3 = null;
            for(int m=0;m<Moyene.size();m++){
                if(ib.existsByInscriptionAnnuelle_IdInscription(insA.get(m).getIdInscription()) && ib.existsByModule_IdModule(ms.getIdModule())) {
                    insAN3 = (ib.findByInscriptionAnnuelle_IdInscription(insA.get(m).getIdInscription()));
                    for (int p = 0; p < Moyene.size(); p++) {


                        //insAN4.add(new InscriptionModule(id2, Moyene.get(m), 0, 0, listv.get(m), "khfks", ms, insA.get(p)));

                    }
                }else{
                    for (int p = 0; p < insAN2.size(); p++) {
                    insAN4.add(new InscriptionModule(Moyene.get(m),0,0,listv.get(m),"khfks", ms, insA.get(m)));
                    }
                }


            }
            for(int u=0;u<insAN2.size();u++){
                ib.save(insAN4.get(u));
            }







        }



    }
}
