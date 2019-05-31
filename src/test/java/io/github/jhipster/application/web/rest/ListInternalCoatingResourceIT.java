package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListInternalCoating;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListInternalCoatingRepository;
import io.github.jhipster.application.service.ListInternalCoatingService;
import io.github.jhipster.application.service.dto.ListInternalCoatingDTO;
import io.github.jhipster.application.service.mapper.ListInternalCoatingMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListInternalCoatingCriteria;
import io.github.jhipster.application.service.ListInternalCoatingQueryService;

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
 * Integration tests for the {@Link ListInternalCoatingResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListInternalCoatingResourceIT {

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
    private ListInternalCoatingRepository listInternalCoatingRepository;

    @Autowired
    private ListInternalCoatingMapper listInternalCoatingMapper;

    @Autowired
    private ListInternalCoatingService listInternalCoatingService;

    @Autowired
    private ListInternalCoatingQueryService listInternalCoatingQueryService;

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

    private MockMvc restListInternalCoatingMockMvc;

    private ListInternalCoating listInternalCoating;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListInternalCoatingResource listInternalCoatingResource = new ListInternalCoatingResource(listInternalCoatingService, listInternalCoatingQueryService);
        this.restListInternalCoatingMockMvc = MockMvcBuilders.standaloneSetup(listInternalCoatingResource)
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
    public static ListInternalCoating createEntity(EntityManager em) {
        ListInternalCoating listInternalCoating = new ListInternalCoating()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listInternalCoating;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListInternalCoating createUpdatedEntity(EntityManager em) {
        ListInternalCoating listInternalCoating = new ListInternalCoating()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listInternalCoating;
    }

    @BeforeEach
    public void initTest() {
        listInternalCoating = createEntity(em);
    }

    @Test
    @Transactional
    public void createListInternalCoating() throws Exception {
        int databaseSizeBeforeCreate = listInternalCoatingRepository.findAll().size();

        // Create the ListInternalCoating
        ListInternalCoatingDTO listInternalCoatingDTO = listInternalCoatingMapper.toDto(listInternalCoating);
        restListInternalCoatingMockMvc.perform(post("/api/list-internal-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalCoatingDTO)))
            .andExpect(status().isCreated());

        // Validate the ListInternalCoating in the database
        List<ListInternalCoating> listInternalCoatingList = listInternalCoatingRepository.findAll();
        assertThat(listInternalCoatingList).hasSize(databaseSizeBeforeCreate + 1);
        ListInternalCoating testListInternalCoating = listInternalCoatingList.get(listInternalCoatingList.size() - 1);
        assertThat(testListInternalCoating.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListInternalCoating.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListInternalCoating.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListInternalCoating.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListInternalCoating.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListInternalCoating.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListInternalCoating.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListInternalCoating.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListInternalCoating.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListInternalCoatingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listInternalCoatingRepository.findAll().size();

        // Create the ListInternalCoating with an existing ID
        listInternalCoating.setId(1L);
        ListInternalCoatingDTO listInternalCoatingDTO = listInternalCoatingMapper.toDto(listInternalCoating);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListInternalCoatingMockMvc.perform(post("/api/list-internal-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListInternalCoating in the database
        List<ListInternalCoating> listInternalCoatingList = listInternalCoatingRepository.findAll();
        assertThat(listInternalCoatingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listInternalCoatingRepository.findAll().size();
        // set the field null
        listInternalCoating.setCode(null);

        // Create the ListInternalCoating, which fails.
        ListInternalCoatingDTO listInternalCoatingDTO = listInternalCoatingMapper.toDto(listInternalCoating);

        restListInternalCoatingMockMvc.perform(post("/api/list-internal-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        List<ListInternalCoating> listInternalCoatingList = listInternalCoatingRepository.findAll();
        assertThat(listInternalCoatingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listInternalCoatingRepository.findAll().size();
        // set the field null
        listInternalCoating.setName(null);

        // Create the ListInternalCoating, which fails.
        ListInternalCoatingDTO listInternalCoatingDTO = listInternalCoatingMapper.toDto(listInternalCoating);

        restListInternalCoatingMockMvc.perform(post("/api/list-internal-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        List<ListInternalCoating> listInternalCoatingList = listInternalCoatingRepository.findAll();
        assertThat(listInternalCoatingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listInternalCoatingRepository.findAll().size();
        // set the field null
        listInternalCoating.setFullName(null);

        // Create the ListInternalCoating, which fails.
        ListInternalCoatingDTO listInternalCoatingDTO = listInternalCoatingMapper.toDto(listInternalCoating);

        restListInternalCoatingMockMvc.perform(post("/api/list-internal-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        List<ListInternalCoating> listInternalCoatingList = listInternalCoatingRepository.findAll();
        assertThat(listInternalCoatingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listInternalCoatingRepository.findAll().size();
        // set the field null
        listInternalCoating.setIsCurrentFlag(null);

        // Create the ListInternalCoating, which fails.
        ListInternalCoatingDTO listInternalCoatingDTO = listInternalCoatingMapper.toDto(listInternalCoating);

        restListInternalCoatingMockMvc.perform(post("/api/list-internal-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        List<ListInternalCoating> listInternalCoatingList = listInternalCoatingRepository.findAll();
        assertThat(listInternalCoatingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatings() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList
        restListInternalCoatingMockMvc.perform(get("/api/list-internal-coatings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listInternalCoating.getId().intValue())))
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
    public void getListInternalCoating() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get the listInternalCoating
        restListInternalCoatingMockMvc.perform(get("/api/list-internal-coatings/{id}", listInternalCoating.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listInternalCoating.getId().intValue()))
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
    public void getAllListInternalCoatingsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where code equals to DEFAULT_CODE
        defaultListInternalCoatingShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listInternalCoatingList where code equals to UPDATED_CODE
        defaultListInternalCoatingShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListInternalCoatingShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listInternalCoatingList where code equals to UPDATED_CODE
        defaultListInternalCoatingShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where code is not null
        defaultListInternalCoatingShouldBeFound("code.specified=true");

        // Get all the listInternalCoatingList where code is null
        defaultListInternalCoatingShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where name equals to DEFAULT_NAME
        defaultListInternalCoatingShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listInternalCoatingList where name equals to UPDATED_NAME
        defaultListInternalCoatingShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListInternalCoatingShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listInternalCoatingList where name equals to UPDATED_NAME
        defaultListInternalCoatingShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where name is not null
        defaultListInternalCoatingShouldBeFound("name.specified=true");

        // Get all the listInternalCoatingList where name is null
        defaultListInternalCoatingShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where fullName equals to DEFAULT_FULL_NAME
        defaultListInternalCoatingShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listInternalCoatingList where fullName equals to UPDATED_FULL_NAME
        defaultListInternalCoatingShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListInternalCoatingShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listInternalCoatingList where fullName equals to UPDATED_FULL_NAME
        defaultListInternalCoatingShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where fullName is not null
        defaultListInternalCoatingShouldBeFound("fullName.specified=true");

        // Get all the listInternalCoatingList where fullName is null
        defaultListInternalCoatingShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListInternalCoatingShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listInternalCoatingList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListInternalCoatingShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListInternalCoatingShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listInternalCoatingList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListInternalCoatingShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where isCurrentFlag is not null
        defaultListInternalCoatingShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listInternalCoatingList where isCurrentFlag is null
        defaultListInternalCoatingShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListInternalCoatingShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listInternalCoatingList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListInternalCoatingShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListInternalCoatingShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listInternalCoatingList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListInternalCoatingShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListInternalCoatingsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where description equals to DEFAULT_DESCRIPTION
        defaultListInternalCoatingShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listInternalCoatingList where description equals to UPDATED_DESCRIPTION
        defaultListInternalCoatingShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListInternalCoatingShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listInternalCoatingList where description equals to UPDATED_DESCRIPTION
        defaultListInternalCoatingShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where description is not null
        defaultListInternalCoatingShouldBeFound("description.specified=true");

        // Get all the listInternalCoatingList where description is null
        defaultListInternalCoatingShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListInternalCoatingShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listInternalCoatingList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListInternalCoatingShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListInternalCoatingShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listInternalCoatingList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListInternalCoatingShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where dateCreate is not null
        defaultListInternalCoatingShouldBeFound("dateCreate.specified=true");

        // Get all the listInternalCoatingList where dateCreate is null
        defaultListInternalCoatingShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListInternalCoatingShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listInternalCoatingList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListInternalCoatingShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListInternalCoatingShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listInternalCoatingList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListInternalCoatingShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where dateEdit is not null
        defaultListInternalCoatingShouldBeFound("dateEdit.specified=true");

        // Get all the listInternalCoatingList where dateEdit is null
        defaultListInternalCoatingShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where creator equals to DEFAULT_CREATOR
        defaultListInternalCoatingShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listInternalCoatingList where creator equals to UPDATED_CREATOR
        defaultListInternalCoatingShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListInternalCoatingShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listInternalCoatingList where creator equals to UPDATED_CREATOR
        defaultListInternalCoatingShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where creator is not null
        defaultListInternalCoatingShouldBeFound("creator.specified=true");

        // Get all the listInternalCoatingList where creator is null
        defaultListInternalCoatingShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where editor equals to DEFAULT_EDITOR
        defaultListInternalCoatingShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listInternalCoatingList where editor equals to UPDATED_EDITOR
        defaultListInternalCoatingShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListInternalCoatingShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listInternalCoatingList where editor equals to UPDATED_EDITOR
        defaultListInternalCoatingShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        // Get all the listInternalCoatingList where editor is not null
        defaultListInternalCoatingShouldBeFound("editor.specified=true");

        // Get all the listInternalCoatingList where editor is null
        defaultListInternalCoatingShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalCoatingsByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listInternalCoating.addBendHist(bendHist);
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);
        Long bendHistId = bendHist.getId();

        // Get all the listInternalCoatingList where bendHist equals to bendHistId
        defaultListInternalCoatingShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listInternalCoatingList where bendHist equals to bendHistId + 1
        defaultListInternalCoatingShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListInternalCoatingsByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listInternalCoating.addBuckleArrestorHist(buckleArrestorHist);
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listInternalCoatingList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListInternalCoatingShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listInternalCoatingList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListInternalCoatingShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListInternalCoatingsByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listInternalCoating.addPipeHist(pipeHist);
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);
        Long pipeHistId = pipeHist.getId();

        // Get all the listInternalCoatingList where pipeHist equals to pipeHistId
        defaultListInternalCoatingShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listInternalCoatingList where pipeHist equals to pipeHistId + 1
        defaultListInternalCoatingShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListInternalCoatingsByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listInternalCoating.addTeeHist(teeHist);
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);
        Long teeHistId = teeHist.getId();

        // Get all the listInternalCoatingList where teeHist equals to teeHistId
        defaultListInternalCoatingShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listInternalCoatingList where teeHist equals to teeHistId + 1
        defaultListInternalCoatingShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListInternalCoatingsByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listInternalCoating.addValveHist(valveHist);
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);
        Long valveHistId = valveHist.getId();

        // Get all the listInternalCoatingList where valveHist equals to valveHistId
        defaultListInternalCoatingShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listInternalCoatingList where valveHist equals to valveHistId + 1
        defaultListInternalCoatingShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListInternalCoatingShouldBeFound(String filter) throws Exception {
        restListInternalCoatingMockMvc.perform(get("/api/list-internal-coatings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listInternalCoating.getId().intValue())))
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
        restListInternalCoatingMockMvc.perform(get("/api/list-internal-coatings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListInternalCoatingShouldNotBeFound(String filter) throws Exception {
        restListInternalCoatingMockMvc.perform(get("/api/list-internal-coatings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListInternalCoatingMockMvc.perform(get("/api/list-internal-coatings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListInternalCoating() throws Exception {
        // Get the listInternalCoating
        restListInternalCoatingMockMvc.perform(get("/api/list-internal-coatings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListInternalCoating() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        int databaseSizeBeforeUpdate = listInternalCoatingRepository.findAll().size();

        // Update the listInternalCoating
        ListInternalCoating updatedListInternalCoating = listInternalCoatingRepository.findById(listInternalCoating.getId()).get();
        // Disconnect from session so that the updates on updatedListInternalCoating are not directly saved in db
        em.detach(updatedListInternalCoating);
        updatedListInternalCoating
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListInternalCoatingDTO listInternalCoatingDTO = listInternalCoatingMapper.toDto(updatedListInternalCoating);

        restListInternalCoatingMockMvc.perform(put("/api/list-internal-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalCoatingDTO)))
            .andExpect(status().isOk());

        // Validate the ListInternalCoating in the database
        List<ListInternalCoating> listInternalCoatingList = listInternalCoatingRepository.findAll();
        assertThat(listInternalCoatingList).hasSize(databaseSizeBeforeUpdate);
        ListInternalCoating testListInternalCoating = listInternalCoatingList.get(listInternalCoatingList.size() - 1);
        assertThat(testListInternalCoating.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListInternalCoating.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListInternalCoating.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListInternalCoating.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListInternalCoating.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListInternalCoating.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListInternalCoating.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListInternalCoating.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListInternalCoating.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListInternalCoating() throws Exception {
        int databaseSizeBeforeUpdate = listInternalCoatingRepository.findAll().size();

        // Create the ListInternalCoating
        ListInternalCoatingDTO listInternalCoatingDTO = listInternalCoatingMapper.toDto(listInternalCoating);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListInternalCoatingMockMvc.perform(put("/api/list-internal-coatings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalCoatingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListInternalCoating in the database
        List<ListInternalCoating> listInternalCoatingList = listInternalCoatingRepository.findAll();
        assertThat(listInternalCoatingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListInternalCoating() throws Exception {
        // Initialize the database
        listInternalCoatingRepository.saveAndFlush(listInternalCoating);

        int databaseSizeBeforeDelete = listInternalCoatingRepository.findAll().size();

        // Delete the listInternalCoating
        restListInternalCoatingMockMvc.perform(delete("/api/list-internal-coatings/{id}", listInternalCoating.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListInternalCoating> listInternalCoatingList = listInternalCoatingRepository.findAll();
        assertThat(listInternalCoatingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListInternalCoating.class);
        ListInternalCoating listInternalCoating1 = new ListInternalCoating();
        listInternalCoating1.setId(1L);
        ListInternalCoating listInternalCoating2 = new ListInternalCoating();
        listInternalCoating2.setId(listInternalCoating1.getId());
        assertThat(listInternalCoating1).isEqualTo(listInternalCoating2);
        listInternalCoating2.setId(2L);
        assertThat(listInternalCoating1).isNotEqualTo(listInternalCoating2);
        listInternalCoating1.setId(null);
        assertThat(listInternalCoating1).isNotEqualTo(listInternalCoating2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListInternalCoatingDTO.class);
        ListInternalCoatingDTO listInternalCoatingDTO1 = new ListInternalCoatingDTO();
        listInternalCoatingDTO1.setId(1L);
        ListInternalCoatingDTO listInternalCoatingDTO2 = new ListInternalCoatingDTO();
        assertThat(listInternalCoatingDTO1).isNotEqualTo(listInternalCoatingDTO2);
        listInternalCoatingDTO2.setId(listInternalCoatingDTO1.getId());
        assertThat(listInternalCoatingDTO1).isEqualTo(listInternalCoatingDTO2);
        listInternalCoatingDTO2.setId(2L);
        assertThat(listInternalCoatingDTO1).isNotEqualTo(listInternalCoatingDTO2);
        listInternalCoatingDTO1.setId(null);
        assertThat(listInternalCoatingDTO1).isNotEqualTo(listInternalCoatingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listInternalCoatingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listInternalCoatingMapper.fromId(null)).isNull();
    }
}
