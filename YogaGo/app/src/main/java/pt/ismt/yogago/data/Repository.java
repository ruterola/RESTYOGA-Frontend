package pt.ismt.yogago.data;

import android.app.Application;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import pt.ismt.yogago.model.Artigo;
import pt.ismt.yogago.model.ArtigoResponse;
import pt.ismt.yogago.model.Atividades;
import pt.ismt.yogago.model.AtividadesResponse;
import pt.ismt.yogago.model.Exercicios;
import pt.ismt.yogago.model.LoginResponse;
import pt.ismt.yogago.model.PlanoResponse;
import pt.ismt.yogago.model.Planos;
import pt.ismt.yogago.model.SimpleResponse;
import pt.ismt.yogago.model.Utilizadores;

public class Repository {
    private static Repository INSTANCE;
    private Application application;

    public Repository(Application application) {
        this.application = application;
    }

    public static Repository getINSTANCE (Application application) {
        if (INSTANCE == null){
            synchronized (Repository.class){
                if (INSTANCE == null){
                    INSTANCE = new Repository(application);
                }
            }
        }
        return INSTANCE;
    }

    //CALL LOGIN
    public LoginResponse login (String url, String email, String password) throws JSONException {
        String result = "";
        try {
            URL apiEnd = new URL(url);
            int responseCode;
            HttpURLConnection connection;
            InputStream is;
            connection = (HttpURLConnection) apiEnd.openConnection();
            connection.setRequestMethod("POST"); //LOGIN
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //connection.setRequestProperty("email", email);
            //connection.setRequestProperty("password", password);
            HashMap<String, String> body = new HashMap<>();
            body.put("email", email);
            body.put("password", password);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(buildString(body).toString());
            wr.flush();
            wr.close();
            responseCode = connection.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            result = converterInputStreamToString(is);
            is.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertJsonToLoginResponse(result);
    }

    //CALL ATIVIDADES
    public AtividadesResponse atividades (String url, String token, int id) throws JSONException {
        String result = "";
        try {
            URL apiEnd = new URL(url + "utilizador/" + id);
            int responseCode;
            HttpURLConnection connection;
            InputStream is;
            connection = (HttpURLConnection) apiEnd.openConnection();
            connection.setRequestMethod("GET"); //PERFIL
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", token);
            //HashMap<String, String> body = new HashMap<>();
            //body.put("email", email);
            //body.put("password", password);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            responseCode = connection.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            result = converterInputStreamToString(is);
            is.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(result);
        JSONObject jsonObject1 = jsonObject.optJSONObject("utilizadores");
        return convertJsonToAtividadesResponse(jsonObject1.toString());
    }

    //CALL REGISTO
    public SimpleResponse registo (String url, String name, String email, String password) throws JSONException {
        String result = "";
        try {
            URL apiEnd = new URL(url);
            int responseCode;
            HttpURLConnection connection;
            InputStream is;
            connection = (HttpURLConnection) apiEnd.openConnection();
            connection.setRequestMethod("POST"); //Registo
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            HashMap <String, String> body = new HashMap<>();
            body.put("name", name);
            body.put("email", email);
            body.put("password", password);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(buildString(body).toString());
            wr.flush();
            wr.close();
            responseCode = connection.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            result = converterInputStreamToString(is);
            is.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertJsonToRegistoResponse(result);
    }

    //CALL RESET
    public SimpleResponse reset (String url, String email, String password, String confirmPassword) throws JSONException {
        String result = "";
        try {
            URL apiEnd = new URL(url);
            int responseCode;
            HttpURLConnection connection;
            InputStream is;
            connection = (HttpURLConnection) apiEnd.openConnection();
            connection.setRequestMethod("POST"); //Reset
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            HashMap <String, String> body = new HashMap<>();
            body.put("email", email);
            body.put("password", password);
            body.put("confirmPassword", confirmPassword);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(buildString(body).toString());
            wr.flush();
            wr.close();
            responseCode = connection.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            result = converterInputStreamToString(is);
            is.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertJsonToSimpleResponse(result);
    }

    //CALL PLANOS
    public PlanoResponse planoResponse (String url, int id) throws JSONException {
        String result = "";
        try {
            URL apiEnd = new URL(url + "plano/" + id);
            int responseCode;
            HttpURLConnection connection;
            InputStream is;
            connection = (HttpURLConnection) apiEnd.openConnection();
            connection.setRequestMethod("GET"); //PERFIL
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            responseCode = connection.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            result = converterInputStreamToString(is);
            is.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertJsonToPlanosResponse(result);
    }

    //CALL ALL ARTIGOS
    public ArtigoResponse allArtigos (String url) throws JSONException {
        String result = "";
        try {
            URL apiEnd = new URL(url + "artigos");
            int responseCode;
            HttpURLConnection connection;
            InputStream is;
            connection = (HttpURLConnection) apiEnd.openConnection();
            connection.setRequestMethod("GET"); //PERFIL
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            responseCode = connection.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            result = converterInputStreamToString(is);
            is.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertJsonToArtigoResponse(result);
    }

    //CALL ARTIGOS
    public ArtigoResponse getArtigo (String url, String id) throws JSONException {
        String result = "";
        try {
            URL apiEnd = new URL(url + "artigo/" + id);
            int responseCode;
            HttpURLConnection connection;
            InputStream is;
            connection = (HttpURLConnection) apiEnd.openConnection();
            connection.setRequestMethod("GET"); //PERFIL
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            responseCode = connection.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            result = converterInputStreamToString(is);
            is.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertJsonToArtigoResponse(result);
    }

    //CALL PLANOS
    public PlanoResponse allPlanos (String url, boolean isCursos) throws JSONException {
        String result = "";
        try {
            URL apiEnd = new URL(url + "planos");
            int responseCode;
            HttpURLConnection connection;
            InputStream is;
            connection = (HttpURLConnection) apiEnd.openConnection();
            connection.setRequestMethod("GET"); //PERFIL
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            responseCode = connection.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            result = converterInputStreamToString(is);
            is.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertJsonToAllPlanos(result, isCursos);
    }

    //CALL Inicia & Termina
    public SimpleResponse acaoPlanos (String url, int planoid, String email, String acaoPlanosApi, String token) throws JSONException {
        String result = "";
        try {
            URL apiEnd = new URL(url + acaoPlanosApi);
            int responseCode;
            HttpURLConnection connection;
            InputStream is;
            connection = (HttpURLConnection) apiEnd.openConnection();
            connection.setRequestMethod("POST"); //Registo
            connection.setRequestProperty("Authorization", token);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            HashMap <String, String> body = new HashMap<>();
            body.put("email", email);
            body.put("planoId", String.valueOf(planoid));
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(buildString(body).toString());
            wr.flush();
            wr.close();
            responseCode = connection.getResponseCode();
            if (responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            result = converterInputStreamToString(is);
            is.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertJsonToSimpleResponse(result);
    }

    private ArtigoResponse convertJsonToArtigoResponse(String result) throws  JSONException{
        ArtigoResponse artigoResponse = new ArtigoResponse();
        JSONObject jsonObject = new JSONObject(result);

        artigoResponse.setSuccess(jsonObject.optBoolean("success"));

        JSONArray artigosArray = jsonObject.optJSONArray("artigos");
        List<Artigo> artigosArrayList =  new ArrayList<>();

        for (int i = 0; i < artigosArray.length() ; i++) {
            artigosArrayList.add(convertJsonToArtigos(artigosArray.optString(i)));
        }
        artigoResponse.setArtigoList(artigosArrayList);
        return artigoResponse;
    }

    private Artigo convertJsonToArtigos(String optString) throws JSONException {
        Artigo artigo = new Artigo();
        JSONObject jsonObject = new JSONObject(optString);
        artigo.setId(jsonObject.optInt("id"));
        artigo.setNome(jsonObject.optString("nome"));
        artigo.setImagem(jsonObject.optString("imagem"));
        artigo.setDescricao(jsonObject.optString("descricao"));
        return artigo;
    }

    private AtividadesResponse convertJsonToAtividadesResponse(String result) throws  JSONException{
        AtividadesResponse atividadesResponse = new AtividadesResponse();
        JSONObject jsonObject = new JSONObject(result);

        atividadesResponse.setUtilizadores(convertJsonToUtilizadores(jsonObject.optJSONObject("utilizadores").toString()));

        JSONArray exerciciosArray = jsonObject.optJSONArray("atividades");
        List<Atividades> exerciciosArrayList =  new ArrayList<>();

        for (int i = 0; i < exerciciosArray.length() ; i++) {
            exerciciosArrayList.add(convertJsonToAtividades(exerciciosArray.optString(i)));
        }
        atividadesResponse.setAtividades(exerciciosArrayList);
        return atividadesResponse;
    }

    private SimpleResponse convertJsonToSimpleResponse(String result) throws JSONException {
        SimpleResponse simpleResponse = new SimpleResponse();
        JSONObject jsonObject = new JSONObject(result);

        simpleResponse.setSuccess(jsonObject.optBoolean("success"));
        simpleResponse.setMessage(jsonObject.optString("message"));
        return simpleResponse;
    }

    private PlanoResponse convertJsonToPlanosResponse(String result) throws JSONException {
        PlanoResponse planos = new PlanoResponse();
        JSONObject jsonObj = new JSONObject(result);

        planos.setSuccess(jsonObj.optBoolean("success")); //opt se "existir"
        JSONArray planosArray = jsonObj.optJSONArray("planos");
        List<Planos> planosArrayList =  new ArrayList<>();
        planosArrayList.add(convertJsonToPlanos(planosArray.optString(0)));
        planos.setPlanosList(planosArrayList);

        return planos;

    }

    private PlanoResponse convertJsonToAllPlanos(String result, boolean isCursos) throws JSONException {
        PlanoResponse planos = new PlanoResponse();
        JSONObject jsonObj = new JSONObject(result);

        planos.setSuccess(jsonObj.optBoolean("success")); //opt se "existir"
        JSONArray planosArray = jsonObj.optJSONArray("planos");
        List<Planos> planosArrayList =  new ArrayList<>();
        for (int i = isCursos ? 0 : 3 ; i < planosArray.length(); i++) { //0:3 CURSOS TRUE ? 3> RECOMENDACOES FALSE
            planosArrayList.add(convertJsonToPlanos(planosArray.optString(i)));
        }
        planos.setPlanosList(planosArrayList);

        return planos;

    }

    private Planos convertJsonToPlanos(String result) throws JSONException {
        Planos planos = new Planos();
        JSONObject jsonObj = new JSONObject(result);
        planos.setId(jsonObj.optInt("id"));
        planos.setNome(jsonObj.optString("nome"));
        planos.setImagem(jsonObj.optString("imagem"));
        planos.setDescricao(jsonObj.optString("descricao"));

        JSONArray exerciciosArray = jsonObj.optJSONArray("Exercicios");
        if (exerciciosArray != null ){
            List<Exercicios> exerciciosArrayList =  new ArrayList<>();
            for (int i = 0; i < exerciciosArray.length() ; i++) {
                exerciciosArrayList.add(convertJsonToExercicios(exerciciosArray.optString(i)));
            }
            planos.setExerciciosList(exerciciosArrayList);

        }
        return planos;
    }

    private Exercicios convertJsonToExercicios(String result) throws JSONException {
        Exercicios exercicios = new Exercicios();
        JSONObject jsonObj = new JSONObject(result);
        exercicios.setId(jsonObj.optInt("id"));
        exercicios.setNome(jsonObj.optString("nome"));
        exercicios.setImagem(jsonObj.optString("imagem"));
        exercicios.setDescricao(jsonObj.optString("descricao"));
        return exercicios;
    }

    private StringBuilder buildString(HashMap<String, String> params){
        StringBuilder sbParams = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0){
                    sbParams.append("&");
                }
                sbParams.append(key).append("=")
                        .append(URLEncoder.encode(params.get(key), "UTF-8"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }
        return sbParams;
    }

    //CONVERT-LOGIN
    private LoginResponse convertJsonToLoginResponse (String json) throws JSONException {
        LoginResponse loginResponse = new LoginResponse();
        JSONObject jsonObject = new JSONObject(json);

        loginResponse.setToken(jsonObject.optString("token"));
        try {
            loginResponse.setMessage(jsonObject.optString("message"));
        } catch (Exception e) {
            Log.d("error", "error");
        }
        JSONObject jsonUtilizador = jsonObject.optJSONObject("utilizador");
        Utilizadores utilizadores = convertJsonToUtilizadores(jsonUtilizador.toString());
        loginResponse.setUtilizador(utilizadores);
        return loginResponse;
    }

    //CONVERT-REGISTO
    private SimpleResponse convertJsonToRegistoResponse (String json) throws JSONException {

        return convertJsonToSimpleResponse(json);
    }

    //CONVERT-UTILIZADORES
    private Utilizadores convertJsonToUtilizadores (String json) throws JSONException {
        Utilizadores utilizadores = new Utilizadores();
        JSONObject jsonObj = new JSONObject(json);

        utilizadores.setId(jsonObj.optInt("id"));
        utilizadores.setName(jsonObj.optString("name"));
        utilizadores.setEmail(jsonObj.optString("email"));
        utilizadores.setPassword(jsonObj.optString("password"));
        JSONArray atividades = jsonObj.optJSONArray("Atividades");
        if (atividades != null){
            List<Atividades> atividadesList =  new ArrayList<>();
            for (int i = 0; i < atividades.length() ; i++) {
                atividadesList.add(convertJsonToAtividades(atividades.optString(i)));
            }
            utilizadores.setListaAtividade(atividadesList);

        }
        return utilizadores;
    }

    //ATIVIDADES
    private Atividades convertJsonToAtividades (String json) throws JSONException {
        Atividades atividades = new Atividades();
        JSONObject jsonObj = new JSONObject(json);

        atividades.setId(jsonObj.optInt("id"));
        atividades.setUserId(jsonObj.optInt("userId"));
        atividades.setPlanoId(jsonObj.optInt("planoId"));
        atividades.setDataStart(date(jsonObj.optString("data_start")));
        atividades.setDataFin(date(jsonObj.optString("data_fin")));
        if (jsonObj.optJSONObject("Plano") != null){
            atividades.setPlanos(convertJsonToPlanos(jsonObj.optJSONObject("Plano").toString()));
        }
        return atividades;
    }

    //STRING -> DATE
    private Date date (String string) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(string);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (string.equals("null")){
            return null;
        }
        return date;
    }

    private String converterInputStreamToString(InputStream is) {
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br;
            String line;
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}