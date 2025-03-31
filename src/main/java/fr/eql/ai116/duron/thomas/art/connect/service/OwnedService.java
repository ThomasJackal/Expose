package fr.eql.ai116.duron.thomas.art.connect.service;

import fr.eql.ai116.duron.thomas.art.connect.entity.User;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.UpgradeDto;

public interface OwnedService {

    void upgrade(User user, UpgradeDto dto);
}
