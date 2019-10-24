package test1.ioTest.nettyTest.echo.client;

import test1.ioTest.nettyTest.echo.MyClientHandler;

public class EchoClientHandler extends MyClientHandler {

    public static final String clientId="client1";

    public String getClientId(){
        return this.clientId;
    }
}
