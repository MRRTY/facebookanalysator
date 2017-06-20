
import java.io.IOException;
import java.security.GeneralSecurityException;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.httpclient.Cookie;

public class GetPageSourceAfterJS {
    public static String getSource(String url) throws FailingHttpStatusCodeException, IOException, GeneralSecurityException {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);

        WebClient webClient = new WebClient();
        webClient.setUseInsecureSSL(true);
        CookieManager cookieManager = webClient.getCookieManager();
        cookieManager.addCookie(new Cookie(".facebook.com","wd","1920x4000"));
        cookieManager.addCookie(new Cookie(".facebook.com","c_user","100017299027983"));
        cookieManager.addCookie(new Cookie(".facebook.com","datr","ES3HWOSbXsdkmceNrNnEZaGw"));
        cookieManager.addCookie(new Cookie(".facebook.com","dats","1"));
        cookieManager.addCookie(new Cookie(".facebook.com","dbln","%7B%22100017571697015%22%3A%22g2ZBMVyl%22%2C%22100017299027983%22%3A%22jMLU2ggS%22%7D"));
        cookieManager.addCookie(new Cookie(".facebook.com","fr","0Kcso825SxvRmBlVc.AWXk8ZWGycL3pBwwO0xQZa-YYQI.BYtrQa.VX.AAA.0.0.BZOUiy.AWWCQ_DJ"));
        cookieManager.addCookie(new Cookie(".facebook.com","locale","ru_RU"));
        cookieManager.addCookie(new Cookie(".facebook.com","lu","gA"));
        cookieManager.addCookie(new Cookie(".facebook.com","pl","n"));
        cookieManager.addCookie(new Cookie(".facebook.com","presence","EDvF3EtimeF1496639201EuserFA21B17299027983A2EstateFDutF1496639201755CEchFDp_5f1B17299027983F5CC"));
        cookieManager.addCookie(new Cookie(".facebook.com","sb","MC3HWI4OaylWDgvrvLyYvYZx"));
        cookieManager.addCookie(new Cookie(".facebook.com","xs","40%3AqqTzzgHt18ijzw%3A2%3A1496926386%3A5354%3A15628"));
        for (Cookie c : cookieManager.getCookies()){
            c.setPath("/");
        }

        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(30 * 1000); /* will wait JavaScript to execute up to 30s */

        String pageAsXml = page.asXml();

       return pageAsXml;
    }
}