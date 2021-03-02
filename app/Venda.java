package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Classe que ira armazenar os dados referentes as vendas
class Venda implements Comparable<Venda> {

    public int demanda, mes, ano;
    public String item;

    Venda(String construct) {
        String[] venda = construct.split(",");
        demanda = (int) Float.parseFloat(venda[0]);
        mes = (int) Float.parseFloat(venda[1]);
        ano = (int) Float.parseFloat(venda[2]);
        item = venda[3];
    }

    @Override
    public int compareTo(Venda o) {
        if (o.ano > this.ano)
            return -1;
        else if (o.ano < this.ano)
            return 1;
        else if (o.mes > this.mes)
            return -1;
        else
            return 1;
    }
    // Retorna uma lista com o nome de todos os produtos, sem repeticao
    public static ArrayList<String> getProdutos(ArrayList<Venda> venda){
        ArrayList<String> produtos = new ArrayList<String>();
        for(Venda v : venda){
            if(contain(v, produtos))
                produtos.add(v.item);
        }
        Collections.sort(produtos);
        return produtos;
    }

    // Verifica se existe algum produto na lista com o mesmo nome
    public static boolean contain(Venda v, ArrayList<String> t){
        for(int i = 0;i<t.size();i++){
            String g = t.get(i);
            if(g.equals(v.item))
                return false;
        }
        return true;
    }

    // Recebe o arquivo e constroi a classe de venda
    public static ArrayList<Venda> constroiArquivo(File file)  {
        ArrayList<Venda> listaVenda = new ArrayList<Venda>();
        
        try{
            Scanner leitor = new Scanner(file).useDelimiter("[\r\n]");
            while(leitor.hasNext()){
                Boolean f = true;
                String construct = leitor.nextLine();
                Venda v = new Venda(construct);
                // Verifica se existe venda de um produto em um mesmo periodo, executando a soma se houver
                // caso nao seja produto ou periodo diferente é adicionado na lista
                for(int i = 0; i < listaVenda.size() && f;i++){
                    Venda t = listaVenda.get(i);
                    if(t.item.equals(v.item) && t.ano == v.ano && t.mes == v.mes){
                        t.demanda += v.demanda;
                        f = false;
                    }
                }
                if(f)
                    listaVenda.add(v);
            }
            leitor.close();
        } catch(FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado!");
        }
        Collections.sort(listaVenda);
        return listaVenda;
    }
}
