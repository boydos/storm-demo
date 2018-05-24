package com.ds.storm.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.*;
import java.util.Map;

/**
 * com.ds.storm.spout
 *
 * @author tongdongsheng
 * @date 2018/05/23
 */
public class WordReader implements IRichSpout {

    private FileReader file;
    private SpoutOutputCollector collector;
    private boolean completed = false;

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        try {
            this.file = new FileReader((String) map.get("wordfile"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error read file [" + map.get("wordfile") +"]");
        }
        this.collector = spoutOutputCollector;
    }

    public void close() {

    }

    public void activate() {

    }

    public void deactivate() {

    }

    public void nextTuple() {
        if(completed) {
            return;
        }
        System.out.println("========开始输入数据源========");
        BufferedReader reader = new BufferedReader(file);
        String line;
        try {
            while ((null != (line = reader.readLine()))) {
                collector.emit(new Values(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error read tuple");
        } finally {
            completed = true;
            System.out.println("========录入完毕!========");
        }

    }

    public void ack(Object o) {
        System.out.println("ack object=" + o);
    }

    public void fail(Object o) {
        System.out.println("fail object=" + o);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
