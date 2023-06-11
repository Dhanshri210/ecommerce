package com.bikkadit.ecommerce.helper;

import com.bikkadit.ecommerce.entity.User;
import com.bikkadit.ecommerce.payload.PageableResponse;
import com.bikkadit.ecommerce.payload.UserDto;
import com.bikkadit.ecommerce.serviceimpl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helpers {

    @Autowired
    private ModelMapper modelMapper;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public static <U,V>PageableResponse<V> getPageableResponse(Page<U> page,Class<V> type){
        logger.info("Page Details Allocated form Helper class");
        List<U> entity=page.getContent();
        List<V> collect = entity.stream().map(object -> new ModelMapper()
                .map(object,type)).collect(Collectors.toList());
        PageableResponse<V> response = new PageableResponse<>();
        response.setContent(collect);
        response.setPageNumber(page.getNumber()+1);
        response.setTotalPages(page.getTotalPages());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setLastPage(page.isLast());
        return response;
    }
}
