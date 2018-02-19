package com.book.console.web.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.book.console.web.support.CurrentUserMethodArgumentResolver;
import com.book.console.web.support.PtInterceptor;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhengfeng on 2016-07-21 11:50.
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    private PtInterceptor interceptor;

    /**
     * 静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/app/");
    }

    /**
     * 参数resolver
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserMethodArgumentResolver());
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        super.addFormatters(registry);
    }

    /**
     * 国际化
     * @return
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }

    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
        messageBundle.setBasename("classpath:conf/messages/messages");
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }

    /**
     * 非json形式表单提交日期转换
     * 时间戳转date
     */
    private static class StringToDateConverter implements Converter<String, Date> {

        @Override
        public Date convert(String source) {
            if(StringUtils.isBlank(source))
                return null;
            return new Date(Long.valueOf(source));
        }
    }
}
