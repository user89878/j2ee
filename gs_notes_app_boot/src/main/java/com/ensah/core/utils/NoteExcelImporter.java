package com.ensah.core.utils;


import com.ensah.core.bo.note1;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoteExcelImporter {

      public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }

    public static  String getModule(InputStream is){

        String Module=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 0) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if (cellsNumber == 1) {
                            Module = cell.getStringCellValue();
                            return Module;
                        }
                        cellsNumber++;
                    }
                }
                rowNumber++;


            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return Module;

    }
    public static String getEnseignat(InputStream is){
        String Niveau=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 1) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if (cellsNumber == 1) {
                            Niveau = cell.getStringCellValue();
                            return Niveau;
                        }
                        cellsNumber++;
                    }
                }
                rowNumber++;


            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return Niveau;
    }
    public static String getSession(InputStream is){
        String Niveau=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 1) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if (cellsNumber == 3) {
                            Niveau = cell.getStringCellValue();
                            return Niveau;
                        }
                        cellsNumber++;
                    }
                }
                rowNumber++;


            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return Niveau;
    }
    public static String getClass(InputStream is){
        String Niveau=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 1) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if (cellsNumber == 5) {
                            Niveau = cell.getStringCellValue();
                            return Niveau;
                        }
                        cellsNumber++;
                    }
                }
                rowNumber++;


            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return Niveau;
    }
    public static String anneactual(InputStream is){

        String Niveau=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 0) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if (cellsNumber == 5) {
                            Niveau = cell.getStringCellValue();
                            return Niveau;
                        }
                        cellsNumber++;
                    }
                }
                rowNumber++;


            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return Niveau;




    }
    public static List<String> getElements(InputStream is,int nbrCell){
        List<String> list = new ArrayList<>();
        String cel=null;
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowNumber = 0,cellsNumber=0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cells = row.iterator();
                if (rowNumber == 2) {
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        if(cellsNumber>=nbrCell) {

                            cel = cell.getStringCellValue();
                            if("MOYEN".equals(cel)){
                                break;
                            }
                            list.add(cel);
                        }


                        cellsNumber++;

                    }
                    break;
                }
                rowNumber++;

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static List<Double> getnoteelemnt(InputStream is,int nbcell) throws IOException {
        List<Double> list = new ArrayList<>();
        List<note1> listStudent=new ArrayList<>();
        List<Double> notes1=new ArrayList<>();
        List<Double> notes2=new ArrayList<>();
        long id=0;
        String nom="";
        String prenom="";
        double cne=0;
        double note1=0;
        double note2=0;
        double moyen =0;
        String val="";
        String excelFilePath="C:\\Users\\HP\\Desktop\\Classeur2.xlsx";
        String titre="";
        String nomE="";
        long start = System.currentTimeMillis();
        try {

            Workbook workbook=new XSSFWorkbook(is);
            Sheet firstSheet=workbook.getSheetAt(0);
            Iterator<Row> rowIterator=firstSheet.iterator();
            for(int i=0;i<2;i++){
                if(i==1){
                    Row nextRow = rowIterator.next();
                    Iterator<Cell> cellIterator=nextRow.cellIterator();
                    while(cellIterator.hasNext()) {
                        Cell nextCell=cellIterator.next();
                        int columnIndex=nextCell.getColumnIndex();
                        switch (columnIndex) {
                            case 0:
                                titre=nextCell.getStringCellValue();
                                System.out.println(titre);
                                break;
                            case 1:
                                nomE=nextCell.getStringCellValue();
                                System.out.println(nomE);
                                break;}}

                }
                rowIterator.next();

            }

            while(rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator=nextRow.cellIterator();
                while(cellIterator.hasNext()) {
                    Cell nextCell=cellIterator.next();
                    int columnIndex=nextCell.getColumnIndex();
                    if(columnIndex==nbcell){
                        note1 = nextCell.getNumericCellValue();
                        System.out.println(note1);
                        notes1.add(note1);
                    }


                }
            }

            workbook.close();
            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n", (end - start));
            int cmp=0;
            int cmp1=0;
            for(double i:notes1){
                cmp++;
                if(i<0.0 || i>20.0){
                    System.out.println("l'element"+cmp+1+"est hors norme");                }
            }
            for(double i:notes2){
                cmp1++;
                if(i<0.0 || i>20.0){
                    System.out.println("l'element"+cmp1+1+"est hors norme");                }
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

        return notes1;

    }
    public static List<Integer> getid(InputStream is,int nbcell) throws IOException {
        List<Double> list = new ArrayList<>();
        List<note1> listStudent=new ArrayList<>();
        List<Integer> notes1=new ArrayList<>();
        List<Double> notes2=new ArrayList<>();
        long id=0;
        String nom="";
        String prenom="";
        double cne=0;
        double note1=0;
        double note2=0;
        double moyen =0;
        String val="";
        String excelFilePath="C:\\Users\\HP\\Desktop\\Classeur2.xlsx";
        String titre="";
        String nomE="";
        long start = System.currentTimeMillis();
        try {

            Workbook workbook=new XSSFWorkbook(is);
            Sheet firstSheet=workbook.getSheetAt(0);
            Iterator<Row> rowIterator=firstSheet.iterator();
            for(int i=0;i<2;i++){
                if(i==1){
                    Row nextRow = rowIterator.next();
                    Iterator<Cell> cellIterator=nextRow.cellIterator();
                    while(cellIterator.hasNext()) {
                        Cell nextCell=cellIterator.next();
                        int columnIndex=nextCell.getColumnIndex();
                        switch (columnIndex) {
                            case 0:
                                titre=nextCell.getStringCellValue();
                                System.out.println(titre);
                                break;
                            case 1:
                                nomE=nextCell.getStringCellValue();
                                System.out.println(nomE);
                                break;}}

                }
                rowIterator.next();

            }

            while(rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator=nextRow.cellIterator();
                while(cellIterator.hasNext()) {
                    Cell nextCell=cellIterator.next();
                    int columnIndex=nextCell.getColumnIndex();
                    if(columnIndex==nbcell){
                        note1 = nextCell.getNumericCellValue();
                        System.out.println(note1);
                        notes1.add((int) note1);
                    }


                }
            }

            workbook.close();
            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n", (end - start));
            int cmp=0;
            int cmp1=0;
            for(double i:notes1){
                cmp++;
                if(i<0.0 || i>20.0){
                    System.out.println("l'element"+cmp+1+"est hors norme");                }
            }
            for(double i:notes2){
                cmp1++;
                if(i<0.0 || i>20.0){
                    System.out.println("l'element"+cmp1+1+"est hors norme");                }
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

        return notes1;

    }
    public static List<String> getvalidation(InputStream is,int nbcell) throws IOException {
        List<Double> list = new ArrayList<>();
        List<note1> listStudent=new ArrayList<>();
        List<String> notes1=new ArrayList<>();
        List<Double> notes2=new ArrayList<>();
        long id=0;
        String nom="";
        String prenom="";
        double cne=0;
        String note1="";
        double note2=0;
        double moyen =0;
        String val="";
        String excelFilePath="C:\\Users\\HP\\Desktop\\Classeur2.xlsx";
        String titre="";
        String nomE="";
        long start = System.currentTimeMillis();
        try {

            Workbook workbook=new XSSFWorkbook(is);
            Sheet firstSheet=workbook.getSheetAt(0);
            Iterator<Row> rowIterator=firstSheet.iterator();
            for(int i=0;i<2;i++){
                if(i==1){
                    Row nextRow = rowIterator.next();
                    Iterator<Cell> cellIterator=nextRow.cellIterator();
                    while(cellIterator.hasNext()) {
                        Cell nextCell=cellIterator.next();
                        int columnIndex=nextCell.getColumnIndex();
                        switch (columnIndex) {
                            case 0:
                                titre=nextCell.getStringCellValue();
                                System.out.println(titre);
                                break;
                            case 1:
                                nomE=nextCell.getStringCellValue();
                                System.out.println(nomE);
                                break;}}

                }
                rowIterator.next();

            }

            while(rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator=nextRow.cellIterator();
                while(cellIterator.hasNext()) {
                    Cell nextCell=cellIterator.next();
                    int columnIndex=nextCell.getColumnIndex();
                    if(columnIndex==nbcell){
                        note1 = nextCell.getStringCellValue();
                        System.out.println(note1);
                        notes1.add(note1);
                    }


                }
            }

            workbook.close();
            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n", (end - start));
            int cmp=0;
            int cmp1=0;


        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

        return notes1;

    }





    public List<note1> exImporter(){
        List<note1> listStudent=new ArrayList<>();
        List<Double> notes1=new ArrayList<>();
        List<Double> notes2=new ArrayList<>();

        long id=0;
        String nom="";
        String prenom="";
        double cne=0;
        double note1=0;
        double note2=0;
        double moyen =0;
        String val="";
        String excelFilePath="C:\\Users\\HP\\Desktop\\Classeur2.xlsx";
        String titre="";
        String nomE="";

        long start = System.currentTimeMillis();
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(excelFilePath);
            Workbook workbook=new XSSFWorkbook(inputStream);
            Sheet firstSheet=workbook.getSheetAt(0);
            Iterator<Row> rowIterator=firstSheet.iterator();
            for(int i=0;i<2;i++){
                if(i==1){
                    Row nextRow = rowIterator.next();
                    Iterator<Cell> cellIterator=nextRow.cellIterator();
                    while(cellIterator.hasNext()) {
                        Cell nextCell=cellIterator.next();
                        int columnIndex=nextCell.getColumnIndex();
                        switch (columnIndex) {
                            case 0:
                                titre=nextCell.getStringCellValue();
                                System.out.println(titre);
                                break;
                            case 1:
                                nomE=nextCell.getStringCellValue();
                                System.out.println(nomE);
                                break;}}

                }
                rowIterator.next();

            }

            while(rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator=nextRow.cellIterator();
                while(cellIterator.hasNext()) {
                    Cell nextCell=cellIterator.next();
                    int columnIndex=nextCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            id=(long)nextCell.getNumericCellValue();
                            System.out.println(id);
                            break;
                        case 1:
                            cne=nextCell.getNumericCellValue();
                            System.out.println(cne);
                            break;
                        case 2:
                            nom=nextCell.getStringCellValue();
                            System.out.println(nom);
                            break;
                        case 3:
                            prenom=nextCell.getStringCellValue();
                            System.out.println(prenom);
                            break;

                                case 4:
                                    note1 = nextCell.getNumericCellValue();
                                    System.out.println(note1);
                                    notes1.add(note1);
                                    break;

                        case 6:
                            moyen=nextCell.getNumericCellValue();
                            System.out.println(moyen);
                            break;
                        case 7:
                            val=nextCell.getStringCellValue();
                            System.out.println(val);
                            break;

                    }
                    listStudent.add(new note1(id,nom,prenom,cne,note1,note2,moyen,val));

                }
            }

            workbook.close();
            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n", (end - start));
            int cmp=0;
            int cmp1=0;
            for(double i:notes1){
                cmp++;
                if(i<0.0 || i>20.0){
                    System.out.println("l'element"+cmp+1+"est hors norme");                }
            }
            for(double i:notes2){
                cmp1++;
                if(i<0.0 || i>20.0){
                    System.out.println("l'element"+cmp1+1+"est hors norme");                }
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

        return listStudent;

    }
}