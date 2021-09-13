package quinlan.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author quinlan
 * @date 2021年09月08日 11:13:35
 * @description
 */
@Data
@Accessors(chain = true)
public class PageDto implements Serializable {
    private static final long serialVersionUID = 7812266450714476805L;

    protected Integer pageIndex;
    protected Integer pageSize;

    public int getPageIndex(){
        if(Objects.isNull(pageIndex)){
            return 0;
        }
        return pageIndex;
    }

    public int getPageSize(){
        if(Objects.isNull(pageSize)){
            return 10;
        }
        return pageSize;
    }

}
