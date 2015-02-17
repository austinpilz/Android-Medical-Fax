package medicalfaxnew.duqsp15.com.medicalfax.Model.Patient;

import medicalfaxnew.duqsp15.com.medicalfax.Model.Interface.ModelObj;
import medicalfaxnew.duqsp15.com.medicalfax.Model.Patient.*;

/**
 * Created by austinpilz on 2/16/15.
 */
public class Patient implements ModelObj
{
    public static Name name;
    public static Date date;
    public static Diagnosis diagnosis;
    public static Medicine medicine;

    /*
    Patient constructor which inits class objects
     */
    public Patient()
    {
        name = new Name();
        date = new Date();
        diagnosis = new Diagnosis();
        medicine = new Medicine();
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
    Commits all object fields to database
     */
    public void update()
    {
        //
    }
}
