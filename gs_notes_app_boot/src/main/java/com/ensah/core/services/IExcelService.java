package com.ensah.core.services;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface IExcelService {
    public boolean excelImport(Model model, MultipartFile inputfile) throws IOException;
    public void insererBo(MultipartFile inputfile) throws IOException;


}
