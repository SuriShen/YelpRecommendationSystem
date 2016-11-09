CREATE EXTERNAL TABLE qi_yelp_inverted_table_2_non_orc(
    keyword String,
    business_id_list STRING
    )
COMMENT 'inverted table to get from keyword to business_id, non orc'
ROW FORMAT
DELIMITED FIELDS TERMINATED BY '\001'
LINES TERMINATED BY '\n';

LOAD DATA INPATH 'hdfs:///user/qizhu/output/inverted_table_keyword_to_business_id' OVERWRITE INTO TABLE qi_yelp_inverted_table_2_non_orc;

CREATE TABLE qi_yelp_inverted_table_2(
    keyword String,
    business_id_list STRING
    )
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
with SERDEPROPERTIES ("hbase.columns.mapping" = ":key, business:business_id")
TBLPROPERTIES ("hbase.table.name" = "qi_yelp_inverted_table_2");

INSERT OVERWRITE TABLE qi_yelp_inverted_table_2 SELECT * FROM qi_yelp_inverted_table_2_non_orc;

