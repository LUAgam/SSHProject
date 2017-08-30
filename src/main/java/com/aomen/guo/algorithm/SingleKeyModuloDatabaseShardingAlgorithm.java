package com.aomen.guo.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

/** 
* @author AMGuo E-mail:www.guoao@foxmail.com 
* @version 创建时间：2017年8月30日 下午4:26:21 
* 类说明 
*/
public final class SingleKeyModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {

    private int dbCount = 1;

    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % dbCount + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<Long> values = shardingValue.getValues();
        for (Long value : values) {
            for (String dataSourceName : availableTargetNames) {
                if (dataSourceName.endsWith(value % dbCount + "")) {
                    result.add(dataSourceName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % dbCount + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }

    /**
     * 设置database分库的个数
     * @param dbCount
     */
    public void setDbCount(int dbCount) {
        this.dbCount = dbCount;
    }
}
