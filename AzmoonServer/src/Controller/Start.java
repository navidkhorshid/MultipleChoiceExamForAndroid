package Controller;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class Start
{
    public static void main(String[] args)
    {
        try
        {
            HttpServer server = HttpServerFactory.create("http://localhost:9888/");
            server.start();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
