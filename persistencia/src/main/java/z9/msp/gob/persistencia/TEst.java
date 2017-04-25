package z9.msp.gob.persistencia;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import z9.msp.gob.persistencia.entity.Pueblo;

/**
 * Created by henry on 4/2/2017.
 */

public class TEst {
    public static void main(String args[]){
      //  final String json = "{\"id\":46,\"nombre\":\"Miguel\",\"empresa\":\"Autentia\"}";

    }

    public static void populated(String json)  {
        JSONArray  jObject = null;
        try {
            jObject = new JSONArray (json);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
