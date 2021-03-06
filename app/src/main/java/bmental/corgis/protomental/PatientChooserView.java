package bmental.corgis.protomental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Set;

/**
 * Created by Folly on 6/1/2015.
 */
public class PatientChooserView extends CorgiView{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view);


        SharedPreferences stored = getSharedPreferences("prefs", 0);
        stored.getString("current_patient", null);

        findViewById(R.id.select_patient).setOnClickListener(proceedWithChosen);
        findViewById(R.id.add_patient).setOnClickListener(addPatient);



        Spinner chooser = (Spinner) findViewById(R.id.spin_choice);

//        String[] meow = {"Patient1", "Patient2", "Patient3"};
        Set<String> temp = stored.getStringSet("patients", null);
        String[] meow = temp.toArray(new String[temp.size()]);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_for_patients, meow);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        chooser.setAdapter(adapter);

    }
    private final View.OnClickListener proceedWithChosen;
    {
        proceedWithChosen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences stored = getSharedPreferences("prefs", 0);

                TextView very = (TextView) ((Spinner)findViewById(R.id.spin_choice)).getSelectedView();
                SharedPreferences.Editor e = stored.edit();
                e.putString("current_patient", very.getText().toString());

                e.commit();

                Intent change = new Intent(getApplicationContext(), PatientView.class);

                startActivity(change);
            }

        };
    }

    private final View.OnClickListener addPatient;
    {
        addPatient = new View.OnClickListener() {
            public void onClick(View v){
                Intent change = new Intent(getApplicationContext(), NewPatient.class);
                startActivity(change);

            }
        };
    }
}

