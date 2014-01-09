package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public abstract class HttpCall
{

    protected String httpURL   = "";
    protected String suffixURL = "";

    protected List<String> performHttpGetRequest(String request) throws IOException
    {
        long m1 = System.currentTimeMillis();
        boolean exception;
        List<String> list = null;
        do
        {
            exception = false;
            BufferedReader reader = null;
            try
            {
                list = new ArrayList<>();
                URL url = new URL(request);
                URLConnection urlConnection = url.openConnection();

                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String text;
                int count = 0;

                while ((text = reader.readLine()) != null)
                {
                    list.add(text);
                    count++;
                }
                System.out.print("Performing Http GET Request : " + request + ", ");
                System.out.println(count + " lines read from request, in " + (System.currentTimeMillis() - m1) + " ms");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                exception = true;
            }
            finally
            {
                reader.close();
            }
        }
        while (exception);

        return list;
    }

}
