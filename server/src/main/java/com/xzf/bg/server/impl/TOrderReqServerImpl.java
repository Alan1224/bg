package com.xzf.bg.server.impl;

import com.xzf.bg.dao.TCpInfoDao;
import com.xzf.bg.dao.TOrderReqDao;
import com.xzf.bg.dto.TOderReq;
import com.xzf.bg.entity.RateTime;
import com.xzf.bg.entity.Result;
import com.xzf.bg.entity.TOrderReq;
import com.xzf.bg.server.TOrderReqServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.*;

@Service("tOrderReqServer")
public class TOrderReqServerImpl implements TOrderReqServer {
//    String String.valueOf(month) = "10";
    Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH) + 1;

    @Autowired
    TCpInfoDao tCpInfoDao;
    @Autowired
    private TOrderReqDao tOrderReqDao;
    private TOrderReq tOrderReq = null;
    private Result result = new Result();
    public Result successAll(List<Result> list){

        int count = 0;
        double success = 0;
        double rate = 0;
        double fee = 0;
        for (Result r:list) {
            count += r.getCount();
            success += r.getSuccess();
            rate += Double.valueOf(r.getRate());
            fee += Double.valueOf(r.getFee());
        }
        result = new Result();
        result.setCount(count);
        rate = success / count;
        DecimalFormat df = new DecimalFormat("######0.00");
        result.setRate(df.format(rate));
        result.setFee(String.valueOf(df.format(fee)));
        result.setSuccess(success);
        result.setCpId("selectAll");
        return result;
    }

    public String feeBack(String ffid,String cpparam,String fee,String cpid) {
        String status = "1";
        String str=cpid.substring(0,cpid.length()-2);
        String key = "8b2de685acc4ef"+str+"3f147eaded49bd";
        String url = tCpInfoDao.selectByCpId(cpid).getUrl();
        //cpid,cpparam,fee,ffid
        String signValue="cpid="+cpid+"&cpparam="+cpparam+"&fee="+fee+"&ffid="+ffid+"&key="+key;
        String sign="";
        try {
            sign=md532(signValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String param="cpid="+cpid+"&cpparam="+cpparam+"&fee="+fee+"&ffid="+ffid+"&status="+status+"&sign="+sign;
        String result = sendGet(url,param);
        if (result.equals("ok")){
            //如果收到ok在数据库改status=1
            int i = tOrderReqDao.updateFeeBack(cpparam,String.valueOf(month));
            return result+i;
        }else {
            return url+"?"+param;
        }
    }

    /**还需要再获取cpid，根据cpid进行匹配对应的数据
     *
     *
     * @return 返回粗略的信息
     */
    @Override
    public List<TOderReq> rateTime(String typeId,String cpId1) {
        List<TOderReq> tOderReqs = new ArrayList<TOderReq>();
        String[] cpId;
        if ("all".equals(cpId1)){
            cpId = tOrderReqDao.selectCpId(String.valueOf(month), typeId).toArray(new String[0]);
        }else {
            cpId = cpId1.split("#");
        }
        List<RateTime> rateList30 = tOrderReqDao.selectRateTime("30",String.valueOf(month),typeId,"minute");
        List<RateTime> rateList10 = tOrderReqDao.selectRateTime("10",String.valueOf(month),typeId,"minute");
        List<RateTime> rateList60 = tOrderReqDao.selectRateTime("60",String.valueOf(month),typeId,"minute");
        List<RateTime> rateList = tOrderReqDao.selectRateTime("1",String.valueOf(month),typeId,"day");
        List<RateTime> rateListFee = tOrderReqDao.selectRateTimeFee("1",String.valueOf(month),typeId,"day");
        TOderReq tOderReqSum = new TOderReq();
        tOderReqSum.setSuccessFee(0.0);
        tOderReqSum.setCpId("selectAll");
        tOderReqSum.setName("总和");
        tOderReqSum.setSuccess(0);
        tOderReqSum.setCount(0);
        for (String cpid:cpId) {
            TOderReq tOderReq = new TOderReq();
            tOderReq.setCpId(cpid);
            for (RateTime rate:rateList30) {
                if (cpid.equals(rate.getCpId())){
                    tOderReq.setRateMinute30(rate.getSuccess()/1.00/rate.getCount());
                }
            }
            for (RateTime rate:rateList10) {
                if (cpid.equals(rate.getCpId())){
                    tOderReq.setRateMinute10(rate.getSuccess()/1.00/rate.getCount());
                }
            }
            for (RateTime rate:rateList60) {
                if (cpid.equals(rate.getCpId())){
                    tOderReq.setRateHOUR(rate.getSuccess()/1.00/rate.getCount());
                }
            }
            for (RateTime rate:rateList) {
                if (cpid.equals(rate.getCpId())){
                    tOderReq.setRate(rate.getSuccess()/1.00/rate.getCount());
                    tOderReq.setCount(rate.getCount());
                    tOderReq.setSuccess(rate.getSuccess());
                    tOderReq.setName(rate.getName());
                    tOderReqSum.setCount(tOderReqSum.getCount()+rate.getCount());
                    tOderReqSum.setSuccess(tOderReqSum.getSuccess()+rate.getSuccess());
                }
            }
            for (RateTime rate:rateListFee) {
                if (cpid.equals(rate.getCpId())){
                    tOderReq.setSuccessFee(rate.getSuccessFee() / 100.00);
                    tOderReqSum.setSuccessFee(tOderReqSum.getSuccessFee()+(rate.getSuccessFee() / 100.00));
                }
            }
            tOderReqs.add(tOderReq);
        }
        tOderReqs.add(tOderReqSum);
        return tOderReqs;
    }

    @Override
    public TOrderReq select(String cpParam,String ffId) {
        if (cpParam != null && !cpParam.equals("")){
            return tOrderReqDao.selectByCpParam(cpParam,String.valueOf(month));
        }else if (ffId != null && !ffId.equals("")){
            return tOrderReqDao.selectByFfId(ffId,String.valueOf(month));
        }
        return null;
    }

    /**
     * 详细的信息
     * @param typeId
     * @param time
     * @return 详细的信息
     */
    public List<TOderReq> rateTimeMinute(String typeId,String time,String cpId1) {
        List<TOderReq> tOderReqs = new ArrayList<TOderReq>();
        String[] cpId;
        if ("all".equals(cpId1)){
            cpId = tOrderReqDao.selectCpId(String.valueOf(month), typeId).toArray(new String[0]);
        }else {
            cpId = cpId1.split("#");
        }
        List<RateTime> rateList = tOrderReqDao.selectRateTime(time,String.valueOf(month),typeId,"minute");
        List<RateTime> rateListFee = tOrderReqDao.selectRateTimeFee(time,String.valueOf(month),typeId,"minute");
        for (String cpid:cpId) {
            TOderReq tOderReq = new TOderReq();
            tOderReq.setCpId(cpid);
            for (RateTime rate:rateList) {
                if (cpid.equals(rate.getCpId())){
                    tOderReq.setRate(rate.getSuccess()/1.00/rate.getCount());
                    tOderReq.setCount(rate.getCount());
                    tOderReq.setSuccess(rate.getSuccess());
                    tOderReq.setName(rate.getName());
                    tOderReq.setFee(rate.getFee() / 100.00);
                }
            }
            for (RateTime rate:rateListFee) {
                if (cpid.equals(rate.getCpId())){
                    tOderReq.setSuccessFee(rate.getSuccessFee() / 100.00);
                }
            }
            tOderReqs.add(tOderReq);
        }
        return tOderReqs;
    }

    public static String md532(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        //获取文本明文为字节
        md.update(text.getBytes());
        //创建字节摘要数组
        byte b[] = md.digest();
        //创建 int 类型变量i
        int i;
        //创建StringBuffer容器
        StringBuffer buf = new StringBuffer("");
        for (int j = 0; j < b.length; j++) {
            i = b[j];
            if (i < 0)
                i += 256;   //md5加密最长32位字符.一个字符占8个字节.所以最长允许256个字节的字符串
            if (i < 16)     //一个字符=8个字节 0-15不足字符俩字符则补0拼接
                buf.append("0");
            buf.append(Integer.toHexString(i));//int类型10进制转16进制
        }
        //32位加密
        return buf.toString();
    }
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            String timeout = "8000";
            connection.setConnectTimeout(Integer.parseInt(timeout));
            connection.setReadTimeout(Integer.parseInt(timeout));
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public List<Result> success(String timeType) {
        List<Double> successList = tOrderReqDao.selectSuccess(timeType,String.valueOf(month));
        List<Double> feeList = tOrderReqDao.selectFee(timeType,String.valueOf(month));
        List<Integer> countList = tOrderReqDao.selectCount(timeType,String.valueOf(month));
        List<String> cpIdList = tOrderReqDao.selectCpId(String.valueOf(month),timeType);
        List<Result> resultsList = new ArrayList<Result>();
        //a成功率是0的
        int a = 0;
        for (int i = 0; i < cpIdList.size(); i++) {
            /**
             * 解决successList少的情况方法
             * 1写一个根据cpid查看成功数是否是0的方法
             * 2计算is_success是0的个数，总数减去0得到成功数
             */
            if (tOrderReqDao.selectIsSuccess(cpIdList.get(i),timeType,String.valueOf(month)) == 0){
                a++;
            }else {
                DecimalFormat df = new DecimalFormat("######0.00");
                result.setSuccess(successList.get(i-a));
                result.setCpId(cpIdList.get(i));
                result.setFee(df.format(feeList.get(i-a) / 100.00));
                result.setCount(countList.get(i));
                result.setRate(df.format(successList.get(i-a)/countList.get(i)));
                resultsList.add(result);
                result = new Result();
            }
        }
        return resultsList;
    }
    public TOrderReq difference(String type,String id) {
        if ("CPID".equals(type)){
            tOrderReq = tOrderReqDao.selectByCpParam(id,String.valueOf(month));
        }else {
            tOrderReq = tOrderReqDao.selectByFfId(id,String.valueOf(month));
        }
        return tOrderReq;
    }
}