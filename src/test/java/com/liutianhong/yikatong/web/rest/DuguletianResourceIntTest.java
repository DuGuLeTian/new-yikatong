package com.liutianhong.yikatong.web.rest;

import com.liutianhong.yikatong.YikatongApp;

import com.liutianhong.yikatong.domain.Duguletian;
import com.liutianhong.yikatong.repository.DuguletianRepository;
import com.liutianhong.yikatong.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DuguletianResource REST controller.
 *
 * @see DuguletianResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YikatongApp.class)
public class DuguletianResourceIntTest {

    private static final String DEFAULT_DUGULETIAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DUGULETIAN_NAME = "BBBBBBBBBB";

    @Autowired
    private DuguletianRepository duguletianRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDuguletianMockMvc;

    private Duguletian duguletian;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DuguletianResource duguletianResource = new DuguletianResource(duguletianRepository);
        this.restDuguletianMockMvc = MockMvcBuilders.standaloneSetup(duguletianResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Duguletian createEntity(EntityManager em) {
        Duguletian duguletian = new Duguletian()
            .duguletian_name(DEFAULT_DUGULETIAN_NAME);
        return duguletian;
    }

    @Before
    public void initTest() {
        duguletian = createEntity(em);
    }

    @Test
    @Transactional
    public void createDuguletian() throws Exception {
        int databaseSizeBeforeCreate = duguletianRepository.findAll().size();

        // Create the Duguletian
        restDuguletianMockMvc.perform(post("/api/duguletians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duguletian)))
            .andExpect(status().isCreated());

        // Validate the Duguletian in the database
        List<Duguletian> duguletianList = duguletianRepository.findAll();
        assertThat(duguletianList).hasSize(databaseSizeBeforeCreate + 1);
        Duguletian testDuguletian = duguletianList.get(duguletianList.size() - 1);
        assertThat(testDuguletian.getDuguletian_name()).isEqualTo(DEFAULT_DUGULETIAN_NAME);
    }

    @Test
    @Transactional
    public void createDuguletianWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = duguletianRepository.findAll().size();

        // Create the Duguletian with an existing ID
        duguletian.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDuguletianMockMvc.perform(post("/api/duguletians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duguletian)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Duguletian> duguletianList = duguletianRepository.findAll();
        assertThat(duguletianList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDuguletians() throws Exception {
        // Initialize the database
        duguletianRepository.saveAndFlush(duguletian);

        // Get all the duguletianList
        restDuguletianMockMvc.perform(get("/api/duguletians?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(duguletian.getId().intValue())))
            .andExpect(jsonPath("$.[*].duguletian_name").value(hasItem(DEFAULT_DUGULETIAN_NAME.toString())));
    }

    @Test
    @Transactional
    public void getDuguletian() throws Exception {
        // Initialize the database
        duguletianRepository.saveAndFlush(duguletian);

        // Get the duguletian
        restDuguletianMockMvc.perform(get("/api/duguletians/{id}", duguletian.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(duguletian.getId().intValue()))
            .andExpect(jsonPath("$.duguletian_name").value(DEFAULT_DUGULETIAN_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDuguletian() throws Exception {
        // Get the duguletian
        restDuguletianMockMvc.perform(get("/api/duguletians/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDuguletian() throws Exception {
        // Initialize the database
        duguletianRepository.saveAndFlush(duguletian);
        int databaseSizeBeforeUpdate = duguletianRepository.findAll().size();

        // Update the duguletian
        Duguletian updatedDuguletian = duguletianRepository.findOne(duguletian.getId());
        updatedDuguletian
            .duguletian_name(UPDATED_DUGULETIAN_NAME);

        restDuguletianMockMvc.perform(put("/api/duguletians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDuguletian)))
            .andExpect(status().isOk());

        // Validate the Duguletian in the database
        List<Duguletian> duguletianList = duguletianRepository.findAll();
        assertThat(duguletianList).hasSize(databaseSizeBeforeUpdate);
        Duguletian testDuguletian = duguletianList.get(duguletianList.size() - 1);
        assertThat(testDuguletian.getDuguletian_name()).isEqualTo(UPDATED_DUGULETIAN_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingDuguletian() throws Exception {
        int databaseSizeBeforeUpdate = duguletianRepository.findAll().size();

        // Create the Duguletian

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDuguletianMockMvc.perform(put("/api/duguletians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(duguletian)))
            .andExpect(status().isCreated());

        // Validate the Duguletian in the database
        List<Duguletian> duguletianList = duguletianRepository.findAll();
        assertThat(duguletianList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDuguletian() throws Exception {
        // Initialize the database
        duguletianRepository.saveAndFlush(duguletian);
        int databaseSizeBeforeDelete = duguletianRepository.findAll().size();

        // Get the duguletian
        restDuguletianMockMvc.perform(delete("/api/duguletians/{id}", duguletian.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Duguletian> duguletianList = duguletianRepository.findAll();
        assertThat(duguletianList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Duguletian.class);
    }
}
