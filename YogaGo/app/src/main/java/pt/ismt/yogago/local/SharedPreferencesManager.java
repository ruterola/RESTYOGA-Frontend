package pt.ismt.yogago.local;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import pt.ismt.yogago.model.LoginResponse;

public class SharedPreferencesManager {
    private static SharedPreferencesManager INSTANCE;
    private Application application;
    private static String USERPREFS_KEY = "USERPREFS_KEY";
    private static String USERPREFS = "USERPREFS";

    //Criar uma unica instancia do objecto (singleton)
    public SharedPreferencesManager(Application application) {
        this.application = application;
    }

    public static SharedPreferencesManager getINSTANCE(Application application) {
        if (INSTANCE == null) {
            synchronized (SharedPreferencesManager.class){

                if (INSTANCE == null){
                    INSTANCE = new SharedPreferencesManager(application);
                }
            }
        }
        return INSTANCE;
    }

    // Armazena os dados do login na sharedPreferences
    public void setUser(LoginResponse loginResponse){
        SharedPreferences sharedPreferences = application.getSharedPreferences(USERPREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERPREFS_KEY, new Gson().toJson(loginResponse, LoginResponse.class));
        editor.apply();
    }

    // Vai buscar os dados do login de um utilizador especifico
    public LoginResponse getUser(){
        SharedPreferences sharedPreferences = application.getSharedPreferences(USERPREFS, Context.MODE_PRIVATE);
        return new Gson().fromJson(sharedPreferences.getString(USERPREFS_KEY, ""), LoginResponse.class);
    }
}
