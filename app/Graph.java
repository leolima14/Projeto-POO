package app;

import java.util.ArrayList;

public class Graph {
    public Integer abscissa;
    public Integer ordenada;

    Graph(int mes, int demanda){
        this.abscissa = mes;
        this.ordenada = demanda;
    }
    // Retorna os valores da abscissa e ordenada para o ano e produto recebidos
    public static ArrayList<Graph> getAbscissaOrdenada(int ano, String produto, ArrayList<Venda> venda){
        ArrayList<Graph> dados = new ArrayList<Graph>();
        for(Venda v : venda) {
            if(v.ano == ano && v.item.equals(produto)){
                Graph g = new Graph(v.mes, v.demanda);
                dados.add(g);
            }
        }
        return dados;
    }
}
