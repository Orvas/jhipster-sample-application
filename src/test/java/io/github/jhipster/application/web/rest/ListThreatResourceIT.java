package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListThreat;
import io.github.jhipster.application.domain.ListThreatGroup;
import io.github.jhipster.application.repository.ListThreatRepository;
import io.github.jhipster.application.service.ListThreatService;
import io.github.jhipster.application.service.dto.ListThreatDTO;
import io.github.jhipster.application.service.mapper.ListThreatMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListThreatCriteria;
import io.github.jhipster.application.service.ListThreatQueryService;

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
 * Integration tests for the {@Link ListThreatResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListThreatResourceIT {

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
    private ListThreatRepository listThreatRepository;

    @Autowired
    private ListThreatMapper listThreatMapper;

    @Autowired
    private ListThreatService listThreatService;

    @Autowired
    private ListThreatQueryService listThreatQueryService;

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

    private MockMvc restListThreatMockMvc;

    private ListThreat listThreat;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListThreatResource listThreatResource = new ListThreatResource(listThreatService, listThreatQueryService);
        this.restListThreatMockMvc = MockMvcBuilders.standaloneSetup(listThreatResource)
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
    public static ListThreat createEntity(EntityManager em) {
        ListThreat listThreat = new ListThreat()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        ListThreatGroup listThreatGroup;
        if (TestUtil.findAll(em, ListThreatGroup.class).isEmpty()) {
            listThreatGroup = ListThreatGroupResourceIT.createEntity(em);
            em.persist(listThreatGroup);
            em.flush();
        } else {
            listThreatGroup = TestUtil.findAll(em, ListThreatGroup.class).get(0);
        }
        listThreat.setIdGroup(listThreatGroup);
        return listThreat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListThreat createUpdatedEntity(EntityManager em) {
        ListThreat listThreat = new ListThreat()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        ListThreatGroup listThreatGroup;
        if (TestUtil.findAll(em, ListThreatGroup.class).isEmpty()) {
            listThreatGroup = ListThreatGroupResourceIT.createUpdatedEntity(em);
            em.persist(listThreatGroup);
            em.flush();
        } else {
            listThreatGroup = TestUtil.findAll(em, ListThreatGroup.class).get(0);
        }
        listThreat.setIdGroup(listThreatGroup);
        return listThreat;
    }

    @BeforeEach
    public void initTest() {
        listThreat = createEntity(em);
    }

    @Test
    @Transactional
    public void createListThreat() throws Exception {
        int databaseSizeBeforeCreate = listThreatRepository.findAll().size();

        // Create the ListThreat
        ListThreatDTO listThreatDTO = listThreatMapper.toDto(listThreat);
        restListThreatMockMvc.perform(post("/api/list-threats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatDTO)))
            .andExpect(status().isCreated());

        // Validate the ListThreat in the database
        List<ListThreat> listThreatList = listThreatRepository.findAll();
        assertThat(listThreatList).hasSize(databaseSizeBeforeCreate + 1);
        ListThreat testListThreat = listThreatList.get(listThreatList.size() - 1);
        assertThat(testListThreat.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListThreat.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListThreat.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListThreat.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListThreat.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListThreat.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListThreat.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListThreat.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListThreat.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListThreatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listThreatRepository.findAll().size();

        // Create the ListThreat with an existing ID
        listThreat.setId(1L);
        ListThreatDTO listThreatDTO = listThreatMapper.toDto(listThreat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListThreatMockMvc.perform(post("/api/list-threats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListThreat in the database
        List<ListThreat> listThreatList = listThreatRepository.findAll();
        assertThat(listThreatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listThreatRepository.findAll().size();
        // set the field null
        listThreat.setCode(null);

        // Create the ListThreat, which fails.
        ListThreatDTO listThreatDTO = listThreatMapper.toDto(listThreat);

        restListThreatMockMvc.perform(post("/api/list-threats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatDTO)))
            .andExpect(status().isBadRequest());

        List<ListThreat> listThreatList = listThreatRepository.findAll();
        assertThat(listThreatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listThreatRepository.findAll().size();
        // set the field null
        listThreat.setName(null);

        // Create the ListThreat, which fails.
        ListThreatDTO listThreatDTO = listThreatMapper.toDto(listThreat);

        restListThreatMockMvc.perform(post("/api/list-threats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatDTO)))
            .andExpect(status().isBadRequest());

        List<ListThreat> listThreatList = listThreatRepository.findAll();
        assertThat(listThreatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listThreatRepository.findAll().size();
        // set the field null
        listThreat.setFullName(null);

        // Create the ListThreat, which fails.
        ListThreatDTO listThreatDTO = listThreatMapper.toDto(listThreat);

        restListThreatMockMvc.perform(post("/api/list-threats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatDTO)))
            .andExpect(status().isBadRequest());

        List<ListThreat> listThreatList = listThreatRepository.findAll();
        assertThat(listThreatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listThreatRepository.findAll().size();
        // set the field null
        listThreat.setIsCurrentFlag(null);

        // Create the ListThreat, which fails.
        ListThreatDTO listThreatDTO = listThreatMapper.toDto(listThreat);

        restListThreatMockMvc.perform(post("/api/list-threats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatDTO)))
            .andExpect(status().isBadRequest());

        List<ListThreat> listThreatList = listThreatRepository.findAll();
        assertThat(listThreatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListThreats() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList
        restListThreatMockMvc.perform(get("/api/list-threats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listThreat.getId().intValue())))
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
    public void getListThreat() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get the listThreat
        restListThreatMockMvc.perform(get("/api/list-threats/{id}", listThreat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listThreat.getId().intValue()))
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
    public void getAllListThreatsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where code equals to DEFAULT_CODE
        defaultListThreatShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listThreatList where code equals to UPDATED_CODE
        defaultListThreatShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListThreatsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListThreatShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listThreatList where code equals to UPDATED_CODE
        defaultListThreatShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListThreatsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where code is not null
        defaultListThreatShouldBeFound("code.specified=true");

        // Get all the listThreatList where code is null
        defaultListThreatShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where name equals to DEFAULT_NAME
        defaultListThreatShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listThreatList where name equals to UPDATED_NAME
        defaultListThreatShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListThreatsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListThreatShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listThreatList where name equals to UPDATED_NAME
        defaultListThreatShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListThreatsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where name is not null
        defaultListThreatShouldBeFound("name.specified=true");

        // Get all the listThreatList where name is null
        defaultListThreatShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where fullName equals to DEFAULT_FULL_NAME
        defaultListThreatShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listThreatList where fullName equals to UPDATED_FULL_NAME
        defaultListThreatShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListThreatsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListThreatShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listThreatList where fullName equals to UPDATED_FULL_NAME
        defaultListThreatShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListThreatsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where fullName is not null
        defaultListThreatShouldBeFound("fullName.specified=true");

        // Get all the listThreatList where fullName is null
        defaultListThreatShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListThreatShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listThreatList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListThreatShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListThreatsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListThreatShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listThreatList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListThreatShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListThreatsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where isCurrentFlag is not null
        defaultListThreatShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listThreatList where isCurrentFlag is null
        defaultListThreatShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListThreatShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listThreatList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListThreatShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListThreatsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListThreatShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listThreatList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListThreatShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListThreatsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where description equals to DEFAULT_DESCRIPTION
        defaultListThreatShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listThreatList where description equals to UPDATED_DESCRIPTION
        defaultListThreatShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListThreatsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListThreatShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listThreatList where description equals to UPDATED_DESCRIPTION
        defaultListThreatShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListThreatsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where description is not null
        defaultListThreatShouldBeFound("description.specified=true");

        // Get all the listThreatList where description is null
        defaultListThreatShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListThreatShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listThreatList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListThreatShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListThreatsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListThreatShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listThreatList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListThreatShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListThreatsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where dateCreate is not null
        defaultListThreatShouldBeFound("dateCreate.specified=true");

        // Get all the listThreatList where dateCreate is null
        defaultListThreatShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListThreatShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listThreatList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListThreatShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListThreatsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListThreatShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listThreatList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListThreatShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListThreatsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where dateEdit is not null
        defaultListThreatShouldBeFound("dateEdit.specified=true");

        // Get all the listThreatList where dateEdit is null
        defaultListThreatShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where creator equals to DEFAULT_CREATOR
        defaultListThreatShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listThreatList where creator equals to UPDATED_CREATOR
        defaultListThreatShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListThreatsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListThreatShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listThreatList where creator equals to UPDATED_CREATOR
        defaultListThreatShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListThreatsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where creator is not null
        defaultListThreatShouldBeFound("creator.specified=true");

        // Get all the listThreatList where creator is null
        defaultListThreatShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where editor equals to DEFAULT_EDITOR
        defaultListThreatShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listThreatList where editor equals to UPDATED_EDITOR
        defaultListThreatShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListThreatsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListThreatShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listThreatList where editor equals to UPDATED_EDITOR
        defaultListThreatShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListThreatsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        // Get all the listThreatList where editor is not null
        defaultListThreatShouldBeFound("editor.specified=true");

        // Get all the listThreatList where editor is null
        defaultListThreatShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListThreatsByIdGroupIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListThreatGroup idGroup = listThreat.getIdGroup();
        listThreatRepository.saveAndFlush(listThreat);
        Long idGroupId = idGroup.getId();

        // Get all the listThreatList where idGroup equals to idGroupId
        defaultListThreatShouldBeFound("idGroupId.equals=" + idGroupId);

        // Get all the listThreatList where idGroup equals to idGroupId + 1
        defaultListThreatShouldNotBeFound("idGroupId.equals=" + (idGroupId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListThreatShouldBeFound(String filter) throws Exception {
        restListThreatMockMvc.perform(get("/api/list-threats?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listThreat.getId().intValue())))
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
        restListThreatMockMvc.perform(get("/api/list-threats/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListThreatShouldNotBeFound(String filter) throws Exception {
        restListThreatMockMvc.perform(get("/api/list-threats?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListThreatMockMvc.perform(get("/api/list-threats/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListThreat() throws Exception {
        // Get the listThreat
        restListThreatMockMvc.perform(get("/api/list-threats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListThreat() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        int databaseSizeBeforeUpdate = listThreatRepository.findAll().size();

        // Update the listThreat
        ListThreat updatedListThreat = listThreatRepository.findById(listThreat.getId()).get();
        // Disconnect from session so that the updates on updatedListThreat are not directly saved in db
        em.detach(updatedListThreat);
        updatedListThreat
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListThreatDTO listThreatDTO = listThreatMapper.toDto(updatedListThreat);

        restListThreatMockMvc.perform(put("/api/list-threats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatDTO)))
            .andExpect(status().isOk());

        // Validate the ListThreat in the database
        List<ListThreat> listThreatList = listThreatRepository.findAll();
        assertThat(listThreatList).hasSize(databaseSizeBeforeUpdate);
        ListThreat testListThreat = listThreatList.get(listThreatList.size() - 1);
        assertThat(testListThreat.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListThreat.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListThreat.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListThreat.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListThreat.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListThreat.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListThreat.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListThreat.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListThreat.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListThreat() throws Exception {
        int databaseSizeBeforeUpdate = listThreatRepository.findAll().size();

        // Create the ListThreat
        ListThreatDTO listThreatDTO = listThreatMapper.toDto(listThreat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListThreatMockMvc.perform(put("/api/list-threats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listThreatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListThreat in the database
        List<ListThreat> listThreatList = listThreatRepository.findAll();
        assertThat(listThreatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListThreat() throws Exception {
        // Initialize the database
        listThreatRepository.saveAndFlush(listThreat);

        int databaseSizeBeforeDelete = listThreatRepository.findAll().size();

        // Delete the listThreat
        restListThreatMockMvc.perform(delete("/api/list-threats/{id}", listThreat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListThreat> listThreatList = listThreatRepository.findAll();
        assertThat(listThreatList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListThreat.class);
        ListThreat listThreat1 = new ListThreat();
        listThreat1.setId(1L);
        ListThreat listThreat2 = new ListThreat();
        listThreat2.setId(listThreat1.getId());
        assertThat(listThreat1).isEqualTo(listThreat2);
        listThreat2.setId(2L);
        assertThat(listThreat1).isNotEqualTo(listThreat2);
        listThreat1.setId(null);
        assertThat(listThreat1).isNotEqualTo(listThreat2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListThreatDTO.class);
        ListThreatDTO listThreatDTO1 = new ListThreatDTO();
        listThreatDTO1.setId(1L);
        ListThreatDTO listThreatDTO2 = new ListThreatDTO();
        assertThat(listThreatDTO1).isNotEqualTo(listThreatDTO2);
        listThreatDTO2.setId(listThreatDTO1.getId());
        assertThat(listThreatDTO1).isEqualTo(listThreatDTO2);
        listThreatDTO2.setId(2L);
        assertThat(listThreatDTO1).isNotEqualTo(listThreatDTO2);
        listThreatDTO1.setId(null);
        assertThat(listThreatDTO1).isNotEqualTo(listThreatDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listThreatMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listThreatMapper.fromId(null)).isNull();
    }
}
