package bruno.calculadorainvestimentos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import bruno.calculadorainvestimentos.R;
import bruno.calculadorainvestimentos.dao.UserDAO;
import bruno.calculadorainvestimentos.model.User;

/**
 * Created by UCL on 24/04/2018.
 */

public class ListActivity  extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        carregaLista();
    }

    private void carregaLista() {
        UserDAO dao = new UserDAO(this);
        List<User> users = dao.getStore();
        List<String> usersnames = new ArrayList<String>();
        dao.close();

        for(User elemento: users){
            usersnames.add(elemento.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usersnames);
        ListView list = (ListView) findViewById(R.id.lista_alunos);
        list.setAdapter(adapter);
    }
}

