package quinlan.utils;

import com.google.common.collect.Lists;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author xingxf
 * @date 2021年09月10日 11:16:25
 * @description 注解获取工具类
 */
public class AnnotationGetter {

     private Object obj;

     private AnnotationGetter(Object obj){
         this.obj = obj;
     }

    /**
     * 初始化实例，同时给成员变量obj赋值。
     * @param clazz
     */
    public static AnnotationGetter GetAnnoUtil(Class<?> clazz){
         return new AnnotationGetter(clazz);
    }

    /**
     * 返回一个装载了目标Filed的AnnotationGetter对象
     * @param name
     * @return
     */
    public AnnotationGetter field(String name){
        Field declaredField = null;
        if(this.obj instanceof Class){
            Class clazz = (Class) this.obj;
            try {
                 declaredField = clazz.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return new AnnotationGetter(declaredField);
    }

    /**
     * 返回一个装载目标Annotaion的AnnotationGetter对象
     * @param annotationClazz
     * @return
     */
    public AnnotationGetter annotation(Class<? extends Annotation> annotationClazz){
        Annotation annotation = null;
        //AnnotatedElement: 被注解修饰的Field、Method、Class。
        if(this.obj instanceof AnnotatedElement){
            AnnotatedElement annotatedElement = (AnnotatedElement) this.obj;
            annotation = annotatedElement.getAnnotation(annotationClazz);
        }
        return new AnnotationGetter(annotation);
    }


    /**
     * 返回有指定注解的List<Filed>
     * @param obj
     * @return
     */
    public static List<Field> getHasAnnoFields(Object obj, Class<? extends Annotation> annoClz) {
        ArrayList<Field> arrayList = Lists.newArrayListWithCapacity(obj.getClass().getFields().length);
        Class<?> clazz = obj.getClass();
        Arrays.stream(clazz.getDeclaredFields()).filter(field -> {
           return  !Objects.isNull(field.getDeclaredAnnotation(annoClz));
        }).forEach(field -> {
            arrayList.add(field);
        });
        return arrayList;
    }

    /**
     * 1.SuppressWarnings: 忽略warning
     */
    @SuppressWarnings("unchecked")
    public <T extends Annotation> T get(){
        if(this.obj instanceof Annotation){
            return (T)this.obj;
        }else {
            throw new RuntimeException("过程中参数异常...");
        }
    }
}
