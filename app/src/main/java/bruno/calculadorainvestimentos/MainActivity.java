package bruno.calculadorainvestimentos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import bruno.calculadorainvestimentos.dao.UserDAO;
import bruno.calculadorainvestimentos.model.User;

public class MainActivity extends AppCompatActivity {

    private EditText txtInitialValue;
    private EditText txtMouthAplication;
    private EditText txtApplicationTime;
    private EditText txtRate;
    private Button btnCalculate;
    private TextView txtEndValue;
    private TextView textName;

    private Double initialValue;
    private Double monthlyApplication;
    private Integer applicationTime;
    private Double rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonHandler  = findViewById(R.id.buttonHist);
        buttonHandler.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                onClickHist(v);
            }
        });
    }

    private double calcularRendimento (double valorinicial, double aplicacaomensal, double tempoaplicacao , double taxa ){
        double RendimentoFinal = 0;
        double taxac = 0;
        taxa = (taxa/100);
        taxac = taxa+1;


        RendimentoFinal = valorinicial * Math.pow(taxac, tempoaplicacao) + aplicacaomensal * ( Math.pow(taxac, tempoaplicacao) - 1)/taxa;


        return RendimentoFinal;
    }

    public void onClickYieldCalculate(View view){
        txtInitialValue = (EditText) findViewById(R.id.textInitialValue);
        txtMouthAplication = (EditText) findViewById(R.id.textContribution);
        txtApplicationTime = (EditText) findViewById(R.id.textTime);
        txtRate = (EditText) findViewById(R.id.textRate);
        btnCalculate = (Button) findViewById(R.id.buttoncalcular);
        txtEndValue = (TextView) findViewById(R.id.textResult);
        textName = (TextView) findViewById(R.id.textName);
        //passando de string para double as variáveis
        initialValue = Double.parseDouble(txtInitialValue.getText().toString());
        monthlyApplication = Double.parseDouble(txtMouthAplication.getText().toString());
        applicationTime = Integer.parseInt (txtApplicationTime.getText().toString());
        rate = Double.parseDouble (txtRate.getText().toString());

        //chamo o metodo calcula rendimento passando os parametros do calculo dos juros composto
        Double rendimento = calcularRendimento(initialValue, monthlyApplication, applicationTime, rate);

        //mostra no textview o resultado
        txtEndValue.setText(rendimento.toString());

        save(textName.getText().toString(), initialValue, monthlyApplication, applicationTime, rate,rendimento);

    }

    private void save(final String name, final Double value, final Double contribuition, final Integer time, final Double rate, final Double result){
        try {

            UserDAO dao = new UserDAO(this);
            User user = new User();

            user.setName(name);
            user.setValorInicial(value);
            user.setAporte(contribuition);
            user.setTempo(time);
            user.setTaxa(rate);
            user.setResultado(result);

            dao.add(user);

        } catch (final Exception e) {

        }
    }

    private void onClickHist(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
