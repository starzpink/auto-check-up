package com.example.autocheckup;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NOME = "base-de-dados-auto-check-up";

    private static final String CREATE_VEICULO = " create table " +
            Tabelas.Veiculo.TABELA + " ( " +
            Tabelas.Veiculo._ID + " integer primary key autoincrement, " +
            Tabelas.Veiculo.COLUNA_APELIDO + " text, " +
            Tabelas.Veiculo.COLUNA_MODELOCARRO + " text, " +
            Tabelas.Veiculo.COLUNA_ANOCARRO + " text, " +
            Tabelas.Veiculo.COLUNA_PLACACARRO + " text, " +
            Tabelas.Veiculo.COLUNA_MEDIAKM + " text, " +
            Tabelas.Veiculo.COLUNA_KMATUAL + " text, " +
            Tabelas.Veiculo.COLUNA_OLEODATA + " text, " +
            Tabelas.Veiculo.COLUNA_FLUTDATA + " text, " +
            Tabelas.Veiculo.COLUNA_FLUFDATA + " text, " +
            Tabelas.Veiculo.COLUNA_FLUDHDATA + " text, " +
            Tabelas.Veiculo.COLUNA_TPNEUDATA + " text, " +
            Tabelas.Veiculo.COLUNA_FREIODATA + " text, " +
            Tabelas.Veiculo.COLUNA_ELETDATA + " text, " +
            Tabelas.Veiculo.COLUNA_REFRDATA + " text, " +
            Tabelas.Veiculo.COLUNA_CORMANGDATA + " text, " +
            Tabelas.Veiculo.COLUNA_REVGERALDATA + " text, " +
            Tabelas.Veiculo.COLUNA_ULT_ABS + " text, " +
            Tabelas.Veiculo.COLUNA_TIPOCOMBUSTIVEL + " text, " +
            Tabelas.Veiculo.COLUNA_QTLITROS + " text, " +
            Tabelas.Veiculo.COLUNA_DATAREGISTRO + " text ) ";

    private static final String DROP_VEICULO = "drop table if exists " +
            Tabelas.Veiculo.TABELA;

    public DbHelper(Context context) {
        super(context, DATABASE_NOME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VEICULO);
        db.execSQL(CREATE_TIPONOTIFICACAO);
        db.execSQL(CREATE_NOTIFICACAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_VEICULO);
        db.execSQL(DROP_TIPONOTIFICACAO);
        db.execSQL(DROP_NOTIFICACAO);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void salvarVeiculo(Veiculo veiculo) {
        SQLiteDatabase db = getWritableDatabase();
        String dataRegistro = getCurrentDateTime();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Tabelas.Veiculo.COLUNA_APELIDO, veiculo.getApelido());
        contentValues.put(Tabelas.Veiculo.COLUNA_MODELOCARRO, veiculo.getModelo_carro());
        contentValues.put(Tabelas.Veiculo.COLUNA_ANOCARRO, veiculo.getAno_carro());
        contentValues.put(Tabelas.Veiculo.COLUNA_PLACACARRO, veiculo.getPlaca_carro());
        contentValues.put(Tabelas.Veiculo.COLUNA_MEDIAKM, veiculo.getMediakm());
        contentValues.put(Tabelas.Veiculo.COLUNA_KMATUAL, veiculo.getKmatual());
        contentValues.put(Tabelas.Veiculo.COLUNA_OLEODATA, veiculo.getOleodata());
        contentValues.put(Tabelas.Veiculo.COLUNA_FLUTDATA, veiculo.getFlutdata());
        contentValues.put(Tabelas.Veiculo.COLUNA_FLUFDATA, veiculo.getFlufdata());
        contentValues.put(Tabelas.Veiculo.COLUNA_FLUDHDATA, veiculo.getFludhdata());
        contentValues.put(Tabelas.Veiculo.COLUNA_TPNEUDATA, veiculo.getTpneudata());
        contentValues.put(Tabelas.Veiculo.COLUNA_FREIODATA, veiculo.getFreiodata());
        contentValues.put(Tabelas.Veiculo.COLUNA_ELETDATA, veiculo.getEletdata());
        contentValues.put(Tabelas.Veiculo.COLUNA_REFRDATA, veiculo.getRefrdata());
        contentValues.put(Tabelas.Veiculo.COLUNA_CORMANGDATA, veiculo.getCormangdata());
        contentValues.put(Tabelas.Veiculo.COLUNA_REVGERALDATA, veiculo.getRevgeraldata());
        contentValues.put(Tabelas.Veiculo.COLUNA_ULT_ABS, veiculo.getUlt_abs());
        contentValues.put(Tabelas.Veiculo.COLUNA_TIPOCOMBUSTIVEL, veiculo.getTipoCombustivel());
        contentValues.put(Tabelas.Veiculo.COLUNA_QTLITROS, veiculo.getQtlitros());
        contentValues.put(Tabelas.Veiculo.COLUNA_DATAREGISTRO, dataRegistro);

        long id = db.insert(Tabelas.Veiculo.TABELA, null, contentValues);
        veiculo.setId(id);
    }

    String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    @SuppressLint("Range")
    public ArrayList consultarVeiculos() {
        ArrayList lista = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " +
                Tabelas.Veiculo.TABELA, null);

        while (cursor.moveToNext()) {
            lista.add(new Veiculo(cursor.getLong(
                    cursor.getColumnIndex(Tabelas.Veiculo._ID)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_APELIDO)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_MODELOCARRO)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_ANOCARRO)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_PLACACARRO)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_MEDIAKM)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_KMATUAL)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_OLEODATA)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_FLUTDATA)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_FLUFDATA)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_FLUDHDATA)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_TPNEUDATA)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_FREIODATA)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_ELETDATA)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_REFRDATA)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_CORMANGDATA)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_REVGERALDATA)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_ULT_ABS)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_TIPOCOMBUSTIVEL)),
                    cursor.getString(cursor.getColumnIndex(Tabelas.Veiculo.COLUNA_QTLITROS))));

        }
        cursor.close();
        return lista;
    }
    private static final String CREATE_TIPONOTIFICACAO = "create table " +
            Tabelas.TipoNotificacao.TABELA + "(" +
            Tabelas.TipoNotificacao._ID + " int, " +
            Tabelas.TipoNotificacao.COLUNA_DESCRICAO + " text, " +
            Tabelas.TipoNotificacao.COLUNA_TEMPO + " text ) ";

    private static final String DROP_TIPONOTIFICACAO = "drop table if exists " +
            Tabelas.TipoNotificacao.TABELA;

    private static final String CREATE_NOTIFICACAO = "create table " +
            Tabelas.Notificacao.TABELA + "(" +
            Tabelas.Notificacao.COLUNA_IDVEICULO + " int, " +
            Tabelas.Notificacao.COLUNA_IDTIPONOTIF + " int, " +
            Tabelas.Notificacao.COLUNA_MENSAGEM + " text, " +
            " foreign key(" + Tabelas.Notificacao.COLUNA_IDVEICULO + ") references " +
            Tabelas.Veiculo.TABELA + "(" + Tabelas.Veiculo._ID + "), " +
            " foreign key(" + Tabelas.Notificacao.COLUNA_IDTIPONOTIF + ") references " +
            Tabelas.TipoNotificacao.TABELA + "(" + Tabelas.TipoNotificacao._ID + "))";


    private static final String DROP_NOTIFICACAO = "drop table if exists " +
            Tabelas.Notificacao.TABELA;

    public void TipoNotificacao(){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Tabelas.TipoNotificacao._ID, 1);
        contentValues.put(Tabelas.TipoNotificacao.COLUNA_DESCRICAO, "Atualização da ficha do veiculo");
        contentValues.put(Tabelas.TipoNotificacao.COLUNA_DESCRICAO, "1 mês");
        long newRowId = db.insert(Tabelas.TipoNotificacao.TABELA, null, contentValues);


        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(Tabelas.TipoNotificacao._ID, 2);
        contentValues2.put(Tabelas.TipoNotificacao.COLUNA_DESCRICAO, "Troca de óleo");
        contentValues2.put(Tabelas.TipoNotificacao.COLUNA_DESCRICAO, "6 meses");
        long newRowId2 = db.insert(Tabelas.TipoNotificacao.TABELA, null, contentValues2);

        ContentValues contentValues3 = new ContentValues();
        contentValues.put(Tabelas.TipoNotificacao._ID, 3);
        contentValues3.put(Tabelas.TipoNotificacao.COLUNA_DESCRICAO, "Revisão completa");
        contentValues3.put(Tabelas.TipoNotificacao.COLUNA_DESCRICAO, "6 meses");
        long newRowId3 = db.insert(Tabelas.TipoNotificacao.TABELA, null, contentValues3);
    }

    public void Notificacao(){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        long idVeiculo = db.insert(Tabelas.Veiculo.TABELA, null, contentValues);

        ContentValues notificacaoValues1 = new ContentValues();
        notificacaoValues1.put(Tabelas.Notificacao.COLUNA_IDVEICULO, idVeiculo);
        notificacaoValues1.put(Tabelas.Notificacao.COLUNA_IDTIPONOTIF, 1);
        notificacaoValues1.put(Tabelas.Notificacao.COLUNA_MENSAGEM, "Olá! Já faz um mês desde a última atualização do registro do seu veículo. O que acha de atualizar agora?");
        db.insert(Tabelas.Notificacao.TABELA, null, notificacaoValues1);

        ContentValues notificacaoValues2 = new ContentValues();
        notificacaoValues2.put(Tabelas.Notificacao.COLUNA_IDVEICULO, idVeiculo);
        notificacaoValues2.put(Tabelas.Notificacao.COLUNA_IDTIPONOTIF, 2);
        notificacaoValues2.put(Tabelas.Notificacao.COLUNA_MENSAGEM, "Olá! Já fazem seis meses desde a última troca de óleo do seu veículo. O que acha de agendar uma visita ao mecânico?");
        db.insert(Tabelas.Notificacao.TABELA, null, notificacaoValues2);

        ContentValues notificacaoValues3 = new ContentValues();
        notificacaoValues3.put(Tabelas.Notificacao.COLUNA_IDVEICULO, idVeiculo);
        notificacaoValues3.put(Tabelas.Notificacao.COLUNA_IDTIPONOTIF, 3);
        notificacaoValues3.put(Tabelas.Notificacao.COLUNA_MENSAGEM, "Olá! Já fazem seis meses desde a última revisão do seu veículo. O que acha de agendar uma visita ao mecânico?");
        db.insert(Tabelas.Notificacao.TABELA, null, notificacaoValues3);
    }
        public String obterTrocaDeOleo() {
            SQLiteDatabase db = this.getReadableDatabase();
            String trocaDeOleo = "";

            Cursor cursor = db.rawQuery("SELECT oleodata FROM veiculo", null);
            if (cursor.moveToFirst()) {
                trocaDeOleo = cursor.getString(0); // Supondo que 'troca_oleo' é a coluna onde os dados estão armazenados
            }
            cursor.close();
            return trocaDeOleo;
        }

        public String obterRevisaoCompleta() {
            SQLiteDatabase db = this.getReadableDatabase();
            String revisaoCompleta = "";

            Cursor cursor = db.rawQuery("SELECT revgeraldata FROM veiculo", null);
            if (cursor.moveToFirst()) {
                revisaoCompleta = cursor.getString(0);
            }
            cursor.close();
            return revisaoCompleta;
        }
    }






