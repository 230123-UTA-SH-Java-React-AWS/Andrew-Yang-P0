import com.revature.controllers.*;
import com.revature.model.*;
import com.sun.net.httpserver.HttpServer;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;


public class App {
    public static void main(String args[]) throws IOException {
        // Creates the server
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Sub-links to the different webpages
        server.createContext("/", new Homepage());
        server.createContext("/register", new Registration());
        server.createContext("/login", new Login());
        server.createContext("/user-account", new UserAccount());
        server.createContext("/user-ticket", new UserTicket());
        server.createContext("/manage-tickets", new ManageTickets());  // For managers
        server.createContext("/your-tickets", new YourTickets());      // For employees

        // Run the server
        server.setExecutor(null);
        server.start();
    }

    // Code Snippets
    /*
    Person p = new Employee("Test", "test");

        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = "";

        try {
            jsonObject = mapper.writeValueAsString(p);

            File f = new File("./src/output.txt");
            f.createNewFile();

            FileWriter fw = new FileWriter(f);

            fw.write(jsonObject);
            fw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    System.out.println(jsonObject);
     */
}
