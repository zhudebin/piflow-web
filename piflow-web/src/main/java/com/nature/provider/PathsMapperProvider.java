package com.nature.provider;

import com.nature.base.util.DateUtils;
import com.nature.base.util.SessionUserUtil;
import com.nature.base.util.SqlUtils;
import com.nature.base.vo.UserVo;
import com.nature.component.flow.model.Flow;
import com.nature.component.flow.model.Paths;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PathsMapperProvider {

    /**
     * 插入list<Paths> 注意拼sql的方法必须用map接 Param内容为键值
     *
     * @param map (内容： 键为pathsList,值为List<Paths>)
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public String addPathsList(Map map) {
        List<Paths> pathsList = (List<Paths>) map.get("pathsList");
        StringBuffer sql = new StringBuffer();
        if (null != pathsList && pathsList.size() > 0) {
            sql.append("insert into ");
            sql.append("flow_path ");
            sql.append("(");
            sql.append("id,");
            sql.append("crt_dttm,");
            sql.append("crt_user,");
            sql.append("last_update_dttm,");
            sql.append("last_update_user,");
            sql.append("version,");
            sql.append("enable_flag,");
            sql.append("line_from,");
            sql.append("line_to,");
            sql.append("line_outport,");
            sql.append("line_inport,");
            sql.append("page_id,");
            sql.append("fk_flow_id");
            sql.append(") ");
            sql.append("values");
            int i = 0;
            for (Paths paths : pathsList) {
                i++;
                if (null != paths) {
                    String id = paths.getId();
                    Date crtDttm = paths.getCrtDttm();
                    String crtUser = paths.getCrtUser();
                    Date lastUpdateDttm = paths.getLastUpdateDttm();
                    String lastUpdateUser = paths.getLastUpdateUser();
                    Long version = paths.getVersion();
                    Boolean enableFlag = paths.getEnableFlag();
                    String from = paths.getFrom();
                    String to = paths.getTo();
                    String outport = paths.getOutport();
                    String inport = paths.getInport();
                    String pageId = paths.getPageId();
                    Flow flow = paths.getFlow();

                    sql.append("(");
                    sql.append (SqlUtils.addSqlStr((id == null ? "" : id)) + ",");
                    sql.append (SqlUtils.addSqlStr((crtDttm == null ? "" : DateUtils.dateTimesToStr(crtDttm))) + ",");
                    sql.append (SqlUtils.addSqlStr((crtUser == null ? "" : crtUser)) + ",");
                    sql.append (SqlUtils.addSqlStr((lastUpdateDttm == null ? "" : DateUtils.dateTimesToStr(lastUpdateDttm)))
                            + ",");
                    sql.append (SqlUtils.addSqlStr((lastUpdateUser == null ? "" : lastUpdateUser)) + ",");
                    sql.append((version == null ? "0" : version) + ",");
                    sql.append((enableFlag == null ? "1" : (enableFlag ? 1 : 0)) + ",");
                    sql.append (SqlUtils.addSqlStr((from == null ? "" : from)) + ",");
                    sql.append (SqlUtils.addSqlStr((to == null ? "" : to)) + ",");
                    sql.append (SqlUtils.addSqlStr((outport == null ? "" : outport)) + ",");
                    sql.append (SqlUtils.addSqlStr((inport == null ? "" : inport)) + ",");
                    sql.append (SqlUtils.addSqlStr((pageId == null ? "" : pageId)) + ",");
                    sql.append (SqlUtils.addSqlStr((flow == null ? "" : flow.getId())));
                    if (i != pathsList.size()) {
                        sql.append("),");
                    } else {
                        sql.append(")");
                    }
                }
            }
            sql.append(";");
        }
        return sql.toString();
    }

    /**
     * 新增paths
     *
     * @param paths
     * @return
     */
    public String addPaths(Paths paths) {
        String sqlStr = "";
        if (null != paths) {
            String id = paths.getId();
            Date crtDttm = paths.getCrtDttm();
            String crtUser = paths.getCrtUser();
            Date lastUpdateDttm = paths.getLastUpdateDttm();
            String lastUpdateUser = paths.getLastUpdateUser();
            Long version = paths.getVersion();
            Boolean enableFlag = paths.getEnableFlag();
            String from = paths.getFrom();
            String to = paths.getTo();
            String outport = paths.getOutport();
            String inport = paths.getInport();
            String pageId = paths.getPageId();
            Flow flow = paths.getFlow();
            SQL sql = new SQL();
            sql.INSERT_INTO("flow_path");

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
            sql.VALUES("id",  SqlUtils.addSqlStr(id));
            sql.VALUES("crt_dttm",  SqlUtils.addSqlStr(DateUtils.dateTimesToStr(crtDttm)));
            sql.VALUES("crt_user",  SqlUtils.addSqlStr(crtUser));
            sql.VALUES("last_update_dttm",  SqlUtils.addSqlStr(DateUtils.dateTimesToStr(lastUpdateDttm)));
            sql.VALUES("last_update_user",  SqlUtils.addSqlStr(lastUpdateUser));
            sql.VALUES("version", (version + 1) + "");
            sql.VALUES("ENABLE_FLAG", (enableFlag ? 1 : 0) + "");

            // 处理其他字段
            if (StringUtils.isNotBlank(from)) {
                sql.VALUES("line_from",  SqlUtils.addSqlStr(from));
            }
            if (StringUtils.isNotBlank(to)) {
                sql.VALUES("line_to",  SqlUtils.addSqlStr(to));
            }
            if (StringUtils.isNotBlank(outport)) {
                sql.VALUES("line_outport",  SqlUtils.addSqlStr(outport));
            }
            if (StringUtils.isNotBlank(inport)) {
                sql.VALUES("line_inport",  SqlUtils.addSqlStr(inport));
            }
            if (StringUtils.isNotBlank(pageId)) {
                sql.VALUES("line_port",  SqlUtils.addSqlStr(pageId));
            }
            if (null != flow) {
                String flowId = flow.getId();
                if (StringUtils.isNotBlank(flowId)) {
                    sql.VALUES("fk_flow_id",  SqlUtils.addSqlStr(flowId));
                }
            }
            sqlStr = sql.toString();

        }
        return sqlStr;
    }

    /**
     * 修改paths
     *
     * @param paths
     * @return
     */
    public String updatePaths(Paths paths) {
        String sqlStr = "";
        if (null != paths) {
            String id = paths.getId();
            Date lastUpdateDttm = paths.getLastUpdateDttm();
            String lastUpdateUser = paths.getLastUpdateUser();
            Long version = paths.getVersion();
            Boolean enableFlag = paths.getEnableFlag();
            String from = paths.getFrom();
            String to = paths.getTo();
            String outport = paths.getOutport();
            String inport = paths.getInport();
            SQL sql = new SQL();
            sql.UPDATE("flow_path");

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
            String lastUpdateDttmStr = DateUtils.dateTimesToStr(lastUpdateDttm);
            sql.SET("LAST_UPDATE_DTTM = " +  SqlUtils.addSqlStr(lastUpdateDttmStr));
            sql.SET("LAST_UPDATE_USER = " +  SqlUtils.addSqlStr(lastUpdateUser));
            sql.SET("VERSION = " + (version + 1));

            // 处理其他字段
            if (null != enableFlag) {
                sql.SET("ENABLE_FLAG = " + (enableFlag ? 1 : 0));
            }
            if (StringUtils.isNotBlank(from)) {
                sql.SET("line_from = " +  SqlUtils.addSqlStr(from));
            }
            if (StringUtils.isNotBlank(to)) {
                sql.SET("line_to = " +  SqlUtils.addSqlStr(to));
            }
            if (StringUtils.isNotBlank(outport)) {
                sql.SET("line_outport = " +  SqlUtils.addSqlStr(outport));
            }
            if (StringUtils.isNotBlank(inport)) {
                sql.SET("line_inport = " +  SqlUtils.addSqlStr(inport));
            }
            sql.WHERE("VERSION = " + version);
            sql.WHERE("id = " +  SqlUtils.addSqlStr(id));
            sqlStr = sql.toString();

        }
        return sqlStr;
    }

    /**
     * 根据flowId查询
     *
     * @param flowId
     * @return
     */
    public String getPathsListByFlowId(String flowId) {
        String sqlStr = "";
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("flow_path");
        sql.WHERE("enable_flag = 1");
        sql.WHERE("fk_flow_id = " +  SqlUtils.addSqlStr(flowId));
        sqlStr = sql.toString();
        return sqlStr;
    }

    /**
     * 查询连线信息
     *
     * @param flowId
     * @param pageId
     * @param from
     * @param to
     * @return
     */
    public String getPaths(String flowId, String pageId, String from, String to) {
        String sqlStr = "";
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("flow_path");
        sql.WHERE("enable_flag = 1");
        if (StringUtils.isNotBlank(flowId)) {
            sql.WHERE("fk_flow_id = " +  SqlUtils.addSqlStr(flowId));
        }
        if (StringUtils.isNotBlank(pageId)) {
            sql.WHERE("page_id = " +  SqlUtils.addSqlStr(pageId));
        }
        if (StringUtils.isNotBlank(from)) {
            sql.WHERE("line_from = " +  SqlUtils.addSqlStr(from));
        }
        if (StringUtils.isNotBlank(to)) {
            sql.WHERE("line_to = " +  SqlUtils.addSqlStr(to));
        }
        sqlStr = sql.toString();
        return sqlStr;
    }

    /**
     * 查询连线的数量
     *
     * @param flowId
     * @param pageId
     * @param from
     * @param to
     * @return
     */
    public String getPathsCounts(String flowId, String pageId, String from, String to) {
        String sqlStr = "";
        SQL sql = new SQL();
        sql.SELECT("count(id)");
        sql.FROM("flow_path");
        sql.WHERE("enable_flag = 1");
        if (StringUtils.isNotBlank(flowId)) {
            sql.WHERE("fk_flow_id = " +  SqlUtils.addSqlStr(flowId));
        }
        if (StringUtils.isNotBlank(pageId)) {
            sql.WHERE("page_id = " +  SqlUtils.addSqlStr(pageId));
        }
        if (StringUtils.isNotBlank(from)) {
            sql.WHERE("line_from = " +  SqlUtils.addSqlStr(from));
        }
        if (StringUtils.isNotBlank(to)) {
            sql.WHERE("line_to = " +  SqlUtils.addSqlStr(to));
        }
        sqlStr = sql.toString();
        return sqlStr;
    }

    /**
     * 根据id查询paths
     *
     * @param id
     * @return
     */
    public String getPathsById(String id) {
        String sqlStr = "select 0";
        if (StringUtils.isNotBlank(id)) {
            SQL sql = new SQL();
            sql.SELECT("*");
            sql.FROM("flow_path");
            sql.WHERE("enable_flag = 1");
            sql.WHERE("id=" +  SqlUtils.addSqlStr(id));
            sqlStr = sql.toString();
        }
        return sqlStr;
    }
    
    
    /**
     * 根据flowId逻辑删除,设为无效
     * @param flowId
     * @return
     */
    public String updateEnableFlagByFlowId(String flowId) {
      	 UserVo user = SessionUserUtil.getCurrentUser();
           String username = (null != user) ? user.getUsername() : "-1";
           String sqlStr = "select 0";
          if (StringUtils.isNotBlank(flowId)) {
              SQL sql = new SQL();
              sql.UPDATE("flow_path");
              sql.SET("ENABLE_FLAG = 0");
              sql.SET("last_update_user = " +  SqlUtils.addSqlStr(username) );
              sql.SET("last_update_dttm = " +  SqlUtils.addSqlStr(DateUtils.dateTimesToStr(new Date())) );
              sql.WHERE("ENABLE_FLAG = 1");
              sql.WHERE("fk_flow_id = " +  SqlUtils.addSqlStrAndReplace(flowId));

              sqlStr = sql.toString();
          }
          return sqlStr;
      }
}
