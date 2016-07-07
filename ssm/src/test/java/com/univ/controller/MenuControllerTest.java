package com.univ.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by Univ on 16/6/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/springMVC-servlet.xml","classpath:applicationContext.xml"})
public class MenuControllerTest {
    @Autowired
    private WebApplicationContext wac;	//不需要在配置文件中配置
    private MockMvc mockMvc;  //不需要配置，通过静态方法获得

    @Before
    public void setup() throws Exception {
        this.mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build();//初始化
    }

    @Test
    public void getLevelOne() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/menu/getMenuTree"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].iconCls").value("icon-save"))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type","application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAddMenu() throws Exception {

        /**
         * 演示如何在springmvc的单元测试中给类类型传参。
         * 会直接将这里的name和url和controller方法中的menu的name和url属性绑定在一起
         */
        mockMvc.perform(MockMvcRequestBuilders.post("/menu/addMenu")
                    .param("name","xxx")
                    .param("url","xxxxxxxxxx")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
