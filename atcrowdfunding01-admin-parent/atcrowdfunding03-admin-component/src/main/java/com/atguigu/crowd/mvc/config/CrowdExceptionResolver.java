package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUseException;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.exception.RemoveMenuFailedException;
import com.atguigu.crowd.utils.CrowdUtil;
import com.atguigu.crowd.utils.ResultEntity;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author linlingde
 * @version 1.0
 * @className CrowdExceptionResolver
 * @description 异常处理
 * @date 2022/7/9 11:25
 **/

// @ControllerAdvice:表示当前是一个基于注解的异常处理类
@ControllerAdvice
public class CrowdExceptionResolver {


    // 处理数学异常
    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView resolveMathException(ArithmeticException e,
                                             HttpServletRequest request,
                                             HttpServletResponse response)
            throws IOException {
        ModelAndView modelAndView = commonResolve(e, request, response, "system-error");
        if (modelAndView == null)
            return null;
        return modelAndView;

    }

    @ExceptionHandler(value = RemoveMenuFailedException.class)
    public ResponseEntity resolveRemoveMenuFailedException(RemoveMenuFailedException e,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response)
            throws IOException {

        commonResolve(e, request, response, null);
        return null;
    }

    // 处理NullPointException
    // @ExceptionHandler:将一个具体的异常类型与一段java程序关联起来
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointException(NullPointerException e,
                                                  HttpServletRequest request,
                                                  HttpServletResponse response)
            throws IOException {

        String name = "system-error";
        ModelAndView modelAndView = commonResolve(e, request, response, name);
        if (modelAndView == null) return null;

        // 9. 返回ModelAndView
        return modelAndView;

    }


    // @ExceptionHandler:将一个具体的异常类型与一段java程序关联起来
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(LoginFailedException e,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response)
            throws IOException {


        String name = "admin-login";
        ModelAndView modelAndView = commonResolve(e, request, response, name);
        if (modelAndView == null) return null;

        // 9. 返回ModelAndView
        return modelAndView;

    }

    @ExceptionHandler(value = LoginAcctAlreadyInUseForUpdateException.class)
    public ModelAndView resolveLoginFailedException(LoginAcctAlreadyInUseForUpdateException e,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response)
            throws IOException {


        String name = "system-error";
        ModelAndView modelAndView = commonResolve(e, request, response, name);
        if (modelAndView == null) return null;

        // 9. 返回ModelAndView
        return modelAndView;

    }

    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUse(LoginAcctAlreadyInUseException e,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response)
            throws IOException {


        String name = "admin-add";
        ModelAndView modelAndView = commonResolve(e, request, response, name);
        if (modelAndView == null) return null;

        // 9. 返回ModelAndView
        return modelAndView;

    }

    /**
     * @param e        :异常
     * @param request  :请求
     * @param response :响应
     * @param viewName :要跳转的视图名称
     * @return ViewAndModel
     * @throws IOException
     */
    private static ModelAndView commonResolve(Exception e,
                                              HttpServletRequest request,
                                              HttpServletResponse response,
                                              String viewName)
            throws IOException {
        // 1. 判断当前请求的类型
        boolean judgeRequestType = CrowdUtil.judgeRequestType(request);

        // 2. 如果是Ajax请求
        if (judgeRequestType) {
            // 3. 创建resultEntity
            ResultEntity<Object> resultEntity = ResultEntity.failed(e.getMessage());
            // 4. 将resultEntity转换为json
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            // 5. 使用Response将信息返回
            response.getWriter().write(json);
            return null;
        }
        // 6. 如果不是Ajax请求,创建ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // 7. 将Exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, e);
        // 8. 设置对应的视图名称
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

}
