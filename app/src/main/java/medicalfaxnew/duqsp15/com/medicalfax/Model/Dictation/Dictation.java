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
    private final int REQ_CODE_SPEECH_INPUT = 100; //magic constant I don't understand yet

    public Dictation(Activity ac) //could pass the value of the activity to this constructor and then
    {
        activity = ac;
    }
    
/*
    //method for signifying start of dictation
    //that way they can signify a loading bar or something (??)
    public boolean dictationStarted(){
        //return true
        //try{}catch(){}
        getSpeech();
        return true;

    }*/
    
/*
    //method that will actually return the speechs
    public String getSpeech(){
        String s = "Sorry, dictation is currently down";
        return s;
        
    }
*/

    //helps in displaying speech input
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

    //sort of unnecessary.. added to show that this is the only way
    //i found for the calling functions back and forth to work
    public ArrayList<String> returnSpeech(Intent data){
        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        return result;
    }
}
