/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionsandfiles.DAL;

import exceptionsandfiles.BE.Customer;
import exceptionsandfiles.GUI.Controller.MainViewController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeppjleemoritzled
 */
public class FileManager
{
    private final String fileName = "myCustomers.txt";
    
    public void saveAll(List<Customer> custList)
    {
        String csvString = "";
        for (Customer customer : custList)
        {
            csvString += customer.getName() 
                      +  ","
                      +  customer.getEmail()
                      +  String.format("%n");
        }
        
        try(BufferedWriter bw =
                new BufferedWriter(
                        new FileWriter(fileName)
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
    
    public List<Customer> getAll()
    {
        List<Customer> custList =
                new ArrayList();
        
        try(BufferedReader br = 
                new BufferedReader(
                        new FileReader(fileName)
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
        
        return custList;
    }
}
