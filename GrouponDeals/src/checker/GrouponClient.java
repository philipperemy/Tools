package checker;

import java.io.IOException;
import java.net.MalformedURLException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class GrouponClient
{
    
    public static WebClient getGrouponBrowserClient()
    {
        final WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setAppletEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setPrintContentOnFailingStatusCode(true);
        client.getOptions().setPopupBlockerEnabled(true);
        client.getOptions().setThrowExceptionOnScriptError(false);
        return client;
    }
    
    public static WebClient getGrouponBrowserClient(String username, String pass) throws FailingHttpStatusCodeException, MalformedURLException, IOException
    {
        final WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setAppletEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setPrintContentOnFailingStatusCode(true);
        client.getOptions().setPopupBlockerEnabled(true);
        client.getOptions().setThrowExceptionOnScriptError(false);

        final HtmlPage homePage = client.getPage("http://www.groupon.fr/login");

        String htmlStr = homePage.asText();
        if (!htmlStr.contains("Mes abonnements"))
        {
            System.out.print("Submitting... ");
            //final HtmlForm form = homePage.getFormByName("jLoginForm");
            final HtmlForm form = homePage.getHtmlElementById("jLoginForm");
            
            final HtmlTextInput identifier = form.getInputByName("email");
            final HtmlPasswordInput password = form.getInputByName("password");
            final HtmlButton submit = (HtmlButton) homePage.getElementById("jSignInSubmitBtn");

            identifier.setValueAttribute(username);
            password.setValueAttribute(pass);

            final HtmlPage page2 = submit.click();
            htmlStr = page2.asText();
            System.out.println("Logging successful");
        }
        else
        {
            System.out.println("Already logged... Resuming");
        }
        if (!htmlStr.contains("Mes abonnements"))
        {
            System.out.println("Still not OK");
            System.exit(0);
        }

        return client;
    }

    public static HtmlPage getHtmlPage(WebClient client) throws FailingHttpStatusCodeException, MalformedURLException, IOException
    {
        return client.getPage("https://www.groupon.fr/mycredits");

    }
}
