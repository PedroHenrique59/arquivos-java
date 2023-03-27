package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        File fileTxt = new File("C:\\Users\\Pedro\\Desktop\\Estudo\\ArquivosJava\\src\\main\\java\\org\\example\\arquivo.txt");
        File fileCsv = new File("C:\\Users\\Pedro\\Desktop\\Estudo\\ArquivosJava\\src\\main\\java\\org\\example\\arquivo.csv");

        if (!fileCsv.exists()) {
            fileCsv.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(fileCsv);

        fileWriter.write("Coluna 1" + ";" + "Coluna 2" + ";");
        fileWriter.flush();
        fileWriter.close();

        Scanner lerArquivo = new Scanner(fileCsv, "UTF-8");

        while (lerArquivo.hasNext()) {
            String linha = lerArquivo.nextLine();
            System.out.println(linha);
        }
    }
}