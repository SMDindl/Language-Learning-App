
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader extends DataConstants{
	
	public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			FileReader reader = new FileReader(DataConstants.GAME_DATA_FILE);
			JSONParser parser = new JSONParser();	
			JSONArray dataJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < dataJSON.size(); i++) {
                
				// users.add(new User(id, userName, firstName, lastName, age, phoneNumber));
			}
			
			return users;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}