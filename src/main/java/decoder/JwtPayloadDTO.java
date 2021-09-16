package decoder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtPayloadDTO<T> {

    private T body;

}
