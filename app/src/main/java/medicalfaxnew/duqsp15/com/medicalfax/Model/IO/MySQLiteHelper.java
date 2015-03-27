package medicalfaxnew.duqsp15.com.medicalfax.Model.IO;


/**
 * Created by austinpilz on 2/11/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.Arrays;
import java.util.List;


import java.net.IDN;

import medicalfaxnew.duqsp15.com.medicalfax.Model.ModelInterface;
import medicalfaxnew.duqsp15.com.medicalfax.Model.Patient.Allergy;
import medicalfaxnew.duqsp15.com.medicalfax.Model.Patient.Medicine;
import medicalfaxnew.duqsp15.com.medicalfax.Model.Patient.Patient;

public class MySQLiteHelper extends SQLiteOpenHelper
{

    //Physician
    public final String TABLE_PHYSICIAN = "Physician";
    public final String PHYSICIAN_COLUMN_ID = "ID";
    public final String PHYSICIAN_COLUMN_NAME = "Name";
    public final String PHYSICIAN_COLUMN_NPI = "NPI";
    public final String PHYSICIAN_COLUMN_HOSPITAL = "Hospital";
    public final String PHYSICIAN_COLUMN_DEPARTMENT = "Department";
    public final String PHYSICIAN_COLUMN_TITLE = "Title";
    public final String PHYSICIAN_COLUMN_PHONE = "Phone";
    public final String PHYSICIAN_COLUMN_EMAIL = "Email";

    private final String PHYSICIAN_TABLE_CREATE = "create table IF NOT EXISTS "
            + TABLE_PHYSICIAN + "(" + PHYSICIAN_COLUMN_ID
            + " integer primary key autoincrement, "
            + PHYSICIAN_COLUMN_NAME + " text not null,"
            + PHYSICIAN_COLUMN_NPI + " text not null,"
            + PHYSICIAN_COLUMN_HOSPITAL + " text not null,"
            + PHYSICIAN_COLUMN_DEPARTMENT + " text not null,"
            + PHYSICIAN_COLUMN_TITLE + " text not null,"
            + PHYSICIAN_COLUMN_PHONE + " text not null,"
            + PHYSICIAN_COLUMN_EMAIL + " text not null"
            + ");";


    //Patient
    public final String TABLE_PATIENT = "Patient";
    public final String PATIENT_COLUMN_ID = "ID";
    public final String PATIENT_COLUMN_NAME = "First";
    public final String PATIENT_COLUMN_DOB = "DOB";
    public final String PATIENT_COLUMN_MRN = "MRN";
    public final String PATIENT_COLUMN_ADMISSIONDATE = "AdmissionDate";
    public final String PATIENT_COLUMN_PCPName = "PCP";
    public final String PATIENT_COLUMN_AttendingName = "Attending";
    public final String PATIENT_COLUMN_CodeStatus = "CodeStatus";
    public final String PATIENT_COLUMN_ChiefComplaint = "ChiefComplaint";
    public final String PATIENT_COLUMN_HPI = "HPI";
    public final String PATIENT_COLUMN_HospitalCourse = "HospitalCourse";
    public final String PATIENT_COLUMN_Consultants = "Consultants";
    public final String PATIENT_COLUMN_Tests = "Tests";
    public final String PATIENT_COLUMN_PatientMedications = "PatientMedications";
    public final String PATIENT_COLUMN_HomeMedications = "HomeMedications";
    public final String PATIENT_COLUMN_MedicalHistory = "MedicalHistory";
    public final String PATIENT_COLUMN_Allergies = "Allergies";
    public final String PATIENT_COLUMN_DIAGNOSIS = "Diagnosis";
    public final String PATIENT_COLUMN_ANTIBIOTIC = "Antibiotics";

    private final String PATIENT_TABLE_CREATE = "create table IF NOT EXISTS "
            + TABLE_PATIENT + "(" + PATIENT_COLUMN_ID
            + " integer primary key autoincrement, "
            + PATIENT_COLUMN_NAME + " text not null,"
            + PATIENT_COLUMN_DOB + " text not null,"
            + PATIENT_COLUMN_MRN + " text not null,"
            + PATIENT_COLUMN_ADMISSIONDATE + " text not null,"
            + PATIENT_COLUMN_PCPName + " text not null,"
            + PATIENT_COLUMN_AttendingName + " text not null,"
            + PATIENT_COLUMN_CodeStatus + " text not null,"
            + PATIENT_COLUMN_ChiefComplaint + " text not null,"
            + PATIENT_COLUMN_HPI + " text not null,"
            + PATIENT_COLUMN_HospitalCourse + " text not null,"
            + PATIENT_COLUMN_Consultants + " text not null,"
            + PATIENT_COLUMN_Tests + " text not null,"
            + PATIENT_COLUMN_PatientMedications + " text not null,"
            + PATIENT_COLUMN_HomeMedications + " text not null,"
            + PATIENT_COLUMN_MedicalHistory + " text not null,"
            + PATIENT_COLUMN_Allergies + " text not null,"
            + PATIENT_COLUMN_DIAGNOSIS + " text not null,"
            + ");";




    //GENERAL DATABASE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "JacksonJ";



    public MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    Creates the Physician and Patient tables
     */
    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(PHYSICIAN_TABLE_CREATE); //Creates Physician Table
        createPatient(database); //Creates initial patient row
        database.execSQL(PATIENT_TABLE_CREATE); //Creates Patient Table
        createPhysician(database); //creates initial physician row
    }

    /*
    Creates and inserts the first patient row in the database with blank values
     */
    private void createPatient(SQLiteDatabase database)
    {
        ContentValues cv = new ContentValues();

        cv.put(PATIENT_COLUMN_ID, 1);
        cv.put(PATIENT_COLUMN_NAME, "");
        cv.put(PATIENT_COLUMN_DOB, "");
        cv.put(PATIENT_COLUMN_MRN, "");
        cv.put(PATIENT_COLUMN_ADMISSIONDATE, "");
        cv.put(PATIENT_COLUMN_PCPName, "");
        cv.put(PATIENT_COLUMN_AttendingName, "");
        cv.put(PATIENT_COLUMN_CodeStatus, "");
        cv.put(PATIENT_COLUMN_ChiefComplaint, "");
        cv.put(PATIENT_COLUMN_HPI, "");
        cv.put(PATIENT_COLUMN_HospitalCourse, "");
        cv.put(PATIENT_COLUMN_Consultants, "");
        cv.put(PATIENT_COLUMN_Tests, "");
        cv.put(PATIENT_COLUMN_PatientMedications, "");
        cv.put(PATIENT_COLUMN_HomeMedications, "");
        cv.put(PATIENT_COLUMN_MedicalHistory, "");
        cv.put(PATIENT_COLUMN_Allergies, "");
        cv.put(PATIENT_COLUMN_DIAGNOSIS, "");
        database.insert(TABLE_PATIENT, PATIENT_COLUMN_ID, cv); //Insert statement

    }

    /*
    Loads patient from database into object fields
     */
    protected void loadPatient(SQLiteDatabase database)
    {
        //Selects the row with the patient ID of 1, since that's the row where we store all the data
        Cursor c= database.rawQuery("SELECT * FROM " + TABLE_PATIENT + " WHERE ID =?", new String []{"1"});
        c.moveToFirst();

        //Sets the patient object fields with database values
        ModelInterface.patient.patientName.setName(c.getString(c.getColumnIndex(PATIENT_COLUMN_NAME)));
        ModelInterface.patient.medRecNum.setMrn(c.getString(c.getColumnIndex(PATIENT_COLUMN_MRN)));
        ModelInterface.patient.pcpName.setName(c.getString(c.getColumnIndex(PATIENT_COLUMN_PCPName)));
        ModelInterface.patient.attendingName.setName(c.getString(c.getColumnIndex(PATIENT_COLUMN_AttendingName)));
        ModelInterface.patient.codeStatus.setAsString(c.getString(c.getColumnIndex(PATIENT_COLUMN_CodeStatus)));
        ModelInterface.patient.chiefComplaint.setMedicalHistory(c.getString(c.getColumnIndex(PATIENT_COLUMN_ChiefComplaint)));
        ModelInterface.patient.hospitalCourse.setHospitalCourse(c.getString(c.getColumnIndex(PATIENT_COLUMN_HospitalCourse)));
        ModelInterface.patient.medHistory.setMedicalHistory(c.getString(c.getColumnIndex(PATIENT_COLUMN_MedicalHistory)));
        ModelInterface.patient.patientDiagnosis.setPrimaryDiagnosis(c.getString(c.getColumnIndex(PATIENT_COLUMN_DIAGNOSIS)));

        c.close();




        //Now when we load the array lists, they're stored separated by commas, so we have to explode them
        String tmpDOB = c.getString(c.getColumnIndex(PATIENT_COLUMN_DOB));
        String tmpAdmissionDate = c.getString(c.getColumnIndex(PATIENT_COLUMN_ADMISSIONDATE));
        String tmpConsultants = c.getString(c.getColumnIndex(PATIENT_COLUMN_Consultants));
        String tmpTests = c.getString(c.getColumnIndex(PATIENT_COLUMN_Tests));
        String tmpPatientMedications = c.getString(c.getColumnIndex(PATIENT_COLUMN_PatientMedications));
        String tmpHomeMedications = c.getString(c.getColumnIndex(PATIENT_COLUMN_HomeMedications));
        String tmpAllergies = c.getString(c.getColumnIndex(PATIENT_COLUMN_Allergies));
        String tmpHPI = c.getString(c.getColumnIndex(PATIENT_COLUMN_HPI));

        //Date Of Birth
        List<String> DOB = Arrays.asList(tmpDOB.split("/"));
        ModelInterface.patient.dateOfBirth.setDay(Integer.parseInt(DOB.get(0)));
        ModelInterface.patient.dateOfBirth.setMonth(DOB.get(1));
        ModelInterface.patient.dateOfBirth.setYear(Integer.parseInt(DOB.get(2)));

        //Admission Date
        List<String> AdmissionDate = Arrays.asList(tmpAdmissionDate.split("/"));
        ModelInterface.patient.admDate.setDay(Integer.parseInt(AdmissionDate.get(0)));
        ModelInterface.patient.admDate.setMonth(AdmissionDate.get(1));
        ModelInterface.patient.admDate.setYear(Integer.parseInt(AdmissionDate.get(2)));


        //HPI
        List<String> HPI = Arrays.asList(tmpHPI.split(","));
        for (String hpi : HPI)
        {
            //Todo
        }

        //Consultants
        List<String> Consultants = Arrays.asList(tmpConsultants.split(","));
        for (String consultant : Consultants)
        {
            //
        }

        //Tests
        List<String> Tests = Arrays.asList(tmpTests.split(","));
        for (String test : Tests)
        {
            //ModelInterface.patient.addTestList();
        }

        //Patient meds
        List<String> PatientMedications = Arrays.asList(tmpPatientMedications.split(","));
        for (String pmed : PatientMedications)
        {
            ModelInterface.patient.addPatientMedicationList(new Medicine(pmed, "", ""));
        }

        //Patient home meds
        List<String> PatientHomeMedications = Arrays.asList(tmpHomeMedications.split(","));
        for (String hmed : PatientHomeMedications)
        {
            ModelInterface.patient.addPatientMedicationListsHome(new Medicine(hmed, "", ""));
        }

        //Allergies
        List<String> Allergies = Arrays.asList(tmpAllergies.split(","));
        for (String allergy : Allergies)
        {
            ModelInterface.patient.addAllergiesList(new Allergy(allergy));
        }

    }

    /*
    Updates patient field info into database
     */
    protected void updatePatient(SQLiteDatabase database)
    {
        ContentValues cv = new ContentValues();
        cv.put(PATIENT_COLUMN_NAME, ModelInterface.patient.patientName.getName());
        cv.put(PATIENT_COLUMN_DOB, "");
        cv.put(PATIENT_COLUMN_MRN, ModelInterface.patient.medRecNum.getMrn().toString());
        cv.put(PATIENT_COLUMN_ADMISSIONDATE, "");
        cv.put(PATIENT_COLUMN_PCPName, ModelInterface.patient.pcpName.getName());
        cv.put(PATIENT_COLUMN_AttendingName, ModelInterface.patient.attendingName.getName());
        cv.put(PATIENT_COLUMN_CodeStatus, ModelInterface.patient.codeStatus.getCodeStatus());
        cv.put(PATIENT_COLUMN_ChiefComplaint, ModelInterface.patient.chiefComplaint.getChiefComplaint());
        cv.put(PATIENT_COLUMN_HPI, "");
        cv.put(PATIENT_COLUMN_HospitalCourse, ModelInterface.patient.hospitalCourse.getHospitalCourse());
        cv.put(PATIENT_COLUMN_Consultants, "");
        cv.put(PATIENT_COLUMN_Tests, "");
        cv.put(PATIENT_COLUMN_PatientMedications, "");
        cv.put(PATIENT_COLUMN_HomeMedications, "");
        cv.put(PATIENT_COLUMN_MedicalHistory, "");
        cv.put(PATIENT_COLUMN_Allergies, "");
        cv.put(PATIENT_COLUMN_DIAGNOSIS, "");

        database.update(TABLE_PATIENT, cv, PATIENT_COLUMN_ID + "=1", null);
    }

    /*
    Creates and inserts the first physician row in the database with blank values
     */
    private void createPhysician(SQLiteDatabase database)
    {
        ContentValues cv = new ContentValues();

        cv.put(PHYSICIAN_COLUMN_ID, 1);
        cv.put(PHYSICIAN_COLUMN_NAME, "");
        cv.put(PHYSICIAN_COLUMN_NPI, "");
        cv.put(PHYSICIAN_COLUMN_HOSPITAL, "");
        cv.put(PHYSICIAN_COLUMN_DEPARTMENT, "");
        cv.put(PHYSICIAN_COLUMN_TITLE, "");
        cv.put(PHYSICIAN_COLUMN_PHONE, "");
        cv.put(PHYSICIAN_COLUMN_EMAIL, "");
        database.insert(TABLE_PHYSICIAN, PHYSICIAN_COLUMN_ID, cv); //Insert statement

    }

    /*
    Loads physician row from database
     */
    protected void loadPhysician(SQLiteDatabase database)
    {
        //Selects the row with the patient ID of 1, since that's the row where we store all the data
        Cursor c= database.rawQuery("SELECT * FROM " + TABLE_PHYSICIAN + " WHERE ID =?", new String []{"1"});
        c.moveToFirst();

        //Sets the patient object fields with database values
        ModelInterface.physician.name.setName(c.getString(c.getColumnIndex(PHYSICIAN_COLUMN_NAME)));
        ModelInterface.physician.npi.setNPI(c.getString(c.getColumnIndex(PHYSICIAN_COLUMN_NPI)));
        ModelInterface.physician.hospital.setHomeHospital(c.getString(c.getColumnIndex(PHYSICIAN_COLUMN_HOSPITAL)));
        ModelInterface.physician.hospital.setDepartment(c.getString(c.getColumnIndex(PHYSICIAN_COLUMN_DEPARTMENT)));
        ModelInterface.physician.hospital.setTitle(c.getString(c.getColumnIndex(PHYSICIAN_COLUMN_TITLE)));
        ModelInterface.physician.contact.setPhone(c.getString(c.getColumnIndex(PHYSICIAN_COLUMN_PHONE)));
        ModelInterface.physician.contact.setEmail(c.getString(c.getColumnIndex(PHYSICIAN_COLUMN_EMAIL)));


        c.close();
    }


    /*
    Updates physician row in database
     */
    protected void updatePhysician(SQLiteDatabase database)
    {
        ContentValues cv = new ContentValues();
        cv.put(PHYSICIAN_COLUMN_NAME, ModelInterface.physician.name.getName());
        cv.put(PHYSICIAN_COLUMN_NPI, ModelInterface.physician.npi.getNPI());
        cv.put(PHYSICIAN_COLUMN_HOSPITAL, ModelInterface.physician.hospital.getHomeHospital());
        cv.put(PHYSICIAN_COLUMN_DEPARTMENT, ModelInterface.physician.hospital.getDepartment());
        cv.put(PHYSICIAN_COLUMN_TITLE, ModelInterface.physician.hospital.getTitle());
        cv.put(PHYSICIAN_COLUMN_PHONE, ModelInterface.physician.contact.getPhone());
        cv.put(PHYSICIAN_COLUMN_EMAIL, ModelInterface.physician.contact.getEmail());

        database.update(TABLE_PHYSICIAN, cv, PHYSICIAN_COLUMN_ID + "=1", null);
    }

    /*
    Upgrades the database if database version is newer than that of existing database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}