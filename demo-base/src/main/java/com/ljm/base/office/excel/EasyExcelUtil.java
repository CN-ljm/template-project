package com.ljm.base.office.excel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Created by liangjiaming on 2021/1/22
 * @title
 * @Desc
 */
public class EasyExcelUtil {

    /**
     * 降数据导成Excel文件
     *
     * @param fileOutputStream 文件输出流
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param sheetName sheet名
     * @param clas      映射实体类，Excel 模型
     */
    public static void writeExcelToFile(FileOutputStream fileOutputStream, final List<? extends BaseRowModel> list,
                                        final String sheetName, final Class<? extends BaseRowModel> clas) {
        final ExcelWriter writer = new ExcelWriter(fileOutputStream, ExcelTypeEnum.XLSX);
        final Sheet sheet = new Sheet(1, 0, clas);
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        writer.finish();
    }

}
