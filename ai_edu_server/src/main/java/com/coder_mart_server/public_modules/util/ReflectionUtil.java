package com.coder_mart_server.public_modules.util;

import com.coder_mart_server.public_modules.model.bo.FieldDescriptorBO;
import com.coder_mart_server.public_modules.statemachine.PropertyStateContextHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具类
 */
public class ReflectionUtil {

    //set方法前缀
    private final static String setMethodPrefix = "set";

    /**
     * 传入类的类对象，获取其所有属性的set方法
     * @param clazz 需要获取set方法集合的类
     * @return 属性set方法集合，key为属性名，value中有两个属性，一个为属性类型，一个是set方法名称
     */
    public static HashMap<String, FieldDescriptorBO> getAllFieldSetMethod(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        return getAllFieldSetMethod(fields);
    }

    /**
     * 获取类属性的所有set方法
     * @param fields 属性列表
     * @return 以属性名（fieldName）为key，方法名和类型为value
     */
    public static HashMap<String, FieldDescriptorBO> getAllFieldSetMethod(Field[] fields){
        HashMap<String, FieldDescriptorBO> result = new HashMap<>();

        for(int i = 0;i<fields.length;i++){
            String fieldName = fields[i].getName();
            Class<?> fieldType = fields[i].getType();

            //创建set方法名
            String setMethodName = FieldUtil.fieldSetMethodBuild(fields[i]);

            FieldDescriptorBO fieldDescriptorBO = new FieldDescriptorBO();
            fieldDescriptorBO.setMethodName(setMethodName);
            fieldDescriptorBO.setPropertyType(fieldType);

            result.put(fieldName,fieldDescriptorBO);
        }

        return result;
    }

    /**
     * 通过反射创建一个对象
     * @param classType 需要创建的对象类型
     * @param fieldProperty 需要赋值的属性列表，key为属性名称，value为属性需要赋的值
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T buildObjectByClass(Class<T> classType,Map<String,Object> fieldProperty) throws Exception {
        //创建对象
        Constructor<T> constructor = classType.getConstructor();
        T obj = constructor.newInstance();

        //进行set赋值操作
        assignField(obj,fieldProperty);

        return obj;
    }

    /**
     * 调用属性的set方法进行赋值
     * @param object 需要赋值的对象
     * @param fieldProperty 赋值map，key为属性名，value为属性值
     * @param <T>
     * @throws Exception
     */
    public static <T> void assignField(T object,Map<String,Object> fieldProperty) throws Exception {
        //获取当前对象的class对象
        Class<?> classType = object.getClass();

        //获取当前对象的属性列表
        Field[] fields = classType.getDeclaredFields();

        //获取所有属性的set方法
        HashMap<String, FieldDescriptorBO> allFieldSetMethod = getAllFieldSetMethod(classType);

        for(int i = 0;i<fields.length;i++){
            FieldDescriptorBO fieldDescriptorBO = allFieldSetMethod.get(fields[i].getName());

            //获取对应的set方法
            Class<?> propertyType = fieldDescriptorBO.getPropertyType();
            String methodName = fieldDescriptorBO.getMethodName();
            Method method = classType.getMethod(methodName, propertyType);

            //将String类型数据转为对应类型数据
            Object parseObjectByClassType = PropertyStateContextHandler.parseObjectByClassType(propertyType, fieldProperty.get(fields[i].getName()));

            Object cast = propertyType.cast(parseObjectByClassType);

            method.invoke(object,cast);
        }
    }

    /**
     * 获取所有属性上的注解
     * @param annotationType 注解的class对象
     * @param fields 属性列表
     * @param <T>
     * @return 返回一个hashmap，以注解为key，属性为value
     */
    public static <T extends Annotation> Map<T, Field> getAllFieldAnnotation(Class<T> annotationType, Field[] fields){
        HashMap<T, Field> result = new HashMap<>();

        //遍历字段数组，获取每个字段上的注解
        for(int i = 0;i<fields.length;i++){
            T annotation = (T) fields[i].getAnnotation(annotationType);
            result.put(annotation,fields[i]);
        }

        return result;
    }

    /**
     * 属性工具
     */
    class FieldUtil{

        //根据属性名，生成对应set方法
        public static String fieldSetMethodBuild(Field field){
            String fieldName = field.getName();
            //属性首字母大写，并将前缀“set”进行拼接
            String setMethodName = setMethodPrefix + ASCIIUtil.firstCodeBig(fieldName);
            return setMethodName;
        }
    }

}



















