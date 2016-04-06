package com.neutronmobile.yelpetl;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.codehaus.jackson.map.ObjectMapper;

import com.neutronmobile.property.*;

public class MRRunner {

	public static void main(String args[]) throws Exception {
	
		MREngine engine = new MREngine();
		engine.runJob(args[0] , args[1]);
		
	}
	
}
