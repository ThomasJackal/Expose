package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

public record SavePayementInfoDto (
        long cardNumbers,
        String cardExpirationDate,
        int CVV
){
}
