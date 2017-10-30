package io.aomen.guo.web.formbean;

import java.io.Serializable;

/**
 * @author AMGuo E-mail:www.guoao@foxmail.com
 * @version 创建时间：2017年8月30日 下午4:56:33 类说明
 */
public class BaseFormBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1491027097871073533L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
