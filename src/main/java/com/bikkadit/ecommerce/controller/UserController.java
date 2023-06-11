package com.bikkadit.ecommerce.controller;
import com.bikkadit.ecommerce.constant.AppConstant;
import com.bikkadit.ecommerce.entity.BaseEntity;
import com.bikkadit.ecommerce.helper.ApiResponse;
import com.bikkadit.ecommerce.payload.BaseEntityDto;
import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.service.UserService;
import com.bikkadit.ecommerce.payload.UserDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class UserController extends BaseEntityDto {

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Create User
     *
     * @param userDto
     *
     * @return
     */

    @PostMapping("/Create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Request Created For Create User"   +userDto);
        UserDto userDto1 = userService.createUser(userDto);
        logger.info("Request Completed For Create User"    +userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Update User
     *
     * @param userId
     *
     * @return
     */

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userId")
                                              String userId,@RequestBody UserDto userDto) {
        logger.info("Request Created For Update User"    +userId);
        UserDto updateUser = userService.updateUser(userDto, userId);
        logger.info("Request Completed For Update User"     +userId);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Delete User
     *
     * @param userId
     *
     * @return
     */

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser
            (@PathVariable String userId) {
        logger.warn("Request Created For Delete User"       +userId);
        userService.deleteUser(userId);
        ApiResponse message = ApiResponse
                .builder()
                .message(AppConstant.USER_DELETE)
                .success(true)
                .status(HttpStatus.OK).build();
        logger.warn("Request Completed For Update User"      +userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Get All User
     *
     * @param userDto
     *
     * @return
     */

    @GetMapping("/getAllUser")
    public ResponseEntity<PageableResponse<UserDto>> getAllUser(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir){
        logger.info("All Users Are Fetch Successfully");
        return new ResponseEntity<PageableResponse<UserDto>>
                (userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);

    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Get Single User
     *
     * @param userId
     *
     * @return
     */

    @GetMapping("/getSingle/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
        logger.info("Single Users Fetch Successfully"    +userId);
        return new ResponseEntity<>(userService.getUser(userId)
                , HttpStatus.OK);
    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Fetch User by Emailid
     *
     * @param userDto
     *
     * @return
     */

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<UserDto> getUserByEmail
            (@PathVariable String userEmail) {
        logger.info("User Fetch By Email Id"    +userEmail);
        return new ResponseEntity<>(userService
                .getUserByEmail(userEmail), HttpStatus.OK);
    }

    /*
     * @author Dhanshri
     *
     * @apiNote This Api is used For Search User
     *
     * @param userDto
     *
     * @return
     */

    @GetMapping("/search/{keyword}")
    public ResponseEntity<Stream<UserDto>> searchUser(@PathVariable String keyword) {
        logger.info("Search User By Using Keywords"    +keyword);
        return new ResponseEntity<>(userService.searchUser(keyword), HttpStatus.OK);
    }

}