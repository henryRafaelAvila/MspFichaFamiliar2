package z9.msp.gob.mspfichafamiliar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by henry on 5/20/2017.
 */

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setFormulariosId(String formulariosId) {
        prefs.edit().putString("formularioId", formulariosId).commit();

    }

    public String getFormulariosId() {
        String formulariosId = prefs.getString("formularioId","");
        return formulariosId;
    }
}
