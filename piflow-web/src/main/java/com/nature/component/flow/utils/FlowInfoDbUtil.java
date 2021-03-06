package com.nature.component.flow.utils;

import org.springframework.beans.BeanUtils;

import com.nature.component.flow.model.FlowInfoDb;
import com.nature.component.flow.vo.FlowInfoDbVo;

public class FlowInfoDbUtil {
    /**
     * flowInfoDb实体转Vo
     *
     * @param mxGraphModel
     * @return
     */
    public static FlowInfoDbVo flowInfoDbToVo(FlowInfoDb flowInfoDb){
    	FlowInfoDbVo flowInfoDbVo = null;
        if(null!=flowInfoDb){
        	flowInfoDbVo = new FlowInfoDbVo();
            BeanUtils.copyProperties(flowInfoDb, flowInfoDbVo);
        }
        return flowInfoDbVo;
    }
}
