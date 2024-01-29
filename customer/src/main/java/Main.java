import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) throws NamingException {
        String email="@appa.com";

            String domain = email.substring(email.indexOf("@") + 1);
        System.out.println(domain);
                Hashtable<String, String> env = new Hashtable<>();
                env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
                DirContext ictx = new InitialDirContext(env);
                Attributes attrs = ictx.getAttributes(domain, new String[]{"MX"});
                Attribute attr = attrs.get("MX");
                System.out.println( attr != null);


    }
}
