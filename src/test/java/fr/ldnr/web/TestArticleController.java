/*package fr.ldnr.web;


import fr.ldnr.business.IBusinessImpl;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class TestArticleController {

    @Autowired
    MockMvc mvc;

    @MockBean
    IBusinessImpl business;

    @Test
    public void test_get_welcome() throws  Exception {
        // GIVEN
        when(business.great()).thenReturn("Hello, Mock");

        this.mvc.perform(get("/greating"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                .string(containsString("Hello, Mock")));
    }

}*/
