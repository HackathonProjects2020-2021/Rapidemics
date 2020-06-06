package app.patientocity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class patientInfoAdapter extends ArrayAdapter<patient> {
    private Activity context;
    private List<patient> patientsList;

    public patientInfoAdapter(Activity context, List<patient> patientsList){
        super(context,R.layout.list_view, patientsList);
        this.context = context;
        this.patientsList = patientsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.list_view, null, true);

        TextView id =  (TextView) listView.findViewById(R.id.idTextView);
        TextView name =  (TextView) listView.findViewById(R.id.nameTextView);
        TextView symptoms =  (TextView) listView.findViewById(R.id.symptomsTextView);
        TextView diagnosis =  (TextView) listView.findViewById(R.id.diagnosisTextView);
        TextView room =  (TextView) listView.findViewById(R.id.roomTextView);
        TextView datetime =  (TextView) listView.findViewById(R.id.datetimeTextView);

        patient p = patientsList.get(position);
        id.setText(p.getPatienId());
        name.setText(p.getPatientName());
        id.setText(p.getPatientSymptom());
        id.setText(p.getPatientDiag());
        id.setText(p.getPatientRoom());
        id.setText(p.getDateTime());

        return listView;




    }
}
