package quinlan.aspect;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import quinlan.annotation.Dictionary;
import quinlan.utils.AnnotationGetter;
import quinlan.utils.Result;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xingxf
 * @date 2021年09月09日 13:50:41
 * @description 字典aop,未使用分页插件版本。
 */
@Slf4j
@Aspect
@Component
public class DictAspect {

    @Pointcut("execution(public * quinlan..*.*Controller.*(..))")
    public void pointCutPosition(){}

    @Around("pointCutPosition()")
    public Object afterDo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long proStart = System.currentTimeMillis();
        //重要：控制切点代码执行，在这里return了，前端就收不到了，需要手动return个新结果给前端。
        Object result = proceedingJoinPoint.proceed();
        long proEnd = System.currentTimeMillis();
        log.info("procceding execute time is " + (proEnd-proStart)+"ms");
        long paraseStart = System.currentTimeMillis();
        this.convertCodeToText(result);
        long paraseEnd = System.currentTimeMillis();
        log.info("convert execute time is " + (paraseEnd-paraseStart)+"ms");
        return result;
    }

    public void convertCodeToText(Object result){
        //根据Result模板类修改
        if(result instanceof Result){
            //如果使用Ipage&PageInfo，可以加一层判断。
            Object listResult = ((Result<?>) result).getResult();
            if(listResult instanceof List){
                List<Object> list = (List<Object>) listResult;
                ArrayList<Object> recordList = Lists.newArrayListWithCapacity(list.size());
                list.forEach(
                    record->{
                        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(record);
                        //获取注解的工具
                        List<Field> annoyFields = AnnotationGetter.getHasAnnoFields(record, Dictionary.class);
                        annoyFields.stream().forEach(field -> {
                            Dictionary dict = field.getAnnotation(Dictionary.class);
                            //注解codeColValue值
                            String codeColValue = dict.codeColValue();
                            if(StringUtils.isEmpty(codeColValue)){
                                field.setAccessible(true);
                                try {
                                    //字典属性值为null,不进行映射操作。
                                    if(Objects.isNull(field.get(record))){
                                        return;
                                    }
                                    codeColValue = field.get(record).toString();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                            String textValue = this.getTextByCode(
                                    dict.tableName(),
                                    dict.dictName(),
                                    dict.codeColName(),
                                    codeColValue,
                                    dict.textColName());
                            jsonObject.put(field.getName()+"_dict",textValue);
                        });
                        recordList.add(jsonObject);
                    }
                );
                list.clear();
                list.addAll(recordList);
                log.info(((Result<?>) result).getResult().toString());
            }
        }
    }

    /**
     * 查询数据库
     * @param tableName
     * @param dictName
     * @param codeColName
     * @param codeColValue
     * @param textColName
     * @return
     */
    private String getTextByCode(
            String tableName,
            String dictName,
            String codeColName,
            String codeColValue,
            String textColName){

        //查询字典表
        return "字典映射值";
    }

}
