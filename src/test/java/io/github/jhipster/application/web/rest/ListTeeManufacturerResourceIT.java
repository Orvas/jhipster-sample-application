package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListTeeManufacturer;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.repository.ListTeeManufacturerRepository;
import io.github.jhipster.application.service.ListTeeManufacturerService;
import io.github.jhipster.application.service.dto.ListTeeManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListTeeManufacturerMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListTeeManufacturerCriteria;
import io.github.jhipster.application.service.ListTeeManufacturerQueryService;

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
 * Integration tests for the {@Link ListTeeManufacturerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListTeeManufacturerResourceIT {

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
    private ListTeeManufacturerRepository listTeeManufacturerRepository;

    @Autowired
    private ListTeeManufacturerMapper listTeeManufacturerMapper;

    @Autowired
    private ListTeeManufacturerService listTeeManufacturerService;

    @Autowired
    private ListTeeManufacturerQueryService listTeeManufacturerQueryService;

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

    private MockMvc restListTeeManufacturerMockMvc;

    private ListTeeManufacturer listTeeManufacturer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListTeeManufacturerResource listTeeManufacturerResource = new ListTeeManufacturerResource(listTeeManufacturerService, listTeeManufacturerQueryService);
        this.restListTeeManufacturerMockMvc = MockMvcBuilders.standaloneSetup(listTeeManufacturerResource)
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
    public static ListTeeManufacturer createEntity(EntityManager em) {
        ListTeeManufacturer listTeeManufacturer = new ListTeeManufacturer()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listTeeManufacturer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListTeeManufacturer createUpdatedEntity(EntityManager em) {
        ListTeeManufacturer listTeeManufacturer = new ListTeeManufacturer()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listTeeManufacturer;
    }

    @BeforeEach
    public void initTest() {
        listTeeManufacturer = createEntity(em);
    }

    @Test
    @Transactional
    public void createListTeeManufacturer() throws Exception {
        int databaseSizeBeforeCreate = listTeeManufacturerRepository.findAll().size();

        // Create the ListTeeManufacturer
        ListTeeManufacturerDTO listTeeManufacturerDTO = listTeeManufacturerMapper.toDto(listTeeManufacturer);
        restListTeeManufacturerMockMvc.perform(post("/api/list-tee-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeManufacturerDTO)))
            .andExpect(status().isCreated());

        // Validate the ListTeeManufacturer in the database
        List<ListTeeManufacturer> listTeeManufacturerList = listTeeManufacturerRepository.findAll();
        assertThat(listTeeManufacturerList).hasSize(databaseSizeBeforeCreate + 1);
        ListTeeManufacturer testListTeeManufacturer = listTeeManufacturerList.get(listTeeManufacturerList.size() - 1);
        assertThat(testListTeeManufacturer.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListTeeManufacturer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListTeeManufacturer.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListTeeManufacturer.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListTeeManufacturer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListTeeManufacturer.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListTeeManufacturer.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListTeeManufacturer.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListTeeManufacturer.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListTeeManufacturerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listTeeManufacturerRepository.findAll().size();

        // Create the ListTeeManufacturer with an existing ID
        listTeeManufacturer.setId(1L);
        ListTeeManufacturerDTO listTeeManufacturerDTO = listTeeManufacturerMapper.toDto(listTeeManufacturer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListTeeManufacturerMockMvc.perform(post("/api/list-tee-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListTeeManufacturer in the database
        List<ListTeeManufacturer> listTeeManufacturerList = listTeeManufacturerRepository.findAll();
        assertThat(listTeeManufacturerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeManufacturerRepository.findAll().size();
        // set the field null
        listTeeManufacturer.setCode(null);

        // Create the ListTeeManufacturer, which fails.
        ListTeeManufacturerDTO listTeeManufacturerDTO = listTeeManufacturerMapper.toDto(listTeeManufacturer);

        restListTeeManufacturerMockMvc.perform(post("/api/list-tee-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeManufacturer> listTeeManufacturerList = listTeeManufacturerRepository.findAll();
        assertThat(listTeeManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeManufacturerRepository.findAll().size();
        // set the field null
        listTeeManufacturer.setName(null);

        // Create the ListTeeManufacturer, which fails.
        ListTeeManufacturerDTO listTeeManufacturerDTO = listTeeManufacturerMapper.toDto(listTeeManufacturer);

        restListTeeManufacturerMockMvc.perform(post("/api/list-tee-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeManufacturer> listTeeManufacturerList = listTeeManufacturerRepository.findAll();
        assertThat(listTeeManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeManufacturerRepository.findAll().size();
        // set the field null
        listTeeManufacturer.setFullName(null);

        // Create the ListTeeManufacturer, which fails.
        ListTeeManufacturerDTO listTeeManufacturerDTO = listTeeManufacturerMapper.toDto(listTeeManufacturer);

        restListTeeManufacturerMockMvc.perform(post("/api/list-tee-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeManufacturer> listTeeManufacturerList = listTeeManufacturerRepository.findAll();
        assertThat(listTeeManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeManufacturerRepository.findAll().size();
        // set the field null
        listTeeManufacturer.setIsCurrentFlag(null);

        // Create the ListTeeManufacturer, which fails.
        ListTeeManufacturerDTO listTeeManufacturerDTO = listTeeManufacturerMapper.toDto(listTeeManufacturer);

        restListTeeManufacturerMockMvc.perform(post("/api/list-tee-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeManufacturer> listTeeManufacturerList = listTeeManufacturerRepository.findAll();
        assertThat(listTeeManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturers() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList
        restListTeeManufacturerMockMvc.perform(get("/api/list-tee-manufacturers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listTeeManufacturer.getId().intValue())))
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
    public void getListTeeManufacturer() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get the listTeeManufacturer
        restListTeeManufacturerMockMvc.perform(get("/api/list-tee-manufacturers/{id}", listTeeManufacturer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listTeeManufacturer.getId().intValue()))
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
    public void getAllListTeeManufacturersByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where code equals to DEFAULT_CODE
        defaultListTeeManufacturerShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listTeeManufacturerList where code equals to UPDATED_CODE
        defaultListTeeManufacturerShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListTeeManufacturerShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listTeeManufacturerList where code equals to UPDATED_CODE
        defaultListTeeManufacturerShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where code is not null
        defaultListTeeManufacturerShouldBeFound("code.specified=true");

        // Get all the listTeeManufacturerList where code is null
        defaultListTeeManufacturerShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where name equals to DEFAULT_NAME
        defaultListTeeManufacturerShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listTeeManufacturerList where name equals to UPDATED_NAME
        defaultListTeeManufacturerShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListTeeManufacturerShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listTeeManufacturerList where name equals to UPDATED_NAME
        defaultListTeeManufacturerShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where name is not null
        defaultListTeeManufacturerShouldBeFound("name.specified=true");

        // Get all the listTeeManufacturerList where name is null
        defaultListTeeManufacturerShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where fullName equals to DEFAULT_FULL_NAME
        defaultListTeeManufacturerShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listTeeManufacturerList where fullName equals to UPDATED_FULL_NAME
        defaultListTeeManufacturerShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListTeeManufacturerShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listTeeManufacturerList where fullName equals to UPDATED_FULL_NAME
        defaultListTeeManufacturerShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where fullName is not null
        defaultListTeeManufacturerShouldBeFound("fullName.specified=true");

        // Get all the listTeeManufacturerList where fullName is null
        defaultListTeeManufacturerShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListTeeManufacturerShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listTeeManufacturerList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeManufacturerShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListTeeManufacturerShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listTeeManufacturerList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeManufacturerShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where isCurrentFlag is not null
        defaultListTeeManufacturerShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listTeeManufacturerList where isCurrentFlag is null
        defaultListTeeManufacturerShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListTeeManufacturerShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listTeeManufacturerList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeManufacturerShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListTeeManufacturerShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listTeeManufacturerList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeManufacturerShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListTeeManufacturersByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where description equals to DEFAULT_DESCRIPTION
        defaultListTeeManufacturerShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listTeeManufacturerList where description equals to UPDATED_DESCRIPTION
        defaultListTeeManufacturerShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListTeeManufacturerShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listTeeManufacturerList where description equals to UPDATED_DESCRIPTION
        defaultListTeeManufacturerShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where description is not null
        defaultListTeeManufacturerShouldBeFound("description.specified=true");

        // Get all the listTeeManufacturerList where description is null
        defaultListTeeManufacturerShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListTeeManufacturerShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listTeeManufacturerList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListTeeManufacturerShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListTeeManufacturerShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listTeeManufacturerList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListTeeManufacturerShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where dateCreate is not null
        defaultListTeeManufacturerShouldBeFound("dateCreate.specified=true");

        // Get all the listTeeManufacturerList where dateCreate is null
        defaultListTeeManufacturerShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListTeeManufacturerShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listTeeManufacturerList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListTeeManufacturerShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListTeeManufacturerShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listTeeManufacturerList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListTeeManufacturerShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where dateEdit is not null
        defaultListTeeManufacturerShouldBeFound("dateEdit.specified=true");

        // Get all the listTeeManufacturerList where dateEdit is null
        defaultListTeeManufacturerShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where creator equals to DEFAULT_CREATOR
        defaultListTeeManufacturerShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listTeeManufacturerList where creator equals to UPDATED_CREATOR
        defaultListTeeManufacturerShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListTeeManufacturerShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listTeeManufacturerList where creator equals to UPDATED_CREATOR
        defaultListTeeManufacturerShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where creator is not null
        defaultListTeeManufacturerShouldBeFound("creator.specified=true");

        // Get all the listTeeManufacturerList where creator is null
        defaultListTeeManufacturerShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where editor equals to DEFAULT_EDITOR
        defaultListTeeManufacturerShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listTeeManufacturerList where editor equals to UPDATED_EDITOR
        defaultListTeeManufacturerShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListTeeManufacturerShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listTeeManufacturerList where editor equals to UPDATED_EDITOR
        defaultListTeeManufacturerShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        // Get all the listTeeManufacturerList where editor is not null
        defaultListTeeManufacturerShouldBeFound("editor.specified=true");

        // Get all the listTeeManufacturerList where editor is null
        defaultListTeeManufacturerShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeManufacturersByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listTeeManufacturer.addTeeHist(teeHist);
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);
        Long teeHistId = teeHist.getId();

        // Get all the listTeeManufacturerList where teeHist equals to teeHistId
        defaultListTeeManufacturerShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listTeeManufacturerList where teeHist equals to teeHistId + 1
        defaultListTeeManufacturerShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListTeeManufacturerShouldBeFound(String filter) throws Exception {
        restListTeeManufacturerMockMvc.perform(get("/api/list-tee-manufacturers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listTeeManufacturer.getId().intValue())))
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
        restListTeeManufacturerMockMvc.perform(get("/api/list-tee-manufacturers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListTeeManufacturerShouldNotBeFound(String filter) throws Exception {
        restListTeeManufacturerMockMvc.perform(get("/api/list-tee-manufacturers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListTeeManufacturerMockMvc.perform(get("/api/list-tee-manufacturers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListTeeManufacturer() throws Exception {
        // Get the listTeeManufacturer
        restListTeeManufacturerMockMvc.perform(get("/api/list-tee-manufacturers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListTeeManufacturer() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        int databaseSizeBeforeUpdate = listTeeManufacturerRepository.findAll().size();

        // Update the listTeeManufacturer
        ListTeeManufacturer updatedListTeeManufacturer = listTeeManufacturerRepository.findById(listTeeManufacturer.getId()).get();
        // Disconnect from session so that the updates on updatedListTeeManufacturer are not directly saved in db
        em.detach(updatedListTeeManufacturer);
        updatedListTeeManufacturer
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListTeeManufacturerDTO listTeeManufacturerDTO = listTeeManufacturerMapper.toDto(updatedListTeeManufacturer);

        restListTeeManufacturerMockMvc.perform(put("/api/list-tee-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeManufacturerDTO)))
            .andExpect(status().isOk());

        // Validate the ListTeeManufacturer in the database
        List<ListTeeManufacturer> listTeeManufacturerList = listTeeManufacturerRepository.findAll();
        assertThat(listTeeManufacturerList).hasSize(databaseSizeBeforeUpdate);
        ListTeeManufacturer testListTeeManufacturer = listTeeManufacturerList.get(listTeeManufacturerList.size() - 1);
        assertThat(testListTeeManufacturer.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListTeeManufacturer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListTeeManufacturer.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListTeeManufacturer.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListTeeManufacturer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListTeeManufacturer.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListTeeManufacturer.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListTeeManufacturer.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListTeeManufacturer.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListTeeManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = listTeeManufacturerRepository.findAll().size();

        // Create the ListTeeManufacturer
        ListTeeManufacturerDTO listTeeManufacturerDTO = listTeeManufacturerMapper.toDto(listTeeManufacturer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListTeeManufacturerMockMvc.perform(put("/api/list-tee-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListTeeManufacturer in the database
        List<ListTeeManufacturer> listTeeManufacturerList = listTeeManufacturerRepository.findAll();
        assertThat(listTeeManufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListTeeManufacturer() throws Exception {
        // Initialize the database
        listTeeManufacturerRepository.saveAndFlush(listTeeManufacturer);

        int databaseSizeBeforeDelete = listTeeManufacturerRepository.findAll().size();

        // Delete the listTeeManufacturer
        restListTeeManufacturerMockMvc.perform(delete("/api/list-tee-manufacturers/{id}", listTeeManufacturer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListTeeManufacturer> listTeeManufacturerList = listTeeManufacturerRepository.findAll();
        assertThat(listTeeManufacturerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListTeeManufacturer.class);
        ListTeeManufacturer listTeeManufacturer1 = new ListTeeManufacturer();
        listTeeManufacturer1.setId(1L);
        ListTeeManufacturer listTeeManufacturer2 = new ListTeeManufacturer();
        listTeeManufacturer2.setId(listTeeManufacturer1.getId());
        assertThat(listTeeManufacturer1).isEqualTo(listTeeManufacturer2);
        listTeeManufacturer2.setId(2L);
        assertThat(listTeeManufacturer1).isNotEqualTo(listTeeManufacturer2);
        listTeeManufacturer1.setId(null);
        assertThat(listTeeManufacturer1).isNotEqualTo(listTeeManufacturer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListTeeManufacturerDTO.class);
        ListTeeManufacturerDTO listTeeManufacturerDTO1 = new ListTeeManufacturerDTO();
        listTeeManufacturerDTO1.setId(1L);
        ListTeeManufacturerDTO listTeeManufacturerDTO2 = new ListTeeManufacturerDTO();
        assertThat(listTeeManufacturerDTO1).isNotEqualTo(listTeeManufacturerDTO2);
        listTeeManufacturerDTO2.setId(listTeeManufacturerDTO1.getId());
        assertThat(listTeeManufacturerDTO1).isEqualTo(listTeeManufacturerDTO2);
        listTeeManufacturerDTO2.setId(2L);
        assertThat(listTeeManufacturerDTO1).isNotEqualTo(listTeeManufacturerDTO2);
        listTeeManufacturerDTO1.setId(null);
        assertThat(listTeeManufacturerDTO1).isNotEqualTo(listTeeManufacturerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listTeeManufacturerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listTeeManufacturerMapper.fromId(null)).isNull();
    }
}
