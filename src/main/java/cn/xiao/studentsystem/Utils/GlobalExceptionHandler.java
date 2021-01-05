package cn.xiao.studentsystem.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"cn.xiao.studentsystem.Controller","cn.xiao.studentsystem.Service"})
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自定义 ServiceException 异常，经常用到
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        logger.debug("[serviceExceptionHandler]", ex);
        // 包装 CommonResult 结果
        return CommonResult.error(ex.getCode(), ex.getMessage(),ex.getData());
    }
    //建议不要处理其他非自定义异常，捕获其他异常你又自定义不了多少，还是直接在业务里抛出制作成自定义比较好。————系统异常发生了也不用你捕获，应该是硬件错误，那个你也处理不了。

}