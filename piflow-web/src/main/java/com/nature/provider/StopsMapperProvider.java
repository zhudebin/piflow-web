package com.nature.provider;

import com.nature.base.util.DateUtils;
import com.nature.base.util.SessionUserUtil;
import com.nature.base.util.SqlUtils;
import com.nature.base.vo.UserVo;
import com.nature.common.Eunm.PortType;
import com.nature.component.flow.model.Flow;
import com.nature.component.flow.model.Stops;
import com.nature.third.vo.flowInfo.ThirdFlowInfoStopVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class StopsMapperProvider {
    /**
     * 新增Stops
     *
     * @param stops
     * @return
     */
    public String addStops(Stops stops) {
        String sqlStr = "";
        if (null != stops) {
            String id = stops.getId();
            Date crtDttm = stops.getCrtDttm();
            String crtUser = stops.getCrtUser();
            Date lastUpdateDttm = stops.getLastUpdateDttm();
            String lastUpdateUser = stops.getLastUpdateUser();
            Long version = stops.getVersion();
            Boolean enableFlag = stops.getEnableFlag();
            String bundel = stops.getBundel();
            String description = stops.getDescription().equals("null") ? null : stops.getDescription();
            String groups = stops.getGroups();
            String name = stops.getName();
            String inports = stops.getInports();
            PortType inPortType = stops.getInPortType();
            String outports = stops.getOutports();
            PortType outPortType = stops.getOutPortType();
            String owner = stops.getOwner();
            String pageId = stops.getPageId();
            Boolean checkpoint = stops.getCheckpoint();
            Flow flow = stops.getFlow();
            SQL sql = new SQL();

            sql.INSERT_INTO("flow_stops");

            //先处理修改必填字段
            if (null == crtDttm) {
                crtDttm = new Date();
            }
            if (StringUtils.isBlank(crtUser)) {
                crtUser = "-1";
            }
            if (null == lastUpdateDttm) {
                lastUpdateDttm = new Date();
            }
            if (StringUtils.isBlank(lastUpdateUser)) {
                lastUpdateUser = "-1";
            }
            if (null == version) {
                version = 0L;
            }
            if (null == enableFlag) {
                enableFlag = true;
            }
            sql.VALUES("id", SqlUtils.addSqlStr(id));
            sql.VALUES("crt_dttm", SqlUtils.addSqlStr(DateUtils.dateTimesToStr(crtDttm)));
            sql.VALUES("crt_user", SqlUtils.addSqlStr(crtUser));
            sql.VALUES("last_update_dttm", SqlUtils.addSqlStr(DateUtils.dateTimesToStr(lastUpdateDttm)));
            sql.VALUES("last_update_user", SqlUtils.addSqlStr(lastUpdateUser));
            sql.VALUES("version", version + "");
            sql.VALUES("ENABLE_FLAG", (enableFlag ? 1 : 0) + "");

            // 处理其他字段
            if (StringUtils.isNotBlank(bundel)) {
                sql.VALUES("bundel", SqlUtils.addSqlStr(bundel));
            }
            if (StringUtils.isNotBlank(description)) {
                sql.VALUES("description", SqlUtils.addSqlStr(description));
            }
            if (StringUtils.isNotBlank(groups)) {
                sql.VALUES("groups", SqlUtils.addSqlStr(groups));
            }
            if (StringUtils.isNotBlank(name)) {
                sql.VALUES("name", SqlUtils.addSqlStr(name));
            }
            if (StringUtils.isNotBlank(inports)) {
                sql.VALUES("inports", SqlUtils.addSqlStr(inports));
            }
            if (null != inPortType) {
                sql.VALUES("in_port_type", SqlUtils.addSqlStr(inPortType.name()));
            }
            if (StringUtils.isNotBlank(outports)) {
                sql.VALUES("outports", SqlUtils.addSqlStr(outports));
            }
            if (null != outPortType) {
                sql.VALUES("out_port_type", SqlUtils.addSqlStr(outPortType.name()));
            }
            if (StringUtils.isNotBlank(owner)) {
                sql.VALUES("owner", SqlUtils.addSqlStr(owner));
            }
            if (StringUtils.isNotBlank(pageId)) {
                sql.VALUES("page_id", SqlUtils.addSqlStr(pageId));
            }
            if (null != checkpoint) {
                sql.VALUES("is_checkpoint", (checkpoint ? 1 : 0) + "");
            }
            if (null != flow) {
                String flowId = flow.getId();
                if (StringUtils.isNotBlank(flowId)) {
                    sql.VALUES("FK_FLOW_ID", SqlUtils.addSqlStr(flowId));
                }
            }

            sqlStr = sql.toString();
        }
        return sqlStr;
    }

    /**
     * 插入list<Stops> 注意拼sql的方法必须用map接 Param内容为键值
     *
     * @param map (内容： 键为stopsList,值为List<Stops>)
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public String addStopsList(Map map) {
        List<Stops> stopsList = (List<Stops>) map.get("stopsList");
        StringBuffer sql = new StringBuffer();
        if (null != stopsList && stopsList.size() > 0) {
            sql.append("insert into ");
            sql.append("flow_stops ");
            sql.append("(");
            sql.append("id,");
            sql.append("crt_dttm,");
            sql.append("crt_user,");
            sql.append("last_update_dttm,");
            sql.append("last_update_user,");
            sql.append("version,");
            sql.append("enable_flag,");
            sql.append("bundel,");
            sql.append("description,");
            sql.append("groups,");
            sql.append("name,");
            sql.append("inports,");
            sql.append("in_port_type,");
            sql.append("outports,");
            sql.append("out_port_type,");
            sql.append("owner,");
            sql.append("page_id,");
            sql.append("is_checkpoint,");
            sql.append("fk_flow_id");
            sql.append(") ");
            sql.append("values");
            int i = 0;
            for (Stops stops : stopsList) {
                i++;
                String id = stops.getId();
                Date crtDttm = stops.getCrtDttm();
                String crtUser = stops.getCrtUser();
                Date lastUpdateDttm = stops.getLastUpdateDttm();
                String lastUpdateUser = stops.getLastUpdateUser();
                Long version = stops.getVersion();
                Boolean enableFlag = stops.getEnableFlag();
                String bundel = stops.getBundel();
                String description = stops.getDescription();
                String groups = stops.getGroups();
                String name = stops.getName();
                String inports = stops.getInports();
                PortType inPortType = stops.getInPortType();
                String outports = stops.getOutports();
                PortType outPortType = stops.getOutPortType();
                String owner = stops.getOwner();
                String pageId = stops.getPageId();
                Boolean checkpoint = stops.getCheckpoint();
                Flow flow = stops.getFlow();
                String flowId = "";
                if (null != flow) {
                    if (StringUtils.isNotBlank(flow.getId())) {
                        flowId = flow.getId();
                    }
                }

                sql.append("(");

                //先处理修改必填字段
                sql.append(SqlUtils.addSqlStr((id == null ? "" : id)) + ",");
                sql.append(SqlUtils.addSqlStr((crtDttm == null ? DateUtils.dateTimesToStr(new Date()) : DateUtils.dateTimesToStr(crtDttm))) + ",");
                sql.append(SqlUtils.addSqlStr((crtUser == null ? "-1" : crtUser)) + ",");
                sql.append(SqlUtils.addSqlStr((lastUpdateDttm == null ? DateUtils.dateTimesToStr(new Date()) : DateUtils.dateTimesToStr(lastUpdateDttm))) + ",");
                sql.append(SqlUtils.addSqlStr((lastUpdateUser == null ? "-1" : lastUpdateUser)) + ",");
                sql.append((version == null ? 0 : version) + ",");
                sql.append((enableFlag == null ? 1 : (enableFlag ? 1 : 0)) + ",");

                // 处理其他字段
                sql.append(SqlUtils.addSqlStr((bundel == null ? "" : bundel)) + ",");
                sql.append(SqlUtils.addSqlStr((description == null ? "" : description)) + ",");
                sql.append(SqlUtils.addSqlStr((groups == null ? "" : groups)) + ",");
                sql.append(SqlUtils.addSqlStr((name == null ? "" : name)) + ",");
                sql.append(SqlUtils.addSqlStr((inports == null ? "" : inports)) + ",");
                sql.append(SqlUtils.addSqlStr((inPortType == null ? "" : inPortType.name())) + ",");
                sql.append(SqlUtils.addSqlStr((outports == null ? "" : outports)) + ",");
                sql.append(SqlUtils.addSqlStr((outPortType == null ? "" : outPortType.name())) + ",");
                sql.append(SqlUtils.addSqlStr((owner == null ? "" : owner)) + ",");
                sql.append(SqlUtils.addSqlStr((pageId == null ? "" : pageId)) + ",");
                sql.append((checkpoint == null ? 0 : (checkpoint ? 1 : 0)) + ",");
                sql.append(SqlUtils.addSqlStr((flowId == null ? "" : flowId)));
                if (i != stopsList.size()) {
                    sql.append("),");
                } else {
                    sql.append(")");
                }
            }
            sql.append(";");
        }
        return sql.toString();
    }

    /**
     * 修改stops
     *
     * @param stops
     * @return
     */
    public String updateStops(Stops stops) {
        String sqlStr = "";
        if (null != stops) {
            String id = stops.getId();
            Date lastUpdateDttm = stops.getLastUpdateDttm();
            String lastUpdateUser = stops.getLastUpdateUser();
            Long version = stops.getVersion();
            Boolean enableFlag = stops.getEnableFlag();
            String bundel = stops.getBundel();
            String description = stops.getDescription();
            String groups = stops.getGroups();
            String name = stops.getName();
            String inports = stops.getInports();
            PortType inPortType = stops.getInPortType();
            String outports = stops.getOutports();
            PortType outPortType = stops.getOutPortType();
            String owner = stops.getOwner();
            Boolean checkpoint = stops.getCheckpoint();
            SQL sql = new SQL();

            sql.UPDATE("flow_stops");

            //先处理修改必填字段
            if (null == lastUpdateDttm) {
                lastUpdateDttm = new Date();
            }
            if (StringUtils.isBlank(lastUpdateUser)) {
                lastUpdateUser = "-1";
            }
            if (null == version) {
                version = 0L;
            }
            sql.SET("LAST_UPDATE_DTTM = " + SqlUtils.addSqlStr(DateUtils.dateTimesToStr(lastUpdateDttm)));
            sql.SET("LAST_UPDATE_USER = " + SqlUtils.addSqlStr(lastUpdateUser));
            sql.SET("VERSION = " + (version + 1));

            // 处理其他字段
            if (null != enableFlag) {
                sql.SET("ENABLE_FLAG = " + (enableFlag ? 1 : 0));
            }
            if (StringUtils.isNotBlank(bundel)) {
                sql.SET("bundel = " + SqlUtils.addSqlStr(bundel));
            }
            if (StringUtils.isNotBlank(description)) {
                sql.SET("description = " + SqlUtils.addSqlStr(description));
            }
            if (StringUtils.isNotBlank(groups)) {
                sql.SET("groups = " + SqlUtils.addSqlStr(groups));
            }
            if (StringUtils.isNotBlank(name)) {
                sql.SET("name = " + SqlUtils.addSqlStr(name));
            }
            if (StringUtils.isNotBlank(inports)) {
                sql.SET("inports = " + SqlUtils.addSqlStr(inports));
            }
            if (null != inPortType) {
                sql.SET("in_port_type = " + SqlUtils.addSqlStr(inPortType.name()));
            }
            if (StringUtils.isNotBlank(outports)) {
                sql.SET("outports = " + SqlUtils.addSqlStr(outports));
            }
            if (null != outPortType) {
                sql.SET("out_port_type = " + SqlUtils.addSqlStr(outPortType.name()));
            }
            if (StringUtils.isNotBlank(owner)) {
                sql.SET("owner = " + SqlUtils.addSqlStr(owner));
            }
            if (null != checkpoint) {
                int checkpointInt = checkpoint ? 1 : 0;
                sql.SET("is_checkpoint = " + checkpointInt);
            }
            sql.WHERE("VERSION = " + version);
            sql.WHERE("id = " + SqlUtils.addSqlStr(id));
            sqlStr = sql.toString();
            if (StringUtils.isBlank(id)) {
                sqlStr = "";
            }
        }
        return sqlStr;
    }

    /**
     * 查询所有的stops数据
     *
     * @return
     */
    public String getStopsAll() {
        String sqlStr = "";
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("flow_stops");
        sql.WHERE("enable_flag = 1");
        sqlStr = sql.toString();
        return sqlStr;
    }

    /**
     * 根据flowId查询StopsList
     *
     * @param flowId
     * @return
     */
    public String getStopsListByFlowId(String flowId) {
        String sqlStr = "";
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("flow_stops");
        sql.WHERE("enable_flag = 1");
        sql.WHERE("fk_flow_id = " + SqlUtils.addSqlStr(flowId));
        sqlStr = sql.toString();
        return sqlStr;
    }

    /**
     * 根据flowId查询StopsList
     *
     * @param map
     * @return
     */
    public String getStopsListByFlowIdAndPageIds(Map map) {
        String flowId = (String) map.get("flowId");
        String[] pageIds = (String[]) map.get("pageIds");
        String sqlStr = "";
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("flow_stops");
        sql.WHERE("enable_flag = 1");
        sql.WHERE("fk_flow_id = " + SqlUtils.addSqlStr(flowId));
        if (null != pageIds && pageIds.length > 0) {
            StringBuffer pageIdsStr = new StringBuffer();
            pageIdsStr.append("( ");
            for (int i = 0; i < pageIds.length; i++) {
                String pageId = pageIds[i];
                pageIdsStr.append("page_id = " + SqlUtils.addSqlStr(pageId));
                if (i < pageIds.length - 1) {
                    pageIdsStr.append(" OR ");
                }
            }
            pageIdsStr.append(")");
            sql.WHERE(pageIdsStr.toString());
        }
        sqlStr = sql.toString();
        return sqlStr;
    }

    /**
     * 根据stopsId查询
     *
     * @param Id
     * @return
     */
    public String getStopsById(String Id) {
        String sqlStr = "";
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("flow_stops");
        sql.WHERE("enable_flag = 1");
        sql.WHERE("id = " + SqlUtils.addSqlStr(Id));
        sqlStr = sql.toString();
        return sqlStr;
    }

    /**
     * 根据flowId和name修改stops状态信息
     *
     * @param stopVo
     * @return
     */
    public String updateStopsByFlowIdAndName(ThirdFlowInfoStopVo stopVo) {
        String sqlStr = "";
        SQL sql = new SQL();
        sql.UPDATE("flow_stops");
        Date endTime = DateUtils.strCstToDate(stopVo.getEndTime());
        Date startTime = DateUtils.strCstToDate(stopVo.getStartTime());
        String name = stopVo.getName();
        String state = stopVo.getState();
        String flowId = stopVo.getFlowId();
        if (null != endTime) {
            sql.SET("stop_time = " + SqlUtils.addSqlStr(DateUtils.dateTimeToStr(endTime)));
        }
        if (StringUtils.isNotBlank(state)) {
            sql.SET("state = " + SqlUtils.addSqlStr(state));
        }
        if (null != startTime) {
            sql.SET("start_time = " + SqlUtils.addSqlStr(DateUtils.dateTimeToStr(startTime)));
        }
        sql.WHERE("fk_flow_id = " + SqlUtils.addSqlStr(flowId));
        sql.WHERE("name = " + SqlUtils.addSqlStr(name));
        sqlStr = sql.toString();
        return sqlStr;
    }
    
    
    public String updateEnableFlagByFlowId(String flowId) {
   	 UserVo user = SessionUserUtil.getCurrentUser();
        String username = (null != user) ? user.getUsername() : "-1";
        String sqlStr = "select 0";
       if (StringUtils.isNotBlank(flowId)) {
           SQL sql = new SQL();
           sql.UPDATE("flow_stops");
           sql.SET("ENABLE_FLAG = 0");
           sql.SET("last_update_user = " + SqlUtils.addSqlStr(username) );
           sql.SET("last_update_dttm = " + SqlUtils.addSqlStr(DateUtils.dateTimesToStr(new Date())) );
           sql.WHERE("ENABLE_FLAG = 1");
           sql.WHERE("fk_flow_id = " + SqlUtils.addSqlStrAndReplace(flowId));

           sqlStr = sql.toString();
       }
       return sqlStr;
   }

}
