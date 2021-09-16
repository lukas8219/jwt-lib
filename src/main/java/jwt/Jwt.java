package jwt;

public class Jwt<T> {

    private final JwtBody<T> body;

    public Jwt(T principal, AlgorithmEnum algorithmEnum){
        body = createJwtBody(principal, algorithmEnum);
    }

    public Jwt(T principal){
        body = createJwtBody(principal, null);
    }

    private JwtBody<T> createJwtBody(T t, AlgorithmEnum algorithmEnum) {
        if(algorithmEnum == null){
            algorithmEnum = AlgorithmEnum.HS256;
        }
        var header = new JwtHeader(algorithmEnum);
        var payload = new JwtPayload<>(t);
        var signature = new JwtSignature(header, payload);
        return new JwtBody<>(header, payload, signature);
    }

    public String getToken(){
        return body.getJwt();
    }

}
