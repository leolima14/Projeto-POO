package app;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class mainframe {

    private ArrayList<Venda> listaVenda = new ArrayList<Venda>();
    private ArrayList<Graph> dados = new ArrayList<Graph>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem fileOpen;

    @FXML
    private MenuItem fileClose;

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private CategoryAxis eixoMes;

    @FXML
    private NumberAxis eixoDemanda;

    @FXML
    private ListView<Integer> listYear;

    @FXML
    private ChoiceBox<String> listProducts;

    @FXML
    void onFileClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void onFileOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showOpenDialog(null);
        listaVenda = Venda.constroiArquivo(f);
        listProducts.getItems().clear();
        listYear.getItems().clear();
        int[] a = {2003, 2004, 2005};
        ArrayList<String> produtos = Venda.getProdutos(listaVenda);
        for(String p : produtos){
            listProducts.getItems().add(p);
        }
        for(Integer b : a){
            listYear.getItems().add(b);
        }
    }

    @FXML
    void onMouseClickedProducts(MouseEvent event) {

    }

    @FXML
    void onMouseClickedYear(MouseEvent event) {
        lineChart.getData().clear();
        Integer ano = listYear.getSelectionModel().getSelectedItem();
        String produto = listProducts.getValue();
        if(produto != null){
            dados = Graph.getAbscissaOrdenada(ano, produto, listaVenda);
            lineChart.setTitle("Vendas");
            eixoMes.setLabel("Mes");
            eixoDemanda.setLabel("Demanda");
            XYChart.Series<String, Integer> S = new XYChart.Series<String, Integer>();
            S.setName(produto);
            for(Graph d : dados){
                S.getData().add(new XYChart.Data<String, Integer>(Integer.toString(d.abscissa), d.ordenada));
            }
            lineChart.getData().add(S);
        }
        
    }

    @FXML
    void initialize() {
        assert fileOpen != null : "fx:id=\"fileOpen\" was not injected: check your FXML file 'mainframe.fxml'.";
        assert fileClose != null : "fx:id=\"fileClose\" was not injected: check your FXML file 'mainframe.fxml'.";
        assert lineChart != null : "fx:id=\"lineChart\" was not injected: check your FXML file 'mainframe.fxml'.";
        assert eixoMes != null : "fx:id=\"eixoMes\" was not injected: check your FXML file 'mainframe.fxml'.";
        assert eixoDemanda != null : "fx:id=\"eixoDemanda\" was not injected: check your FXML file 'mainframe.fxml'.";
        assert listYear != null : "fx:id=\"listYear\" was not injected: check your FXML file 'mainframe.fxml'.";
        assert listProducts != null : "fx:id=\"listProducts\" was not injected: check your FXML file 'mainframe.fxml'.";

    }
}
