package com.xzf.bg.server.impl;

import com.xzf.bg.dao.TCpInfoDao;
import com.xzf.bg.dao.TInsteadDao;
import com.xzf.bg.dao.TOrderReqDao;
import com.xzf.bg.dto.InsteadNameDto;
import com.xzf.bg.dto.InsteadPayDto;
import com.xzf.bg.entity.InsteadInfo;
import com.xzf.bg.entity.TCpInfo;
import com.xzf.bg.entity.TInstead;
import com.xzf.bg.server.InsteadPayServer;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service("insteadPayServer")
public class InsteadPayServerImpl implements InsteadPayServer {
    Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH) + 1;
    @Autowired
    private TCpInfoDao tCpInfoDao;
    @Autowired
    private TInsteadDao tInsteadDao;
    @Autowired
    private TOrderReqDao tOrderReqDao;
    @Override
    public InsteadNameDto parsExcel(HttpServletRequest request) {
        InsteadNameDto nameDto = new InsteadNameDto();
        List<InsteadPayDto> result = new ArrayList<InsteadPayDto>();
        InsteadPayDto instead = new InsteadPayDto();
        //        判断表单类型
//        是上传文件

//            创建对象
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
//            设置编码
        upload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem f:fileItems) {
                if (f.isFormField()){
                    System.out.println("不是文件类型");
                }else {

                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                    String name = df.format(new Date());

                    nameDto.setName(name);
                    f.write(new File("/root/update/"+ name +".xls"));
                    Workbook workbook = WorkbookFactory.create(new File("/root/update/" + name + ".xls"));
                    Sheet s = workbook.getSheet("测试POI导出EXCEL文档");
                    int rowNum = s.getLastRowNum();
                    for (int i = 1; i < rowNum; i++) {
                        Row r = s.getRow(i);
                        if (r.getCell(0) != null)
                            instead.setNamePay(r.getCell(0).getStringCellValue());
                        if (r.getCell(1) != null)
                            instead.setCar(r.getCell(1).getStringCellValue());
                        if (r.getCell(2) != null)
                            instead.setMoney(r.getCell(2).getNumericCellValue());
                        if (r.getCell(3) != null)
                            instead.setNameBank(r.getCell(3).getStringCellValue());
                        if (r.getCell(4) != null)
                            instead.setBankAddress(r.getCell(4).getStringCellValue());
                        result.add(instead);
                        instead = new InsteadPayDto();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameDto.setList(result);
        return nameDto;
    }

    @Override
    public List<InsteadInfo> getInsteadInfoByCp(String cpId1){
        String[] c;
        if ("all".equals(cpId1)){
            c = tOrderReqDao.selectCpId(String.valueOf(month), "cp_id").toArray(new String[0]);
        }else {
            c = cpId1.split("#");
        }
        List<InsteadInfo> list = new ArrayList<InsteadInfo>();
        TCpInfo cpInfo;
        for (int i = 0; i <c.length ; i++) {
            if (c != null && !c.equals("")){
                cpInfo = tCpInfoDao.selectInstead(c[i]);
                InsteadInfo insteadInfo = new InsteadInfo();
                insteadInfo.setCpId(c[i]);
                insteadInfo.setDealMoney(cpInfo.getMoney());
                insteadInfo.setRate(cpInfo.getRate());
                insteadInfo.setWithdraw(cpInfo.getMoney() * (1 - cpInfo.getRate()));
                list.add(insteadInfo);
            }
        }
        return list;
    }

    @Override
    public String makeInstead(List<InsteadPayDto> dtos,String cpId,String insteadId) {
        double m = 0;
        for (InsteadPayDto i:dtos){
            m = i.getMoney() + m;
        }
        TCpInfo instead = tCpInfoDao.selectInstead(cpId);
        Double withdraw = instead.getMoney();
        Double rate = instead.getRate();
        //可提现金额大于提交金额，可以继续
        if (withdraw * (1-rate) >= m  * 100){
            //把cp资金减去m
            tCpInfoDao.updateInstead(cpId,m / (1-rate) * 100);
            //增加一条提现记录
            TInstead tInstead = new TInstead();
            tInstead.setInsteadId(insteadId);
            tInstead.setCpId(cpId);
            tInstead.setMoney(m);
            tInstead.setStatus(0);
            tInstead.setCreateTime(new Date());
            tInstead.setUpdateTime(new Date());
            tInsteadDao.insert(tInstead);
            return "success";
        }
        return "fail";
    }

    @Override
    public List<TInstead> get(String cpId1) {
        String[] c;
        if ("all".equals(cpId1)){
            c = tOrderReqDao.selectCpId(String.valueOf(month), "cp_id").toArray(new String[0]);
        }else {
            c = cpId1.split("#");
        }
        List<TInstead> list = new ArrayList<TInstead>();
        for (String cpId:c) {
            List<TInstead> tInsteads = tInsteadDao.select(cpId);
            if (tInsteads != null)
            for (int i = 0; i < tInsteads.size(); i++) {
                list.add(tInsteadDao.select(cpId).get(i));
            }
        }
        return list;
    }

    @Override
    public void install(HttpServletRequest request, HttpServletResponse response, String insteadId) throws IOException {
        response.setCharacterEncoding("UTF-8");
        //设置ContentType字段值
        response.setContentType("text/html;charset=utf-8");
        //获取所要下载的文件名称
        response.setHeader("content-disposition",
                "attachment;filename=" + URLEncoder.encode(insteadId + ".xls", "utf-8"));
        // 读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream("/root/update" + "/" + insteadId + ".xls");
        // 创建输出流
        OutputStream out = response.getOutputStream();
        // 创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        // 循环将输入流中的内容读取到缓冲区中
        while ((len = in.read(buffer)) > 0) {
            // 输出缓冲区内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        // 关闭文件流
        in.close();
        // 关闭输出流
        out.close();
    }

    @Override
    public void edit(String insteadId, Integer status,String cpId,Double money) {
        tInsteadDao.update(insteadId,status);
        if (status == 2){
            tCpInfoDao.updateInsteadAdd(cpId,money);
        }
    }
}