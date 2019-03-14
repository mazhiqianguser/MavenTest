package cn.com.test.user.aop;

/**
 * 自定义注解 
 * @author mazhiqiang
 * 2017-08-07
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    String function_id();
    String operate_type();
    String operate_content();
}
