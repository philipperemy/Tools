package main;

import java.io.IOException;
import java.net.MalformedURLException;
import buyer.GrouponBuyerClient;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class MainGrouponClient
{
    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException
    {
        String urlString = "http://info.groupon.fr/c/r?ACTION=ri&EMID=08G02SJABPD01NVPN0R&UID=R3NBX6HVMWLYQRSEVWSA&provision_secret=B2eKOV&deal_id=31795655&url_param=CID%3DFR_CRM_18_0_0_357%26a%3D2287";
        String email = "grouponparent3+5@gmail.com";
        String CB = "5132685343358758";
        String code = "366";
        String monthExpiry = "FEBRUARY";
        GrouponBuyerClient.buy(urlString, "Luc", "Cyrano", "22", "Rue Dufourmartelle", "75020", "Paris", email, "2296NABLT", CB, code, monthExpiry, "2014");
    }
}
