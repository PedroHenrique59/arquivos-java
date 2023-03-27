package org.example;

import com.google.gson.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ApachePoi {

    public static void main(String[] args) throws IOException {
        //criarPlanilhaExcell();
        //lerDadosPlanilhaExcell();
        //adicionarColunaPlanilhaExcell();
        //escreverJsonEmTxt();
        lerArquivoJson();
    }

    public static void criarPlanilhaExcell() throws IOException {

        File file = new File("C:\\Users\\Pedro\\Desktop\\Estudo\\ArquivosJava\\src\\main\\java\\org\\example\\arquivo_excel.xls");

        if (!file.exists()) {
            file.createNewFile();
        }

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Planilha Teste");

        Pessoa pessoa1 = new Pessoa("Pedro", "pedro@gmail.com", "31 123456");
        Pessoa pessoa2 = new Pessoa("Jose", "jose@gmail.com", "31 147852");
        Pessoa pessoa3 = new Pessoa("Joao", "joao@gmail.com", "31 325698");
        Pessoa pessoa4 = new Pessoa("Maria", "maria@gmail.com", "31 123789");

        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2, pessoa3, pessoa4);

        int numeroLinha = 0;

        for (Pessoa pessoa : pessoas) {
            Row linha = hssfSheet.createRow(numeroLinha++);

            int celula = 0;

            Cell celulaNome = linha.createCell(celula++);
            celulaNome.setCellValue(pessoa.getNome());

            Cell celulaEmail = linha.createCell(celula++);
            celulaEmail.setCellValue(pessoa.getEmail());

            Cell celulaTelefone = linha.createCell(celula++);
            celulaTelefone.setCellValue(pessoa.getTelefone());
        }

        FileOutputStream saida = new FileOutputStream(file);
        hssfWorkbook.write(saida);
        saida.flush();
        saida.close();
    }

    public static void lerDadosPlanilhaExcell() throws IOException {

        FileInputStream entrada = new FileInputStream("C:\\Users\\Pedro\\Desktop\\Estudo\\ArquivosJava\\src\\main\\java\\org\\example\\arquivo_excel.xls");

        //Le a planilha
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(entrada);

        //Pega a primeira planilha do arquivo excell
        HSSFSheet planilha = hssfWorkbook.getSheetAt(0);

        Iterator<Row> linhaIterator = planilha.iterator();

        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        while (linhaIterator.hasNext()) {
            Row linha = linhaIterator.next();

            Iterator<Cell> cellIterator = linha.iterator();

            Pessoa pessoa = new Pessoa();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getColumnIndex()) {
                    case 0:
                        pessoa.setNome(cell.getStringCellValue());
                        break;
                    case 1:
                        pessoa.setEmail(cell.getStringCellValue());
                        break;
                    case 2:
                        pessoa.setTelefone(cell.getStringCellValue());
                    default:
                        break;
                }
            }
            pessoas.add(pessoa);
        }

        entrada.close();

        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }
    }

    public static void adicionarColunaPlanilhaExcell() throws IOException {

        File file = new File("C:\\Users\\Pedro\\Desktop\\Estudo\\ArquivosJava\\src\\main\\java\\org\\example\\arquivo_excel.xls");

        FileInputStream entrada = new FileInputStream(file);

        //Le a planilha
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(entrada);

        //Pega a primeira planilha do arquivo excell
        HSSFSheet planilha = hssfWorkbook.getSheetAt(0);

        Iterator<Row> linhaIterator = planilha.iterator();

        while (linhaIterator.hasNext()) {
            Row linha = linhaIterator.next();

            int numeroTotalCelulas = linha.getPhysicalNumberOfCells();

            Cell cell = linha.createCell(numeroTotalCelulas);
            cell.setCellValue("Teste Nova Coluna");

        }

        entrada.close();

        FileOutputStream saida = new FileOutputStream(file);
        hssfWorkbook.write(saida);
        saida.flush();
        saida.close();
    }

    public static void escreverJsonEmTxt() throws IOException {

        Usuario usuario1 = new Usuario("admin", "123", "14785236987", "Pedro");
        Usuario usuario2 = new Usuario("user", "123", "1567482397", "Joao");

        List<Usuario> usuarios = new ArrayList<>(Arrays.asList(usuario1, usuario2));

        //Converte a lista de usuarios para um arquivo no formato JSON porém fica um JSON mal formatado
        String jsonUsuario = new Gson().toJson(usuarios);

        //Converte a lista de usuarios para um arquivo no formato JSON com a formatação padrão de um JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonUsuarioFormatado = gson.toJson(usuarios);

        FileWriter fileWriter = new FileWriter("C:\\Users\\Pedro\\Desktop\\Estudo\\ArquivosJava\\src\\main\\java\\org\\example\\usuariosJson.json");
        fileWriter.write(jsonUsuarioFormatado);
        fileWriter.flush();
        fileWriter.close();

    }

    public static void lerArquivoJson() throws FileNotFoundException {

        FileReader fileReader = new FileReader("C:\\Users\\Pedro\\Desktop\\Estudo\\ArquivosJava\\src\\main\\java\\org\\example\\usuariosJson.json");

        JsonArray jsonArray = (JsonArray) JsonParser.parseReader(fileReader);

        List<Usuario> usuarios = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            Usuario usuario = new Gson().fromJson(jsonElement, Usuario.class);
            usuarios.add(usuario);
        }

        System.out.println(usuarios);

    }
}

