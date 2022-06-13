package progaming.hust.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner From;
    private Spinner To;
    private TextView TextFrom;
    private TextView TextTo;
    private TextView TextRateFrom;
    private TextView TextRateTo;
    private TextView Result;
    private float Rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.From = this.findViewById(R.id.from);
        this.To = this.findViewById(R.id.to);
        this.TextFrom = this.findViewById(R.id.textfrom);
        this.TextTo = this.findViewById(R.id.textto);
        this.TextRateFrom = this.findViewById(R.id.ratefrom);
        this.TextRateTo = this.findViewById(R.id.rateto);
        this.Result = this.findViewById(R.id.result);

        ArrayAdapter<CharSequence> adapterFrom = ArrayAdapter.createFromResource(this,
                R.array.item_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterTo = ArrayAdapter.createFromResource(this,
                R.array.item_array, android.R.layout.simple_spinner_item);
        this.From.setAdapter(adapterFrom);
        this.To.setAdapter(adapterTo);
        this.From.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                String a = (String) adapter.getItem(i);
                setTextRate(a, TextRateFrom);
                updateRate();
                setTo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.To.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();
                String a = (String) adapter.getItem(i);
                setTextRate(a, TextRateTo);
                updateRate();
                setTo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void dot(View view) {
        String a = this.TextFrom.getText().toString();
        if(a.indexOf(".") > 0) return;
        this.TextFrom.setText(a.concat("."));

    }

    public void zero(View view) {
        setTextFrom(0);
        setTo();
    }

    public void one(View view) {
        setTextFrom(1);
        setTo();
    }

    public void two(View view) {
        setTextFrom(2);
        setTo();
    }
    public void three(View view) {
        setTextFrom(3);
        setTo();
    }
    public void four(View view) {
        setTextFrom(4);
        setTo();
    }
    public void five(View view) {
        setTextFrom(5);
        setTo();
    }
    public void six(View view) {
        setTextFrom(6);
        setTo();
    }
    public void seven(View view) {
        setTextFrom(7);
        setTo();
    }
    public void eight(View view) {
        setTextFrom(8);
        setTo();
    }
    public void nine(View view) {
        setTextFrom(9);
        setTo();
    }
    public void ce(View view) {
        this.TextFrom.setText("0");
        setTo();
    }
    public void del(View view) {
        String a = this.TextFrom.getText().toString();
        if(a.equals("0")) return;
        if(a.length() == 1){
            this.TextFrom.setText("0");
            setTo();
            return;
        }
        try {
            a = a.substring(0, a.length() - 1);
            this.TextFrom.setText(a);
        }
        catch (Exception e){
            setTo();
        }
        setTo();
    }

    private void setTextFrom(int number){
        String a = this.TextFrom.getText().toString();
        try {
            if (Integer.parseInt(a) != 0) {
                this.TextFrom.setText(a.concat(String.valueOf(number)));
            } else {
                this.TextFrom.setText(String.valueOf(number));
            }
        }
        catch (Exception e){
            this.TextFrom.setText(a.concat(String.valueOf(number)));
        }
    }

    private void setTextRate(String rate, TextView textView){
        if(rate.equals("US - $")){
            textView.setText("$");
        }
        else if(rate.equals("Việt Nam - VNĐ")){
            textView.setText("Đ");
        }
        else if(rate.equals("Japan - ¥")){
            textView.setText("¥");
        }
        else if(rate.equals("Europe - €")){
            textView.setText("€");
        }
    }

    private void updateRate(){
        String a = this.TextRateFrom.getText().toString();
        String b = this.TextRateTo.getText().toString();
        if( a.equals(b)) Rate = 1;
        if(a.equals("$") && b.equals("Đ")) Rate = (float) 23000;
        if(a.equals("Đ") && b.equals("$")) Rate = (float) 0.000043;
        if(a.equals("$") && b.equals("¥")) Rate = (float) 134.35;
        if(a.equals("¥") && b.equals("$")) Rate = (float) 0.0074;
        if(a.equals("$") && b.equals("€")) Rate = (float) 0.95;
        if(a.equals("€") && b.equals("$")) Rate = (float) 1.05;
        if(a.equals("Đ") && b.equals("¥")) Rate = (float) 0.0058;
        if(a.equals("¥") && b.equals("Đ")) Rate = (float) 206;
        if(a.equals("Đ") && b.equals("€")) Rate = (float) 0.000041;
        if(a.equals("€") && b.equals("Đ")) Rate = (float) 24297.94;
        if(a.equals("¥") && b.equals("€")) Rate = (float) 0.0071;
        if(a.equals("€") && b.equals("¥")) Rate = (float) 140.75;
    }

    private void setTo(){
        String form = this.TextFrom.getText().toString();
        float to = Float.parseFloat(form)*Rate;
        this.TextTo.setText(String.valueOf(to));
        String Result = form + " " + this.TextRateFrom.getText() + " = " + String.valueOf(to) + " " + this.TextRateTo.getText();
        this.Result.setText(Result);
    }
}