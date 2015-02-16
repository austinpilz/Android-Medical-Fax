package medicalfaxnew.duqsp15.com.medicalfax.Model.Physician;

import medicalfaxnew.duqsp15.com.medicalfax.Model.Interface.ModelObj;
import medicalfaxnew.duqsp15.com.medicalfax.Model.ModelInterface;

/**
 * Created by austinpilz on 2/16/15.
 */
public class Physician implements ModelObj
{
    //public static Name name;
    //do not do = new Whatever() here, only in constructor

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
