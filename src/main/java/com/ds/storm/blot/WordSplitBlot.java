package com.ds.storm.blot;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * com.ds.storm.blot
 *
 * @author tongdongsheng
 * @date 2018/05/23
 */
public class WordSplitBlot implements IRichBolt {

    private String name;
    private Integer id;
    private OutputCollector collector;
    private int num=0;
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
        this.name = topologyContext.getThisComponentId();
        this.id = topologyContext.getThisTaskId();
    }
    public void execute(Tuple tuple) {
        String line = tuple.getString(0);
        String[] words = line.split(" ");
        System.out.println(String
                .format("========[Blot名称:%s,任务ID:%d],第[%d]次处理========",
                name,
                id,
                ++num));
        for(String word : words) {
            word = word.trim();
            if(word.length() != 0) {
                word = word.toLowerCase();
                this.collector.emit(new Values(word));
            }
        }
        this.collector.ack(tuple);
    }
    public void cleanup() {

    }
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
