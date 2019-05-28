package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.ListObjectStatusRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ListObjectStatusResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListObjectStatusResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CURRENT_FLAG = false;
    private static final Boolean UPDATED_IS_CURRENT_FLAG = true;

    @Autowired
    private ListObjectStatusRepository listObjectStatusRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restListObjectStatusMockMvc;

    private ListObjectStatus listObjectStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListObjectStatusResource listObjectStatusResource = new ListObjectStatusResource(listObjectStatusRepository);
        this.restListObjectStatusMockMvc = MockMvcBuilders.standaloneSetup(listObjectStatusResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListObjectStatus createEntity(EntityManager em) {
        ListObjectStatus listObjectStatus = new ListObjectStatus()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG);
        return listObjectStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListObjectStatus createUpdatedEntity(EntityManager em) {
        ListObjectStatus listObjectStatus = new ListObjectStatus()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG);
        return listObjectStatus;
    }

    @BeforeEach
    public void initTest() {
        listObjectStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createListObjectStatus() throws Exception {
        int databaseSizeBeforeCreate = listObjectStatusRepository.findAll().size();

        // Create the ListObjectStatus
        restListObjectStatusMockMvc.perform(post("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatus)))
            .andExpect(status().isCreated());

        // Validate the ListObjectStatus in the database
        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ListObjectStatus testListObjectStatus = listObjectStatusList.get(listObjectStatusList.size() - 1);
        assertThat(testListObjectStatus.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListObjectStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListObjectStatus.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListObjectStatus.isIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void createListObjectStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listObjectStatusRepository.findAll().size();

        // Create the ListObjectStatus with an existing ID
        listObjectStatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListObjectStatusMockMvc.perform(post("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatus)))
            .andExpect(status().isBadRequest());

        // Validate the ListObjectStatus in the database
        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllListObjectStatuses() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get all the listObjectStatusList
        restListObjectStatusMockMvc.perform(get("/api/list-object-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listObjectStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getListObjectStatus() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        // Get the listObjectStatus
        restListObjectStatusMockMvc.perform(get("/api/list-object-statuses/{id}", listObjectStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listObjectStatus.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingListObjectStatus() throws Exception {
        // Get the listObjectStatus
        restListObjectStatusMockMvc.perform(get("/api/list-object-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListObjectStatus() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        int databaseSizeBeforeUpdate = listObjectStatusRepository.findAll().size();

        // Update the listObjectStatus
        ListObjectStatus updatedListObjectStatus = listObjectStatusRepository.findById(listObjectStatus.getId()).get();
        // Disconnect from session so that the updates on updatedListObjectStatus are not directly saved in db
        em.detach(updatedListObjectStatus);
        updatedListObjectStatus
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG);

        restListObjectStatusMockMvc.perform(put("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedListObjectStatus)))
            .andExpect(status().isOk());

        // Validate the ListObjectStatus in the database
        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeUpdate);
        ListObjectStatus testListObjectStatus = listObjectStatusList.get(listObjectStatusList.size() - 1);
        assertThat(testListObjectStatus.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListObjectStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListObjectStatus.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListObjectStatus.isIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void updateNonExistingListObjectStatus() throws Exception {
        int databaseSizeBeforeUpdate = listObjectStatusRepository.findAll().size();

        // Create the ListObjectStatus

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListObjectStatusMockMvc.perform(put("/api/list-object-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectStatus)))
            .andExpect(status().isBadRequest());

        // Validate the ListObjectStatus in the database
        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListObjectStatus() throws Exception {
        // Initialize the database
        listObjectStatusRepository.saveAndFlush(listObjectStatus);

        int databaseSizeBeforeDelete = listObjectStatusRepository.findAll().size();

        // Delete the listObjectStatus
        restListObjectStatusMockMvc.perform(delete("/api/list-object-statuses/{id}", listObjectStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListObjectStatus> listObjectStatusList = listObjectStatusRepository.findAll();
        assertThat(listObjectStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListObjectStatus.class);
        ListObjectStatus listObjectStatus1 = new ListObjectStatus();
        listObjectStatus1.setId(1L);
        ListObjectStatus listObjectStatus2 = new ListObjectStatus();
        listObjectStatus2.setId(listObjectStatus1.getId());
        assertThat(listObjectStatus1).isEqualTo(listObjectStatus2);
        listObjectStatus2.setId(2L);
        assertThat(listObjectStatus1).isNotEqualTo(listObjectStatus2);
        listObjectStatus1.setId(null);
        assertThat(listObjectStatus1).isNotEqualTo(listObjectStatus2);
    }
}
