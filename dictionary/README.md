# **@Dictionary**

**功能**：用于实体类属性，实现数字和字符的转换(数据字典)

**主要涉及**：接口开发、aop切面编程、反射

**项目结构**：

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210910172142229.png" alt="image-20210910172142229" style="zoom:80%;" />

**简单配置**

```
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dictionary {
    //默认字典表名
    String tableName() default "dict";
    ///默认代号列名
    String codeColName() default "code";
    ///默认描述列名
    String textColName() default "text";
    //代号值,自动取实体类属性值
    String codeColValue() default "";
    //数字字典名,必须指定
    String dictName();
}
```

**使用示例**

```
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidTestDto extends PageDto implements Serializable {
    private static final long serialVersionUID = -8103829920082360062L;

    @NotNull
    private Long id;

    @Dictionary(dictName = "valid_status")
    @NotBlank
    private String status;

    @Dictionary(dictName = "valid_code")
    private String code;
}
 
```

**测试**

```
postman GET：http://localhost:6060/dictTest/dict

@RequestMapping("/dictTest")
@RestController
public class DictController {
    
    @GetMapping("/dict")
    public Result<?> dictTest02(){
        ArrayList<Object> arrayList = Lists.newArrayListWithCapacity(10);
        for(int i=0;i<10;i++){
            ValidTestDto validTestDto = new ValidTestDto();
            validTestDto.setId(111111L);
            validTestDto.setCode("10086");
            //如使用了字典的属性值=null,会忽略
            //validTestDto.setStatus("10");
            arrayList.add(validTestDto);
        }
        return Result.ok(arrayList);
    }
}
```

**测试结果**

```
{
    "success": true,
    "message": "操作成功！",
    "code": 200,
    "result": [
        {
            "code": "10086",
            "code_dict": "字典映射值",
            "pageIndex": 0,
            "pageSize": 10,
            "id": 111111,
            "status": null
        },
        {
            "code": "10086",
            "code_dict": "字典映射值",
            "pageIndex": 0,
            "pageSize": 10,
            "id": 111111,
            "status": null
        },
        {
            "code": "10086",
            "code_dict": "字典映射值",
            "pageIndex": 0,
            "pageSize": 10,
            "id": 111111,
            "status": null
        },
        {
            "code": "10086",
            "code_dict": "字典映射值",
            "pageIndex": 0,
            "pageSize": 10,
            "id": 111111,
            "status": null
        },
        {
            "code": "10086",
            "code_dict": "字典映射值",
            "pageIndex": 0,
            "pageSize": 10,
            "id": 111111,
            "status": null
        },
        {
            "code": "10086",
            "code_dict": "字典映射值",
            "pageIndex": 0,
            "pageSize": 10,
            "id": 111111,
            "status": null
        },
        {
            "code": "10086",
            "code_dict": "字典映射值",
            "pageIndex": 0,
            "pageSize": 10,
            "id": 111111,
            "status": null
        },
        {
            "code": "10086",
            "code_dict": "字典映射值",
            "pageIndex": 0,
            "pageSize": 10,
            "id": 111111,
            "status": null
        },
        {
            "code": "10086",
            "code_dict": "字典映射值",
            "pageIndex": 0,
            "pageSize": 10,
            "id": 111111,
            "status": null
        },
        {
            "code": "10086",
            "code_dict": "字典映射值",
            "pageIndex": 0,
            "pageSize": 10,
            "id": 111111,
            "status": null
        }
    ],
    "timestamp": 1631521956500
}
```