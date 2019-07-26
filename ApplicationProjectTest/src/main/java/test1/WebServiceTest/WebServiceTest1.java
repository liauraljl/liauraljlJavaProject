package test1.WebServiceTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liaura_ljl on 2018/5/23.
 */
public class WebServiceTest1 {

    public static void main(String[] args){
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test() throws IOException {
        //服务的地址
        URL wsUrl = new URL("http://192.168.1.95:7080/HZServer/rest/RestService/layerSyncInfo");

        HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

        conn.setDoInput(true);
        conn.setDoOutput(true);
        //conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

        //OutputStream os = conn.getOutputStream();

        //请求体
        //String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://ws.itcast.cn/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
         //       "<soapenv:Body> <q0:sayHello><arg0>aaa</arg0>  </q0:sayHello> </soapenv:Body> </soapenv:Envelope>";

        //os.write(soap.getBytes());

        InputStream is = conn.getInputStream();

        byte[] b = new byte[1024];
        int len = 0;
        String s = "";
        while((len = is.read(b)) != -1){
            String ss = new String(b,0,len,"UTF-8");
            s += ss;
        }
        System.out.println(s);

        is.close();
        //os.close();
        conn.disconnect();
    }

//
//    public static String getService(String user) {
//        URL url = null;
//        try {
//            url = new URL(
//                    "http://192.168.0.100:8080/ca3/services/caSynrochnized");
//        } catch (MalformedURLException mue) {
//            return mue.getMessage();
//        }
//        // This is the main SOAP object
//        Call soapCall = new Call();
//        // Use SOAP encoding
//        soapCall.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
//        // This is the remote object we're asking for the price
//        soapCall.setTargetObjectURI("urn:xmethods-caSynrochnized");
//        // This is the name of the method on the above object
//        soapCall.setMethodName("getUser");
//        // We need to send the ISBN number as an input parameter to the method
//        Vector soapParams = new Vector();
//
//        // name, type, value, encoding style
//        Parameter isbnParam = new Parameter("userName", String.class, user,
//                null);
//        soapParams.addElement(isbnParam);
//        soapCall.setParams(soapParams);
//        try {
//            // Invoke the remote method on the object
//            Response soapResponse = soapCall.invoke(url, "");
//            // Check to see if there is an error, return "N/A"
//            if (soapResponse.generatedFault()) {
//                Fault fault = soapResponse.getFault();
//                String f = fault.getFaultString();
//                return f;
//            } else {
//                // read result
//                Parameter soapResult = soapResponse.getReturnValue();
//                // get a string from the result
//                return soapResult.getValue().toString();
//            }
//        } catch (SOAPException se) {
//            return se.getMessage();
//        }
//    }
}
