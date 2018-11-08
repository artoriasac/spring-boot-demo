package com.test.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.exception.excel.ExcelExportException;
import com.common.model.EasyPoiTestModel;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EasypoiTest {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("D:\\test.xlsx");

        EasyPoiTestModel userLog1 = new EasyPoiTestModel("悄立市桥人不识", "一星如月看多时");
        EasyPoiTestModel userLog2 = new EasyPoiTestModel("醉后不知天在水", "满船清梦压星河");
        EasyPoiTestModel userLog3 = new EasyPoiTestModel("睡起莞尔成独笑", "数声渔笛在沧浪");
        EasyPoiTestModel userLog4 = new EasyPoiTestModel("我心匪石情难转", "志夺秋霜意不移");

        List<EasyPoiTestModel> data = new ArrayList<>();
        data.add(userLog1);
        data.add(userLog2);
        data.add(userLog3);
        data.add(userLog4);

        Workbook workbook= exportExcel("artorias", "DARK SOUL", true, EasyPoiTestModel.class, data);
        workbook.write(fos);
        workbook.close();
    }


    public static Workbook exportExcel(String title, String sheetName, boolean isCreateHeader,
                                   Class<?> pojoClass, List<?> data
    ) throws ExcelExportException {

        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);

        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, data);
        return workbook;
    }

}


