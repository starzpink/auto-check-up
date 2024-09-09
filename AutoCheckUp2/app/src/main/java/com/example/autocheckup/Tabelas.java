package com.example.autocheckup;

import android.provider.BaseColumns;

public class Tabelas {
    public static class Veiculo implements BaseColumns{
        public static final String TABELA = "veiculo";
        public static final String COLUNA_APELIDO = "apelido";
        public static final String COLUNA_MODELOCARRO = "modelo_carro";
        public static final String COLUNA_ANOCARRO = "ano_carro";
        public static final String COLUNA_PLACACARRO = "placa_carro";
        public static final String COLUNA_MEDIAKM = "mediakm";
        public static final String COLUNA_KMATUAL = "kmatual";
        public static final String COLUNA_OLEODATA = "oleodata";
        public static final String COLUNA_FLUTDATA = "flutdata";
        public static final String COLUNA_FLUFDATA = "flufdata";
        public static final String COLUNA_FLUDHDATA = "fludhdata";
        public static final String COLUNA_TPNEUDATA = "tpneudata";
        public static final String COLUNA_FREIODATA = "freiodata";
        public static final String COLUNA_ELETDATA = "eletdata";
        public static final String COLUNA_REFRDATA = "refrdata";
        public static final String COLUNA_CORMANGDATA = "cormangdata";
        public static final String COLUNA_REVGERALDATA = "revgeraldata";
        public static final String COLUNA_ULT_ABS = "ult_abs";
        public static final String COLUNA_TIPOCOMBUSTIVEL = "tipoCombustivel";
        public static final String COLUNA_QTLITROS = "qtlitros";
        public static final String COLUNA_DATAREGISTRO = "dataRegistro";
    }
    public static class TipoNotificacao implements BaseColumns{
        public static final String TABELA = "tiponotif";
        public static final String COLUNA_DESCRICAO= "descricao";
        public static final String COLUNA_TEMPO = "tempo";

    }

    public static class Notificacao implements BaseColumns{
        public static final String TABELA = "notificacao";
        public static final String COLUNA_IDVEICULO = "id_veiculo";
        public static final String COLUNA_IDTIPONOTIF = "id_tiponotif";
        public static final String COLUNA_MENSAGEM = "mensagem";
    }
}
