package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListClcResult;
import io.github.jhipster.application.repository.ListClcResultRepository;
import io.github.jhipster.application.service.ListClcResultService;
import io.github.jhipster.application.service.dto.ListClcResultDTO;
import io.github.jhipster.application.service.mapper.ListClcResultMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListClcResultCriteria;
import io.github.jhipster.application.service.ListClcResultQueryService;

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
 * Integration tests for the {@Link ListClcResultResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListClcResultResourceIT {

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
    private ListClcResultRepository listClcResultRepository;

    @Autowired
    private ListClcResultMapper listClcResultMapper;

    @Autowired
    private ListClcResultService listClcResultService;

    @Autowired
    private ListClcResultQueryService listClcResultQueryService;

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

    private MockMvc restListClcResultMockMvc;

    private ListClcResult listClcResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListClcResultResource listClcResultResource = new ListClcResultResource(listClcResultService, listClcResultQueryService);
        this.restListClcResultMockMvc = MockMvcBuilders.standaloneSetup(listClcResultResource)
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
    public static ListClcResult createEntity(EntityManager em) {
        ListClcResult listClcResult = new ListClcResult()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listClcResult;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListClcResult createUpdatedEntity(EntityManager em) {
        ListClcResult listClcResult = new ListClcResult()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listClcResult;
    }

    @BeforeEach
    public void initTest() {
        listClcResult = createEntity(em);
    }

    @Test
    @Transactional
    public void createListClcResult() throws Exception {
        int databaseSizeBeforeCreate = listClcResultRepository.findAll().size();

        // Create the ListClcResult
        ListClcResultDTO listClcResultDTO = listClcResultMapper.toDto(listClcResult);
        restListClcResultMockMvc.perform(post("/api/list-clc-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcResultDTO)))
            .andExpect(status().isCreated());

        // Validate the ListClcResult in the database
        List<ListClcResult> listClcResultList = listClcResultRepository.findAll();
        assertThat(listClcResultList).hasSize(databaseSizeBeforeCreate + 1);
        ListClcResult testListClcResult = listClcResultList.get(listClcResultList.size() - 1);
        assertThat(testListClcResult.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListClcResult.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListClcResult.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListClcResult.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListClcResult.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListClcResult.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListClcResult.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListClcResult.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListClcResult.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListClcResultWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listClcResultRepository.findAll().size();

        // Create the ListClcResult with an existing ID
        listClcResult.setId(1L);
        ListClcResultDTO listClcResultDTO = listClcResultMapper.toDto(listClcResult);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListClcResultMockMvc.perform(post("/api/list-clc-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcResultDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListClcResult in the database
        List<ListClcResult> listClcResultList = listClcResultRepository.findAll();
        assertThat(listClcResultList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcResultRepository.findAll().size();
        // set the field null
        listClcResult.setCode(null);

        // Create the ListClcResult, which fails.
        ListClcResultDTO listClcResultDTO = listClcResultMapper.toDto(listClcResult);

        restListClcResultMockMvc.perform(post("/api/list-clc-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcResultDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcResult> listClcResultList = listClcResultRepository.findAll();
        assertThat(listClcResultList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcResultRepository.findAll().size();
        // set the field null
        listClcResult.setName(null);

        // Create the ListClcResult, which fails.
        ListClcResultDTO listClcResultDTO = listClcResultMapper.toDto(listClcResult);

        restListClcResultMockMvc.perform(post("/api/list-clc-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcResultDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcResult> listClcResultList = listClcResultRepository.findAll();
        assertThat(listClcResultList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcResultRepository.findAll().size();
        // set the field null
        listClcResult.setFullName(null);

        // Create the ListClcResult, which fails.
        ListClcResultDTO listClcResultDTO = listClcResultMapper.toDto(listClcResult);

        restListClcResultMockMvc.perform(post("/api/list-clc-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcResultDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcResult> listClcResultList = listClcResultRepository.findAll();
        assertThat(listClcResultList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcResultRepository.findAll().size();
        // set the field null
        listClcResult.setIsCurrentFlag(null);

        // Create the ListClcResult, which fails.
        ListClcResultDTO listClcResultDTO = listClcResultMapper.toDto(listClcResult);

        restListClcResultMockMvc.perform(post("/api/list-clc-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcResultDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcResult> listClcResultList = listClcResultRepository.findAll();
        assertThat(listClcResultList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListClcResults() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList
        restListClcResultMockMvc.perform(get("/api/list-clc-results?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listClcResult.getId().intValue())))
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
    public void getListClcResult() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get the listClcResult
        restListClcResultMockMvc.perform(get("/api/list-clc-results/{id}", listClcResult.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listClcResult.getId().intValue()))
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
    public void getAllListClcResultsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where code equals to DEFAULT_CODE
        defaultListClcResultShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listClcResultList where code equals to UPDATED_CODE
        defaultListClcResultShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListClcResultShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listClcResultList where code equals to UPDATED_CODE
        defaultListClcResultShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where code is not null
        defaultListClcResultShouldBeFound("code.specified=true");

        // Get all the listClcResultList where code is null
        defaultListClcResultShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcResultsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where name equals to DEFAULT_NAME
        defaultListClcResultShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listClcResultList where name equals to UPDATED_NAME
        defaultListClcResultShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListClcResultShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listClcResultList where name equals to UPDATED_NAME
        defaultListClcResultShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where name is not null
        defaultListClcResultShouldBeFound("name.specified=true");

        // Get all the listClcResultList where name is null
        defaultListClcResultShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcResultsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where fullName equals to DEFAULT_FULL_NAME
        defaultListClcResultShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listClcResultList where fullName equals to UPDATED_FULL_NAME
        defaultListClcResultShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListClcResultShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listClcResultList where fullName equals to UPDATED_FULL_NAME
        defaultListClcResultShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where fullName is not null
        defaultListClcResultShouldBeFound("fullName.specified=true");

        // Get all the listClcResultList where fullName is null
        defaultListClcResultShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcResultsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcResultShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcResultList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcResultShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListClcResultShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listClcResultList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcResultShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where isCurrentFlag is not null
        defaultListClcResultShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listClcResultList where isCurrentFlag is null
        defaultListClcResultShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcResultsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcResultShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcResultList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcResultShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcResultShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcResultList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcResultShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListClcResultsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where description equals to DEFAULT_DESCRIPTION
        defaultListClcResultShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listClcResultList where description equals to UPDATED_DESCRIPTION
        defaultListClcResultShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListClcResultShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listClcResultList where description equals to UPDATED_DESCRIPTION
        defaultListClcResultShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where description is not null
        defaultListClcResultShouldBeFound("description.specified=true");

        // Get all the listClcResultList where description is null
        defaultListClcResultShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcResultsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListClcResultShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listClcResultList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListClcResultShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListClcResultShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listClcResultList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListClcResultShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where dateCreate is not null
        defaultListClcResultShouldBeFound("dateCreate.specified=true");

        // Get all the listClcResultList where dateCreate is null
        defaultListClcResultShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcResultsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListClcResultShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listClcResultList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListClcResultShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListClcResultShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listClcResultList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListClcResultShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where dateEdit is not null
        defaultListClcResultShouldBeFound("dateEdit.specified=true");

        // Get all the listClcResultList where dateEdit is null
        defaultListClcResultShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcResultsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where creator equals to DEFAULT_CREATOR
        defaultListClcResultShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listClcResultList where creator equals to UPDATED_CREATOR
        defaultListClcResultShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListClcResultShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listClcResultList where creator equals to UPDATED_CREATOR
        defaultListClcResultShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where creator is not null
        defaultListClcResultShouldBeFound("creator.specified=true");

        // Get all the listClcResultList where creator is null
        defaultListClcResultShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcResultsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where editor equals to DEFAULT_EDITOR
        defaultListClcResultShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listClcResultList where editor equals to UPDATED_EDITOR
        defaultListClcResultShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListClcResultShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listClcResultList where editor equals to UPDATED_EDITOR
        defaultListClcResultShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListClcResultsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        // Get all the listClcResultList where editor is not null
        defaultListClcResultShouldBeFound("editor.specified=true");

        // Get all the listClcResultList where editor is null
        defaultListClcResultShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListClcResultShouldBeFound(String filter) throws Exception {
        restListClcResultMockMvc.perform(get("/api/list-clc-results?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listClcResult.getId().intValue())))
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
        restListClcResultMockMvc.perform(get("/api/list-clc-results/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListClcResultShouldNotBeFound(String filter) throws Exception {
        restListClcResultMockMvc.perform(get("/api/list-clc-results?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListClcResultMockMvc.perform(get("/api/list-clc-results/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListClcResult() throws Exception {
        // Get the listClcResult
        restListClcResultMockMvc.perform(get("/api/list-clc-results/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListClcResult() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        int databaseSizeBeforeUpdate = listClcResultRepository.findAll().size();

        // Update the listClcResult
        ListClcResult updatedListClcResult = listClcResultRepository.findById(listClcResult.getId()).get();
        // Disconnect from session so that the updates on updatedListClcResult are not directly saved in db
        em.detach(updatedListClcResult);
        updatedListClcResult
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListClcResultDTO listClcResultDTO = listClcResultMapper.toDto(updatedListClcResult);

        restListClcResultMockMvc.perform(put("/api/list-clc-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcResultDTO)))
            .andExpect(status().isOk());

        // Validate the ListClcResult in the database
        List<ListClcResult> listClcResultList = listClcResultRepository.findAll();
        assertThat(listClcResultList).hasSize(databaseSizeBeforeUpdate);
        ListClcResult testListClcResult = listClcResultList.get(listClcResultList.size() - 1);
        assertThat(testListClcResult.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListClcResult.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListClcResult.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListClcResult.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListClcResult.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListClcResult.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListClcResult.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListClcResult.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListClcResult.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListClcResult() throws Exception {
        int databaseSizeBeforeUpdate = listClcResultRepository.findAll().size();

        // Create the ListClcResult
        ListClcResultDTO listClcResultDTO = listClcResultMapper.toDto(listClcResult);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListClcResultMockMvc.perform(put("/api/list-clc-results")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcResultDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListClcResult in the database
        List<ListClcResult> listClcResultList = listClcResultRepository.findAll();
        assertThat(listClcResultList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListClcResult() throws Exception {
        // Initialize the database
        listClcResultRepository.saveAndFlush(listClcResult);

        int databaseSizeBeforeDelete = listClcResultRepository.findAll().size();

        // Delete the listClcResult
        restListClcResultMockMvc.perform(delete("/api/list-clc-results/{id}", listClcResult.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListClcResult> listClcResultList = listClcResultRepository.findAll();
        assertThat(listClcResultList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListClcResult.class);
        ListClcResult listClcResult1 = new ListClcResult();
        listClcResult1.setId(1L);
        ListClcResult listClcResult2 = new ListClcResult();
        listClcResult2.setId(listClcResult1.getId());
        assertThat(listClcResult1).isEqualTo(listClcResult2);
        listClcResult2.setId(2L);
        assertThat(listClcResult1).isNotEqualTo(listClcResult2);
        listClcResult1.setId(null);
        assertThat(listClcResult1).isNotEqualTo(listClcResult2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListClcResultDTO.class);
        ListClcResultDTO listClcResultDTO1 = new ListClcResultDTO();
        listClcResultDTO1.setId(1L);
        ListClcResultDTO listClcResultDTO2 = new ListClcResultDTO();
        assertThat(listClcResultDTO1).isNotEqualTo(listClcResultDTO2);
        listClcResultDTO2.setId(listClcResultDTO1.getId());
        assertThat(listClcResultDTO1).isEqualTo(listClcResultDTO2);
        listClcResultDTO2.setId(2L);
        assertThat(listClcResultDTO1).isNotEqualTo(listClcResultDTO2);
        listClcResultDTO1.setId(null);
        assertThat(listClcResultDTO1).isNotEqualTo(listClcResultDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listClcResultMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listClcResultMapper.fromId(null)).isNull();
    }
}
