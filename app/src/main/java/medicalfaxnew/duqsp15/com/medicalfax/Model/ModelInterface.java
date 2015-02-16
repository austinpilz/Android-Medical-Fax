package medicalfaxnew.duqsp15.com.medicalfax.Model;


        import medicalfaxnew.duqsp15.com.medicalfax.Model.Objects.*;
        import medicalfaxnew.duqsp15.com.medicalfax.Model.IO.*;
        import medicalfaxnew.duqsp15.com.medicalfax.Model.Dictation.*;
        import medicalfaxnew.duqsp15.com.medicalfax.Model.Patient.*;
        import medicalfaxnew.duqsp15.com.medicalfax.Model.Physician.*;
        import android.content.Context;

/**
 * Created by austinpilz on 2/11/15.
 * Patient and Physician objects are for getting and setting data
 * Loading data from database is automatic on app startup
 * Saving data to database is called in the Patient and Physician apps (commit())
 *
 */
public class ModelInterface
{
    public static Patient patient; //Patient Object
    public static Physician physician; //Physician Object
    public static Dictation dictation;
    protected static InputOutput IO; //IO for Model Team ONLY
    public static ModelInterface MController; //Instance

    public ModelInterface(Context context)
    {
        this.patient = new Patient(); //Creates the default patient object
        this.physician = new Physician(); //Creates the default physician object
        this.IO = new InputOutput(context); //Creates IO object
            IO.loadPhysician(); //Loads database physician data
            IO.loadPatient(); //Loads database patient data
        this.dictation = new Dictation();

        this.MController = this; //Creates class instance pointer
    }
}
