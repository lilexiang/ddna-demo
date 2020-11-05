package com.kangwang.word;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.deltadna.android.sdk.DDNA;
import com.deltadna.android.sdk.Event;
import com.deltadna.android.sdk.EventActionHandler;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DDNATest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_d_n_a_test);
            try {
                DDNA.instance().startSdk();
            }catch (Exception e){
                e.printStackTrace();
            }

    }

    public void sendEvent(View view){
        Log.i("DDNATest","sendEvent()");
        HashMap<String,Object> event = new HashMap<>();
        String eventName = "levelStarted";
        event.put("ifFirstPlay",true);
        event.put("currentCoins",10);
        event.put("levelID",1);
        event.put("eventName",eventName);
        logDDNAEvent(event);
    }


    public void logDDNAEvent(HashMap<String, Object> event) {
            String eventName = (String) event.get("eventName");
            Event ddnaEvent = new Event(eventName);
            event.remove("eventName");
            Iterator<String> iterator = event.keySet().iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                Object value = event.get(key);
                ddnaEvent.putParam(key,value);
            }
                DDNA.instance().recordEvent(ddnaEvent).add(new EventActionHandler.GameParametersHandler(new EventActionHandler.Callback<JSONObject>() {
                    @Override
                    public void handle(JSONObject action) {
                        if(action!=null) {
                            Log.i("DDNATest", action.toString());
                        }
                    }
                })).run();

    }

}
