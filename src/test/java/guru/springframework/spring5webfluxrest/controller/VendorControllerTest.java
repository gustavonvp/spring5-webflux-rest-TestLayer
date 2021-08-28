package guru.springframework.spring5webfluxrest.controller;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;

public class VendorControllerTest {

    WebTestClient webTestClient;
    VendorController vendorController;
    VendorRepository vendorRepository;


    @Before
    public void setUp() throws Exception {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void list() {
        BDDMockito.given(vendorRepository.findAll()).willReturn(Flux.just(Vendor.builder().firstName("Jhon").lastName("Thompson").build(), Vendor.builder().firstName("Klaus").lastName("Gue").build() ));

        webTestClient.get().uri("/api/v1/vendors/")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);

    }

    @Test
    public void getById() {
        BDDMockito.given(vendorRepository.findById("someid")).willReturn(Mono.just(Vendor.builder().lastName("Jeremias").build()));

        webTestClient.get().uri("/api/v1/vendors/")
                .exchange()
                .expectBodyList(Category.class);
    }
}