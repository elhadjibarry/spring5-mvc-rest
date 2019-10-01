package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VendorController.class)
public class VendorControllerTest {

    public static final String VENDOR_API = "/api/v1/vendors/";

    @MockBean
    private VendorService vendorService;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllVendors() throws Exception {

        given(vendorService.getAllVendors()).willReturn(Arrays.asList(new VendorDTO()));

        mockMvc.perform(get(VENDOR_API).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(1)));
    }

    @Test
    public void getVendorById() {
    }

    @Test
    public void createVendor() {
    }

    @Test
    public void updateVendor() {
    }

    @Test
    public void patchVendor() {
    }

    @Test
    public void deleteVendor() throws Exception {

        mockMvc.perform(delete(VENDOR_API + "1")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(1L);
    }
}