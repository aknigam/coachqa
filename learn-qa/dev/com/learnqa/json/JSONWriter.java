package com.learnqa.json;

import com.coachqa.ws.model.UserModel;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JSONWriter {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		UserModel  value = new UserModel();
		value.setEmail("anand.k.nigam@gmail.com");
		value.setFirstName("Anand");
		String s = mapper.writeValueAsString(value);
		System.out.println(s);
	}
}


