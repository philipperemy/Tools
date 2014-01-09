package buyer;

import java.io.IOException;
import java.net.MalformedURLException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import checker.GrouponClient;

public class GrouponBuyerClient extends GrouponClient
{
    private static final String PAY_SUBMIT_BUTTON = "registerAndPay";
    private static final String CHECKBOX_2 = "dpConfirmed";
    private static final String CHECKBOX_1 = "agbConfirmed";
    private static final String CARD_EXPIRY_YEAR = "pn.cc.valid.year";
    private static final String CARD_EXPIRY_MONTH = "pn.cc.valid.month";
    private static final String CODE = "jpn.cc.sec.code";
    private static final String CARD_NUMBER = "pn.cc.number";
    private static final String PASSWORD_REPEAT = "passwordRepeat";
    private static final String PASSWORD = "registerView.password";
    private static final String EMAIL = "registerView.email";
    private static final String CITY_NAME = "registerView.userAddress.cityName";
    private static final String POSTAL_CODE = "registerView.userAddress.postalCode";
    private static final String STREET = "registerView.userAddress.street";
    private static final String STREET_NUMBER = "registerView.userAddress.streetNumber";
    private static final String LAST_NAME = "registerView.userAddress.lastName";
    private static final String FIRST_NAME = "registerView.userAddress.firstName";
    private static final String GENDER = "registerView.userAddress.gender";

    public static void buy(String _url, String _prenom, String _nom, String _streetNumber, String _street, String _postalCode, String _cityName, String _email, String _password, String _cardNumber, String _numberCode, String _monthExpiry, String _yearExpiry) throws FailingHttpStatusCodeException, MalformedURLException, IOException
    {
        WebClient client = getGrouponBrowserClient();

        final HtmlPage dealPage = client.getPage(_url);

        String dealPageText = dealPage.asText();
        HtmlSubmitInput button = null;
        if (dealPageText.contains("multisubmit"))
        {
            button = dealPage.getElementByName("multisubmit1");
        }
        else
        {
            button = dealPage.getElementByName("sdds");
        }

        final HtmlPage buyPage = button.click();

        {
            final HtmlSelect gender = buyPage.getElementByName(GENDER);
            HtmlOption male = gender.getOptionByValue("MALE");
            gender.setSelectedAttribute(male, true);
        }

        {

            final HtmlTextInput prenom = buyPage.getElementByName(FIRST_NAME);
            prenom.setValueAttribute(_prenom);
        }

        {
            final HtmlTextInput nom = buyPage.getElementByName(LAST_NAME);
            nom.setValueAttribute(_nom);
        }

        {
            final HtmlTextInput streetNumber = buyPage.getElementByName(STREET_NUMBER);
            streetNumber.setValueAttribute(_streetNumber);
        }

        {
            final HtmlTextInput street = buyPage.getElementByName(STREET);
            street.setValueAttribute(_street);
        }

        {
            final HtmlTextInput postalCode = buyPage.getElementByName(POSTAL_CODE);
            postalCode.setValueAttribute(_postalCode);
        }

        {
            final HtmlTextInput cityName = buyPage.getElementByName(CITY_NAME);
            cityName.setValueAttribute(_cityName);
        }

        {
            final HtmlTextInput email = buyPage.getElementByName(EMAIL);
            email.setValueAttribute(_email);
        }

        {
            final HtmlPasswordInput password = buyPage.getElementByName(PASSWORD);
            password.setValueAttribute(_password);
        }

        {
            final HtmlPasswordInput password2 = buyPage.getElementByName(PASSWORD_REPEAT);
            password2.setValueAttribute(_password);
        }

        {
            final HtmlHiddenInput cardNumber = (HtmlHiddenInput) buyPage.getElementById(CARD_NUMBER);
            cardNumber.setValueAttribute(_cardNumber);
        }

        {
            final HtmlTextInput numberCode = (HtmlTextInput) buyPage.getElementById(CODE);
            numberCode.setValueAttribute(_numberCode);
        }

        {
            final HtmlSelect month = buyPage.getElementByName(CARD_EXPIRY_MONTH);
            HtmlOption monthExpiry = month.getOptionByValue(_monthExpiry.toUpperCase());
            month.setSelectedAttribute(monthExpiry, true);
        }

        {
            final HtmlSelect year = buyPage.getElementByName(CARD_EXPIRY_YEAR);
            HtmlOption yearExpiry = year.getOptionByValue(_yearExpiry);
            year.setSelectedAttribute(yearExpiry, true);
        }

        {
            final HtmlCheckBoxInput firstInbox = buyPage.getElementByName(CHECKBOX_1);
            firstInbox.setChecked(true);
        }

        {
            final HtmlCheckBoxInput secondInbox = buyPage.getElementByName(CHECKBOX_2);
            secondInbox.setChecked(true);
        }

        {
            final HtmlButton submit = buyPage.getElementByName(PAY_SUBMIT_BUTTON);
            HtmlPage submitPage = submit.click();
            System.out.println(submitPage.asText());
        }

    }
}
