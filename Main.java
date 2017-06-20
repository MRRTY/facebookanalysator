
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;


public class Main {

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        Suspect user1 = new Suspect("https://mobile.facebook.com/iaroslavfedorenko");
        System.out.println(user1);
        System.out.println(Suspect.result(user1));


    }
}
