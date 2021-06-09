package com.example.medial;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.medial.entity.Lead;
import com.example.medial.iservice.LeadService;
import com.example.medial.repository.LeadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes=MediaLApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(
		  locations = "classpath:application-test.properties")
class MediaLApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private LeadService leads;
	
	
	
//	private LeadRepository leadr;
	
	@Test
	void contextLoads() {
	}
		
	@Test
	void getAllEmployeeTest() throws Exception
	{
		createTestLead(1);
		
		mvc.perform(get("/leads/")
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk())
		   .andExpect(content()
		   .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		   .andExpect(jsonPath("$[0].lead_id", is(1)));
		    
	}
	
	@Test
	void getEmployeeTest() throws Exception{
		
		createTestLead(2);
		mvc.perform(get("/leads/lead/2")
				   .contentType(MediaType.APPLICATION_JSON))
				   .andExpect(status().isOk())
				   .andExpect(content()
						   .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
						   .andExpect(jsonPath("lead_id", is(2)));
		
	}	
	
	@Test
	void getEmployeeNotFoundTest() throws Exception{
		
		createTestLead(2);
		mvc.perform(get("/leads/lead/4"))
					.andDo(print()).andExpect(status().isNotFound());		   
			}
	
	@Test
	void PostLeadTest() throws Exception
	{
		Lead lead=new Lead();
		lead.setLead_id(3);
		
		mvc.perform( MockMvcRequestBuilders
			      .post("/leads/save")
			      .content(asJsonString(lead))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("lead_id", is(3)));
	
			      
		//	mvc.perform(post("/leads/save")).andExpect(status().is2xxSuccessful());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	    //	System.out.println("helloo"+new ObjectMapper().writeValueAsString(obj));
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	private void createTestLead(int id) {
		Lead lead=new Lead();
		lead.setLead_id(id);
		leads.save(lead);
	}
	
	

}
