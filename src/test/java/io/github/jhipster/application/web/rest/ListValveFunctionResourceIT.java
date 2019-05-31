package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListValveFunction;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListValveFunctionRepository;
import io.github.jhipster.application.service.ListValveFunctionService;
import io.github.jhipster.application.service.dto.ListValveFunctionDTO;
import io.github.jhipster.application.service.mapper.ListValveFunctionMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListValveFunctionCriteria;
import io.github.jhipster.application.service.ListValveFunctionQueryService;

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
 * Integration tests for the {@Link ListValveFunctionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListValveFunctionResourceIT {

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
    private ListValveFunctionRepository listValveFunctionRepository;

    @Autowired
    private ListValveFunctionMapper listValveFunctionMapper;

    @Autowired
    private ListValveFunctionService listValveFunctionService;

    @Autowired
    private ListValveFunctionQueryService listValveFunctionQueryService;

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

    private MockMvc restListValveFunctionMockMvc;

    private ListValveFunction listValveFunction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListValveFunctionResource listValveFunctionResource = new ListValveFunctionResource(listValveFunctionService, listValveFunctionQueryService);
        this.restListValveFunctionMockMvc = MockMvcBuilders.standaloneSetup(listValveFunctionResource)
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
    public static ListValveFunction createEntity(EntityManager em) {
        ListValveFunction listValveFunction = new ListValveFunction()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listValveFunction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListValveFunction createUpdatedEntity(EntityManager em) {
        ListValveFunction listValveFunction = new ListValveFunction()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listValveFunction;
    }

    @BeforeEach
    public void initTest() {
        listValveFunction = createEntity(em);
    }

    @Test
    @Transactional
    public void createListValveFunction() throws Exception {
        int databaseSizeBeforeCreate = listValveFunctionRepository.findAll().size();

        // Create the ListValveFunction
        ListValveFunctionDTO listValveFunctionDTO = listValveFunctionMapper.toDto(listValveFunction);
        restListValveFunctionMockMvc.perform(post("/api/list-valve-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveFunctionDTO)))
            .andExpect(status().isCreated());

        // Validate the ListValveFunction in the database
        List<ListValveFunction> listValveFunctionList = listValveFunctionRepository.findAll();
        assertThat(listValveFunctionList).hasSize(databaseSizeBeforeCreate + 1);
        ListValveFunction testListValveFunction = listValveFunctionList.get(listValveFunctionList.size() - 1);
        assertThat(testListValveFunction.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListValveFunction.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListValveFunction.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListValveFunction.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListValveFunction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListValveFunction.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListValveFunction.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListValveFunction.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListValveFunction.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListValveFunctionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listValveFunctionRepository.findAll().size();

        // Create the ListValveFunction with an existing ID
        listValveFunction.setId(1L);
        ListValveFunctionDTO listValveFunctionDTO = listValveFunctionMapper.toDto(listValveFunction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListValveFunctionMockMvc.perform(post("/api/list-valve-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveFunctionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListValveFunction in the database
        List<ListValveFunction> listValveFunctionList = listValveFunctionRepository.findAll();
        assertThat(listValveFunctionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveFunctionRepository.findAll().size();
        // set the field null
        listValveFunction.setCode(null);

        // Create the ListValveFunction, which fails.
        ListValveFunctionDTO listValveFunctionDTO = listValveFunctionMapper.toDto(listValveFunction);

        restListValveFunctionMockMvc.perform(post("/api/list-valve-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveFunctionDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveFunction> listValveFunctionList = listValveFunctionRepository.findAll();
        assertThat(listValveFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveFunctionRepository.findAll().size();
        // set the field null
        listValveFunction.setName(null);

        // Create the ListValveFunction, which fails.
        ListValveFunctionDTO listValveFunctionDTO = listValveFunctionMapper.toDto(listValveFunction);

        restListValveFunctionMockMvc.perform(post("/api/list-valve-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveFunctionDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveFunction> listValveFunctionList = listValveFunctionRepository.findAll();
        assertThat(listValveFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveFunctionRepository.findAll().size();
        // set the field null
        listValveFunction.setFullName(null);

        // Create the ListValveFunction, which fails.
        ListValveFunctionDTO listValveFunctionDTO = listValveFunctionMapper.toDto(listValveFunction);

        restListValveFunctionMockMvc.perform(post("/api/list-valve-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveFunctionDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveFunction> listValveFunctionList = listValveFunctionRepository.findAll();
        assertThat(listValveFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveFunctionRepository.findAll().size();
        // set the field null
        listValveFunction.setIsCurrentFlag(null);

        // Create the ListValveFunction, which fails.
        ListValveFunctionDTO listValveFunctionDTO = listValveFunctionMapper.toDto(listValveFunction);

        restListValveFunctionMockMvc.perform(post("/api/list-valve-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveFunctionDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveFunction> listValveFunctionList = listValveFunctionRepository.findAll();
        assertThat(listValveFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListValveFunctions() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList
        restListValveFunctionMockMvc.perform(get("/api/list-valve-functions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listValveFunction.getId().intValue())))
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
    public void getListValveFunction() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get the listValveFunction
        restListValveFunctionMockMvc.perform(get("/api/list-valve-functions/{id}", listValveFunction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listValveFunction.getId().intValue()))
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
    public void getAllListValveFunctionsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where code equals to DEFAULT_CODE
        defaultListValveFunctionShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listValveFunctionList where code equals to UPDATED_CODE
        defaultListValveFunctionShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListValveFunctionShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listValveFunctionList where code equals to UPDATED_CODE
        defaultListValveFunctionShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where code is not null
        defaultListValveFunctionShouldBeFound("code.specified=true");

        // Get all the listValveFunctionList where code is null
        defaultListValveFunctionShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where name equals to DEFAULT_NAME
        defaultListValveFunctionShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listValveFunctionList where name equals to UPDATED_NAME
        defaultListValveFunctionShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListValveFunctionShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listValveFunctionList where name equals to UPDATED_NAME
        defaultListValveFunctionShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where name is not null
        defaultListValveFunctionShouldBeFound("name.specified=true");

        // Get all the listValveFunctionList where name is null
        defaultListValveFunctionShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where fullName equals to DEFAULT_FULL_NAME
        defaultListValveFunctionShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listValveFunctionList where fullName equals to UPDATED_FULL_NAME
        defaultListValveFunctionShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListValveFunctionShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listValveFunctionList where fullName equals to UPDATED_FULL_NAME
        defaultListValveFunctionShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where fullName is not null
        defaultListValveFunctionShouldBeFound("fullName.specified=true");

        // Get all the listValveFunctionList where fullName is null
        defaultListValveFunctionShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveFunctionShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveFunctionList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveFunctionShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListValveFunctionShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listValveFunctionList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveFunctionShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where isCurrentFlag is not null
        defaultListValveFunctionShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listValveFunctionList where isCurrentFlag is null
        defaultListValveFunctionShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveFunctionShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveFunctionList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveFunctionShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveFunctionShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveFunctionList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveFunctionShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListValveFunctionsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where description equals to DEFAULT_DESCRIPTION
        defaultListValveFunctionShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listValveFunctionList where description equals to UPDATED_DESCRIPTION
        defaultListValveFunctionShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListValveFunctionShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listValveFunctionList where description equals to UPDATED_DESCRIPTION
        defaultListValveFunctionShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where description is not null
        defaultListValveFunctionShouldBeFound("description.specified=true");

        // Get all the listValveFunctionList where description is null
        defaultListValveFunctionShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListValveFunctionShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listValveFunctionList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListValveFunctionShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListValveFunctionShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listValveFunctionList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListValveFunctionShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where dateCreate is not null
        defaultListValveFunctionShouldBeFound("dateCreate.specified=true");

        // Get all the listValveFunctionList where dateCreate is null
        defaultListValveFunctionShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListValveFunctionShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listValveFunctionList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListValveFunctionShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListValveFunctionShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listValveFunctionList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListValveFunctionShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where dateEdit is not null
        defaultListValveFunctionShouldBeFound("dateEdit.specified=true");

        // Get all the listValveFunctionList where dateEdit is null
        defaultListValveFunctionShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where creator equals to DEFAULT_CREATOR
        defaultListValveFunctionShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listValveFunctionList where creator equals to UPDATED_CREATOR
        defaultListValveFunctionShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListValveFunctionShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listValveFunctionList where creator equals to UPDATED_CREATOR
        defaultListValveFunctionShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where creator is not null
        defaultListValveFunctionShouldBeFound("creator.specified=true");

        // Get all the listValveFunctionList where creator is null
        defaultListValveFunctionShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where editor equals to DEFAULT_EDITOR
        defaultListValveFunctionShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listValveFunctionList where editor equals to UPDATED_EDITOR
        defaultListValveFunctionShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListValveFunctionShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listValveFunctionList where editor equals to UPDATED_EDITOR
        defaultListValveFunctionShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        // Get all the listValveFunctionList where editor is not null
        defaultListValveFunctionShouldBeFound("editor.specified=true");

        // Get all the listValveFunctionList where editor is null
        defaultListValveFunctionShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveFunctionsByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listValveFunction.addValveHist(valveHist);
        listValveFunctionRepository.saveAndFlush(listValveFunction);
        Long valveHistId = valveHist.getId();

        // Get all the listValveFunctionList where valveHist equals to valveHistId
        defaultListValveFunctionShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listValveFunctionList where valveHist equals to valveHistId + 1
        defaultListValveFunctionShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListValveFunctionShouldBeFound(String filter) throws Exception {
        restListValveFunctionMockMvc.perform(get("/api/list-valve-functions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listValveFunction.getId().intValue())))
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
        restListValveFunctionMockMvc.perform(get("/api/list-valve-functions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListValveFunctionShouldNotBeFound(String filter) throws Exception {
        restListValveFunctionMockMvc.perform(get("/api/list-valve-functions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListValveFunctionMockMvc.perform(get("/api/list-valve-functions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListValveFunction() throws Exception {
        // Get the listValveFunction
        restListValveFunctionMockMvc.perform(get("/api/list-valve-functions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListValveFunction() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        int databaseSizeBeforeUpdate = listValveFunctionRepository.findAll().size();

        // Update the listValveFunction
        ListValveFunction updatedListValveFunction = listValveFunctionRepository.findById(listValveFunction.getId()).get();
        // Disconnect from session so that the updates on updatedListValveFunction are not directly saved in db
        em.detach(updatedListValveFunction);
        updatedListValveFunction
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListValveFunctionDTO listValveFunctionDTO = listValveFunctionMapper.toDto(updatedListValveFunction);

        restListValveFunctionMockMvc.perform(put("/api/list-valve-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveFunctionDTO)))
            .andExpect(status().isOk());

        // Validate the ListValveFunction in the database
        List<ListValveFunction> listValveFunctionList = listValveFunctionRepository.findAll();
        assertThat(listValveFunctionList).hasSize(databaseSizeBeforeUpdate);
        ListValveFunction testListValveFunction = listValveFunctionList.get(listValveFunctionList.size() - 1);
        assertThat(testListValveFunction.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListValveFunction.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListValveFunction.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListValveFunction.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListValveFunction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListValveFunction.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListValveFunction.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListValveFunction.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListValveFunction.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListValveFunction() throws Exception {
        int databaseSizeBeforeUpdate = listValveFunctionRepository.findAll().size();

        // Create the ListValveFunction
        ListValveFunctionDTO listValveFunctionDTO = listValveFunctionMapper.toDto(listValveFunction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListValveFunctionMockMvc.perform(put("/api/list-valve-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveFunctionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListValveFunction in the database
        List<ListValveFunction> listValveFunctionList = listValveFunctionRepository.findAll();
        assertThat(listValveFunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListValveFunction() throws Exception {
        // Initialize the database
        listValveFunctionRepository.saveAndFlush(listValveFunction);

        int databaseSizeBeforeDelete = listValveFunctionRepository.findAll().size();

        // Delete the listValveFunction
        restListValveFunctionMockMvc.perform(delete("/api/list-valve-functions/{id}", listValveFunction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListValveFunction> listValveFunctionList = listValveFunctionRepository.findAll();
        assertThat(listValveFunctionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListValveFunction.class);
        ListValveFunction listValveFunction1 = new ListValveFunction();
        listValveFunction1.setId(1L);
        ListValveFunction listValveFunction2 = new ListValveFunction();
        listValveFunction2.setId(listValveFunction1.getId());
        assertThat(listValveFunction1).isEqualTo(listValveFunction2);
        listValveFunction2.setId(2L);
        assertThat(listValveFunction1).isNotEqualTo(listValveFunction2);
        listValveFunction1.setId(null);
        assertThat(listValveFunction1).isNotEqualTo(listValveFunction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListValveFunctionDTO.class);
        ListValveFunctionDTO listValveFunctionDTO1 = new ListValveFunctionDTO();
        listValveFunctionDTO1.setId(1L);
        ListValveFunctionDTO listValveFunctionDTO2 = new ListValveFunctionDTO();
        assertThat(listValveFunctionDTO1).isNotEqualTo(listValveFunctionDTO2);
        listValveFunctionDTO2.setId(listValveFunctionDTO1.getId());
        assertThat(listValveFunctionDTO1).isEqualTo(listValveFunctionDTO2);
        listValveFunctionDTO2.setId(2L);
        assertThat(listValveFunctionDTO1).isNotEqualTo(listValveFunctionDTO2);
        listValveFunctionDTO1.setId(null);
        assertThat(listValveFunctionDTO1).isNotEqualTo(listValveFunctionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listValveFunctionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listValveFunctionMapper.fromId(null)).isNull();
    }
}
