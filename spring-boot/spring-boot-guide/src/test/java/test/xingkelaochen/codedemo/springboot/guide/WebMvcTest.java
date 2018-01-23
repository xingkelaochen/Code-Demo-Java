package test.xingkelaochen.codedemo.springboot.guide;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xingkelaochen.codedemo.springboot.guide.Application;
import com.xingkelaochen.codedemo.springboot.guide.configuration.MyConfigurationBinding;
import com.xingkelaochen.codedemo.springboot.guide.configuration.MyConfigurationBinding.Info;
import com.xingkelaochen.codedemo.springboot.guide.controller.DemoRestController;

/**
 * Spring Boot提供了快速测试Spring MVC的方法，使用@WebMvcTest将开启自动Spring Mvc配置，提供一个MockMvc对象。
 * <p>
 * 这将自动扫描加载@Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, WebMvcConfigurer,  HandlerMethodArgumentResolver。
 * 一般的，@WebMvcTest用来测试单独Controller，测试依赖路径中的相关Spring Bean将使用本测试类中用@MockBean标注的模拟对象替换（如果有的话）。
 * </p>
 *
 * @author xingkelaochen
 * 
 * <p>
 * E-MAIL: admin@xingkelaochen.com
 * <br />
 * SITE: http://www.xingkelaochen.com
 * </p>
 *
 */
@RunWith(SpringRunner.class)
@org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest(DemoRestController.class)
@ContextConfiguration(classes= {Application.class})
public class WebMvcTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MyConfigurationBinding myConfigurationBinding;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void controllerTest() throws JsonProcessingException, Exception {
		
		Info info = new Info();
		info.setName("test");
		info.setSex("女");
		
		when(myConfigurationBinding.getInfo()).thenReturn(info);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/printMyConfiguratioinInfo"))
			.andExpect(status().isOk())
			.andExpect(content().string(objectMapper.writeValueAsString(info)));
		
	}
}
