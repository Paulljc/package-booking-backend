package com.oocl.packagebooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.packagebooking.entity.Packaging;
import com.oocl.packagebooking.service.PackagingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PackagingController.class)
public class PackagingControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PackagingService packagingService;

    @Test
    public void should_return_package_when_find_all_package() throws Exception {
        List<Packaging> packagings = Arrays.asList(
                new Packaging("12", "me", "123"),
                new Packaging("12", "me", "123")
        );

        when(packagingService.findAllPackage()).thenReturn(packagings);

        ResultActions result = mvc.perform(get("/packages"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].billno", is("12")))
                .andExpect(jsonPath("$[1].billno", is("13")));
    }


    @Test
    public void should_return_package_when_find_package_by_status() throws Exception {
        List<Packaging> packagings = Arrays.asList(
                new Packaging("12", "me", "123"),
                new Packaging("12", "me", "123")
        );

        when(packagingService.findPackageByStatus(anyString())).thenReturn(packagings);

        ResultActions result = mvc.perform(get("/packages/{status}", "haven"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].billno", is("12")))
                .andExpect(jsonPath("$[1].billno", is("13")));
        verify(packagingService).findPackageByStatus("haven");
    }

    @Test
    public void should_return_package_when_update_package_by_status() throws Exception {
        Packaging packaging = new Packaging("12", "me", "123");

        when(packagingService.updatePackageStatus(anyLong(), any())).thenReturn(packaging);

        ResultActions result = mvc.perform(put("/packages/{packageId}", packaging.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(packaging)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.billno", is("12")))
                .andExpect(jsonPath("$.receiver", is("me")));
    }

    @Test
    public void should_return_package_when_update_package_by_apptime() throws Exception {
        Packaging packaging = new Packaging("12", "me", "123");

        when(packagingService.updatePickTimeByBillNumber(anyString(), any())).thenReturn(packaging);

        ResultActions result = mvc.perform(put("/packages/{BillNumber}", packaging.getApptime())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(packaging)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.billno", is("12")))
                .andExpect(jsonPath("$.receiver", is("me")))
                .andExpect(jsonPath("$.phonenum", is("123")));
    }

    @Test
    public void should_return_package_when_add_package() throws Exception {
        Packaging packaging = new Packaging("12", "me", "123");

        when(packagingService.addPackage(any())).thenReturn(packaging);

        ResultActions result = mvc.perform(post("/packages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(packaging))
        );

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.billno", is("12")))
                .andExpect(jsonPath("$.receiver", is("me")))
                .andExpect(jsonPath("$.phonenum", is("123")));
    }
}
