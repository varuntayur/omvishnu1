package com.varun.omvishnu.app.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Root
public class Example
{

    public Example()
    {
    }

    public Example(String inFirstName, String inLastName)
    {
        SetFirstname(inFirstName);
        SetLastname(inLastName);
    }

    @Element
    private String firstName;

    public String GetFirstName()
    {
        return firstName;
    }

    public void SetFirstname(String inFirstName)
    {
        firstName = inFirstName;
    }

    @Element
    private String lastName;

    public String GetLastName()
    {
        return lastName;
    }

    public void SetLastname(String inLastName)
    {
        lastName = inLastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Example example = (Example) o;

        if (firstName != null ? !firstName.equals(example.firstName) : example.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(example.lastName) : example.lastName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Example{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
