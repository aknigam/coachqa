package jackson;

import com.coachqa.ws.model.ClassroomMembershipRequest;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Anand on 9/27/2015.
 */
public class SerializationTest {

    public static void serialize(Object model) {
        ObjectMapper om = new ObjectMapper();
        om.writerWithDefaultPrettyPrinter();
        Writer w = new StringWriter();
        try {
            om.writeValue(w, model);
            System.out.println(w.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void desrialize(String json) throws IOException {
        ObjectMapper om = new ObjectMapper();
        ClassroomMembershipRequest request  = om.readValue(json.getBytes(), ClassroomMembershipRequest.class);
        System.out.printf("De-serialization successful "+request);
    }

    public static void main(String[] args) throws IOException {
        desrialize("{\"classroomId\":1,\"approve\":true,\"requests\":[{\"user\":{\"appUserId\":1}},{\"user\":{\"appUserId\":7}}]}");
    }
}
