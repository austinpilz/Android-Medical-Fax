package medicalfaxnew.duqsp15.com.medicalfax.Model.Physician;

import medicalfaxnew.duqsp15.com.medicalfax.Model.Interface.ModelObj;
import medicalfaxnew.duqsp15.com.medicalfax.Model.Patient.*;

/**
 * Created by austinpilz on 2/16/15.
 */
public class Physician implements ModelObj
{
    public static final Name name = new Name();
    public static final Hospital hospital = new Hospital();
    public static final NPI npi = new NPI();
    public static final Contact contact = new Contact();

    /*
    Patient constructor which inits class objects
     */
    public Physician()
    {
      //
    }


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
