package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListClcKind;
import io.github.jhipster.application.repository.ListClcKindRepository;
import io.github.jhipster.application.service.ListClcKindService;
import io.github.jhipster.application.service.dto.ListClcKindDTO;
import io.github.jhipster.application.service.mapper.ListClcKindMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListClcKindCriteria;
import io.github.jhipster.application.service.ListClcKindQueryService;

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
 * Integration tests for the {@Link ListClcKindResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListClcKindResourceIT {

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
    private ListClcKindRepository listClcKindRepository;

    @Autowired
    private ListClcKindMapper listClcKindMapper;

    @Autowired
    private ListClcKindService listClcKindService;

    @Autowired
    private ListClcKindQueryService listClcKindQueryService;

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

    private MockMvc restListClcKindMockMvc;

    private ListClcKind listClcKind;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListClcKindResource listClcKindResource = new ListClcKindResource(listClcKindService, listClcKindQueryService);
        this.restListClcKindMockMvc = MockMvcBuilders.standaloneSetup(listClcKindResource)
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
    public static ListClcKind createEntity(EntityManager em) {
        ListClcKind listClcKind = new ListClcKind()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listClcKind;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListClcKind createUpdatedEntity(EntityManager em) {
        ListClcKind listClcKind = new ListClcKind()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listClcKind;
    }

    @BeforeEach
    public void initTest() {
        listClcKind = createEntity(em);
    }

    @Test
    @Transactional
    public void createListClcKind() throws Exception {
        int databaseSizeBeforeCreate = listClcKindRepository.findAll().size();

        // Create the ListClcKind
        ListClcKindDTO listClcKindDTO = listClcKindMapper.toDto(listClcKind);
        restListClcKindMockMvc.perform(post("/api/list-clc-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcKindDTO)))
            .andExpect(status().isCreated());

        // Validate the ListClcKind in the database
        List<ListClcKind> listClcKindList = listClcKindRepository.findAll();
        assertThat(listClcKindList).hasSize(databaseSizeBeforeCreate + 1);
        ListClcKind testListClcKind = listClcKindList.get(listClcKindList.size() - 1);
        assertThat(testListClcKind.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListClcKind.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListClcKind.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListClcKind.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListClcKind.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListClcKind.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListClcKind.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListClcKind.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListClcKind.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListClcKindWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listClcKindRepository.findAll().size();

        // Create the ListClcKind with an existing ID
        listClcKind.setId(1L);
        ListClcKindDTO listClcKindDTO = listClcKindMapper.toDto(listClcKind);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListClcKindMockMvc.perform(post("/api/list-clc-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcKindDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListClcKind in the database
        List<ListClcKind> listClcKindList = listClcKindRepository.findAll();
        assertThat(listClcKindList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcKindRepository.findAll().size();
        // set the field null
        listClcKind.setCode(null);

        // Create the ListClcKind, which fails.
        ListClcKindDTO listClcKindDTO = listClcKindMapper.toDto(listClcKind);

        restListClcKindMockMvc.perform(post("/api/list-clc-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcKindDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcKind> listClcKindList = listClcKindRepository.findAll();
        assertThat(listClcKindList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcKindRepository.findAll().size();
        // set the field null
        listClcKind.setName(null);

        // Create the ListClcKind, which fails.
        ListClcKindDTO listClcKindDTO = listClcKindMapper.toDto(listClcKind);

        restListClcKindMockMvc.perform(post("/api/list-clc-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcKindDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcKind> listClcKindList = listClcKindRepository.findAll();
        assertThat(listClcKindList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcKindRepository.findAll().size();
        // set the field null
        listClcKind.setFullName(null);

        // Create the ListClcKind, which fails.
        ListClcKindDTO listClcKindDTO = listClcKindMapper.toDto(listClcKind);

        restListClcKindMockMvc.perform(post("/api/list-clc-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcKindDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcKind> listClcKindList = listClcKindRepository.findAll();
        assertThat(listClcKindList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcKindRepository.findAll().size();
        // set the field null
        listClcKind.setIsCurrentFlag(null);

        // Create the ListClcKind, which fails.
        ListClcKindDTO listClcKindDTO = listClcKindMapper.toDto(listClcKind);

        restListClcKindMockMvc.perform(post("/api/list-clc-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcKindDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcKind> listClcKindList = listClcKindRepository.findAll();
        assertThat(listClcKindList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListClcKinds() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList
        restListClcKindMockMvc.perform(get("/api/list-clc-kinds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listClcKind.getId().intValue())))
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
    public void getListClcKind() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get the listClcKind
        restListClcKindMockMvc.perform(get("/api/list-clc-kinds/{id}", listClcKind.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listClcKind.getId().intValue()))
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
    public void getAllListClcKindsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where code equals to DEFAULT_CODE
        defaultListClcKindShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listClcKindList where code equals to UPDATED_CODE
        defaultListClcKindShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListClcKindShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listClcKindList where code equals to UPDATED_CODE
        defaultListClcKindShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where code is not null
        defaultListClcKindShouldBeFound("code.specified=true");

        // Get all the listClcKindList where code is null
        defaultListClcKindShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcKindsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where name equals to DEFAULT_NAME
        defaultListClcKindShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listClcKindList where name equals to UPDATED_NAME
        defaultListClcKindShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListClcKindShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listClcKindList where name equals to UPDATED_NAME
        defaultListClcKindShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where name is not null
        defaultListClcKindShouldBeFound("name.specified=true");

        // Get all the listClcKindList where name is null
        defaultListClcKindShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcKindsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where fullName equals to DEFAULT_FULL_NAME
        defaultListClcKindShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listClcKindList where fullName equals to UPDATED_FULL_NAME
        defaultListClcKindShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListClcKindShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listClcKindList where fullName equals to UPDATED_FULL_NAME
        defaultListClcKindShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where fullName is not null
        defaultListClcKindShouldBeFound("fullName.specified=true");

        // Get all the listClcKindList where fullName is null
        defaultListClcKindShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcKindsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcKindShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcKindList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcKindShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListClcKindShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listClcKindList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcKindShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where isCurrentFlag is not null
        defaultListClcKindShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listClcKindList where isCurrentFlag is null
        defaultListClcKindShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcKindsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcKindShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcKindList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcKindShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcKindShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcKindList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcKindShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListClcKindsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where description equals to DEFAULT_DESCRIPTION
        defaultListClcKindShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listClcKindList where description equals to UPDATED_DESCRIPTION
        defaultListClcKindShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListClcKindShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listClcKindList where description equals to UPDATED_DESCRIPTION
        defaultListClcKindShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where description is not null
        defaultListClcKindShouldBeFound("description.specified=true");

        // Get all the listClcKindList where description is null
        defaultListClcKindShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcKindsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListClcKindShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listClcKindList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListClcKindShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListClcKindShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listClcKindList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListClcKindShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where dateCreate is not null
        defaultListClcKindShouldBeFound("dateCreate.specified=true");

        // Get all the listClcKindList where dateCreate is null
        defaultListClcKindShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcKindsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListClcKindShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listClcKindList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListClcKindShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListClcKindShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listClcKindList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListClcKindShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where dateEdit is not null
        defaultListClcKindShouldBeFound("dateEdit.specified=true");

        // Get all the listClcKindList where dateEdit is null
        defaultListClcKindShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcKindsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where creator equals to DEFAULT_CREATOR
        defaultListClcKindShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listClcKindList where creator equals to UPDATED_CREATOR
        defaultListClcKindShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListClcKindShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listClcKindList where creator equals to UPDATED_CREATOR
        defaultListClcKindShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where creator is not null
        defaultListClcKindShouldBeFound("creator.specified=true");

        // Get all the listClcKindList where creator is null
        defaultListClcKindShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcKindsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where editor equals to DEFAULT_EDITOR
        defaultListClcKindShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listClcKindList where editor equals to UPDATED_EDITOR
        defaultListClcKindShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListClcKindShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listClcKindList where editor equals to UPDATED_EDITOR
        defaultListClcKindShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListClcKindsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        // Get all the listClcKindList where editor is not null
        defaultListClcKindShouldBeFound("editor.specified=true");

        // Get all the listClcKindList where editor is null
        defaultListClcKindShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListClcKindShouldBeFound(String filter) throws Exception {
        restListClcKindMockMvc.perform(get("/api/list-clc-kinds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listClcKind.getId().intValue())))
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
        restListClcKindMockMvc.perform(get("/api/list-clc-kinds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListClcKindShouldNotBeFound(String filter) throws Exception {
        restListClcKindMockMvc.perform(get("/api/list-clc-kinds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListClcKindMockMvc.perform(get("/api/list-clc-kinds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListClcKind() throws Exception {
        // Get the listClcKind
        restListClcKindMockMvc.perform(get("/api/list-clc-kinds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListClcKind() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        int databaseSizeBeforeUpdate = listClcKindRepository.findAll().size();

        // Update the listClcKind
        ListClcKind updatedListClcKind = listClcKindRepository.findById(listClcKind.getId()).get();
        // Disconnect from session so that the updates on updatedListClcKind are not directly saved in db
        em.detach(updatedListClcKind);
        updatedListClcKind
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListClcKindDTO listClcKindDTO = listClcKindMapper.toDto(updatedListClcKind);

        restListClcKindMockMvc.perform(put("/api/list-clc-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcKindDTO)))
            .andExpect(status().isOk());

        // Validate the ListClcKind in the database
        List<ListClcKind> listClcKindList = listClcKindRepository.findAll();
        assertThat(listClcKindList).hasSize(databaseSizeBeforeUpdate);
        ListClcKind testListClcKind = listClcKindList.get(listClcKindList.size() - 1);
        assertThat(testListClcKind.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListClcKind.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListClcKind.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListClcKind.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListClcKind.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListClcKind.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListClcKind.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListClcKind.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListClcKind.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListClcKind() throws Exception {
        int databaseSizeBeforeUpdate = listClcKindRepository.findAll().size();

        // Create the ListClcKind
        ListClcKindDTO listClcKindDTO = listClcKindMapper.toDto(listClcKind);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListClcKindMockMvc.perform(put("/api/list-clc-kinds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcKindDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListClcKind in the database
        List<ListClcKind> listClcKindList = listClcKindRepository.findAll();
        assertThat(listClcKindList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListClcKind() throws Exception {
        // Initialize the database
        listClcKindRepository.saveAndFlush(listClcKind);

        int databaseSizeBeforeDelete = listClcKindRepository.findAll().size();

        // Delete the listClcKind
        restListClcKindMockMvc.perform(delete("/api/list-clc-kinds/{id}", listClcKind.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListClcKind> listClcKindList = listClcKindRepository.findAll();
        assertThat(listClcKindList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListClcKind.class);
        ListClcKind listClcKind1 = new ListClcKind();
        listClcKind1.setId(1L);
        ListClcKind listClcKind2 = new ListClcKind();
        listClcKind2.setId(listClcKind1.getId());
        assertThat(listClcKind1).isEqualTo(listClcKind2);
        listClcKind2.setId(2L);
        assertThat(listClcKind1).isNotEqualTo(listClcKind2);
        listClcKind1.setId(null);
        assertThat(listClcKind1).isNotEqualTo(listClcKind2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListClcKindDTO.class);
        ListClcKindDTO listClcKindDTO1 = new ListClcKindDTO();
        listClcKindDTO1.setId(1L);
        ListClcKindDTO listClcKindDTO2 = new ListClcKindDTO();
        assertThat(listClcKindDTO1).isNotEqualTo(listClcKindDTO2);
        listClcKindDTO2.setId(listClcKindDTO1.getId());
        assertThat(listClcKindDTO1).isEqualTo(listClcKindDTO2);
        listClcKindDTO2.setId(2L);
        assertThat(listClcKindDTO1).isNotEqualTo(listClcKindDTO2);
        listClcKindDTO1.setId(null);
        assertThat(listClcKindDTO1).isNotEqualTo(listClcKindDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listClcKindMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listClcKindMapper.fromId(null)).isNull();
    }
}
