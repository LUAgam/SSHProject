package io.aomen.guo.entity.huoche;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.aomen.guo.entity.BaseEntity;
import io.aomen.guo.entity.User;

/** 
* @author AMGuo E-mail:www.guoao@foxmail.com 
* @version 创建时间：2018年1月4日 下午4:28:51 
* 类说明 
*/
@Entity
@Table(name = "tbl_train")
public class Train extends BaseEntity<User>{

    /**
     * 
     */
    private static final long serialVersionUID = -1733749099050420067L;
    
    //不清楚字段
    @Column(length = 2000)
    private String column_1;
    
    //预订按钮文字
    private String btnText;
    
    //不清楚字段
    private String column_2;
    
    //火车编号
    private String no;
    
    //本火车起点站编号
    private String trainStartStation;
    
    //本火车终点站编号
    private String trainEndStation;
    
    //搜索起点站编号
    private String searchStartStation;
    
    //搜索终点站编号
    private String searchEndStation;
    
    //搜索起点站出发时间
    private Date searchStartTime;
    
  //搜索终点站到达时间
    private Date searchEndTime;
    
    //搜索起点到终点历时
    private Date searchTime;
    
    //不清楚字段
    private String column_3;
    
    //不清楚字段
    private String column_4;
    
    //出发日期
    private String trainDate;
    
    //不清楚字段
    private String column_5;
    
    

}
