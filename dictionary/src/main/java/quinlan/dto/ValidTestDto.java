package quinlan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quinlan.annotation.Dictionary;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author quinlan
 * @date 2021年09月07日 14:43:01
 * @description
 */
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
