package com.neutronmobile.yelpetl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.neutronmobile.property.InfoWritable;

public class MREngine {

	public void runJob(String input , String output) throws Exception {
		Configuration conf = new Configuration();
		Job job = new Job(conf);
		
		job.setJarByClass(MRRunner.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(InfoWritable.class);
		job.setMapperClass(JsonMapper.class);
		job.setReducerClass(JsonReducer.class);
		
		job.setNumReduceTasks(1);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path(input));
		Path outPath = new Path(output);
		FileOutputFormat.setOutputPath(job, outPath);
		outPath.getFileSystem(conf).delete(outPath , true);
		
		job.waitForCompletion(true);
		

	}
	
}
