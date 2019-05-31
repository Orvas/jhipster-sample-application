package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListExternalCoating;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.PipejointHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListExternalCoatingRepository;
import io.github.jhipster.application.service.ListExternalCoatingService;
import io.github.jhipster.application.service.dto.ListExternalCoatingDTO;
import io.github.jhipster.application.service.mapper.ListExternalCoatingMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListExternalCoatingCriteria;
import io.github.jhipster.application.service.ListExternalCoatingQueryService;

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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ListExternalCoatingResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListExternalCoatingResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DENSITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_DENSITY = new BigDecimal(2);

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
    private ListExternalCoatingRepository listExternalCoatingRepository;

    @Autowired
    private ListExternalCoatingMapper listExternalCoatingMapper;

    @Autowired
    private ListExternalCoatingService listExternalCoatingService;

    @Autowired
    private ListExternalCoatingQueryService listExternalCoatingQueryService;

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

    private MockMvc restListExternalCoatingMockMvc;

    private ListExternalCoating listExternalCoating;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListExternalCoatingResource listExternalCoatingResource = new ListExternalCoatingResource(listExternalCoatingService, listExternalCoatingQueryService);
        this.restListExternalCoatingMockMvc = MockMvcBuilders.standaloneSetup(listExternalCoatingResource)
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
    public static ListExternalCoating createEntity(EntityManager em) {
        ListExternalCoating listExternalCoating = new ListExternalCoating()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .density(DEFAULT_DENSITY)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listExternalCoating;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListExternalCoating createUpdatedEntity(EntityManager em) {
        ListExternalCoating listExternalCoating = new ListExternalCoating()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .density(UPDATED_DENSITY)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listExternalCoating;
    }

    @BeforeEach
    public void initTest() {
        listExternalCoating = createEntity(em);
    }

    @Test
    @Transactional
    public void createListExternalCoating() throws Exception {
        int databaseSizeBeforeCreate = listExternalCoatingRepository.findAll().size();

        // Create the ListExternalCoating
        ListExternalCoatingDTO listExternalCoatingDTO = listExternalCoatingMapper.toDto(listExternalCoating);
        restListExternalCoatingMockMvc.perform(post("/api/list-external-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listExternalCoatingDTO)))
            .andExpect(status().isCreated());

        // Validate the ListExternalCoating in the database
        List<ListExternalCoating> listExternalCoatingList = listExternalCoatingRepository.findAll();
        assertThat(listExternalCoatingList).hasSize(databaseSizeBeforeCreate + 1);
        ListExternalCoating testListExternalCoating = listExternalCoatingList.get(listExternalCoatingList.size() - 1);
        assertThat(testListExternalCoating.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListExternalCoating.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListExternalCoating.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListExternalCoating.getDensity()).isEqualTo(DEFAULT_DENSITY);
        assertThat(testListExternalCoating.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListExternalCoating.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListExternalCoating.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListExternalCoating.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListExternalCoating.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListExternalCoating.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListExternalCoatingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listExternalCoatingRepository.findAll().size();

        // Create the ListExternalCoating with an existing ID
        listExternalCoating.setId(1L);
        ListExternalCoatingDTO listExternalCoatingDTO = listExternalCoatingMapper.toDto(listExternalCoating);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListExternalCoatingMockMvc.perform(post("/api/list-external-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listExternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListExternalCoating in the database
        List<ListExternalCoating> listExternalCoatingList = listExternalCoatingRepository.findAll();
        assertThat(listExternalCoatingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listExternalCoatingRepository.findAll().size();
        // set the field null
        listExternalCoating.setCode(null);

        // Create the ListExternalCoating, which fails.
        ListExternalCoatingDTO listExternalCoatingDTO = listExternalCoatingMapper.toDto(listExternalCoating);

        restListExternalCoatingMockMvc.perform(post("/api/list-external-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listExternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        List<ListExternalCoating> listExternalCoatingList = listExternalCoatingRepository.findAll();
        assertThat(listExternalCoatingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listExternalCoatingRepository.findAll().size();
        // set the field null
        listExternalCoating.setName(null);

        // Create the ListExternalCoating, which fails.
        ListExternalCoatingDTO listExternalCoatingDTO = listExternalCoatingMapper.toDto(listExternalCoating);

        restListExternalCoatingMockMvc.perform(post("/api/list-external-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listExternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        List<ListExternalCoating> listExternalCoatingList = listExternalCoatingRepository.findAll();
        assertThat(listExternalCoatingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listExternalCoatingRepository.findAll().size();
        // set the field null
        listExternalCoating.setFullName(null);

        // Create the ListExternalCoating, which fails.
        ListExternalCoatingDTO listExternalCoatingDTO = listExternalCoatingMapper.toDto(listExternalCoating);

        restListExternalCoatingMockMvc.perform(post("/api/list-external-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listExternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        List<ListExternalCoating> listExternalCoatingList = listExternalCoatingRepository.findAll();
        assertThat(listExternalCoatingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listExternalCoatingRepository.findAll().size();
        // set the field null
        listExternalCoating.setIsCurrentFlag(null);

        // Create the ListExternalCoating, which fails.
        ListExternalCoatingDTO listExternalCoatingDTO = listExternalCoatingMapper.toDto(listExternalCoating);

        restListExternalCoatingMockMvc.perform(post("/api/list-external-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listExternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        List<ListExternalCoating> listExternalCoatingList = listExternalCoatingRepository.findAll();
        assertThat(listExternalCoatingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatings() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList
        restListExternalCoatingMockMvc.perform(get("/api/list-external-coatings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listExternalCoating.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].density").value(hasItem(DEFAULT_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getListExternalCoating() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get the listExternalCoating
        restListExternalCoatingMockMvc.perform(get("/api/list-external-coatings/{id}", listExternalCoating.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listExternalCoating.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.density").value(DEFAULT_DENSITY.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where code equals to DEFAULT_CODE
        defaultListExternalCoatingShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listExternalCoatingList where code equals to UPDATED_CODE
        defaultListExternalCoatingShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListExternalCoatingShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listExternalCoatingList where code equals to UPDATED_CODE
        defaultListExternalCoatingShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where code is not null
        defaultListExternalCoatingShouldBeFound("code.specified=true");

        // Get all the listExternalCoatingList where code is null
        defaultListExternalCoatingShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where name equals to DEFAULT_NAME
        defaultListExternalCoatingShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listExternalCoatingList where name equals to UPDATED_NAME
        defaultListExternalCoatingShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListExternalCoatingShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listExternalCoatingList where name equals to UPDATED_NAME
        defaultListExternalCoatingShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where name is not null
        defaultListExternalCoatingShouldBeFound("name.specified=true");

        // Get all the listExternalCoatingList where name is null
        defaultListExternalCoatingShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where fullName equals to DEFAULT_FULL_NAME
        defaultListExternalCoatingShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listExternalCoatingList where fullName equals to UPDATED_FULL_NAME
        defaultListExternalCoatingShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListExternalCoatingShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listExternalCoatingList where fullName equals to UPDATED_FULL_NAME
        defaultListExternalCoatingShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where fullName is not null
        defaultListExternalCoatingShouldBeFound("fullName.specified=true");

        // Get all the listExternalCoatingList where fullName is null
        defaultListExternalCoatingShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDensityIsEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where density equals to DEFAULT_DENSITY
        defaultListExternalCoatingShouldBeFound("density.equals=" + DEFAULT_DENSITY);

        // Get all the listExternalCoatingList where density equals to UPDATED_DENSITY
        defaultListExternalCoatingShouldNotBeFound("density.equals=" + UPDATED_DENSITY);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDensityIsInShouldWork() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where density in DEFAULT_DENSITY or UPDATED_DENSITY
        defaultListExternalCoatingShouldBeFound("density.in=" + DEFAULT_DENSITY + "," + UPDATED_DENSITY);

        // Get all the listExternalCoatingList where density equals to UPDATED_DENSITY
        defaultListExternalCoatingShouldNotBeFound("density.in=" + UPDATED_DENSITY);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDensityIsNullOrNotNull() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where density is not null
        defaultListExternalCoatingShouldBeFound("density.specified=true");

        // Get all the listExternalCoatingList where density is null
        defaultListExternalCoatingShouldNotBeFound("density.specified=false");
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListExternalCoatingShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listExternalCoatingList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListExternalCoatingShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListExternalCoatingShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listExternalCoatingList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListExternalCoatingShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where isCurrentFlag is not null
        defaultListExternalCoatingShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listExternalCoatingList where isCurrentFlag is null
        defaultListExternalCoatingShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListExternalCoatingShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listExternalCoatingList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListExternalCoatingShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListExternalCoatingShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listExternalCoatingList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListExternalCoatingShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListExternalCoatingsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where description equals to DEFAULT_DESCRIPTION
        defaultListExternalCoatingShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listExternalCoatingList where description equals to UPDATED_DESCRIPTION
        defaultListExternalCoatingShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListExternalCoatingShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listExternalCoatingList where description equals to UPDATED_DESCRIPTION
        defaultListExternalCoatingShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where description is not null
        defaultListExternalCoatingShouldBeFound("description.specified=true");

        // Get all the listExternalCoatingList where description is null
        defaultListExternalCoatingShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListExternalCoatingShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listExternalCoatingList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListExternalCoatingShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListExternalCoatingShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listExternalCoatingList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListExternalCoatingShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where dateCreate is not null
        defaultListExternalCoatingShouldBeFound("dateCreate.specified=true");

        // Get all the listExternalCoatingList where dateCreate is null
        defaultListExternalCoatingShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListExternalCoatingShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listExternalCoatingList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListExternalCoatingShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListExternalCoatingShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listExternalCoatingList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListExternalCoatingShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where dateEdit is not null
        defaultListExternalCoatingShouldBeFound("dateEdit.specified=true");

        // Get all the listExternalCoatingList where dateEdit is null
        defaultListExternalCoatingShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where creator equals to DEFAULT_CREATOR
        defaultListExternalCoatingShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listExternalCoatingList where creator equals to UPDATED_CREATOR
        defaultListExternalCoatingShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListExternalCoatingShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listExternalCoatingList where creator equals to UPDATED_CREATOR
        defaultListExternalCoatingShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where creator is not null
        defaultListExternalCoatingShouldBeFound("creator.specified=true");

        // Get all the listExternalCoatingList where creator is null
        defaultListExternalCoatingShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where editor equals to DEFAULT_EDITOR
        defaultListExternalCoatingShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listExternalCoatingList where editor equals to UPDATED_EDITOR
        defaultListExternalCoatingShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListExternalCoatingShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listExternalCoatingList where editor equals to UPDATED_EDITOR
        defaultListExternalCoatingShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        // Get all the listExternalCoatingList where editor is not null
        defaultListExternalCoatingShouldBeFound("editor.specified=true");

        // Get all the listExternalCoatingList where editor is null
        defaultListExternalCoatingShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListExternalCoatingsByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listExternalCoating.addBendHist(bendHist);
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);
        Long bendHistId = bendHist.getId();

        // Get all the listExternalCoatingList where bendHist equals to bendHistId
        defaultListExternalCoatingShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listExternalCoatingList where bendHist equals to bendHistId + 1
        defaultListExternalCoatingShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListExternalCoatingsByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listExternalCoating.addBuckleArrestorHist(buckleArrestorHist);
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listExternalCoatingList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListExternalCoatingShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listExternalCoatingList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListExternalCoatingShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListExternalCoatingsByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listExternalCoating.addPipeHist(pipeHist);
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);
        Long pipeHistId = pipeHist.getId();

        // Get all the listExternalCoatingList where pipeHist equals to pipeHistId
        defaultListExternalCoatingShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listExternalCoatingList where pipeHist equals to pipeHistId + 1
        defaultListExternalCoatingShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListExternalCoatingsByPipejointHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipejointHist pipejointHist = PipejointHistResourceIT.createEntity(em);
        em.persist(pipejointHist);
        em.flush();
        listExternalCoating.addPipejointHist(pipejointHist);
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);
        Long pipejointHistId = pipejointHist.getId();

        // Get all the listExternalCoatingList where pipejointHist equals to pipejointHistId
        defaultListExternalCoatingShouldBeFound("pipejointHistId.equals=" + pipejointHistId);

        // Get all the listExternalCoatingList where pipejointHist equals to pipejointHistId + 1
        defaultListExternalCoatingShouldNotBeFound("pipejointHistId.equals=" + (pipejointHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListExternalCoatingsByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listExternalCoating.addTeeHist(teeHist);
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);
        Long teeHistId = teeHist.getId();

        // Get all the listExternalCoatingList where teeHist equals to teeHistId
        defaultListExternalCoatingShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listExternalCoatingList where teeHist equals to teeHistId + 1
        defaultListExternalCoatingShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListExternalCoatingsByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listExternalCoating.addValveHist(valveHist);
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);
        Long valveHistId = valveHist.getId();

        // Get all the listExternalCoatingList where valveHist equals to valveHistId
        defaultListExternalCoatingShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listExternalCoatingList where valveHist equals to valveHistId + 1
        defaultListExternalCoatingShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListExternalCoatingShouldBeFound(String filter) throws Exception {
        restListExternalCoatingMockMvc.perform(get("/api/list-external-coatings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listExternalCoating.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].density").value(hasItem(DEFAULT_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restListExternalCoatingMockMvc.perform(get("/api/list-external-coatings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListExternalCoatingShouldNotBeFound(String filter) throws Exception {
        restListExternalCoatingMockMvc.perform(get("/api/list-external-coatings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListExternalCoatingMockMvc.perform(get("/api/list-external-coatings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListExternalCoating() throws Exception {
        // Get the listExternalCoating
        restListExternalCoatingMockMvc.perform(get("/api/list-external-coatings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListExternalCoating() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        int databaseSizeBeforeUpdate = listExternalCoatingRepository.findAll().size();

        // Update the listExternalCoating
        ListExternalCoating updatedListExternalCoating = listExternalCoatingRepository.findById(listExternalCoating.getId()).get();
        // Disconnect from session so that the updates on updatedListExternalCoating are not directly saved in db
        em.detach(updatedListExternalCoating);
        updatedListExternalCoating
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .density(UPDATED_DENSITY)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListExternalCoatingDTO listExternalCoatingDTO = listExternalCoatingMapper.toDto(updatedListExternalCoating);

        restListExternalCoatingMockMvc.perform(put("/api/list-external-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listExternalCoatingDTO)))
            .andExpect(status().isOk());

        // Validate the ListExternalCoating in the database
        List<ListExternalCoating> listExternalCoatingList = listExternalCoatingRepository.findAll();
        assertThat(listExternalCoatingList).hasSize(databaseSizeBeforeUpdate);
        ListExternalCoating testListExternalCoating = listExternalCoatingList.get(listExternalCoatingList.size() - 1);
        assertThat(testListExternalCoating.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListExternalCoating.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListExternalCoating.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListExternalCoating.getDensity()).isEqualTo(UPDATED_DENSITY);
        assertThat(testListExternalCoating.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListExternalCoating.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListExternalCoating.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListExternalCoating.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListExternalCoating.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListExternalCoating.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListExternalCoating() throws Exception {
        int databaseSizeBeforeUpdate = listExternalCoatingRepository.findAll().size();

        // Create the ListExternalCoating
        ListExternalCoatingDTO listExternalCoatingDTO = listExternalCoatingMapper.toDto(listExternalCoating);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListExternalCoatingMockMvc.perform(put("/api/list-external-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listExternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListExternalCoating in the database
        List<ListExternalCoating> listExternalCoatingList = listExternalCoatingRepository.findAll();
        assertThat(listExternalCoatingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListExternalCoating() throws Exception {
        // Initialize the database
        listExternalCoatingRepository.saveAndFlush(listExternalCoating);

        int databaseSizeBeforeDelete = listExternalCoatingRepository.findAll().size();

        // Delete the listExternalCoating
        restListExternalCoatingMockMvc.perform(delete("/api/list-external-coatings/{id}", listExternalCoating.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListExternalCoating> listExternalCoatingList = listExternalCoatingRepository.findAll();
        assertThat(listExternalCoatingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListExternalCoating.class);
        ListExternalCoating listExternalCoating1 = new ListExternalCoating();
        listExternalCoating1.setId(1L);
        ListExternalCoating listExternalCoating2 = new ListExternalCoating();
        listExternalCoating2.setId(listExternalCoating1.getId());
        assertThat(listExternalCoating1).isEqualTo(listExternalCoating2);
        listExternalCoating2.setId(2L);
        assertThat(listExternalCoating1).isNotEqualTo(listExternalCoating2);
        listExternalCoating1.setId(null);
        assertThat(listExternalCoating1).isNotEqualTo(listExternalCoating2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListExternalCoatingDTO.class);
        ListExternalCoatingDTO listExternalCoatingDTO1 = new ListExternalCoatingDTO();
        listExternalCoatingDTO1.setId(1L);
        ListExternalCoatingDTO listExternalCoatingDTO2 = new ListExternalCoatingDTO();
        assertThat(listExternalCoatingDTO1).isNotEqualTo(listExternalCoatingDTO2);
        listExternalCoatingDTO2.setId(listExternalCoatingDTO1.getId());
        assertThat(listExternalCoatingDTO1).isEqualTo(listExternalCoatingDTO2);
        listExternalCoatingDTO2.setId(2L);
        assertThat(listExternalCoatingDTO1).isNotEqualTo(listExternalCoatingDTO2);
        listExternalCoatingDTO1.setId(null);
        assertThat(listExternalCoatingDTO1).isNotEqualTo(listExternalCoatingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listExternalCoatingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listExternalCoatingMapper.fromId(null)).isNull();
    }
}
