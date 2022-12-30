package com.example.merge_pdfs;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class MergePdFsApplication {

    public static void main(String[] args) throws IOException {
        String file01, file02, mergeFile, tmp, dir;

        dir = "C:\\MEVITZ\\";

        System.out.print("first file : ");
        Scanner sc01 = new Scanner(System.in);
        tmp = sc01.next();
        file01 = dir + tmp;
        System.out.println(file01);
        tmp = "";


        System.out.print("second file : ");
        Scanner sc02 = new Scanner(System.in);
        tmp = sc02.next();
        file02 = dir + tmp;
        System.out.println(file02);
        tmp = "";

        System.out.print("Merge File name : ");
        Scanner sc03 = new Scanner(System.in);
        tmp = sc03.next();
        mergeFile = dir + tmp;

        PDFMergerUtility PDFMerger = new PDFMergerUtility();
        PDFMerger.setDestinationFileName(mergeFile);

        PDFMerger.addSource(file01);
        PDFMerger.addSource(file02);

        PDFMerger.mergeDocuments(null);

        System.out.println("Document is Merged : " + mergeFile);

    }

}
