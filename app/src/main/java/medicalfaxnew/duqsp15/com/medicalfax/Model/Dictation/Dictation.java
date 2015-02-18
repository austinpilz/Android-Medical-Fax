package medicalfaxnew.duqsp15.com.medicalfax.Model.Dictation;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import medicalfaxnew.duqsp15.com.medicalfax.R;

/**
 * Created by austinpilz on 2/16/15.
 * Edited by Brady Sheehan and Justin Chilleo on 2/16/15
 * Added getSpeechInput() and returnSpeech() methods - Brady Sheehan 2/18/15
 */
public class Dictation
{
    public Activity activity;
    private boolean processing;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    public Dictation(Activity ac) //need access to the activity in this class for speech
    {
        activity = ac;
    }

    /**
     * Creates the Intent for speech recognition and starts the activity
     * currently called in the main method
     * @author Brady Sheehan
     * @param none
     * @exception ActivityNotFoundException
     * @return No return value.
     */
    public void getSpeechInput(){

        //this creates its own activity
        //http://developer.android.com/reference/android/app/Activity.html#startActivityForResult%28android.content.Intent%2c%20int%29
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        try{
            activity.startActivityForResult(intent, REQ_CODE_SPEECH_INPUT); //this is called on the implicit activity that was created
            //can't have intents without an activity
        }catch(ActivityNotFoundException a){
            Toast.makeText(activity.getApplicationContext(),activity.getString(R.string.speech_not_supported),Toast.LENGTH_SHORT ).show();
        }
    }

    /**
     * Needs to be passed the intent from the main method currently
     * @author Brady Sheehan
     * @param Intent
     * @exception none
     * @return ArrayList<String>
     */
    public ArrayList<String> returnSpeech(Intent data){
        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        return result;
    }
}
