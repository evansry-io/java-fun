import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Class.forName;


public class ItemMapper {

    public ArrayList<ArrayList<Item>> getItems() throws IOException, ParseException, ClassNotFoundException {
        ArrayList<ArrayList<Item>> completeItemList = new ArrayList<>();

        for(EquipmentTypes value : EquipmentTypes.values()) {
//            System.out.println(EquipmentTypes.values());
//            System.out.println(value);
            ArrayList itemArrayList = new ArrayList<Item>();

            Object[] objects = parseFile(value.toString().toLowerCase());
            mapObjects(itemArrayList, forName("Items."+ value), objects);
            completeItemList.add(itemArrayList);
        }
        return completeItemList;
    }

    private void mapObjects(ArrayList itemArrayList, Class clazz, Object[] objects) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        for(Object object : objects){
            itemArrayList.add(objectMapper.readValue(object.toString(),clazz));
        }
    }

    private Object[] parseFile(String itemType) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray data = (JSONArray) parser.parse(new FileReader(String.format("src/main/java/Items/Descriptions/%s.json", itemType)));
        Object[] objects = data.toArray();
        return objects;
    }
}
