package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListRiskConsequence;
import io.github.jhipster.application.repository.ListRiskConsequenceRepository;
import io.github.jhipster.application.service.ListRiskConsequenceService;
import io.github.jhipster.application.service.dto.ListRiskConsequenceDTO;
import io.github.jhipster.application.service.mapper.ListRiskConsequenceMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListRiskConsequenceCriteria;
import io.github.jhipster.application.service.ListRiskConsequenceQueryService;

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
 * Integration tests for the {@Link ListRiskConsequenceResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListRiskConsequenceResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SAFETY = "AAAAAAAAAA";
    private static final String UPDATED_SAFETY = "BBBBBBBBBB";

    private static final String DEFAULT_COMMER_IMPACT = "AAAAAAAAAA";
    private static final String UPDATED_COMMER_IMPACT = "BBBBBBBBBB";

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
    private ListRiskConsequenceRepository listRiskConsequenceRepository;

    @Autowired
    private ListRiskConsequenceMapper listRiskConsequenceMapper;

    @Autowired
    private ListRiskConsequenceService listRiskConsequenceService;

    @Autowired
    private ListRiskConsequenceQueryService listRiskConsequenceQueryService;

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

    private MockMvc restListRiskConsequenceMockMvc;

    private ListRiskConsequence listRiskConsequence;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListRiskConsequenceResource listRiskConsequenceResource = new ListRiskConsequenceResource(listRiskConsequenceService, listRiskConsequenceQueryService);
        this.restListRiskConsequenceMockMvc = MockMvcBuilders.standaloneSetup(listRiskConsequenceResource)
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
    public static ListRiskConsequence createEntity(EntityManager em) {
        ListRiskConsequence listRiskConsequence = new ListRiskConsequence()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .safety(DEFAULT_SAFETY)
            .commerImpact(DEFAULT_COMMER_IMPACT)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listRiskConsequence;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListRiskConsequence createUpdatedEntity(EntityManager em) {
        ListRiskConsequence listRiskConsequence = new ListRiskConsequence()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .safety(UPDATED_SAFETY)
            .commerImpact(UPDATED_COMMER_IMPACT)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listRiskConsequence;
    }

    @BeforeEach
    public void initTest() {
        listRiskConsequence = createEntity(em);
    }

    @Test
    @Transactional
    public void createListRiskConsequence() throws Exception {
        int databaseSizeBeforeCreate = listRiskConsequenceRepository.findAll().size();

        // Create the ListRiskConsequence
        ListRiskConsequenceDTO listRiskConsequenceDTO = listRiskConsequenceMapper.toDto(listRiskConsequence);
        restListRiskConsequenceMockMvc.perform(post("/api/list-risk-consequences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskConsequenceDTO)))
            .andExpect(status().isCreated());

        // Validate the ListRiskConsequence in the database
        List<ListRiskConsequence> listRiskConsequenceList = listRiskConsequenceRepository.findAll();
        assertThat(listRiskConsequenceList).hasSize(databaseSizeBeforeCreate + 1);
        ListRiskConsequence testListRiskConsequence = listRiskConsequenceList.get(listRiskConsequenceList.size() - 1);
        assertThat(testListRiskConsequence.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListRiskConsequence.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListRiskConsequence.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListRiskConsequence.getSafety()).isEqualTo(DEFAULT_SAFETY);
        assertThat(testListRiskConsequence.getCommerImpact()).isEqualTo(DEFAULT_COMMER_IMPACT);
        assertThat(testListRiskConsequence.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListRiskConsequence.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListRiskConsequence.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListRiskConsequence.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListRiskConsequence.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListRiskConsequence.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListRiskConsequenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listRiskConsequenceRepository.findAll().size();

        // Create the ListRiskConsequence with an existing ID
        listRiskConsequence.setId(1L);
        ListRiskConsequenceDTO listRiskConsequenceDTO = listRiskConsequenceMapper.toDto(listRiskConsequence);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListRiskConsequenceMockMvc.perform(post("/api/list-risk-consequences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskConsequenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListRiskConsequence in the database
        List<ListRiskConsequence> listRiskConsequenceList = listRiskConsequenceRepository.findAll();
        assertThat(listRiskConsequenceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listRiskConsequenceRepository.findAll().size();
        // set the field null
        listRiskConsequence.setCode(null);

        // Create the ListRiskConsequence, which fails.
        ListRiskConsequenceDTO listRiskConsequenceDTO = listRiskConsequenceMapper.toDto(listRiskConsequence);

        restListRiskConsequenceMockMvc.perform(post("/api/list-risk-consequences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskConsequenceDTO)))
            .andExpect(status().isBadRequest());

        List<ListRiskConsequence> listRiskConsequenceList = listRiskConsequenceRepository.findAll();
        assertThat(listRiskConsequenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listRiskConsequenceRepository.findAll().size();
        // set the field null
        listRiskConsequence.setName(null);

        // Create the ListRiskConsequence, which fails.
        ListRiskConsequenceDTO listRiskConsequenceDTO = listRiskConsequenceMapper.toDto(listRiskConsequence);

        restListRiskConsequenceMockMvc.perform(post("/api/list-risk-consequences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskConsequenceDTO)))
            .andExpect(status().isBadRequest());

        List<ListRiskConsequence> listRiskConsequenceList = listRiskConsequenceRepository.findAll();
        assertThat(listRiskConsequenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listRiskConsequenceRepository.findAll().size();
        // set the field null
        listRiskConsequence.setFullName(null);

        // Create the ListRiskConsequence, which fails.
        ListRiskConsequenceDTO listRiskConsequenceDTO = listRiskConsequenceMapper.toDto(listRiskConsequence);

        restListRiskConsequenceMockMvc.perform(post("/api/list-risk-consequences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskConsequenceDTO)))
            .andExpect(status().isBadRequest());

        List<ListRiskConsequence> listRiskConsequenceList = listRiskConsequenceRepository.findAll();
        assertThat(listRiskConsequenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listRiskConsequenceRepository.findAll().size();
        // set the field null
        listRiskConsequence.setIsCurrentFlag(null);

        // Create the ListRiskConsequence, which fails.
        ListRiskConsequenceDTO listRiskConsequenceDTO = listRiskConsequenceMapper.toDto(listRiskConsequence);

        restListRiskConsequenceMockMvc.perform(post("/api/list-risk-consequences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskConsequenceDTO)))
            .andExpect(status().isBadRequest());

        List<ListRiskConsequence> listRiskConsequenceList = listRiskConsequenceRepository.findAll();
        assertThat(listRiskConsequenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequences() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList
        restListRiskConsequenceMockMvc.perform(get("/api/list-risk-consequences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listRiskConsequence.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].safety").value(hasItem(DEFAULT_SAFETY.toString())))
            .andExpect(jsonPath("$.[*].commerImpact").value(hasItem(DEFAULT_COMMER_IMPACT.toString())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getListRiskConsequence() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get the listRiskConsequence
        restListRiskConsequenceMockMvc.perform(get("/api/list-risk-consequences/{id}", listRiskConsequence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listRiskConsequence.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.safety").value(DEFAULT_SAFETY.toString()))
            .andExpect(jsonPath("$.commerImpact").value(DEFAULT_COMMER_IMPACT.toString()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where code equals to DEFAULT_CODE
        defaultListRiskConsequenceShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listRiskConsequenceList where code equals to UPDATED_CODE
        defaultListRiskConsequenceShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListRiskConsequenceShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listRiskConsequenceList where code equals to UPDATED_CODE
        defaultListRiskConsequenceShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where code is not null
        defaultListRiskConsequenceShouldBeFound("code.specified=true");

        // Get all the listRiskConsequenceList where code is null
        defaultListRiskConsequenceShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where name equals to DEFAULT_NAME
        defaultListRiskConsequenceShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listRiskConsequenceList where name equals to UPDATED_NAME
        defaultListRiskConsequenceShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListRiskConsequenceShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listRiskConsequenceList where name equals to UPDATED_NAME
        defaultListRiskConsequenceShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where name is not null
        defaultListRiskConsequenceShouldBeFound("name.specified=true");

        // Get all the listRiskConsequenceList where name is null
        defaultListRiskConsequenceShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where fullName equals to DEFAULT_FULL_NAME
        defaultListRiskConsequenceShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listRiskConsequenceList where fullName equals to UPDATED_FULL_NAME
        defaultListRiskConsequenceShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListRiskConsequenceShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listRiskConsequenceList where fullName equals to UPDATED_FULL_NAME
        defaultListRiskConsequenceShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where fullName is not null
        defaultListRiskConsequenceShouldBeFound("fullName.specified=true");

        // Get all the listRiskConsequenceList where fullName is null
        defaultListRiskConsequenceShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesBySafetyIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where safety equals to DEFAULT_SAFETY
        defaultListRiskConsequenceShouldBeFound("safety.equals=" + DEFAULT_SAFETY);

        // Get all the listRiskConsequenceList where safety equals to UPDATED_SAFETY
        defaultListRiskConsequenceShouldNotBeFound("safety.equals=" + UPDATED_SAFETY);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesBySafetyIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where safety in DEFAULT_SAFETY or UPDATED_SAFETY
        defaultListRiskConsequenceShouldBeFound("safety.in=" + DEFAULT_SAFETY + "," + UPDATED_SAFETY);

        // Get all the listRiskConsequenceList where safety equals to UPDATED_SAFETY
        defaultListRiskConsequenceShouldNotBeFound("safety.in=" + UPDATED_SAFETY);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesBySafetyIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where safety is not null
        defaultListRiskConsequenceShouldBeFound("safety.specified=true");

        // Get all the listRiskConsequenceList where safety is null
        defaultListRiskConsequenceShouldNotBeFound("safety.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByCommerImpactIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where commerImpact equals to DEFAULT_COMMER_IMPACT
        defaultListRiskConsequenceShouldBeFound("commerImpact.equals=" + DEFAULT_COMMER_IMPACT);

        // Get all the listRiskConsequenceList where commerImpact equals to UPDATED_COMMER_IMPACT
        defaultListRiskConsequenceShouldNotBeFound("commerImpact.equals=" + UPDATED_COMMER_IMPACT);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByCommerImpactIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where commerImpact in DEFAULT_COMMER_IMPACT or UPDATED_COMMER_IMPACT
        defaultListRiskConsequenceShouldBeFound("commerImpact.in=" + DEFAULT_COMMER_IMPACT + "," + UPDATED_COMMER_IMPACT);

        // Get all the listRiskConsequenceList where commerImpact equals to UPDATED_COMMER_IMPACT
        defaultListRiskConsequenceShouldNotBeFound("commerImpact.in=" + UPDATED_COMMER_IMPACT);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByCommerImpactIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where commerImpact is not null
        defaultListRiskConsequenceShouldBeFound("commerImpact.specified=true");

        // Get all the listRiskConsequenceList where commerImpact is null
        defaultListRiskConsequenceShouldNotBeFound("commerImpact.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListRiskConsequenceShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listRiskConsequenceList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListRiskConsequenceShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListRiskConsequenceShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listRiskConsequenceList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListRiskConsequenceShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where isCurrentFlag is not null
        defaultListRiskConsequenceShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listRiskConsequenceList where isCurrentFlag is null
        defaultListRiskConsequenceShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListRiskConsequenceShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listRiskConsequenceList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListRiskConsequenceShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListRiskConsequenceShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listRiskConsequenceList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListRiskConsequenceShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListRiskConsequencesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where description equals to DEFAULT_DESCRIPTION
        defaultListRiskConsequenceShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listRiskConsequenceList where description equals to UPDATED_DESCRIPTION
        defaultListRiskConsequenceShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListRiskConsequenceShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listRiskConsequenceList where description equals to UPDATED_DESCRIPTION
        defaultListRiskConsequenceShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where description is not null
        defaultListRiskConsequenceShouldBeFound("description.specified=true");

        // Get all the listRiskConsequenceList where description is null
        defaultListRiskConsequenceShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListRiskConsequenceShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listRiskConsequenceList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListRiskConsequenceShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListRiskConsequenceShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listRiskConsequenceList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListRiskConsequenceShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where dateCreate is not null
        defaultListRiskConsequenceShouldBeFound("dateCreate.specified=true");

        // Get all the listRiskConsequenceList where dateCreate is null
        defaultListRiskConsequenceShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListRiskConsequenceShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listRiskConsequenceList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListRiskConsequenceShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListRiskConsequenceShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listRiskConsequenceList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListRiskConsequenceShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where dateEdit is not null
        defaultListRiskConsequenceShouldBeFound("dateEdit.specified=true");

        // Get all the listRiskConsequenceList where dateEdit is null
        defaultListRiskConsequenceShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where creator equals to DEFAULT_CREATOR
        defaultListRiskConsequenceShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listRiskConsequenceList where creator equals to UPDATED_CREATOR
        defaultListRiskConsequenceShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListRiskConsequenceShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listRiskConsequenceList where creator equals to UPDATED_CREATOR
        defaultListRiskConsequenceShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where creator is not null
        defaultListRiskConsequenceShouldBeFound("creator.specified=true");

        // Get all the listRiskConsequenceList where creator is null
        defaultListRiskConsequenceShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where editor equals to DEFAULT_EDITOR
        defaultListRiskConsequenceShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listRiskConsequenceList where editor equals to UPDATED_EDITOR
        defaultListRiskConsequenceShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListRiskConsequenceShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listRiskConsequenceList where editor equals to UPDATED_EDITOR
        defaultListRiskConsequenceShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListRiskConsequencesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        // Get all the listRiskConsequenceList where editor is not null
        defaultListRiskConsequenceShouldBeFound("editor.specified=true");

        // Get all the listRiskConsequenceList where editor is null
        defaultListRiskConsequenceShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListRiskConsequenceShouldBeFound(String filter) throws Exception {
        restListRiskConsequenceMockMvc.perform(get("/api/list-risk-consequences?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listRiskConsequence.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].safety").value(hasItem(DEFAULT_SAFETY)))
            .andExpect(jsonPath("$.[*].commerImpact").value(hasItem(DEFAULT_COMMER_IMPACT)))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restListRiskConsequenceMockMvc.perform(get("/api/list-risk-consequences/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListRiskConsequenceShouldNotBeFound(String filter) throws Exception {
        restListRiskConsequenceMockMvc.perform(get("/api/list-risk-consequences?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListRiskConsequenceMockMvc.perform(get("/api/list-risk-consequences/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListRiskConsequence() throws Exception {
        // Get the listRiskConsequence
        restListRiskConsequenceMockMvc.perform(get("/api/list-risk-consequences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListRiskConsequence() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        int databaseSizeBeforeUpdate = listRiskConsequenceRepository.findAll().size();

        // Update the listRiskConsequence
        ListRiskConsequence updatedListRiskConsequence = listRiskConsequenceRepository.findById(listRiskConsequence.getId()).get();
        // Disconnect from session so that the updates on updatedListRiskConsequence are not directly saved in db
        em.detach(updatedListRiskConsequence);
        updatedListRiskConsequence
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .safety(UPDATED_SAFETY)
            .commerImpact(UPDATED_COMMER_IMPACT)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListRiskConsequenceDTO listRiskConsequenceDTO = listRiskConsequenceMapper.toDto(updatedListRiskConsequence);

        restListRiskConsequenceMockMvc.perform(put("/api/list-risk-consequences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskConsequenceDTO)))
            .andExpect(status().isOk());

        // Validate the ListRiskConsequence in the database
        List<ListRiskConsequence> listRiskConsequenceList = listRiskConsequenceRepository.findAll();
        assertThat(listRiskConsequenceList).hasSize(databaseSizeBeforeUpdate);
        ListRiskConsequence testListRiskConsequence = listRiskConsequenceList.get(listRiskConsequenceList.size() - 1);
        assertThat(testListRiskConsequence.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListRiskConsequence.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListRiskConsequence.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListRiskConsequence.getSafety()).isEqualTo(UPDATED_SAFETY);
        assertThat(testListRiskConsequence.getCommerImpact()).isEqualTo(UPDATED_COMMER_IMPACT);
        assertThat(testListRiskConsequence.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListRiskConsequence.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListRiskConsequence.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListRiskConsequence.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListRiskConsequence.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListRiskConsequence.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListRiskConsequence() throws Exception {
        int databaseSizeBeforeUpdate = listRiskConsequenceRepository.findAll().size();

        // Create the ListRiskConsequence
        ListRiskConsequenceDTO listRiskConsequenceDTO = listRiskConsequenceMapper.toDto(listRiskConsequence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListRiskConsequenceMockMvc.perform(put("/api/list-risk-consequences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listRiskConsequenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListRiskConsequence in the database
        List<ListRiskConsequence> listRiskConsequenceList = listRiskConsequenceRepository.findAll();
        assertThat(listRiskConsequenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListRiskConsequence() throws Exception {
        // Initialize the database
        listRiskConsequenceRepository.saveAndFlush(listRiskConsequence);

        int databaseSizeBeforeDelete = listRiskConsequenceRepository.findAll().size();

        // Delete the listRiskConsequence
        restListRiskConsequenceMockMvc.perform(delete("/api/list-risk-consequences/{id}", listRiskConsequence.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListRiskConsequence> listRiskConsequenceList = listRiskConsequenceRepository.findAll();
        assertThat(listRiskConsequenceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListRiskConsequence.class);
        ListRiskConsequence listRiskConsequence1 = new ListRiskConsequence();
        listRiskConsequence1.setId(1L);
        ListRiskConsequence listRiskConsequence2 = new ListRiskConsequence();
        listRiskConsequence2.setId(listRiskConsequence1.getId());
        assertThat(listRiskConsequence1).isEqualTo(listRiskConsequence2);
        listRiskConsequence2.setId(2L);
        assertThat(listRiskConsequence1).isNotEqualTo(listRiskConsequence2);
        listRiskConsequence1.setId(null);
        assertThat(listRiskConsequence1).isNotEqualTo(listRiskConsequence2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListRiskConsequenceDTO.class);
        ListRiskConsequenceDTO listRiskConsequenceDTO1 = new ListRiskConsequenceDTO();
        listRiskConsequenceDTO1.setId(1L);
        ListRiskConsequenceDTO listRiskConsequenceDTO2 = new ListRiskConsequenceDTO();
        assertThat(listRiskConsequenceDTO1).isNotEqualTo(listRiskConsequenceDTO2);
        listRiskConsequenceDTO2.setId(listRiskConsequenceDTO1.getId());
        assertThat(listRiskConsequenceDTO1).isEqualTo(listRiskConsequenceDTO2);
        listRiskConsequenceDTO2.setId(2L);
        assertThat(listRiskConsequenceDTO1).isNotEqualTo(listRiskConsequenceDTO2);
        listRiskConsequenceDTO1.setId(null);
        assertThat(listRiskConsequenceDTO1).isNotEqualTo(listRiskConsequenceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listRiskConsequenceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listRiskConsequenceMapper.fromId(null)).isNull();
    }
}
