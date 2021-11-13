package services;

import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.exception.SmsapiException;

public class SendSmsService {

    public void sendSms(String recipentPhoneNum) throws SmsapiException {
        var client = new OAuthClient("zCzYDSgIz3Ke5VBhxo9uy1pR8uEfyialyJwwfAxh");

        var smsFactory = new SmsFactory(client);
        var actionSmsSend = smsFactory.actionSend(
                recipentPhoneNum,
                "Your order has been confirmed");
        actionSmsSend.execute();
    }

}
