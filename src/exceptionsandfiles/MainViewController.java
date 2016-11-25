/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionsandfiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String csvString = "";
        for (Customer customer : tableCustomers.getItems())
        {
            csvString += customer.getName() 
                      +  ","
                      +  customer.getEmail()
                      +  String.format("%n");
        }
        
        try(BufferedWriter bw =
                new BufferedWriter(
                        new FileWriter("myCustomers.txt")
                )
            )
        {
            bw.write(csvString);
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTextFileIntoView() 
    {
        ObservableList<Customer> custList =
                FXCollections.observableArrayList();
        
        try(BufferedReader br = 
                new BufferedReader(
                        new FileReader("myCustomers.txt")
                )
            )
        {
            Scanner scanner = new Scanner(br);
            while(scanner.hasNext())
            {
                // Gets next line in file
                String line = scanner.nextLine();
                // Splits line into array by comma
                // fields[0] is name
                // fields[1] is email
                String[] fields = line.split(",");
                custList.add(
                    new Customer(
                            fields[0].trim(), 
                            fields[1].trim()
                    ));
            }
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
        }
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
        try(ObjectInputStream ois =
                new ObjectInputStream(
                    new FileInputStream("myCustomers.ser")))
        {
            tableCustomers.setItems(
                FXCollections.observableArrayList(
                    (List<Customer>)ois.readObject()));
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickSaveSerial(ActionEvent event)
    {
        List<Customer> customerList =
                new ArrayList(tableCustomers.getItems());
        
        try(ObjectOutputStream oos =
                new ObjectOutputStream(
                        new FileOutputStream("myCustomers.ser")))
        {
            oos.writeObject(customerList);
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
