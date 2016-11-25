/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionsandfiles.GUI.Controller;

import exceptionsandfiles.BE.Customer;
import exceptionsandfiles.BE.FileType;
import exceptionsandfiles.BLL.CustomerManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author jeppjleemoritzled
 */
public class MainViewController implements Initializable
{
    @FXML
    private TextField textName;
    @FXML
    private TextField textEmail;
    @FXML
    private TableView<Customer> tableCustomers;
    @FXML
    private TableColumn<Customer, String> columnName;
    @FXML
    private TableColumn<Customer, String> columnEmail;
    
    private CustomerManager manager =
            new CustomerManager();
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        columnName.setCellValueFactory(
                new PropertyValueFactory("name"));
        columnEmail.setCellValueFactory(
                new PropertyValueFactory("email"));
    }    
    
    private void saveTextFileFromView()
    {
        List<Customer> custList =
                new ArrayList(tableCustomers.getItems());
        
        manager.setFileType(FileType.TEXTFILE);
        manager.saveAll(custList);
    }
    
    private void loadTextFileIntoView() 
    {
        manager.setFileType(FileType.TEXTFILE);
        
        ObservableList<Customer> custList =
                FXCollections.observableArrayList(
                    manager.getAll());
        
        tableCustomers.setItems(custList);
    }

    @FXML
    private void clickAdd(ActionEvent event)
    {
        Customer cust = new Customer(
                textName.getText(), 
                textEmail.getText()
        );
        
        tableCustomers.getItems().add(cust);
        
        textName.clear();
        textEmail.clear();
    }

    @FXML
    private void clickReadText(ActionEvent event)
    {
        loadTextFileIntoView();
    }

    @FXML
    private void clickWriteText(ActionEvent event)
    {
        saveTextFileFromView();
    }

    @FXML
    private void clickReadSerial(ActionEvent event)
    {
        manager.setFileType(FileType.SERIALFILE);
        ObservableList<Customer> custList =
            FXCollections.observableArrayList(
                    manager.getAll());
        tableCustomers.setItems(custList);
    }

    @FXML
    private void clickSaveSerial(ActionEvent event)
    {
        List<Customer> custList = 
                new ArrayList(tableCustomers.getItems());
        manager.setFileType(FileType.SERIALFILE);
        manager.saveAll(custList);
    }
    
}
