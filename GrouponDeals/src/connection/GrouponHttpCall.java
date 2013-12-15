package connection;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import connection.Constants.City;

public class GrouponHttpCall extends HttpCall
{

    private Map<Double, String> patternsMapCached = null;

    private NumberFormat        formatter         = new DecimalFormat("##,## euros");

    private String createURLQuery(String cityName)
    {
        return "http://www.groupon.fr/deals/" + cityName;
    }

    public Map<City, List<String>> searchDeals(double maxPrice) throws IOException
    {
        Map<City, List<String>> deals = new HashMap<>();

        for (City city : City.values())
        {
            String query = createURLQuery(city.getName());
            deals.put(city, performHttpGetRequest(query));
        }

        return processDeals(deals, maxPrice);
    }

    private Map<City, List<String>> processDeals(Map<City, List<String>> deals, double maxPrice)
    {
        Map<City, List<String>> correspondingDeals = new HashMap<>();

        for (City city : deals.keySet())
        {
            correspondingDeals.put(city, new ArrayList<String>());

            for (Entry<Double, String> patternEntry : getAllPatterns(maxPrice).entrySet())
            {
                boolean getDealNameAndBreak = false;

                for (String str : deals.get(city))
                {
                    if (getDealNameAndBreak)
                    {
                        // End of price line
                        if (str.contains("h4"))
                        {
                            continue;
                        }

                        // Deal name line
                        String dealName = "( " + formatter.format(patternEntry.getKey()) + " ) ";
                        dealName += extractDealName(str);
                        correspondingDeals.get(city).add(dealName);

                        getDealNameAndBreak = false;
                    }

                    if (str.contains(patternEntry.getValue()))
                    {
                        getDealNameAndBreak = true;
                    }
                }
            }
        }

        return correspondingDeals;
    }

    private String extractDealName(String str)
    {
        int begin = str.indexOf("<p class=\"extraDealTxt\">") + "<p class=\"extraDealTxt\">".length();
        int end = str.lastIndexOf("</p>");
        String resultStr = str.substring(begin, end);
        resultStr = resultStr.replaceAll("â‚¬", "euros");
        resultStr = resultStr.replaceAll("Ã", "à");
        resultStr = resultStr.replaceAll("Ã©", "é");
        resultStr = resultStr.replaceAll("à©", "é");
        resultStr = resultStr.replaceAll("à¨", "è");
        resultStr = resultStr.replaceAll("â€™", "'");
        resultStr = resultStr.replaceAll("Â®", "®");
        resultStr = resultStr.replaceAll("àª", "ê");
        resultStr = resultStr.replaceAll("à¨", "è");
        resultStr = resultStr.replaceAll("à«", "ë");
        resultStr = resultStr.replaceAll("Å“", "oe");
        resultStr = resultStr.replaceAll("  ", " ");
        resultStr = resultStr.replaceAll("à¤", "a");
        resultStr = resultStr.replaceAll("sâ„¢", "s");
        resultStr = resultStr.replaceAll("à¢", "â");
        return resultStr;
    }

    private Map<Double, String> getAllPatterns(double maxPrice)
    {
        if (patternsMapCached == null)
        {
            Map<Double, String> patternsMap = new HashMap<>();
            while (maxPrice >= 0)
            {
                String pattern = new DecimalFormat("#.##").format(maxPrice);
                pattern = pattern.replaceAll("\\.", ",");

                // 130 => 130,00
                if (pattern.indexOf(",") == -1)
                {
                    pattern = pattern + ",00";
                }

                // 0,9 => 0,90
                if (pattern.indexOf(",") != -1 && pattern.indexOf(",") + 2 == pattern.length())
                {
                    pattern = pattern + "0";
                }

                pattern = " " + pattern + " &euro; au lieu de";

                patternsMap.put(maxPrice, pattern);
                maxPrice -= 0.01;
            }

            patternsMapCached = patternsMap;
        }

        return patternsMapCached;
    }
}
