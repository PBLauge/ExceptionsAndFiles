/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionsandfiles.BLL;

import exceptionsandfiles.BE.Customer;
import exceptionsandfiles.DAL.FileManager;
import java.util.List;

/**
 *
 * @author jeppjleemoritzled
 */
public class CustomerManager
{
    private FileManager fileManager = 
            new FileManager();
    
    public void saveAll(List<Customer> custList)
    {
        fileManager.saveAll(custList);
    }
    
    public List<Customer> getAll()
    {
        return fileManager.getAll();
    }
}
