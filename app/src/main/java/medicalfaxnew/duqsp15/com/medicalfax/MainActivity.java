package medicalfaxnew.duqsp15.com.medicalfax;
import medicalfaxnew.duqsp15.com.medicalfax.Model.Dictation.Dictation;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Locale;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.speech.RecognizerIntent;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private Button dictateButton;
    private EditText fullNameDisplay; //no matter what you do, its going to be displayed here
    public Dictation dictation;
    private final int REQ_CODE_SPEECH_INPUT = 100; //magic constant I don't understand yet

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*the code below was added by Brady Sheehan to demonstrate speech to text functionality
        *
        * 2/18/2015
        * */
        dictation = new Dictation(this);//we must pass the activity to use it outside the class

        dictateButton = (Button) findViewById(R.id.Dictate_Button); //allows us to listen for click
        fullNameDisplay = (EditText) findViewById(R.id.Full_Name); //how we can display it in this text view

        dictateButton.setOnClickListener(new View.OnClickListener(){ //temporary listening for button click
            @Override
            public void onClick(View V){
                dictation.getSpeechInput(); //calls the method in getSpeechInput
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*this method was written by Brady Sheehan on 2/18/2015
    * it is meant to handle the resultant activity of getting speech input
    *
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //speech recognition (like this) must require an internet connection!
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE_SPEECH_INPUT && resultCode == RESULT_OK && null != data) {
            ArrayList<String> result = dictation.returnSpeech(data); //returnSpeech(data) will return an arraylist<STRING>
            fullNameDisplay.setText(result.get(0)); //hard coded to just display in one field - fullNameDisplay
        }
    }

    /** This method is called when Up Button is clicked
     *  It causes the cursor to move up once to the text field above the current position
     *  Created by: Kinardi Isnata, February 16 2015
     */
    public void up(View view)
    {
       // Intent intent = new Intent(this, );
       // If it is not at the most top position, then move cursors up from current position
       // EditText.setSelection(int);
    }
    /** This method is called when Down Button is clicked
     *  It causes the cursor to move down once to the text field below the current position
     *  Created by: Kinardi Isnata, February 16 2015
     */
    public void down(View view)
    {
        // Intent intent = new Intent(this, );
        // If it is not at the most bottom position, then move cursors down from current position
        // EditText.setSelection(int);
    }
}
