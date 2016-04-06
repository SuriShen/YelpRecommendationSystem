package com.neutronmobile.yelpetl;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
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


public class JsonMapper extends Mapper<Object, Text , Text , InfoWritable>{
	
	//input : <key , Text of origin>
	//reviewCount > 3 : active users => output <city , info[name , stars] >
	
	private InfoWritable info = new InfoWritable();
	
	public void map(Object key , Text value , Context context) throws IOException , InterruptedException {
		
		ObjectMapper mapper = new ObjectMapper();
		String[] tuple = value.toString().split("\\n");
		
		for(int i = 0 ; i < tuple.length ; i++){
			Bussiness bus = mapper.readValue(tuple[i], Bussiness.class);
			
			if(bus.reviewCount != null && Integer.parseInt(bus.reviewCount) > 3){
				
				info.SetName(bus.name);
				info.SetStars(bus.stars);
				
				if(info != null && bus.city != null){
					context.write(new Text(bus.city), info);
				}
				
			}
			
		}
		
	}
	
}
