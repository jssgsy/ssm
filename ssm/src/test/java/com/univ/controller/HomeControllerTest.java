package com.univ.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by Univ on 16/6/28.
 */

/**
 * 注解说明:
 * @WebAppConfiguration:加载应用上下文
 *  1. 测试时加载的应用上下文是WebApplicationContext;
 *  2. 路径是相对于根路径,需要改变的话使用value值;
 *  3. 必须和@ContextConfiguration一起使用;
 *
 * @ContextConfiguration:加载配置文件(xml类型或者java注解类型)
 *  需要同时加载spring和springmvc的配置文件
 */


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/springMVC-servlet.xml","classpath:applicationContext.xml"})
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext wac;	//不需要在配置文件中配置

    private MockMvc mockMvc;  //不需要配置，通过静态方法获得


    @Before
    public void setup() throws Exception {
        this.mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build();//初始化
    }

    @Test
    public void toHome() throws Exception {
        /**
         * 构造一个get请求对象
         */
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/home/toHome");
        mockHttpServletRequestBuilder.param("param1", "value1");
        mockHttpServletRequestBuilder.param("param12", "value2");

        /**
         * 发出请求,由MockMvc对象发起
         */
        ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder);

        /**
         * 响应的内容封装在ResultMatcher对象中
         */
        ViewResultMatchers viewResultMatchers = MockMvcResultMatchers.view();
        ResultMatcher resultMatcher = viewResultMatchers.name("home");//代表返回视图的ResultMatcher

        StatusResultMatchers statusResultMatchers = MockMvcResultMatchers.status();
        ResultMatcher resultMatcher1 = statusResultMatchers.isOk();//代表响应码的ResultMatcher


        /**
         * 测试,由ResultActions对象代表
         */
        ResultActions resultActions1 = resultActions.andExpect(resultMatcher).andExpect(resultMatcher1);

        /**
         * 添加输出,可作调试使用
         */
        ResultHandler resultHandler = MockMvcResultHandlers.print();
        ResultActions resultActions2 = resultActions1.andDo(resultHandler);//很有用，可以在控制台上输出许多请求响应的信息

        /**
         * 上面的测试代码相当于如下:
         */
       /* mockMvc.perform(MockMvcRequestBuilders.get("/home/toHome").param("param1","value1").param("param2","value2"))
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());*/

    }

    @Test
    public void toConverter() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/home/toConverter").param("param1","value1").param("param2","value2"))
                .andExpect(MockMvcResultMatchers.view().name("converter"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


}