package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListBendManufacturer;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.repository.ListBendManufacturerRepository;
import io.github.jhipster.application.service.ListBendManufacturerService;
import io.github.jhipster.application.service.dto.ListBendManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListBendManufacturerMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListBendManufacturerCriteria;
import io.github.jhipster.application.service.ListBendManufacturerQueryService;

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
 * Integration tests for the {@Link ListBendManufacturerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListBendManufacturerResourceIT {

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
    private ListBendManufacturerRepository listBendManufacturerRepository;

    @Autowired
    private ListBendManufacturerMapper listBendManufacturerMapper;

    @Autowired
    private ListBendManufacturerService listBendManufacturerService;

    @Autowired
    private ListBendManufacturerQueryService listBendManufacturerQueryService;

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

    private MockMvc restListBendManufacturerMockMvc;

    private ListBendManufacturer listBendManufacturer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListBendManufacturerResource listBendManufacturerResource = new ListBendManufacturerResource(listBendManufacturerService, listBendManufacturerQueryService);
        this.restListBendManufacturerMockMvc = MockMvcBuilders.standaloneSetup(listBendManufacturerResource)
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
    public static ListBendManufacturer createEntity(EntityManager em) {
        ListBendManufacturer listBendManufacturer = new ListBendManufacturer()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listBendManufacturer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListBendManufacturer createUpdatedEntity(EntityManager em) {
        ListBendManufacturer listBendManufacturer = new ListBendManufacturer()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listBendManufacturer;
    }

    @BeforeEach
    public void initTest() {
        listBendManufacturer = createEntity(em);
    }

    @Test
    @Transactional
    public void createListBendManufacturer() throws Exception {
        int databaseSizeBeforeCreate = listBendManufacturerRepository.findAll().size();

        // Create the ListBendManufacturer
        ListBendManufacturerDTO listBendManufacturerDTO = listBendManufacturerMapper.toDto(listBendManufacturer);
        restListBendManufacturerMockMvc.perform(post("/api/list-bend-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendManufacturerDTO)))
            .andExpect(status().isCreated());

        // Validate the ListBendManufacturer in the database
        List<ListBendManufacturer> listBendManufacturerList = listBendManufacturerRepository.findAll();
        assertThat(listBendManufacturerList).hasSize(databaseSizeBeforeCreate + 1);
        ListBendManufacturer testListBendManufacturer = listBendManufacturerList.get(listBendManufacturerList.size() - 1);
        assertThat(testListBendManufacturer.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListBendManufacturer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListBendManufacturer.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListBendManufacturer.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListBendManufacturer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListBendManufacturer.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListBendManufacturer.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListBendManufacturer.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListBendManufacturer.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListBendManufacturerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listBendManufacturerRepository.findAll().size();

        // Create the ListBendManufacturer with an existing ID
        listBendManufacturer.setId(1L);
        ListBendManufacturerDTO listBendManufacturerDTO = listBendManufacturerMapper.toDto(listBendManufacturer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListBendManufacturerMockMvc.perform(post("/api/list-bend-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendManufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBendManufacturer in the database
        List<ListBendManufacturer> listBendManufacturerList = listBendManufacturerRepository.findAll();
        assertThat(listBendManufacturerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendManufacturerRepository.findAll().size();
        // set the field null
        listBendManufacturer.setCode(null);

        // Create the ListBendManufacturer, which fails.
        ListBendManufacturerDTO listBendManufacturerDTO = listBendManufacturerMapper.toDto(listBendManufacturer);

        restListBendManufacturerMockMvc.perform(post("/api/list-bend-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendManufacturer> listBendManufacturerList = listBendManufacturerRepository.findAll();
        assertThat(listBendManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendManufacturerRepository.findAll().size();
        // set the field null
        listBendManufacturer.setName(null);

        // Create the ListBendManufacturer, which fails.
        ListBendManufacturerDTO listBendManufacturerDTO = listBendManufacturerMapper.toDto(listBendManufacturer);

        restListBendManufacturerMockMvc.perform(post("/api/list-bend-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendManufacturer> listBendManufacturerList = listBendManufacturerRepository.findAll();
        assertThat(listBendManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendManufacturerRepository.findAll().size();
        // set the field null
        listBendManufacturer.setFullName(null);

        // Create the ListBendManufacturer, which fails.
        ListBendManufacturerDTO listBendManufacturerDTO = listBendManufacturerMapper.toDto(listBendManufacturer);

        restListBendManufacturerMockMvc.perform(post("/api/list-bend-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendManufacturer> listBendManufacturerList = listBendManufacturerRepository.findAll();
        assertThat(listBendManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBendManufacturerRepository.findAll().size();
        // set the field null
        listBendManufacturer.setIsCurrentFlag(null);

        // Create the ListBendManufacturer, which fails.
        ListBendManufacturerDTO listBendManufacturerDTO = listBendManufacturerMapper.toDto(listBendManufacturer);

        restListBendManufacturerMockMvc.perform(post("/api/list-bend-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListBendManufacturer> listBendManufacturerList = listBendManufacturerRepository.findAll();
        assertThat(listBendManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturers() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList
        restListBendManufacturerMockMvc.perform(get("/api/list-bend-manufacturers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBendManufacturer.getId().intValue())))
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
    public void getListBendManufacturer() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get the listBendManufacturer
        restListBendManufacturerMockMvc.perform(get("/api/list-bend-manufacturers/{id}", listBendManufacturer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listBendManufacturer.getId().intValue()))
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
    public void getAllListBendManufacturersByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where code equals to DEFAULT_CODE
        defaultListBendManufacturerShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listBendManufacturerList where code equals to UPDATED_CODE
        defaultListBendManufacturerShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListBendManufacturerShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listBendManufacturerList where code equals to UPDATED_CODE
        defaultListBendManufacturerShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where code is not null
        defaultListBendManufacturerShouldBeFound("code.specified=true");

        // Get all the listBendManufacturerList where code is null
        defaultListBendManufacturerShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where name equals to DEFAULT_NAME
        defaultListBendManufacturerShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listBendManufacturerList where name equals to UPDATED_NAME
        defaultListBendManufacturerShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListBendManufacturerShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listBendManufacturerList where name equals to UPDATED_NAME
        defaultListBendManufacturerShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where name is not null
        defaultListBendManufacturerShouldBeFound("name.specified=true");

        // Get all the listBendManufacturerList where name is null
        defaultListBendManufacturerShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where fullName equals to DEFAULT_FULL_NAME
        defaultListBendManufacturerShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listBendManufacturerList where fullName equals to UPDATED_FULL_NAME
        defaultListBendManufacturerShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListBendManufacturerShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listBendManufacturerList where fullName equals to UPDATED_FULL_NAME
        defaultListBendManufacturerShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where fullName is not null
        defaultListBendManufacturerShouldBeFound("fullName.specified=true");

        // Get all the listBendManufacturerList where fullName is null
        defaultListBendManufacturerShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBendManufacturerShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBendManufacturerList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendManufacturerShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListBendManufacturerShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listBendManufacturerList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendManufacturerShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where isCurrentFlag is not null
        defaultListBendManufacturerShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listBendManufacturerList where isCurrentFlag is null
        defaultListBendManufacturerShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBendManufacturerShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBendManufacturerList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendManufacturerShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBendManufacturerShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBendManufacturerList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBendManufacturerShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListBendManufacturersByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where description equals to DEFAULT_DESCRIPTION
        defaultListBendManufacturerShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listBendManufacturerList where description equals to UPDATED_DESCRIPTION
        defaultListBendManufacturerShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListBendManufacturerShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listBendManufacturerList where description equals to UPDATED_DESCRIPTION
        defaultListBendManufacturerShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where description is not null
        defaultListBendManufacturerShouldBeFound("description.specified=true");

        // Get all the listBendManufacturerList where description is null
        defaultListBendManufacturerShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListBendManufacturerShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listBendManufacturerList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBendManufacturerShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListBendManufacturerShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listBendManufacturerList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBendManufacturerShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where dateCreate is not null
        defaultListBendManufacturerShouldBeFound("dateCreate.specified=true");

        // Get all the listBendManufacturerList where dateCreate is null
        defaultListBendManufacturerShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListBendManufacturerShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listBendManufacturerList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBendManufacturerShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListBendManufacturerShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listBendManufacturerList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBendManufacturerShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where dateEdit is not null
        defaultListBendManufacturerShouldBeFound("dateEdit.specified=true");

        // Get all the listBendManufacturerList where dateEdit is null
        defaultListBendManufacturerShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where creator equals to DEFAULT_CREATOR
        defaultListBendManufacturerShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listBendManufacturerList where creator equals to UPDATED_CREATOR
        defaultListBendManufacturerShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListBendManufacturerShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listBendManufacturerList where creator equals to UPDATED_CREATOR
        defaultListBendManufacturerShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where creator is not null
        defaultListBendManufacturerShouldBeFound("creator.specified=true");

        // Get all the listBendManufacturerList where creator is null
        defaultListBendManufacturerShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where editor equals to DEFAULT_EDITOR
        defaultListBendManufacturerShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listBendManufacturerList where editor equals to UPDATED_EDITOR
        defaultListBendManufacturerShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListBendManufacturerShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listBendManufacturerList where editor equals to UPDATED_EDITOR
        defaultListBendManufacturerShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        // Get all the listBendManufacturerList where editor is not null
        defaultListBendManufacturerShouldBeFound("editor.specified=true");

        // Get all the listBendManufacturerList where editor is null
        defaultListBendManufacturerShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBendManufacturersByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listBendManufacturer.addBendHist(bendHist);
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);
        Long bendHistId = bendHist.getId();

        // Get all the listBendManufacturerList where bendHist equals to bendHistId
        defaultListBendManufacturerShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listBendManufacturerList where bendHist equals to bendHistId + 1
        defaultListBendManufacturerShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListBendManufacturerShouldBeFound(String filter) throws Exception {
        restListBendManufacturerMockMvc.perform(get("/api/list-bend-manufacturers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBendManufacturer.getId().intValue())))
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
        restListBendManufacturerMockMvc.perform(get("/api/list-bend-manufacturers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListBendManufacturerShouldNotBeFound(String filter) throws Exception {
        restListBendManufacturerMockMvc.perform(get("/api/list-bend-manufacturers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListBendManufacturerMockMvc.perform(get("/api/list-bend-manufacturers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListBendManufacturer() throws Exception {
        // Get the listBendManufacturer
        restListBendManufacturerMockMvc.perform(get("/api/list-bend-manufacturers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListBendManufacturer() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        int databaseSizeBeforeUpdate = listBendManufacturerRepository.findAll().size();

        // Update the listBendManufacturer
        ListBendManufacturer updatedListBendManufacturer = listBendManufacturerRepository.findById(listBendManufacturer.getId()).get();
        // Disconnect from session so that the updates on updatedListBendManufacturer are not directly saved in db
        em.detach(updatedListBendManufacturer);
        updatedListBendManufacturer
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListBendManufacturerDTO listBendManufacturerDTO = listBendManufacturerMapper.toDto(updatedListBendManufacturer);

        restListBendManufacturerMockMvc.perform(put("/api/list-bend-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendManufacturerDTO)))
            .andExpect(status().isOk());

        // Validate the ListBendManufacturer in the database
        List<ListBendManufacturer> listBendManufacturerList = listBendManufacturerRepository.findAll();
        assertThat(listBendManufacturerList).hasSize(databaseSizeBeforeUpdate);
        ListBendManufacturer testListBendManufacturer = listBendManufacturerList.get(listBendManufacturerList.size() - 1);
        assertThat(testListBendManufacturer.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListBendManufacturer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListBendManufacturer.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListBendManufacturer.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListBendManufacturer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListBendManufacturer.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListBendManufacturer.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListBendManufacturer.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListBendManufacturer.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListBendManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = listBendManufacturerRepository.findAll().size();

        // Create the ListBendManufacturer
        ListBendManufacturerDTO listBendManufacturerDTO = listBendManufacturerMapper.toDto(listBendManufacturer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListBendManufacturerMockMvc.perform(put("/api/list-bend-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBendManufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBendManufacturer in the database
        List<ListBendManufacturer> listBendManufacturerList = listBendManufacturerRepository.findAll();
        assertThat(listBendManufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListBendManufacturer() throws Exception {
        // Initialize the database
        listBendManufacturerRepository.saveAndFlush(listBendManufacturer);

        int databaseSizeBeforeDelete = listBendManufacturerRepository.findAll().size();

        // Delete the listBendManufacturer
        restListBendManufacturerMockMvc.perform(delete("/api/list-bend-manufacturers/{id}", listBendManufacturer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListBendManufacturer> listBendManufacturerList = listBendManufacturerRepository.findAll();
        assertThat(listBendManufacturerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBendManufacturer.class);
        ListBendManufacturer listBendManufacturer1 = new ListBendManufacturer();
        listBendManufacturer1.setId(1L);
        ListBendManufacturer listBendManufacturer2 = new ListBendManufacturer();
        listBendManufacturer2.setId(listBendManufacturer1.getId());
        assertThat(listBendManufacturer1).isEqualTo(listBendManufacturer2);
        listBendManufacturer2.setId(2L);
        assertThat(listBendManufacturer1).isNotEqualTo(listBendManufacturer2);
        listBendManufacturer1.setId(null);
        assertThat(listBendManufacturer1).isNotEqualTo(listBendManufacturer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBendManufacturerDTO.class);
        ListBendManufacturerDTO listBendManufacturerDTO1 = new ListBendManufacturerDTO();
        listBendManufacturerDTO1.setId(1L);
        ListBendManufacturerDTO listBendManufacturerDTO2 = new ListBendManufacturerDTO();
        assertThat(listBendManufacturerDTO1).isNotEqualTo(listBendManufacturerDTO2);
        listBendManufacturerDTO2.setId(listBendManufacturerDTO1.getId());
        assertThat(listBendManufacturerDTO1).isEqualTo(listBendManufacturerDTO2);
        listBendManufacturerDTO2.setId(2L);
        assertThat(listBendManufacturerDTO1).isNotEqualTo(listBendManufacturerDTO2);
        listBendManufacturerDTO1.setId(null);
        assertThat(listBendManufacturerDTO1).isNotEqualTo(listBendManufacturerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listBendManufacturerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listBendManufacturerMapper.fromId(null)).isNull();
    }
}
