package comepqtesting.wix.socot.epqtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Calculator extends AppCompatActivity {

    String total ="";
    double v1,v2;
    String sign="";
    private View v;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }
    public void OnClick (View v){
        this.v = v;
        Button button = (Button)v;
        String str=button.getText().toString();
        total+=str;
        EditText edit=(EditText)findViewById(R.id.editText);
        edit.setText(total);

    }

    public void OnAdd (View v){
        v1=Double.parseDouble(total);
        total="";
        Button button = (Button)v;
        String str = button.getText().toString();
        sign = str;
        EditText edit = (EditText)findViewById(R.id.editText);
        edit.setText("");
    }
    public void  OnCalculate(View v){
        EditText edit = (EditText)findViewById(R.id.editText);
        String str2=edit.getText().toString();
        v2=Double.parseDouble(str2);
        double grand_total=0;
        if (sign.equals("+")){
            grand_total=v1+v2;}
        else if (sign.equals("-")) {
            grand_total=v1-v2;
        }
        else if(sign.equals("x")){
            grand_total=v1*v2;
        }
        else if(sign.equals("/")){
            grand_total=v1/v2;
        }

        edit.setText(grand_total+"");
    }
    public void OnClear(View v){
        EditText edit = (EditText)findViewById(R.id.editText);
        edit.setText("");
        total="";
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
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
}
