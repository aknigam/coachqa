package com.coachqa.ws.controllor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GsonConverterTest {

    public static void main(String[] args) throws IOException, ParseException {

        GsonConverterFactory gson = getGsonConverterFactory();


        ObjectMapper mapper = new ObjectMapper();

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

        Writer w = new StringWriter();
        Date d = new Date(1543586924000l);
        System.out.println(d);
        mapper.writeValue(w, d);

        String json = w.toString();
        System.out.println(json);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Gson g = gsonBuilder.create();
        Date targetObject = g.fromJson(json, Date.class);

//        Date targetObject = ISO8601Utils.parse(json, new ParsePosition(0));




    }

    private static GsonConverterFactory getGsonConverterFactory() {
        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
//                        http://stackoverflow.com/questions/7816586/gson-java-text-parseexception-unparseable-date
//                        https://developer.android.com/studio/run/index.html#rerun
//                        Use clean and rerun when schema is changed
        gsonConverter.setGson(new GsonBuilder().setDateFormat("yyyy-MM-dd").create());

        return GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd").create());
    }

}
