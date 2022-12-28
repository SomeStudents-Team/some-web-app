package com.game.some.services.implementations;

import com.game.some.entities.BattleClient;
import com.game.some.repositories.BattleClientRepository;
import com.game.some.repositories.BattleRepository;
import com.game.some.services.BattleClientService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_= @Autowired)
public class BattleClientServiceImpl implements BattleClientService {

    BattleClientRepository battleClientRepository;

    @Override
    public Iterable<BattleClient> getAll() {
        return battleClientRepository.findAll();
    }

    @Override
    public BattleClient create(BattleClient battleClient) {
        return battleClientRepository.save(battleClient);
    }

    @Override
    public Optional<BattleClient> findById(Long id) {
        return battleClientRepository.findById(id);
    }

    @Override
    public Optional<BattleClient> findByIpAndName(String ip, String name) {
        return battleClientRepository.findByIpAndName(ip, name);
    }

    @Override
    public void deleteAll() {
        battleClientRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        battleClientRepository.deleteById(id);
    }
}
