package by.bsuir.gurinov;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Serialization<T> {
    /**
     * serialize object to Json
     * @param obj object
     * @param fileName file path
     */
    public void serialize(ArrayList<T> obj, String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deserialize object from Json
     * @param type return type
     * @param fileName file path
     */
    public ArrayList<T> deSerialize(Type type, String fileName) {
        Gson gson = new Gson();
        String json = "";
        try {
            json = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type type1 = new TypeToken<ArrayList<T>>() {}.getType();
        return gson.fromJson(json, type);
    }

}
