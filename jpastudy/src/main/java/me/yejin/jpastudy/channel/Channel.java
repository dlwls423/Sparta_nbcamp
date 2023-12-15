package me.yejin.jpastudy.channel;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;
import me.yejin.jpastudy.thread.Thread;
import me.yejin.jpastudy.user.User;
import me.yejin.jpastudy.userchannel.UserChannel;


// lombok
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// JPA
@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
     */
    @Column(length = 30)
    private String name;


    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        PUBLIC, PRIVATE
    }

    /**
     * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
     */
    @Builder
    public Channel(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    /**
     * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
     */
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Thread> threads = new LinkedHashSet<>();


    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
    @Exclude
    private Set<UserChannel> userChannels = new LinkedHashSet<>();

    /**
     * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
     */
    public void addThread(Thread thread) {
        this.threads.add(thread);
    }


    public UserChannel joinUser(User user) {
        var userChannel = UserChannel.builder().user(user).channel(this).build();
        this.userChannels.add(userChannel);
        user.getUserChannels().add(userChannel);
        return userChannel;
    }

}
