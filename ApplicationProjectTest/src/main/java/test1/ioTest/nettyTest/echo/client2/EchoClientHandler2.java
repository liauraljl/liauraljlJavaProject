package test1.ioTest.nettyTest.echo.client2;

import test1.ioTest.nettyTest.echo.MyClientHandler;

public class EchoClientHandler2 extends MyClientHandler {

    public static final String clientId="client2";

    public String getClientId(){
        return this.clientId;
    }
}
