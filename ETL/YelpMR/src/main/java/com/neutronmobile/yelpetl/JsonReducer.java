package com.neutronmobile.yelpetl;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.neutronmobile.property.*;

public class JsonReducer extends Reducer<Text , InfoWritable , Text , Text>{

	private InfoWritable info = new InfoWritable();
	private TreeMap<Double , Text> treeMap = new TreeMap<Double , Text>();
	
	public void reduce(Text key , Iterable<InfoWritable> values , Context context) throws IOException , InterruptedException {
		
		for(InfoWritable info : values){
			double stars = Double.parseDouble(info.GetStars());
			treeMap.put(stars, new Text("\u0001" + info.GetName()));
			
			if(treeMap.size() > 11){
				treeMap.remove(treeMap.firstKey());
			}
		}
		
		for(Text t : treeMap.values()){
			context.write(key, t);
		}
	}
	
}
