package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListBucklarrManufacturer;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.repository.ListBucklarrManufacturerRepository;
import io.github.jhipster.application.service.ListBucklarrManufacturerService;
import io.github.jhipster.application.service.dto.ListBucklarrManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListBucklarrManufacturerMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListBucklarrManufacturerCriteria;
import io.github.jhipster.application.service.ListBucklarrManufacturerQueryService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ListBucklarrManufacturerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListBucklarrManufacturerResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private ListBucklarrManufacturerRepository listBucklarrManufacturerRepository;

    @Autowired
    private ListBucklarrManufacturerMapper listBucklarrManufacturerMapper;

    @Autowired
    private ListBucklarrManufacturerService listBucklarrManufacturerService;

    @Autowired
    private ListBucklarrManufacturerQueryService listBucklarrManufacturerQueryService;

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

    private MockMvc restListBucklarrManufacturerMockMvc;

    private ListBucklarrManufacturer listBucklarrManufacturer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListBucklarrManufacturerResource listBucklarrManufacturerResource = new ListBucklarrManufacturerResource(listBucklarrManufacturerService, listBucklarrManufacturerQueryService);
        this.restListBucklarrManufacturerMockMvc = MockMvcBuilders.standaloneSetup(listBucklarrManufacturerResource)
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
    public static ListBucklarrManufacturer createEntity(EntityManager em) {
        ListBucklarrManufacturer listBucklarrManufacturer = new ListBucklarrManufacturer()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listBucklarrManufacturer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListBucklarrManufacturer createUpdatedEntity(EntityManager em) {
        ListBucklarrManufacturer listBucklarrManufacturer = new ListBucklarrManufacturer()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listBucklarrManufacturer;
    }

    @BeforeEach
    public void initTest() {
        listBucklarrManufacturer = createEntity(em);
    }

    @Test
    @Transactional
    public void createListBucklarrManufacturer() throws Exception {
        int databaseSizeBeforeCreate = listBucklarrManufacturerRepository.findAll().size();

        // Create the ListBucklarrManufacturer
        ListBucklarrManufacturerDTO listBucklarrManufacturerDTO = listBucklarrManufacturerMapper.toDto(listBucklarrManufacturer);
        restListBucklarrManufacturerMockMvc.perform(post("/api/list-bucklarr-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrManufacturerDTO)))
            .andExpect(status().isCreated());

        // Validate the ListBucklarrManufacturer in the database
        List<ListBucklarrManufacturer> listBucklarrManufacturerList = listBucklarrManufacturerRepository.findAll();
        assertThat(listBucklarrManufacturerList).hasSize(databaseSizeBeforeCreate + 1);
        ListBucklarrManufacturer testListBucklarrManufacturer = listBucklarrManufacturerList.get(listBucklarrManufacturerList.size() - 1);
        assertThat(testListBucklarrManufacturer.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListBucklarrManufacturer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListBucklarrManufacturer.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListBucklarrManufacturer.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListBucklarrManufacturer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListBucklarrManufacturer.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListBucklarrManufacturer.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListBucklarrManufacturer.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListBucklarrManufacturer.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListBucklarrManufacturerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listBucklarrManufacturerRepository.findAll().size();

        // Create the ListBucklarrManufacturer with an existing ID
        listBucklarrManufacturer.setId(1L);
        ListBucklarrManufacturerDTO listBucklarrManufacturerDTO = listBucklarrManufacturerMapper.toDto(listBucklarrManufacturer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListBucklarrManufacturerMockMvc.perform(post("/api/list-bucklarr-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrManufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBucklarrManufacturer in the database
        List<ListBucklarrManufacturer> listBucklarrManufacturerList = listBucklarrManufacturerRepository.findAll();
        assertThat(listBucklarrManufacturerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrManufacturerRepository.findAll().size();
        // set the field null
        listBucklarrManufacturer.setCode(null);

        // Create the ListBucklarrManufacturer, which fails.
        ListBucklarrManufacturerDTO listBucklarrManufacturerDTO = listBucklarrManufacturerMapper.toDto(listBucklarrManufacturer);

        restListBucklarrManufacturerMockMvc.perform(post("/api/list-bucklarr-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrManufacturer> listBucklarrManufacturerList = listBucklarrManufacturerRepository.findAll();
        assertThat(listBucklarrManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrManufacturerRepository.findAll().size();
        // set the field null
        listBucklarrManufacturer.setName(null);

        // Create the ListBucklarrManufacturer, which fails.
        ListBucklarrManufacturerDTO listBucklarrManufacturerDTO = listBucklarrManufacturerMapper.toDto(listBucklarrManufacturer);

        restListBucklarrManufacturerMockMvc.perform(post("/api/list-bucklarr-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrManufacturer> listBucklarrManufacturerList = listBucklarrManufacturerRepository.findAll();
        assertThat(listBucklarrManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrManufacturerRepository.findAll().size();
        // set the field null
        listBucklarrManufacturer.setFullName(null);

        // Create the ListBucklarrManufacturer, which fails.
        ListBucklarrManufacturerDTO listBucklarrManufacturerDTO = listBucklarrManufacturerMapper.toDto(listBucklarrManufacturer);

        restListBucklarrManufacturerMockMvc.perform(post("/api/list-bucklarr-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrManufacturer> listBucklarrManufacturerList = listBucklarrManufacturerRepository.findAll();
        assertThat(listBucklarrManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrManufacturerRepository.findAll().size();
        // set the field null
        listBucklarrManufacturer.setIsCurrentFlag(null);

        // Create the ListBucklarrManufacturer, which fails.
        ListBucklarrManufacturerDTO listBucklarrManufacturerDTO = listBucklarrManufacturerMapper.toDto(listBucklarrManufacturer);

        restListBucklarrManufacturerMockMvc.perform(post("/api/list-bucklarr-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrManufacturer> listBucklarrManufacturerList = listBucklarrManufacturerRepository.findAll();
        assertThat(listBucklarrManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturers() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList
        restListBucklarrManufacturerMockMvc.perform(get("/api/list-bucklarr-manufacturers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBucklarrManufacturer.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getListBucklarrManufacturer() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get the listBucklarrManufacturer
        restListBucklarrManufacturerMockMvc.perform(get("/api/list-bucklarr-manufacturers/{id}", listBucklarrManufacturer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listBucklarrManufacturer.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where code equals to DEFAULT_CODE
        defaultListBucklarrManufacturerShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listBucklarrManufacturerList where code equals to UPDATED_CODE
        defaultListBucklarrManufacturerShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListBucklarrManufacturerShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listBucklarrManufacturerList where code equals to UPDATED_CODE
        defaultListBucklarrManufacturerShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where code is not null
        defaultListBucklarrManufacturerShouldBeFound("code.specified=true");

        // Get all the listBucklarrManufacturerList where code is null
        defaultListBucklarrManufacturerShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where name equals to DEFAULT_NAME
        defaultListBucklarrManufacturerShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listBucklarrManufacturerList where name equals to UPDATED_NAME
        defaultListBucklarrManufacturerShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListBucklarrManufacturerShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listBucklarrManufacturerList where name equals to UPDATED_NAME
        defaultListBucklarrManufacturerShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where name is not null
        defaultListBucklarrManufacturerShouldBeFound("name.specified=true");

        // Get all the listBucklarrManufacturerList where name is null
        defaultListBucklarrManufacturerShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where fullName equals to DEFAULT_FULL_NAME
        defaultListBucklarrManufacturerShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listBucklarrManufacturerList where fullName equals to UPDATED_FULL_NAME
        defaultListBucklarrManufacturerShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListBucklarrManufacturerShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listBucklarrManufacturerList where fullName equals to UPDATED_FULL_NAME
        defaultListBucklarrManufacturerShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where fullName is not null
        defaultListBucklarrManufacturerShouldBeFound("fullName.specified=true");

        // Get all the listBucklarrManufacturerList where fullName is null
        defaultListBucklarrManufacturerShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBucklarrManufacturerShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBucklarrManufacturerList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrManufacturerShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrManufacturerShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listBucklarrManufacturerList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrManufacturerShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where isCurrentFlag is not null
        defaultListBucklarrManufacturerShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listBucklarrManufacturerList where isCurrentFlag is null
        defaultListBucklarrManufacturerShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBucklarrManufacturerShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBucklarrManufacturerList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrManufacturerShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBucklarrManufacturerShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBucklarrManufacturerList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrManufacturerShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where description equals to DEFAULT_DESCRIPTION
        defaultListBucklarrManufacturerShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listBucklarrManufacturerList where description equals to UPDATED_DESCRIPTION
        defaultListBucklarrManufacturerShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListBucklarrManufacturerShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listBucklarrManufacturerList where description equals to UPDATED_DESCRIPTION
        defaultListBucklarrManufacturerShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where description is not null
        defaultListBucklarrManufacturerShouldBeFound("description.specified=true");

        // Get all the listBucklarrManufacturerList where description is null
        defaultListBucklarrManufacturerShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListBucklarrManufacturerShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listBucklarrManufacturerList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBucklarrManufacturerShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListBucklarrManufacturerShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listBucklarrManufacturerList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBucklarrManufacturerShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where dateCreate is not null
        defaultListBucklarrManufacturerShouldBeFound("dateCreate.specified=true");

        // Get all the listBucklarrManufacturerList where dateCreate is null
        defaultListBucklarrManufacturerShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListBucklarrManufacturerShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listBucklarrManufacturerList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBucklarrManufacturerShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListBucklarrManufacturerShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listBucklarrManufacturerList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBucklarrManufacturerShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where dateEdit is not null
        defaultListBucklarrManufacturerShouldBeFound("dateEdit.specified=true");

        // Get all the listBucklarrManufacturerList where dateEdit is null
        defaultListBucklarrManufacturerShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where creator equals to DEFAULT_CREATOR
        defaultListBucklarrManufacturerShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listBucklarrManufacturerList where creator equals to UPDATED_CREATOR
        defaultListBucklarrManufacturerShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListBucklarrManufacturerShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listBucklarrManufacturerList where creator equals to UPDATED_CREATOR
        defaultListBucklarrManufacturerShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where creator is not null
        defaultListBucklarrManufacturerShouldBeFound("creator.specified=true");

        // Get all the listBucklarrManufacturerList where creator is null
        defaultListBucklarrManufacturerShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where editor equals to DEFAULT_EDITOR
        defaultListBucklarrManufacturerShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listBucklarrManufacturerList where editor equals to UPDATED_EDITOR
        defaultListBucklarrManufacturerShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListBucklarrManufacturerShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listBucklarrManufacturerList where editor equals to UPDATED_EDITOR
        defaultListBucklarrManufacturerShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        // Get all the listBucklarrManufacturerList where editor is not null
        defaultListBucklarrManufacturerShouldBeFound("editor.specified=true");

        // Get all the listBucklarrManufacturerList where editor is null
        defaultListBucklarrManufacturerShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrManufacturersByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listBucklarrManufacturer.addBuckleArrestorHist(buckleArrestorHist);
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listBucklarrManufacturerList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListBucklarrManufacturerShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listBucklarrManufacturerList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListBucklarrManufacturerShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListBucklarrManufacturerShouldBeFound(String filter) throws Exception {
        restListBucklarrManufacturerMockMvc.perform(get("/api/list-bucklarr-manufacturers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBucklarrManufacturer.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restListBucklarrManufacturerMockMvc.perform(get("/api/list-bucklarr-manufacturers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListBucklarrManufacturerShouldNotBeFound(String filter) throws Exception {
        restListBucklarrManufacturerMockMvc.perform(get("/api/list-bucklarr-manufacturers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListBucklarrManufacturerMockMvc.perform(get("/api/list-bucklarr-manufacturers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListBucklarrManufacturer() throws Exception {
        // Get the listBucklarrManufacturer
        restListBucklarrManufacturerMockMvc.perform(get("/api/list-bucklarr-manufacturers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListBucklarrManufacturer() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        int databaseSizeBeforeUpdate = listBucklarrManufacturerRepository.findAll().size();

        // Update the listBucklarrManufacturer
        ListBucklarrManufacturer updatedListBucklarrManufacturer = listBucklarrManufacturerRepository.findById(listBucklarrManufacturer.getId()).get();
        // Disconnect from session so that the updates on updatedListBucklarrManufacturer are not directly saved in db
        em.detach(updatedListBucklarrManufacturer);
        updatedListBucklarrManufacturer
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListBucklarrManufacturerDTO listBucklarrManufacturerDTO = listBucklarrManufacturerMapper.toDto(updatedListBucklarrManufacturer);

        restListBucklarrManufacturerMockMvc.perform(put("/api/list-bucklarr-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrManufacturerDTO)))
            .andExpect(status().isOk());

        // Validate the ListBucklarrManufacturer in the database
        List<ListBucklarrManufacturer> listBucklarrManufacturerList = listBucklarrManufacturerRepository.findAll();
        assertThat(listBucklarrManufacturerList).hasSize(databaseSizeBeforeUpdate);
        ListBucklarrManufacturer testListBucklarrManufacturer = listBucklarrManufacturerList.get(listBucklarrManufacturerList.size() - 1);
        assertThat(testListBucklarrManufacturer.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListBucklarrManufacturer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListBucklarrManufacturer.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListBucklarrManufacturer.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListBucklarrManufacturer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListBucklarrManufacturer.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListBucklarrManufacturer.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListBucklarrManufacturer.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListBucklarrManufacturer.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListBucklarrManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = listBucklarrManufacturerRepository.findAll().size();

        // Create the ListBucklarrManufacturer
        ListBucklarrManufacturerDTO listBucklarrManufacturerDTO = listBucklarrManufacturerMapper.toDto(listBucklarrManufacturer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListBucklarrManufacturerMockMvc.perform(put("/api/list-bucklarr-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrManufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBucklarrManufacturer in the database
        List<ListBucklarrManufacturer> listBucklarrManufacturerList = listBucklarrManufacturerRepository.findAll();
        assertThat(listBucklarrManufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListBucklarrManufacturer() throws Exception {
        // Initialize the database
        listBucklarrManufacturerRepository.saveAndFlush(listBucklarrManufacturer);

        int databaseSizeBeforeDelete = listBucklarrManufacturerRepository.findAll().size();

        // Delete the listBucklarrManufacturer
        restListBucklarrManufacturerMockMvc.perform(delete("/api/list-bucklarr-manufacturers/{id}", listBucklarrManufacturer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListBucklarrManufacturer> listBucklarrManufacturerList = listBucklarrManufacturerRepository.findAll();
        assertThat(listBucklarrManufacturerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBucklarrManufacturer.class);
        ListBucklarrManufacturer listBucklarrManufacturer1 = new ListBucklarrManufacturer();
        listBucklarrManufacturer1.setId(1L);
        ListBucklarrManufacturer listBucklarrManufacturer2 = new ListBucklarrManufacturer();
        listBucklarrManufacturer2.setId(listBucklarrManufacturer1.getId());
        assertThat(listBucklarrManufacturer1).isEqualTo(listBucklarrManufacturer2);
        listBucklarrManufacturer2.setId(2L);
        assertThat(listBucklarrManufacturer1).isNotEqualTo(listBucklarrManufacturer2);
        listBucklarrManufacturer1.setId(null);
        assertThat(listBucklarrManufacturer1).isNotEqualTo(listBucklarrManufacturer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBucklarrManufacturerDTO.class);
        ListBucklarrManufacturerDTO listBucklarrManufacturerDTO1 = new ListBucklarrManufacturerDTO();
        listBucklarrManufacturerDTO1.setId(1L);
        ListBucklarrManufacturerDTO listBucklarrManufacturerDTO2 = new ListBucklarrManufacturerDTO();
        assertThat(listBucklarrManufacturerDTO1).isNotEqualTo(listBucklarrManufacturerDTO2);
        listBucklarrManufacturerDTO2.setId(listBucklarrManufacturerDTO1.getId());
        assertThat(listBucklarrManufacturerDTO1).isEqualTo(listBucklarrManufacturerDTO2);
        listBucklarrManufacturerDTO2.setId(2L);
        assertThat(listBucklarrManufacturerDTO1).isNotEqualTo(listBucklarrManufacturerDTO2);
        listBucklarrManufacturerDTO1.setId(null);
        assertThat(listBucklarrManufacturerDTO1).isNotEqualTo(listBucklarrManufacturerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listBucklarrManufacturerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listBucklarrManufacturerMapper.fromId(null)).isNull();
    }
}
