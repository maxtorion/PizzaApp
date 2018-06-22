package controllers;

import com.example.pizza.PizzaApplication;
import com.example.pizza.PizzaApplicationTests;
import jpa.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.transaction.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PizzaApplicationTests.class)
//tak jak niżej to się w ogóle wypierdala, nie mam web config
//@ContextConfiguration(classes = PizzaApplication.class)
@WebMvcTest(value = UserRestController.class, secure = false)
public class UserRestControllerTest{

 //   @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserRepository userRepository;




    User mockUser;

    @Before
    public void setUp() throws Exception {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.mockUser = new User("Test", "Test", "Jakis@mail.com", "412-111-222");
        this.mockUser.setId(1);
    }


    @Test
    public void getAllUsers() {


    }

    @Test
    public void getUserByID() throws Exception {
        Mockito.when(this.userRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.ofNullable(mockUser));


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8080/userapi/findbyid/"+mockUser.getId())
               // .param("userID",mockUser.getId().toString())
                .accept(MediaType.APPLICATION_JSON_UTF8);



        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status()
                        .isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();


        System.out.println(result.getResponse());

        String expected = "{login:Test,id:1,type:User,password:Test,status:Enabled,telefon:412-111-222,email:Jakis@mail.com}";
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

    }

    @Test
    public void getUserByLogin() {
    }

    @Test
    public void addUser() {
    }
}