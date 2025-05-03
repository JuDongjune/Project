package com.example.myproject.Service;

import com.example.myproject.Common.FriendStatus;
import com.example.myproject.Dto.FriendRequestDto;
import com.example.myproject.Entity.Friend;
import com.example.myproject.Entity.User;
import com.example.myproject.Repository.FriendRepository;
import com.example.myproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    //팔로잉 요청
    public void requestFollowing(FriendRequestDto dto){
        Optional<User> userOptional = userRepository.findById(dto.getSenderId());
        Optional<User> friendOptional = userRepository.findById(dto.getReceiverId());
        if(dto.getSenderId().equals(dto.getReceiverId())){
            throw new RuntimeException("자기 자신에게 친구 요청을 할 수 없습니다.");
        }
        User _user = null;
        User _friend = null;
        if(userOptional.isEmpty()){
            throw new RuntimeException("친구 요청을 할 수 없는 상태입니다.");
        } else{
            _user = userOptional.get();
        }
        if(friendOptional.isEmpty()){
            throw new RuntimeException("해당 사용자를 찾을 수 없습니다.");
        } else {
            _friend = friendOptional.get();
        }

        Optional<Friend> isAlreadyRequested = friendRepository.findBySenderAndReceiver(_user, _friend);
        if(isAlreadyRequested.isPresent()){
            throw new RuntimeException("이미 보낸 요청입니다.");
        }

        Friend friend = new Friend();
        friend.setSender(_user);
        friend.setReceiver(_friend);
        friend.setFriendStatus(FriendStatus.REQUESTED);
        friendRepository.save(friend);
    }

    //팔로잉 수락(상태 수정)
    public void acceptFollowing(Long friendId, String userId){
        Optional<Friend> optionalFriend = friendRepository.findById(friendId);
        Friend _friend = null;
        if (optionalFriend.isEmpty()){
            throw new RuntimeException("해당 요청을 찾을 수 없습니다.");
        } else {
            _friend = optionalFriend.get();
        }
        if(!_friend.getReceiver().getUserId().equals(userId)){ //내가 요청을 받은 수신자인지 아닌지
            throw new RuntimeException("수락 권한이 없습니다.");
        }
        _friend.setFriendStatus(FriendStatus.ACCEPTED);
        friendRepository.save(_friend);
    }

    //팔로잉 취소,거절
    public void deleteFollowing(long friendId, String userId){
        Optional<Friend> _friend = friendRepository.findById(friendId);
        Friend friend = null;
        if(_friend.isEmpty()){
            throw new RuntimeException("해당 친구 목록을 찾을 수 없습니다.");
        } else{
            friend = _friend.get();
        }
        if(friend.getReceiver().getUserId().equals(userId) || friend.getSender().getUserId().equals(userId)) {
            //사용자가 친구 요청의 수신자나 발신자 둘 중 하나였다면 지울 권한이 있는 거임
            friendRepository.deleteById(friendId);
        }else{
            throw new RuntimeException("취소 또는 삭제 권한이 없습니다.");
        }
    }

    //친구 목록 조회
    public Page<Friend> getFriendList(String userId, int page){
        Optional<User> optionalUser = userRepository.findById(userId);
        User _user = null;
        if (optionalUser.isEmpty()){
            throw new RuntimeException("해당 사용자를 찾을 수 없습니다.");
        } else{
            _user = optionalUser.get();
        }
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdDt"));
        return friendRepository.findBySenderOrderByCreatedDtDesc(_user, pageRequest);
    }
}
