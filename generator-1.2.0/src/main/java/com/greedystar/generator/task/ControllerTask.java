package com.greedystar.generator.task;

import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.task.base.BaseTask;
import com.greedystar.generator.utils.ConfigUtil;
import com.greedystar.generator.utils.FileUtil;
import com.greedystar.generator.utils.FreemarketConfigUtils;
import com.greedystar.generator.utils.StringUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author GreedyStar
 * Date   2018/4/20
 */
public class ControllerTask extends BaseTask {

    public ControllerTask(String className) {
        super(className);
    }
    public ControllerTask(String className,List<ColumnInfo> infos ){
        super(className, null, null, null, infos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Controller填充数据
        System.out.println("Generating " + className + "Controller.java");
        Map<String, String> controllerData = new HashMap<>();
        controllerData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        controllerData.put("ControllerPackageName", ConfigUtil.getConfiguration().getPath().getController());
        controllerData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getService());
        controllerData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        controllerData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        controllerData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        controllerData.put("ClassName", className);
        controllerData.put("EntityName", StringUtil.firstToLowerCase(className));
        controllerData.put("PrimaryKey", getPrimaryKeyColumnInfo(tableInfos).getColumnName());
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getController());
        String fileName = className + "Controller.java";
        // 生成Controller文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_CONTROLLER, controllerData, filePath + fileName);
    }
    private ColumnInfo getPrimaryKeyColumnInfo(List<ColumnInfo> list) {
        for (ColumnInfo columnInfo : list) {
            if (columnInfo.isPrimaryKey()) {
                return columnInfo;
            }
        }
        return null;
    }
}
