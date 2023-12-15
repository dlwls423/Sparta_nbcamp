package me.yejin.jpastudy.thread;

import jakarta.transaction.Transactional;
import me.yejin.jpastudy.channel.Channel;
import me.yejin.jpastudy.channel.ChannelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.apache.naming.ContextBindings.getThread;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ThreadRepositoryTest {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ThreadRepository threadRepository;

    @Test
    void insertSelectThreadTest() {
        // given
        var newChannel = Channel.builder().name("new-channel").build();
        var newThread = Thread.builder().message("new-message").build();
        newThread.setChannel(newChannel);

        // when
        var saveChannel = channelRepository.insertChannel(newChannel);
        var savedThread = threadRepository.insertThread(newThread);

        // then
        var foundThread = threadRepository.selectThread(savedThread.getId());
        assert foundThread.getChannel().getName().equals(newChannel.getName());
    }

    @Test
    void deleteThreadByOrphanRemovalTest() {
        // given
        var newChannel = Channel.builder().name("new-channel").build();
        var newThread = Thread.builder().message("new-message1").build();
        var newThread2 = Thread.builder().message("new-message2").build();
        newThread.setChannel(newChannel);
        newThread2.setChannel(newChannel);
        var savedChannel = channelRepository.insertChannel(newChannel);
        var savedThread = threadRepository.insertThread(newThread);
        var savedThread2 = threadRepository.insertThread(newThread2);

        // when
        var foundChannel = channelRepository.selectChannel(savedChannel.getId());
        foundChannel.getThreads().remove(savedThread);

    }

}