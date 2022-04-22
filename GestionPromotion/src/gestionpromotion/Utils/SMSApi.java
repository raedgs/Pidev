package gestionpromotion.Utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Mahdi
 */
public class SMSApi {

    public SMSApi() {
    }
    public static final String ACCOUNT_SID = "ACe37a1109fbb8c949150ee7776ffba405";
    public static final String AUTH_TOKEN = "17uoEtuihi6Lsg4hdedT7PUhF4FNgBPD2F";

    public void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21629774931"),
                new PhoneNumber("+12395109349"),
                "Votre nouveau Promotion est ajouté avec succée, " + msg).create();

        System.out.println(message.getSid());

    }
}
