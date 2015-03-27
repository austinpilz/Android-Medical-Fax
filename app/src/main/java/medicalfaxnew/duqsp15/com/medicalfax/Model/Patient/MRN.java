package medicalfaxnew.duqsp15.com.medicalfax.Model.Patient;

/**
 * Created by Coder Barbie, 11235
 */
public class MRN {

    /**
     * Integer value for the medical record number.
     */
    private String mrn = "0";

    /**
     * Nullary constructor
     */
    public MRN() {
    }

    /**
     * This is the setter for the medical record number
     *
     * @param newMrn - the int to set the mrn to
     */
    public void setMrn(String newMrn) {

        mrn = newMrn;
    }

    /**
     * This is the getter for the medical record number
     *
     * @return
     */
    public String getMrn() {

        return mrn;
    }

    /*
  Verifies that the medical record number field is present
  @return Boolean
   */
    public Boolean verifyMRN() {
        if (mrn.isEmpty()) {
            return false;
        }
        return true;
    }

}
