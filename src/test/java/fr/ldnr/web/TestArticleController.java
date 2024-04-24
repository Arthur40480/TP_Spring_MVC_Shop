package fr.ldnr.web;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestArticleController
{
    @Autowired
    private MockMvc mvc;

    @Test
    public void test_get_welcome() throws  Exception
    {
        
    }
}
