package com.ds.storm.blot;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * com.ds.storm.blot
 *
 * @author tongdongsheng
 * @date 2018/05/23
 */
public class CountBlot implements IRichBolt {

    private String name;
    private Integer id;
    private int num = 0;
    OutputCollector collector;
    Map<String,Integer> data = new HashMap<String, Integer>();

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
        this.name = topologyContext.getThisComponentId();
        this.id = topologyContext.getThisTaskId();
    }
    public void execute(Tuple tuple) {
        String word = tuple.getString(0);
        System.out.println(String
                .format("========[Blot名称:%s,任务ID:%d],第[%d]次统计========",
                        name,
                        id,
                        ++num));
        Integer count = data.get(word);
        if(count == null) {
            data.put(word,1);
        } else {
            data.put(word,count+1);
        }
        collector.ack(tuple);
    }
    public void cleanup() {
        System.out.println(String.format("========[Blot名称:%s,任务ID:%d]统计结束，开始显示单词数量========",
                name,
                id));
        for(Map.Entry<String,Integer> entry : data.entrySet()) {
            System.out.println(String.format("%s=%d", entry.getKey(), entry.getValue()));
        }
        System.out.println(String.format("========[Blot名称:%s,任务ID:%d]统计结束,显示结束！========",
                name,
                id));
        System.out.println("========释放相关资源========");
    }
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
