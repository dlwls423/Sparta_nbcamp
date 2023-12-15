package me.yejin.jpastudy.channel;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ChannelRepositoryTest {

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    void insertSelectChannelTest() {
        // given
        var newChannel = Channel.builder().name("new-group").build();

        // when
        var savedChannel = channelRepository.insertChannel(newChannel);

        // then
        var foundChannel = channelRepository.selectChannel(savedChannel.getId());
        assert foundChannel.getId().equals(savedChannel.getId());
    }

}