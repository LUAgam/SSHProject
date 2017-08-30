package com.aomen.guo.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

/** 
* @author AMGuo E-mail:www.guoao@foxmail.com 
* @version 创建时间：2017年8月30日 下午4:12:34 
* 类说明 
*/
public class SingleKeyModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {
    
    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % 4 + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<Integer> values = shardingValue.getValues();
        for (Integer value : values) {
            for (String each : availableTargetNames) {
                if (each.endsWith(value % 4 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
    
    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer value = range.lowerEndpoint(); value <= range.upperEndpoint(); value++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(value % 4 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
