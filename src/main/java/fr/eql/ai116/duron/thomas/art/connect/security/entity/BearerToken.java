package fr.eql.ai116.duron.thomas.art.connect.security.entity;

public record BearerToken(String accessToken, String tokenType) {

    public BearerToken {
    }

    @Override
    public String accessToken() {
        return accessToken;
    }
}
