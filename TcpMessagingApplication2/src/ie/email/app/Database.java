package our.email.app;

/**
 * 
 * @author Paul Murphy
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database
{
    private static final Map<String, List<String>> map = new HashMap<>();

    public static void newUser(String username)
    {
        map.put(username, new ArrayList<String>());
    }

    public static boolean exists(String username)
    {
        return map.get(username) != null;
    }

    public static void sendMessage(String recipient, String message)
    {
        List<String> messages1 = null;
        messages1 = map.get(recipient);

        if (map.containsKey(recipient))
        {
            messages1 = map.get(recipient);
            messages1.add(message);
        }
    }

    public static List<String> getMessages(String username)
    {
        return map.get(username);
    }
}
