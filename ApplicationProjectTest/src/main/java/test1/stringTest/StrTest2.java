package test1.stringTest;

/**
 * @author jinlei.li
 * @date 2020/3/23 18:05
 * @description
 */
public class StrTest2 {
    public static void main(String[] args) {
        String globalTicket = null;
        String rpcEntryUrl = null;
        String parentRpcId = null;
        if(globalTicket==null||rpcEntryUrl==null||parentRpcId==null){
            tets(globalTicket,rpcEntryUrl,parentRpcId);
        }
        System.out.println(globalTicket+":"+rpcEntryUrl+":"+parentRpcId);
    }

    private static void tets(String globalTicket,String rpcEntryUrl,String parentRpcId){
        globalTicket="ggg";
        rpcEntryUrl="rpc";
        parentRpcId="parent";
    }
}
