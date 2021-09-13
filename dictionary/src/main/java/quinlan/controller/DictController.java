package quinlan.controller;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quinlan.dto.ValidTestDto;
import quinlan.utils.Result;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author quinlan
 * @date 2021年09月07日 17:57:40
 * @description
 */
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
            validTestDto.setStatus("10");
            arrayList.add(validTestDto);
        }
        return Result.ok(arrayList);
    }
}
