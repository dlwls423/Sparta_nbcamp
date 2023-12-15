package me.yejin.jpastudy.userchannel;

import jakarta.transaction.Transactional;
import me.yejin.jpastudy.channel.Channel;
import me.yejin.jpastudy.channel.ChannelRepository;
import me.yejin.jpastudy.user.User;
import me.yejin.jpastudy.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserChannelRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserChannelRepository userChannelRepository;

    @Test
    void userJoinChannelTest() {
        // given
        var newChannel = Channel.builder().name("new-group").build();
        var newUser = User.builder().username("new_user").password("new_password").build();
        var newUserChannel = newChannel.joinUser(newUser);

        // when
        var savedChannel = channelRepository.insertChannel(newChannel);
        var savedUser = userRepository.insertUser(newUser);
        var savedUserChannel = userChannelRepository.insertUserChannel(newUserChannel);

        // then
        var foundChannel = channelRepository.selectChannel(savedChannel.getId());
        assert foundChannel.getUserChannels().stream()
                .map(UserChannel::getChannel)
                .map(Channel::getName)
                .anyMatch(name -> name.equals(newChannel.getName()));
    }

    @Test
    @DisplayName("채널에 유저가입 테스트 (with Cascade)")
    void userJoinChannelWithCascadeTest() {
        // given
        var TEST_USER = User.builder().username("new_user").password("new_pass").build();
        var TEST_CHANNEL = Channel.builder().name("new_group").build();
        var savedUser = userRepository.insertUser(TEST_USER);

        // when
        TEST_CHANNEL.joinUser(savedUser);
        channelRepository.insertChannel(TEST_CHANNEL);

        // then
        var foundUser = userRepository.selectUser(savedUser.getId());
        assert foundUser.getUserChannels().stream()
                .map(UserChannel::getChannel)
                .map(Channel::getName)
                .anyMatch(name -> name.equals(TEST_CHANNEL.getName()));
    }

}