package quinlan.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xingxf
 * @date 2021年09月07日 15:47:42
 * @description 字典类的注解
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dictionary {
    //字典表
    String tableName() default "dict";
    //代号列名
    String codeColName() default "code";
    //描述列名
    String textColName() default "text";
    //代号值
    String codeColValue() default "";
    //数字字典名
    String dictName();
}
