package com.bikkadit.ecommerce.serviceimpl;

import com.bikkadit.ecommerce.constant.AppConstant;
import com.bikkadit.ecommerce.entity.User;
import com.bikkadit.ecommerce.exception.ResourceNotFoundException;
import com.bikkadit.ecommerce.helper.Helpers;
import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.payload.UserDto;
import com.bikkadit.ecommerce.repository.UserRepository;
import com.bikkadit.ecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    // CREATE USER

    @Override
    public UserDto createUser(UserDto userdto) {
        logger.info("Request Created For Create User");
        String userId = UUID.randomUUID().toString();
        userdto.setUserId(userId);
        User user = DtotoUser(userdto);
        User save = this.userRepository.save(user);
        UserDto newuser = UsertoDto(save);
        logger.info("Request Completed For Create User");
        return newuser;
    }

    // UPDATE USER

    @Override
    public UserDto updateUser(UserDto userdto, String userId) {
        logger.info("Request Created For Update User");
        User user = this.userRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException(AppConstant.NOT_FOUND));
        user.setUserName(userdto.getUserName());
        user.setUserEmail(userdto.getUserEmail());
        user.setUserPassword(userdto.getUserPassword());
        user.setUserAbout(userdto.getUserAbout());
        user.setImageName(userdto.getImageName());
        user.setUserAdd(userdto.getUserAdd());
        user.setGender(userdto.getGender());
        User save = this.userRepository.save(user);
        UserDto user2 = this.UsertoDto(save);
        logger.info("Request Completed For Update User");
        return user2;
    }

    //DELETE USER

    @Override
    public void deleteUser(String userId) {
        logger.info("Request Created For Delete User");
        User user = this.userRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException(AppConstant.NOT_FOUND));
        logger.info("Request Completed For Delete User");
        userRepository.delete(user);
    }

    // GET ALL USERS

    @Override
    public PageableResponse<UserDto> getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        logger.info("Request Created For Fetch All User with PageNumbers");
      // Sort sort= Sort.by(sortBy);
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?
                (Sort.by(sortBy).descending())
                :(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<User> users = userRepository.findAll(pageable);
        PageableResponse<UserDto> response= Helpers.getPageableResponse(users,UserDto.class);
        logger.info("Request Completed For Fetch All User with page Numbers");
        return response;
    }

    // GET SINGLE USER

    @Override
    public UserDto getUser(String userId) {
        logger.info("Request Created For Fetch Single User");
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        logger.info("Request Completed For Fetch Single User");
        return this.UsertoDto(user);
    }

    // GET USER BY EMAIL ID

    @Override
    public UserDto getUserByEmail(String userEmail) {
        logger.info("Request Created For Fetch User By Email Id");
      User user = this.userRepository.findByUserEmail(userEmail)
                .orElseThrow(()-> new ResourceNotFoundException(AppConstant.EMAIL_ERROR));
        logger.info("Request Completed For Fetch User By Email Id");
        return this.UsertoDto(user);
    }

    // SEARCH USERS

    @Override
    public Stream<UserDto> searchUser(String keyword) {
        logger.info("Request Created For Searching User");
        List<User> list = userRepository.findByUserNameContaining(keyword);
        Stream<UserDto> users = list.stream().map(user -> UsertoDto(user));
        logger.info("Request Completed For Searching User");
        return  users;
    }

    private UserDto UsertoDto(User user) {

//		UserDto userdto = UserDto.builder()
//				.userId(user.getUserId())
//				.userName(user.getUserName())
//				.userAdd(user.getUserAdd())
//				.userAbout(user.getUserAbout())
//				.userEmail(user.getUserEmail())
//				.Gender(user.getGender())
//				.userPassword(user.getUserPassword())
//				.imageName(user.getImageName())
//				.build();
        return modelMapper.map(user, UserDto.class);
    }

    private User DtotoUser(UserDto userdto) {
//		User user = User.builder()
//				.userId(userdto.getUserId())
//				.userName(userdto.getUserName())
//				.userEmail(userdto.getUserEmail())
//				.userPassword(userdto.getUserPassword())
//				.userAbout(userdto.getUserAbout())
//				.userAdd(userdto.getUserAdd())
//				.Gender(userdto.getGender())
//				.imageName(userdto.getImageName())
//				.build();
        return modelMapper.map(userdto, User.class);
    }
}

