package bruno.calculadorainvestimentos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import bruno.calculadorainvestimentos.model.User;

/**
 * Created by UCL on 24/04/2018.
 */

public class UserDAO extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "users";
    private static final int VERSAO = 1;
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String TAXA = "taxa";
    private static final String RESULTADO = "resultado";
    private static final String TEMPO = "tempo";
    private static final String APORTE = "aporte";
    private static final String VALOR = "valor";

    public UserDAO(Context context) {
        super(context, "DMS", null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();

        query.append("CREATE TABLE " + TABELA  + "(");
        query.append(ID + " integer primary key autoincrement, ");
        query.append(NAME + " text, ");
        query.append(VALOR + " float, ");
        query.append(APORTE + " float, ");
        query.append(TEMPO + " integer, ");
        query.append(TAXA + " float, ");
        query.append(RESULTADO + " float )");

        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder query = new StringBuilder();
        query.append("DROP TABLE IF EXISTS " + TABELA);
        db.execSQL(query.toString());
        onCreate(db);
    }

    public void add(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = userToContent(user);

        db.insert(TABELA, null, dados);
    }

    @NonNull
    private ContentValues userToContent(User user) {
        ContentValues dados = new ContentValues();
        dados.put(NAME, user.getName());
        dados.put(VALOR, user.getValorInicial());
        dados.put(TAXA, user.getTaxa());
        dados.put(TEMPO, user.getTempo());
        dados.put(RESULTADO, user.getResultado());
        dados.put(APORTE, user.getAporte());
        return dados;
    }

    public List<User> getStore() {
        List<User> users = new ArrayList<>();
        try{
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM " + TABELA);

            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery(query.toString(), null);


            while(c.moveToNext()){
                User user = new User();
                user.setId(c.getInt(c.getColumnIndex(ID)));
                user.setName(c.getString(c.getColumnIndex(NAME)));
                user.setValorInicial(c.getDouble(c.getColumnIndex(VALOR)));
                user.setTempo(c.getInt(c.getColumnIndex(TEMPO)));
                user.setTaxa(c.getDouble(c.getColumnIndex(TAXA)));
                user.setAporte(c.getDouble(c.getColumnIndex(APORTE)));
                user.setResultado(c.getDouble(c.getColumnIndex(RESULTADO)));
                users.add(user);
            }
            c.close();

        }catch (Exception e){

        }

        return users;
    }

    public void destroy(User user) {
        try{
            SQLiteDatabase db = getWritableDatabase();
            String[] params = {user.getId().toString()};
            db.delete(TABELA, ID  +" = ?", params);
        }catch (Exception e){

        }
    }

    public void update(User user) {
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues dados = userToContent(user);
            String[] params = {user.getId().toString()};
            db.update(TABELA, dados, ID  +" = ?", params);
        }catch (Exception e){

        }

    }
}