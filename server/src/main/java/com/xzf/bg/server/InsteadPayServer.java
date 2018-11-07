package com.xzf.bg.server;

import com.xzf.bg.dto.InsteadNameDto;
import com.xzf.bg.entity.InsteadInfo;
import com.xzf.bg.dto.InsteadPayDto;
import com.xzf.bg.entity.TInstead;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
@Repository("insteadPayServer")
public interface InsteadPayServer {
    /**
     * 解析excel表
     *  用户上传的代付信息
     * @param request
     * @return
     */
    InsteadNameDto parsExcel(HttpServletRequest request);

    /**
     * 根据cpId得到返回给
     * @param cpId
     * @return
     */
    List<InsteadInfo> getInsteadInfoByCp(String cpId);
    /**
     * 处理用户提交的Excel内容
     * 判断金额是否大于用户可提现金额
     * 并把体现金额从资金内减去
     * 正常，返回提交成功
     * 余额不足
     */
    String makeInstead(List<InsteadPayDto> dtos,String cpId,String instead);
    /**
     * 查看提现记录
     */
    List<TInstead> get(String cpId);
    /**
     * 下载用户提交的excel表
     */
    void install(HttpServletRequest request, HttpServletResponse response,String insteadId) throws IOException;
    /**
     * 审核更改用户状态,
     * 状态为拒绝的话，返回资金
     */
    void edit(String insteadId, Integer status,String cpId,Double money);
}