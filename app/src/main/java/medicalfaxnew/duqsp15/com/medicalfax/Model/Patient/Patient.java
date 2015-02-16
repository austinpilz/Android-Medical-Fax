package medicalfaxnew.duqsp15.com.medicalfax.Model.Patient;

import medicalfaxnew.duqsp15.com.medicalfax.Model.Interface.ModelObj;

/**
 * Created by austinpilz on 2/16/15.
 */
public class Patient implements ModelObj
{
    //public static Name name;
    //do not do = new Whatever() here, only in constructor

    /*
    Patient constructor which inits class objects
     */
    public Patient()
    {
        //name = new Name();
    }

    /*
    Verifies required fields and returns if compliant
     */
    public boolean verify()
    {
        //method required by interface
        return true;
    }

    /*
    Commits object fields to database
     */
    public void update()
    {
        //
    }
}
