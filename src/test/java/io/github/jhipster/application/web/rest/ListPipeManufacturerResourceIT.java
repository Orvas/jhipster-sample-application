package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListPipeManufacturer;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.repository.ListPipeManufacturerRepository;
import io.github.jhipster.application.service.ListPipeManufacturerService;
import io.github.jhipster.application.service.dto.ListPipeManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListPipeManufacturerMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListPipeManufacturerCriteria;
import io.github.jhipster.application.service.ListPipeManufacturerQueryService;

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
 * Integration tests for the {@Link ListPipeManufacturerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListPipeManufacturerResourceIT {

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
    private ListPipeManufacturerRepository listPipeManufacturerRepository;

    @Autowired
    private ListPipeManufacturerMapper listPipeManufacturerMapper;

    @Autowired
    private ListPipeManufacturerService listPipeManufacturerService;

    @Autowired
    private ListPipeManufacturerQueryService listPipeManufacturerQueryService;

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

    private MockMvc restListPipeManufacturerMockMvc;

    private ListPipeManufacturer listPipeManufacturer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListPipeManufacturerResource listPipeManufacturerResource = new ListPipeManufacturerResource(listPipeManufacturerService, listPipeManufacturerQueryService);
        this.restListPipeManufacturerMockMvc = MockMvcBuilders.standaloneSetup(listPipeManufacturerResource)
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
    public static ListPipeManufacturer createEntity(EntityManager em) {
        ListPipeManufacturer listPipeManufacturer = new ListPipeManufacturer()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listPipeManufacturer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListPipeManufacturer createUpdatedEntity(EntityManager em) {
        ListPipeManufacturer listPipeManufacturer = new ListPipeManufacturer()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listPipeManufacturer;
    }

    @BeforeEach
    public void initTest() {
        listPipeManufacturer = createEntity(em);
    }

    @Test
    @Transactional
    public void createListPipeManufacturer() throws Exception {
        int databaseSizeBeforeCreate = listPipeManufacturerRepository.findAll().size();

        // Create the ListPipeManufacturer
        ListPipeManufacturerDTO listPipeManufacturerDTO = listPipeManufacturerMapper.toDto(listPipeManufacturer);
        restListPipeManufacturerMockMvc.perform(post("/api/list-pipe-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeManufacturerDTO)))
            .andExpect(status().isCreated());

        // Validate the ListPipeManufacturer in the database
        List<ListPipeManufacturer> listPipeManufacturerList = listPipeManufacturerRepository.findAll();
        assertThat(listPipeManufacturerList).hasSize(databaseSizeBeforeCreate + 1);
        ListPipeManufacturer testListPipeManufacturer = listPipeManufacturerList.get(listPipeManufacturerList.size() - 1);
        assertThat(testListPipeManufacturer.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListPipeManufacturer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListPipeManufacturer.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListPipeManufacturer.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListPipeManufacturer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListPipeManufacturer.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListPipeManufacturer.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListPipeManufacturer.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListPipeManufacturer.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListPipeManufacturerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listPipeManufacturerRepository.findAll().size();

        // Create the ListPipeManufacturer with an existing ID
        listPipeManufacturer.setId(1L);
        ListPipeManufacturerDTO listPipeManufacturerDTO = listPipeManufacturerMapper.toDto(listPipeManufacturer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListPipeManufacturerMockMvc.perform(post("/api/list-pipe-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListPipeManufacturer in the database
        List<ListPipeManufacturer> listPipeManufacturerList = listPipeManufacturerRepository.findAll();
        assertThat(listPipeManufacturerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipeManufacturerRepository.findAll().size();
        // set the field null
        listPipeManufacturer.setCode(null);

        // Create the ListPipeManufacturer, which fails.
        ListPipeManufacturerDTO listPipeManufacturerDTO = listPipeManufacturerMapper.toDto(listPipeManufacturer);

        restListPipeManufacturerMockMvc.perform(post("/api/list-pipe-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipeManufacturer> listPipeManufacturerList = listPipeManufacturerRepository.findAll();
        assertThat(listPipeManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipeManufacturerRepository.findAll().size();
        // set the field null
        listPipeManufacturer.setName(null);

        // Create the ListPipeManufacturer, which fails.
        ListPipeManufacturerDTO listPipeManufacturerDTO = listPipeManufacturerMapper.toDto(listPipeManufacturer);

        restListPipeManufacturerMockMvc.perform(post("/api/list-pipe-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipeManufacturer> listPipeManufacturerList = listPipeManufacturerRepository.findAll();
        assertThat(listPipeManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipeManufacturerRepository.findAll().size();
        // set the field null
        listPipeManufacturer.setFullName(null);

        // Create the ListPipeManufacturer, which fails.
        ListPipeManufacturerDTO listPipeManufacturerDTO = listPipeManufacturerMapper.toDto(listPipeManufacturer);

        restListPipeManufacturerMockMvc.perform(post("/api/list-pipe-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipeManufacturer> listPipeManufacturerList = listPipeManufacturerRepository.findAll();
        assertThat(listPipeManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipeManufacturerRepository.findAll().size();
        // set the field null
        listPipeManufacturer.setIsCurrentFlag(null);

        // Create the ListPipeManufacturer, which fails.
        ListPipeManufacturerDTO listPipeManufacturerDTO = listPipeManufacturerMapper.toDto(listPipeManufacturer);

        restListPipeManufacturerMockMvc.perform(post("/api/list-pipe-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipeManufacturer> listPipeManufacturerList = listPipeManufacturerRepository.findAll();
        assertThat(listPipeManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturers() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList
        restListPipeManufacturerMockMvc.perform(get("/api/list-pipe-manufacturers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listPipeManufacturer.getId().intValue())))
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
    public void getListPipeManufacturer() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get the listPipeManufacturer
        restListPipeManufacturerMockMvc.perform(get("/api/list-pipe-manufacturers/{id}", listPipeManufacturer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listPipeManufacturer.getId().intValue()))
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
    public void getAllListPipeManufacturersByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where code equals to DEFAULT_CODE
        defaultListPipeManufacturerShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listPipeManufacturerList where code equals to UPDATED_CODE
        defaultListPipeManufacturerShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListPipeManufacturerShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listPipeManufacturerList where code equals to UPDATED_CODE
        defaultListPipeManufacturerShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where code is not null
        defaultListPipeManufacturerShouldBeFound("code.specified=true");

        // Get all the listPipeManufacturerList where code is null
        defaultListPipeManufacturerShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where name equals to DEFAULT_NAME
        defaultListPipeManufacturerShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listPipeManufacturerList where name equals to UPDATED_NAME
        defaultListPipeManufacturerShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListPipeManufacturerShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listPipeManufacturerList where name equals to UPDATED_NAME
        defaultListPipeManufacturerShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where name is not null
        defaultListPipeManufacturerShouldBeFound("name.specified=true");

        // Get all the listPipeManufacturerList where name is null
        defaultListPipeManufacturerShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where fullName equals to DEFAULT_FULL_NAME
        defaultListPipeManufacturerShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listPipeManufacturerList where fullName equals to UPDATED_FULL_NAME
        defaultListPipeManufacturerShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListPipeManufacturerShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listPipeManufacturerList where fullName equals to UPDATED_FULL_NAME
        defaultListPipeManufacturerShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where fullName is not null
        defaultListPipeManufacturerShouldBeFound("fullName.specified=true");

        // Get all the listPipeManufacturerList where fullName is null
        defaultListPipeManufacturerShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipeManufacturerShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipeManufacturerList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipeManufacturerShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListPipeManufacturerShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listPipeManufacturerList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipeManufacturerShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where isCurrentFlag is not null
        defaultListPipeManufacturerShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listPipeManufacturerList where isCurrentFlag is null
        defaultListPipeManufacturerShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipeManufacturerShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipeManufacturerList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipeManufacturerShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipeManufacturerShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipeManufacturerList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipeManufacturerShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListPipeManufacturersByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where description equals to DEFAULT_DESCRIPTION
        defaultListPipeManufacturerShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listPipeManufacturerList where description equals to UPDATED_DESCRIPTION
        defaultListPipeManufacturerShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListPipeManufacturerShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listPipeManufacturerList where description equals to UPDATED_DESCRIPTION
        defaultListPipeManufacturerShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where description is not null
        defaultListPipeManufacturerShouldBeFound("description.specified=true");

        // Get all the listPipeManufacturerList where description is null
        defaultListPipeManufacturerShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListPipeManufacturerShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listPipeManufacturerList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListPipeManufacturerShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListPipeManufacturerShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listPipeManufacturerList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListPipeManufacturerShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where dateCreate is not null
        defaultListPipeManufacturerShouldBeFound("dateCreate.specified=true");

        // Get all the listPipeManufacturerList where dateCreate is null
        defaultListPipeManufacturerShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListPipeManufacturerShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listPipeManufacturerList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListPipeManufacturerShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListPipeManufacturerShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listPipeManufacturerList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListPipeManufacturerShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where dateEdit is not null
        defaultListPipeManufacturerShouldBeFound("dateEdit.specified=true");

        // Get all the listPipeManufacturerList where dateEdit is null
        defaultListPipeManufacturerShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where creator equals to DEFAULT_CREATOR
        defaultListPipeManufacturerShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listPipeManufacturerList where creator equals to UPDATED_CREATOR
        defaultListPipeManufacturerShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListPipeManufacturerShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listPipeManufacturerList where creator equals to UPDATED_CREATOR
        defaultListPipeManufacturerShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where creator is not null
        defaultListPipeManufacturerShouldBeFound("creator.specified=true");

        // Get all the listPipeManufacturerList where creator is null
        defaultListPipeManufacturerShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where editor equals to DEFAULT_EDITOR
        defaultListPipeManufacturerShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listPipeManufacturerList where editor equals to UPDATED_EDITOR
        defaultListPipeManufacturerShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListPipeManufacturerShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listPipeManufacturerList where editor equals to UPDATED_EDITOR
        defaultListPipeManufacturerShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        // Get all the listPipeManufacturerList where editor is not null
        defaultListPipeManufacturerShouldBeFound("editor.specified=true");

        // Get all the listPipeManufacturerList where editor is null
        defaultListPipeManufacturerShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeManufacturersByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listPipeManufacturer.addPipeHist(pipeHist);
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);
        Long pipeHistId = pipeHist.getId();

        // Get all the listPipeManufacturerList where pipeHist equals to pipeHistId
        defaultListPipeManufacturerShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listPipeManufacturerList where pipeHist equals to pipeHistId + 1
        defaultListPipeManufacturerShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListPipeManufacturerShouldBeFound(String filter) throws Exception {
        restListPipeManufacturerMockMvc.perform(get("/api/list-pipe-manufacturers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listPipeManufacturer.getId().intValue())))
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
        restListPipeManufacturerMockMvc.perform(get("/api/list-pipe-manufacturers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListPipeManufacturerShouldNotBeFound(String filter) throws Exception {
        restListPipeManufacturerMockMvc.perform(get("/api/list-pipe-manufacturers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListPipeManufacturerMockMvc.perform(get("/api/list-pipe-manufacturers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListPipeManufacturer() throws Exception {
        // Get the listPipeManufacturer
        restListPipeManufacturerMockMvc.perform(get("/api/list-pipe-manufacturers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListPipeManufacturer() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        int databaseSizeBeforeUpdate = listPipeManufacturerRepository.findAll().size();

        // Update the listPipeManufacturer
        ListPipeManufacturer updatedListPipeManufacturer = listPipeManufacturerRepository.findById(listPipeManufacturer.getId()).get();
        // Disconnect from session so that the updates on updatedListPipeManufacturer are not directly saved in db
        em.detach(updatedListPipeManufacturer);
        updatedListPipeManufacturer
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListPipeManufacturerDTO listPipeManufacturerDTO = listPipeManufacturerMapper.toDto(updatedListPipeManufacturer);

        restListPipeManufacturerMockMvc.perform(put("/api/list-pipe-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeManufacturerDTO)))
            .andExpect(status().isOk());

        // Validate the ListPipeManufacturer in the database
        List<ListPipeManufacturer> listPipeManufacturerList = listPipeManufacturerRepository.findAll();
        assertThat(listPipeManufacturerList).hasSize(databaseSizeBeforeUpdate);
        ListPipeManufacturer testListPipeManufacturer = listPipeManufacturerList.get(listPipeManufacturerList.size() - 1);
        assertThat(testListPipeManufacturer.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListPipeManufacturer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListPipeManufacturer.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListPipeManufacturer.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListPipeManufacturer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListPipeManufacturer.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListPipeManufacturer.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListPipeManufacturer.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListPipeManufacturer.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListPipeManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = listPipeManufacturerRepository.findAll().size();

        // Create the ListPipeManufacturer
        ListPipeManufacturerDTO listPipeManufacturerDTO = listPipeManufacturerMapper.toDto(listPipeManufacturer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListPipeManufacturerMockMvc.perform(put("/api/list-pipe-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeManufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListPipeManufacturer in the database
        List<ListPipeManufacturer> listPipeManufacturerList = listPipeManufacturerRepository.findAll();
        assertThat(listPipeManufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListPipeManufacturer() throws Exception {
        // Initialize the database
        listPipeManufacturerRepository.saveAndFlush(listPipeManufacturer);

        int databaseSizeBeforeDelete = listPipeManufacturerRepository.findAll().size();

        // Delete the listPipeManufacturer
        restListPipeManufacturerMockMvc.perform(delete("/api/list-pipe-manufacturers/{id}", listPipeManufacturer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListPipeManufacturer> listPipeManufacturerList = listPipeManufacturerRepository.findAll();
        assertThat(listPipeManufacturerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListPipeManufacturer.class);
        ListPipeManufacturer listPipeManufacturer1 = new ListPipeManufacturer();
        listPipeManufacturer1.setId(1L);
        ListPipeManufacturer listPipeManufacturer2 = new ListPipeManufacturer();
        listPipeManufacturer2.setId(listPipeManufacturer1.getId());
        assertThat(listPipeManufacturer1).isEqualTo(listPipeManufacturer2);
        listPipeManufacturer2.setId(2L);
        assertThat(listPipeManufacturer1).isNotEqualTo(listPipeManufacturer2);
        listPipeManufacturer1.setId(null);
        assertThat(listPipeManufacturer1).isNotEqualTo(listPipeManufacturer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListPipeManufacturerDTO.class);
        ListPipeManufacturerDTO listPipeManufacturerDTO1 = new ListPipeManufacturerDTO();
        listPipeManufacturerDTO1.setId(1L);
        ListPipeManufacturerDTO listPipeManufacturerDTO2 = new ListPipeManufacturerDTO();
        assertThat(listPipeManufacturerDTO1).isNotEqualTo(listPipeManufacturerDTO2);
        listPipeManufacturerDTO2.setId(listPipeManufacturerDTO1.getId());
        assertThat(listPipeManufacturerDTO1).isEqualTo(listPipeManufacturerDTO2);
        listPipeManufacturerDTO2.setId(2L);
        assertThat(listPipeManufacturerDTO1).isNotEqualTo(listPipeManufacturerDTO2);
        listPipeManufacturerDTO1.setId(null);
        assertThat(listPipeManufacturerDTO1).isNotEqualTo(listPipeManufacturerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listPipeManufacturerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listPipeManufacturerMapper.fromId(null)).isNull();
    }
}
