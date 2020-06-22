package test1.sentinel;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.cluster.client.NettyTransportClient;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.cluster.request.ClusterRequest;

import static com.alibaba.csp.sentinel.cluster.ClusterStateManager.CLUSTER_CLIENT;

/**
 * @author: jinlei.li
 * @time: 2020/6/7 23:08
 */
public class Test {
    public static void main(String[] args) throws Exception {

        NettyTransportClient transportClient=new NettyTransportClient("127.0.0.1",11111);
        transportClient.start();

        Thread.sleep(3000);

        ClusterStateManager.applyState(CLUSTER_CLIENT);

        ClusterClientAssignConfig clientAssignConfig=new ClusterClientAssignConfig("127.0.0.1",11111);
        ClusterClientConfigManager.applyNewAssignConfig(clientAssignConfig);


        transportClient.sendRequest(new ClusterRequest().setId(123));

        Thread.sleep(100000000000L);
    }
}
