package com.ensah.core.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ensah.core.bo.*;
import com.ensah.core.bo.Module;
import com.ensah.core.dao.*;

import com.ensah.core.services.IExcelService;
import com.ensah.core.services.IPersonService;
import com.ensah.core.services.impl.ExcelServiceImpl;
import com.ensah.core.utils.NoteExcelImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/web")
public class HomeController {
    @RequestMapping("/home")
    public String homePage() {
        return "excelimporter";
    }


    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private IPersonService iPersonService;
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
    private iEnseignant enseignant;
    @Autowired
    private IExcelService excel;




    @PostMapping("/import")
    public String upload(@RequestParam(value="file") MultipartFile inputfile, Model model) throws IOException {

if(excel.excelImport(model,inputfile)==false){
    return "Excelerror";

}

excel.insererBo(inputfile);






        return  "File is uploaded and data is chicked to db";



}}
