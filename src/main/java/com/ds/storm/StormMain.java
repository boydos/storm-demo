package com.ds.storm;

import com.ds.storm.blot.CountBlot;
import com.ds.storm.blot.WordSplitBlot;
import com.ds.storm.spout.WordReader;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import java.io.File;

/**
 * com.ds.storm
 *
 * @author tongdongsheng
 * @date 2018/05/23
 */
public class StormMain {

    public static void main(String[] args) throws InterruptedException, InvalidTopologyException, AuthorizationException, AlreadyAliveException {

        boolean debug = true;

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("reader",new WordReader());
        builder.setBolt("wordsplit", new WordSplitBlot(),2).shuffleGrouping("reader");
        builder.setBolt("wordcount",new CountBlot(),3).fieldsGrouping("wordsplit",new Fields("word"));

        File file = new File("src/main/resources/words.txt");
        Config config = new Config();
        config.setDebug(debug);

        if (debug) {
            config.put("wordfile",file.getAbsolutePath());
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("mycluster", config, builder.createTopology());
            Thread.sleep(6000);
            cluster.shutdown();
        } else {
            config.put("wordfile",args[0]);
            StormSubmitter.submitTopology("realCount", config, builder.createTopology());
        }
    }
}
