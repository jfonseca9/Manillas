package operaciones_movil.com.tallermanillas;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Manillas extends AppCompatActivity {
    private Resources resources;
    private TextView total;
    private TextView unit;
    private Spinner material;
    private Spinner dije;
    private Spinner tipo;
    private Spinner moneda;
    private String mat[];
    private String dij[];
    private String tip[];
    private String mon[];
    private EditText cant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manillas);

        total=(TextView)findViewById(R.id.lblPrecioTotal);
        unit=(TextView)findViewById(R.id.lblPrecioUnit);
        cant=(EditText)findViewById(R.id.txtCantidad);
        material=(Spinner)findViewById(R.id.cmbMaterial);
        dije=(Spinner)findViewById(R.id.cmbDije);
        tipo=(Spinner)findViewById(R.id.cmbTipo);
        moneda=(Spinner)findViewById(R.id.cmbMoneda);
        resources = this.getResources();
        mat = resources.getStringArray(R.array.material);
        dij = resources.getStringArray(R.array.dije);
        tip = resources.getStringArray(R.array.tipo);
        mon = resources.getStringArray(R.array.moneda);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mat);
        material.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dij);
        dije.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tip);
        tipo.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mon);
        moneda.setAdapter(adapter4);
    }

    public boolean validar(){
        int ma, di, mo, ti;
        ma = material.getSelectedItemPosition();
        di = dije.getSelectedItemPosition();
        mo = moneda.getSelectedItemPosition();
        ti = tipo.getSelectedItemPosition();

        if(cant.getText().toString().isEmpty()||Integer.parseInt(cant.getText().toString())<=0) {
            cant.setError(resources.getString(R.string.mensaje_error_1));
            return false;
        }
        if(ma==0) {
            Toast.makeText(this, resources.getString(R.string.mensaje_error_5), Toast.LENGTH_SHORT ).show();
            return false;
        }
        if(di==0) {
            Toast.makeText(this, resources.getString(R.string.mensaje_error_3), Toast.LENGTH_SHORT ).show();
            return false;
        }
        if(ti==0) {
            Toast.makeText(this, resources.getString(R.string.mensaje_error_2), Toast.LENGTH_SHORT ).show();
            return false;
        }
        if(mo==0) {
            Toast.makeText(this, resources.getString(R.string.mensaje_error_4), Toast.LENGTH_SHORT ).show();
            return false;
        }





        return true;
    }

    public void calcular (View v){
        double  resultado=0, compra=0;
        int ma, di, mo, ti, can;
        total.setText("");
        if(validar()) {
            ma = material.getSelectedItemPosition();
            di = dije.getSelectedItemPosition();
            ti = tipo.getSelectedItemPosition();
            mo = moneda.getSelectedItemPosition();
            can = Integer.parseInt(cant.getText().toString());


            if(ma==1 && di==1 &&(ti==1||ti==2)){
                resultado=100;
            }

            else if(ma==1 && di==1 && ti==3){
                resultado=80;
            }

            else if(ma==1 && di==1 && ti==4){
                resultado=70;
            }

            else if(ma==1 && di==2 &&(ti==1||ti==2)){
                resultado=120;
            }

            else if(ma==1 && di==2 && ti==3){
                resultado=100;
            }

            else if(ma==1 && di==2 && ti==4){
                resultado=90;
            }

            else if(ma==2 && di==1 &&(ti==1||ti==2)){
                resultado=90;
            }

            else if(ma==2 && di==1 && ti==3){
                resultado=70;
            }

            else if(ma==2 && di==1 && ti==4){
                resultado=50;
            }

            else if(ma==2 && di==2 &&(ti==1||ti==2)){
                resultado=110;
            }

            else if(ma==2 && di==2 && ti==3){
                resultado=90;
            }

            else if(ma==2 && di==2 && ti==4){
                resultado=80;
            }

            compra=resultado;
            compra= compra*can;

            if(mo==1) {
                resultado=resultado*2300;
                compra=compra*2300;

                unit.setText("$: " + String.format("%.2f", resultado)+" PESOS");
                total.setText("$: "+ String.format("%.2f", compra)+" PESOS");
            }
            else if(mo==2) {
                unit.setText("$: " + String.format("%.2f", resultado)+" DOLARES");
                total.setText("$: "+ String.format("%.2f", compra)+" DOLARES");
            }
        }
    }

    public void limpiar (View v){
        total.setText("");
        unit.setText("");
        cant.setText("");
        material.requestFocus();
        material.setSelection(0);
        dije.setSelection(0);
        tipo.setSelection(0);
        moneda.setSelection(0);
    }

}
