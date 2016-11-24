/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionsandfiles;

/**
 *
 * @author jeppjleemoritzled
 */
public class Customer
{
    
    private final String name;
    private final String email;

    public Customer(String name, String email)
    {
        this.name = name;
        this.email = email;
    }
    
    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail()
    {
        return email;
    }


    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName()
    {
        return name;
    }


}
