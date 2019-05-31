package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListWrkKind;
import io.github.jhipster.application.domain.ListWrkStatus;
import io.github.jhipster.application.repository.ListWrkKindRepository;
import io.github.jhipster.application.service.ListWrkKindService;
import io.github.jhipster.application.service.dto.ListWrkKindDTO;
import io.github.jhipster.application.service.mapper.ListWrkKindMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListWrkKindCriteria;
import io.github.jhipster.application.service.ListWrkKindQueryService;

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
 * Integration tests for the {@Link ListWrkKindResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListWrkKindResourceIT {

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
    private ListWrkKindRepository listWrkKindRepository;

    @Autowired
    private ListWrkKindMapper listWrkKindMapper;

    @Autowired
    private ListWrkKindService listWrkKindService;

    @Autowired
    private ListWrkKindQueryService listWrkKindQueryService;

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

    private MockMvc restListWrkKindMockMvc;

    private ListWrkKind listWrkKind;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListWrkKindResource listWrkKindResource = new ListWrkKindResource(listWrkKindService, listWrkKindQueryService);
        this.restListWrkKindMockMvc = MockMvcBuilders.standaloneSetup(listWrkKindResource)
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
    public static ListWrkKind createEntity(EntityManager em) {
        ListWrkKind listWrkKind = new ListWrkKind()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listWrkKind;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListWrkKind createUpdatedEntity(EntityManager em) {
        ListWrkKind listWrkKind = new ListWrkKind()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listWrkKind;
    }

    @BeforeEach
    public void initTest() {
        listWrkKind = createEntity(em);
    }

    @Test
    @Transactional
    public void createListWrkKind() throws Exception {
        int databaseSizeBeforeCreate = listWrkKindRepository.findAll().size();

        // Create the ListWrkKind
        ListWrkKindDTO listWrkKindDTO = listWrkKindMapper.toDto(listWrkKind);
        restListWrkKindMockMvc.perform(post("/api/list-wrk-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkKindDTO)))
            .andExpect(status().isCreated());

        // Validate the ListWrkKind in the database
        List<ListWrkKind> listWrkKindList = listWrkKindRepository.findAll();
        assertThat(listWrkKindList).hasSize(databaseSizeBeforeCreate + 1);
        ListWrkKind testListWrkKind = listWrkKindList.get(listWrkKindList.size() - 1);
        assertThat(testListWrkKind.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListWrkKind.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListWrkKind.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListWrkKind.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListWrkKind.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListWrkKind.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListWrkKind.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListWrkKind.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListWrkKind.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListWrkKindWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listWrkKindRepository.findAll().size();

        // Create the ListWrkKind with an existing ID
        listWrkKind.setId(1L);
        ListWrkKindDTO listWrkKindDTO = listWrkKindMapper.toDto(listWrkKind);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListWrkKindMockMvc.perform(post("/api/list-wrk-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkKindDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListWrkKind in the database
        List<ListWrkKind> listWrkKindList = listWrkKindRepository.findAll();
        assertThat(listWrkKindList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkKindRepository.findAll().size();
        // set the field null
        listWrkKind.setCode(null);

        // Create the ListWrkKind, which fails.
        ListWrkKindDTO listWrkKindDTO = listWrkKindMapper.toDto(listWrkKind);

        restListWrkKindMockMvc.perform(post("/api/list-wrk-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkKindDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkKind> listWrkKindList = listWrkKindRepository.findAll();
        assertThat(listWrkKindList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkKindRepository.findAll().size();
        // set the field null
        listWrkKind.setName(null);

        // Create the ListWrkKind, which fails.
        ListWrkKindDTO listWrkKindDTO = listWrkKindMapper.toDto(listWrkKind);

        restListWrkKindMockMvc.perform(post("/api/list-wrk-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkKindDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkKind> listWrkKindList = listWrkKindRepository.findAll();
        assertThat(listWrkKindList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkKindRepository.findAll().size();
        // set the field null
        listWrkKind.setFullName(null);

        // Create the ListWrkKind, which fails.
        ListWrkKindDTO listWrkKindDTO = listWrkKindMapper.toDto(listWrkKind);

        restListWrkKindMockMvc.perform(post("/api/list-wrk-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkKindDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkKind> listWrkKindList = listWrkKindRepository.findAll();
        assertThat(listWrkKindList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listWrkKindRepository.findAll().size();
        // set the field null
        listWrkKind.setIsCurrentFlag(null);

        // Create the ListWrkKind, which fails.
        ListWrkKindDTO listWrkKindDTO = listWrkKindMapper.toDto(listWrkKind);

        restListWrkKindMockMvc.perform(post("/api/list-wrk-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkKindDTO)))
            .andExpect(status().isBadRequest());

        List<ListWrkKind> listWrkKindList = listWrkKindRepository.findAll();
        assertThat(listWrkKindList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListWrkKinds() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList
        restListWrkKindMockMvc.perform(get("/api/list-wrk-kinds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listWrkKind.getId().intValue())))
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
    public void getListWrkKind() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get the listWrkKind
        restListWrkKindMockMvc.perform(get("/api/list-wrk-kinds/{id}", listWrkKind.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listWrkKind.getId().intValue()))
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
    public void getAllListWrkKindsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where code equals to DEFAULT_CODE
        defaultListWrkKindShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listWrkKindList where code equals to UPDATED_CODE
        defaultListWrkKindShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListWrkKindShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listWrkKindList where code equals to UPDATED_CODE
        defaultListWrkKindShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where code is not null
        defaultListWrkKindShouldBeFound("code.specified=true");

        // Get all the listWrkKindList where code is null
        defaultListWrkKindShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where name equals to DEFAULT_NAME
        defaultListWrkKindShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listWrkKindList where name equals to UPDATED_NAME
        defaultListWrkKindShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListWrkKindShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listWrkKindList where name equals to UPDATED_NAME
        defaultListWrkKindShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where name is not null
        defaultListWrkKindShouldBeFound("name.specified=true");

        // Get all the listWrkKindList where name is null
        defaultListWrkKindShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where fullName equals to DEFAULT_FULL_NAME
        defaultListWrkKindShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listWrkKindList where fullName equals to UPDATED_FULL_NAME
        defaultListWrkKindShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListWrkKindShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listWrkKindList where fullName equals to UPDATED_FULL_NAME
        defaultListWrkKindShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where fullName is not null
        defaultListWrkKindShouldBeFound("fullName.specified=true");

        // Get all the listWrkKindList where fullName is null
        defaultListWrkKindShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkKindShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkKindList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkKindShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListWrkKindShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listWrkKindList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkKindShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where isCurrentFlag is not null
        defaultListWrkKindShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listWrkKindList where isCurrentFlag is null
        defaultListWrkKindShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkKindShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkKindList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkKindShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListWrkKindShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listWrkKindList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListWrkKindShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListWrkKindsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where description equals to DEFAULT_DESCRIPTION
        defaultListWrkKindShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listWrkKindList where description equals to UPDATED_DESCRIPTION
        defaultListWrkKindShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListWrkKindShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listWrkKindList where description equals to UPDATED_DESCRIPTION
        defaultListWrkKindShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where description is not null
        defaultListWrkKindShouldBeFound("description.specified=true");

        // Get all the listWrkKindList where description is null
        defaultListWrkKindShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListWrkKindShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listWrkKindList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListWrkKindShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListWrkKindShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listWrkKindList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListWrkKindShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where dateCreate is not null
        defaultListWrkKindShouldBeFound("dateCreate.specified=true");

        // Get all the listWrkKindList where dateCreate is null
        defaultListWrkKindShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListWrkKindShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listWrkKindList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListWrkKindShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListWrkKindShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listWrkKindList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListWrkKindShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where dateEdit is not null
        defaultListWrkKindShouldBeFound("dateEdit.specified=true");

        // Get all the listWrkKindList where dateEdit is null
        defaultListWrkKindShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where creator equals to DEFAULT_CREATOR
        defaultListWrkKindShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listWrkKindList where creator equals to UPDATED_CREATOR
        defaultListWrkKindShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListWrkKindShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listWrkKindList where creator equals to UPDATED_CREATOR
        defaultListWrkKindShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where creator is not null
        defaultListWrkKindShouldBeFound("creator.specified=true");

        // Get all the listWrkKindList where creator is null
        defaultListWrkKindShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where editor equals to DEFAULT_EDITOR
        defaultListWrkKindShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listWrkKindList where editor equals to UPDATED_EDITOR
        defaultListWrkKindShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListWrkKindShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listWrkKindList where editor equals to UPDATED_EDITOR
        defaultListWrkKindShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        // Get all the listWrkKindList where editor is not null
        defaultListWrkKindShouldBeFound("editor.specified=true");

        // Get all the listWrkKindList where editor is null
        defaultListWrkKindShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListWrkKindsByListWrkStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        ListWrkStatus listWrkStatus = ListWrkStatusResourceIT.createEntity(em);
        em.persist(listWrkStatus);
        em.flush();
        listWrkKind.addListWrkStatus(listWrkStatus);
        listWrkKindRepository.saveAndFlush(listWrkKind);
        Long listWrkStatusId = listWrkStatus.getId();

        // Get all the listWrkKindList where listWrkStatus equals to listWrkStatusId
        defaultListWrkKindShouldBeFound("listWrkStatusId.equals=" + listWrkStatusId);

        // Get all the listWrkKindList where listWrkStatus equals to listWrkStatusId + 1
        defaultListWrkKindShouldNotBeFound("listWrkStatusId.equals=" + (listWrkStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListWrkKindShouldBeFound(String filter) throws Exception {
        restListWrkKindMockMvc.perform(get("/api/list-wrk-kinds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listWrkKind.getId().intValue())))
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
        restListWrkKindMockMvc.perform(get("/api/list-wrk-kinds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListWrkKindShouldNotBeFound(String filter) throws Exception {
        restListWrkKindMockMvc.perform(get("/api/list-wrk-kinds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListWrkKindMockMvc.perform(get("/api/list-wrk-kinds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListWrkKind() throws Exception {
        // Get the listWrkKind
        restListWrkKindMockMvc.perform(get("/api/list-wrk-kinds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListWrkKind() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        int databaseSizeBeforeUpdate = listWrkKindRepository.findAll().size();

        // Update the listWrkKind
        ListWrkKind updatedListWrkKind = listWrkKindRepository.findById(listWrkKind.getId()).get();
        // Disconnect from session so that the updates on updatedListWrkKind are not directly saved in db
        em.detach(updatedListWrkKind);
        updatedListWrkKind
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListWrkKindDTO listWrkKindDTO = listWrkKindMapper.toDto(updatedListWrkKind);

        restListWrkKindMockMvc.perform(put("/api/list-wrk-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkKindDTO)))
            .andExpect(status().isOk());

        // Validate the ListWrkKind in the database
        List<ListWrkKind> listWrkKindList = listWrkKindRepository.findAll();
        assertThat(listWrkKindList).hasSize(databaseSizeBeforeUpdate);
        ListWrkKind testListWrkKind = listWrkKindList.get(listWrkKindList.size() - 1);
        assertThat(testListWrkKind.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListWrkKind.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListWrkKind.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListWrkKind.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListWrkKind.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListWrkKind.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListWrkKind.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListWrkKind.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListWrkKind.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListWrkKind() throws Exception {
        int databaseSizeBeforeUpdate = listWrkKindRepository.findAll().size();

        // Create the ListWrkKind
        ListWrkKindDTO listWrkKindDTO = listWrkKindMapper.toDto(listWrkKind);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListWrkKindMockMvc.perform(put("/api/list-wrk-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listWrkKindDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListWrkKind in the database
        List<ListWrkKind> listWrkKindList = listWrkKindRepository.findAll();
        assertThat(listWrkKindList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListWrkKind() throws Exception {
        // Initialize the database
        listWrkKindRepository.saveAndFlush(listWrkKind);

        int databaseSizeBeforeDelete = listWrkKindRepository.findAll().size();

        // Delete the listWrkKind
        restListWrkKindMockMvc.perform(delete("/api/list-wrk-kinds/{id}", listWrkKind.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListWrkKind> listWrkKindList = listWrkKindRepository.findAll();
        assertThat(listWrkKindList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListWrkKind.class);
        ListWrkKind listWrkKind1 = new ListWrkKind();
        listWrkKind1.setId(1L);
        ListWrkKind listWrkKind2 = new ListWrkKind();
        listWrkKind2.setId(listWrkKind1.getId());
        assertThat(listWrkKind1).isEqualTo(listWrkKind2);
        listWrkKind2.setId(2L);
        assertThat(listWrkKind1).isNotEqualTo(listWrkKind2);
        listWrkKind1.setId(null);
        assertThat(listWrkKind1).isNotEqualTo(listWrkKind2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListWrkKindDTO.class);
        ListWrkKindDTO listWrkKindDTO1 = new ListWrkKindDTO();
        listWrkKindDTO1.setId(1L);
        ListWrkKindDTO listWrkKindDTO2 = new ListWrkKindDTO();
        assertThat(listWrkKindDTO1).isNotEqualTo(listWrkKindDTO2);
        listWrkKindDTO2.setId(listWrkKindDTO1.getId());
        assertThat(listWrkKindDTO1).isEqualTo(listWrkKindDTO2);
        listWrkKindDTO2.setId(2L);
        assertThat(listWrkKindDTO1).isNotEqualTo(listWrkKindDTO2);
        listWrkKindDTO1.setId(null);
        assertThat(listWrkKindDTO1).isNotEqualTo(listWrkKindDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listWrkKindMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listWrkKindMapper.fromId(null)).isNull();
    }
}
