package com.game.some.services.implementations;

import com.game.some.entities.BattleClient;
import com.game.some.entities.BattleMember;
import com.game.some.repositories.BattleClientRepository;
import com.game.some.repositories.BattleMemberRepository;
import com.game.some.services.BattleMemberService;
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
public class BattleMemberServiceImpl implements BattleMemberService {

    BattleMemberRepository battleMemberRepository;

    @Override
    public Iterable<BattleMember> getAll() {
        return battleMemberRepository.findAll();
    }

    @Override
    public BattleMember create(BattleMember battleMember) {
        return battleMemberRepository.save(battleMember);
    }

    @Override
    public Optional<BattleMember> findById(Long id) {
        return battleMemberRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        battleMemberRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        battleMemberRepository.deleteById(id);
    }
}
