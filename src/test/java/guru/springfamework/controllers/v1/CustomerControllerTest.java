package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.services.CategoryService;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest {

    public static final String CUSTOMER_API = "/api/v1/customers/";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).setControllerAdvice(new RestApplicationExceptionHandler()).build();
    }

    @Test
    public void getAllCustomers() throws Exception {

        CustomerDTO customer1 = CustomerDTO.builder().firstName("Michael").lastName("Watson").customerUrl(CUSTOMER_API + 1).build();
        CustomerDTO customer2 = CustomerDTO.builder().firstName("Sam").lastName("Axe").customerUrl(CUSTOMER_API + 2).build();
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get(CUSTOMER_API)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {

        CustomerDTO customer1 = CustomerDTO.builder().firstName("Michael").lastName("Watson").customerUrl(CUSTOMER_API + 1).build();
        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get(CUSTOMER_API  + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michael")));
    }

    @Test
    public void createNewCustomer() throws Exception {

        CustomerDTO customer1 = CustomerDTO.builder().firstName("Michael").lastName("Watson").customerUrl(CUSTOMER_API + 1).build();
        when(customerService.createCustomer(any(CustomerDTO.class))).thenReturn(customer1);

        mockMvc.perform(post(CUSTOMER_API)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(new CustomerDTO())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Michael")))
                .andExpect(jsonPath("$.lastName", equalTo("Watson")))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_API + "1")));

    }

    @Test
    public void updateCustomer() throws Exception {

        CustomerDTO customer1 = CustomerDTO.builder().firstName("Michael").lastName("Watson").customerUrl(CUSTOMER_API + 1).build();
        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customer1);

        mockMvc.perform(put(CUSTOMER_API + "1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(new CustomerDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michael")))
                .andExpect(jsonPath("$.lastName", equalTo("Watson")))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_API + "1")));

    }

    @Test
    public void patchCustomer() throws Exception {

        CustomerDTO customer1 = CustomerDTO.builder().firstName("Michael").lastName("Watson").customerUrl(CUSTOMER_API + 1).build();
        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customer1);

        mockMvc.perform(patch(CUSTOMER_API + "1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(new CustomerDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michael")))
                .andExpect(jsonPath("$.lastName", equalTo("Watson")))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_API + "1")));

    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete(CUSTOMER_API + "1")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());

        verify(customerService).deleteCustomerById(1L);

    }
}