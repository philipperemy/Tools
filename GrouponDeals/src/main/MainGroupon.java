package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import connection.Constants.City;
import connection.GrouponHttpCall;

public class MainGroupon
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("groupon-result.txt");

        if (file.exists())
        {
            file.delete();
        }

        file.createNewFile();

        PrintWriter pw = new PrintWriter(file);

        long m1 = System.currentTimeMillis();
        GrouponHttpCall grouponHttpCall = new GrouponHttpCall();
        Map<City, List<String>> correspondingMap = grouponHttpCall.searchDeals(6.00);

        pw.println("");

        for (Entry<City, List<String>> entry : correspondingMap.entrySet())
        {
            if (!entry.getValue().isEmpty())
            {
                pw.println("_____ " + entry.getKey().getName() + " _____");
                for (String dealName : entry.getValue())
                {
                    pw.println(" - " + dealName);
                }
                pw.println();
            }
        }

        pw.println("Call performed in " + (System.currentTimeMillis() - m1) + " ms");
        pw.close();
    }
}
