package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListMaterial;
import io.github.jhipster.application.domain.AnodeHist;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.PipejointHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListMaterialRepository;
import io.github.jhipster.application.service.ListMaterialService;
import io.github.jhipster.application.service.dto.ListMaterialDTO;
import io.github.jhipster.application.service.mapper.ListMaterialMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListMaterialCriteria;
import io.github.jhipster.application.service.ListMaterialQueryService;

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
 * Integration tests for the {@Link ListMaterialResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListMaterialResourceIT {

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
    private ListMaterialRepository listMaterialRepository;

    @Autowired
    private ListMaterialMapper listMaterialMapper;

    @Autowired
    private ListMaterialService listMaterialService;

    @Autowired
    private ListMaterialQueryService listMaterialQueryService;

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

    private MockMvc restListMaterialMockMvc;

    private ListMaterial listMaterial;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListMaterialResource listMaterialResource = new ListMaterialResource(listMaterialService, listMaterialQueryService);
        this.restListMaterialMockMvc = MockMvcBuilders.standaloneSetup(listMaterialResource)
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
    public static ListMaterial createEntity(EntityManager em) {
        ListMaterial listMaterial = new ListMaterial()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listMaterial;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListMaterial createUpdatedEntity(EntityManager em) {
        ListMaterial listMaterial = new ListMaterial()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listMaterial;
    }

    @BeforeEach
    public void initTest() {
        listMaterial = createEntity(em);
    }

    @Test
    @Transactional
    public void createListMaterial() throws Exception {
        int databaseSizeBeforeCreate = listMaterialRepository.findAll().size();

        // Create the ListMaterial
        ListMaterialDTO listMaterialDTO = listMaterialMapper.toDto(listMaterial);
        restListMaterialMockMvc.perform(post("/api/list-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMaterialDTO)))
            .andExpect(status().isCreated());

        // Validate the ListMaterial in the database
        List<ListMaterial> listMaterialList = listMaterialRepository.findAll();
        assertThat(listMaterialList).hasSize(databaseSizeBeforeCreate + 1);
        ListMaterial testListMaterial = listMaterialList.get(listMaterialList.size() - 1);
        assertThat(testListMaterial.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListMaterial.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListMaterial.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListMaterial.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListMaterial.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListMaterial.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListMaterial.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListMaterial.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListMaterial.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListMaterialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listMaterialRepository.findAll().size();

        // Create the ListMaterial with an existing ID
        listMaterial.setId(1L);
        ListMaterialDTO listMaterialDTO = listMaterialMapper.toDto(listMaterial);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListMaterialMockMvc.perform(post("/api/list-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMaterialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListMaterial in the database
        List<ListMaterial> listMaterialList = listMaterialRepository.findAll();
        assertThat(listMaterialList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMaterialRepository.findAll().size();
        // set the field null
        listMaterial.setCode(null);

        // Create the ListMaterial, which fails.
        ListMaterialDTO listMaterialDTO = listMaterialMapper.toDto(listMaterial);

        restListMaterialMockMvc.perform(post("/api/list-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMaterialDTO)))
            .andExpect(status().isBadRequest());

        List<ListMaterial> listMaterialList = listMaterialRepository.findAll();
        assertThat(listMaterialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMaterialRepository.findAll().size();
        // set the field null
        listMaterial.setName(null);

        // Create the ListMaterial, which fails.
        ListMaterialDTO listMaterialDTO = listMaterialMapper.toDto(listMaterial);

        restListMaterialMockMvc.perform(post("/api/list-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMaterialDTO)))
            .andExpect(status().isBadRequest());

        List<ListMaterial> listMaterialList = listMaterialRepository.findAll();
        assertThat(listMaterialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMaterialRepository.findAll().size();
        // set the field null
        listMaterial.setFullName(null);

        // Create the ListMaterial, which fails.
        ListMaterialDTO listMaterialDTO = listMaterialMapper.toDto(listMaterial);

        restListMaterialMockMvc.perform(post("/api/list-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMaterialDTO)))
            .andExpect(status().isBadRequest());

        List<ListMaterial> listMaterialList = listMaterialRepository.findAll();
        assertThat(listMaterialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMaterialRepository.findAll().size();
        // set the field null
        listMaterial.setIsCurrentFlag(null);

        // Create the ListMaterial, which fails.
        ListMaterialDTO listMaterialDTO = listMaterialMapper.toDto(listMaterial);

        restListMaterialMockMvc.perform(post("/api/list-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMaterialDTO)))
            .andExpect(status().isBadRequest());

        List<ListMaterial> listMaterialList = listMaterialRepository.findAll();
        assertThat(listMaterialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListMaterials() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList
        restListMaterialMockMvc.perform(get("/api/list-materials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listMaterial.getId().intValue())))
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
    public void getListMaterial() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get the listMaterial
        restListMaterialMockMvc.perform(get("/api/list-materials/{id}", listMaterial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listMaterial.getId().intValue()))
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
    public void getAllListMaterialsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where code equals to DEFAULT_CODE
        defaultListMaterialShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listMaterialList where code equals to UPDATED_CODE
        defaultListMaterialShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListMaterialShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listMaterialList where code equals to UPDATED_CODE
        defaultListMaterialShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where code is not null
        defaultListMaterialShouldBeFound("code.specified=true");

        // Get all the listMaterialList where code is null
        defaultListMaterialShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMaterialsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where name equals to DEFAULT_NAME
        defaultListMaterialShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listMaterialList where name equals to UPDATED_NAME
        defaultListMaterialShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListMaterialShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listMaterialList where name equals to UPDATED_NAME
        defaultListMaterialShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where name is not null
        defaultListMaterialShouldBeFound("name.specified=true");

        // Get all the listMaterialList where name is null
        defaultListMaterialShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMaterialsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where fullName equals to DEFAULT_FULL_NAME
        defaultListMaterialShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listMaterialList where fullName equals to UPDATED_FULL_NAME
        defaultListMaterialShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListMaterialShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listMaterialList where fullName equals to UPDATED_FULL_NAME
        defaultListMaterialShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where fullName is not null
        defaultListMaterialShouldBeFound("fullName.specified=true");

        // Get all the listMaterialList where fullName is null
        defaultListMaterialShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMaterialsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListMaterialShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listMaterialList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListMaterialShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListMaterialShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listMaterialList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListMaterialShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where isCurrentFlag is not null
        defaultListMaterialShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listMaterialList where isCurrentFlag is null
        defaultListMaterialShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMaterialsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListMaterialShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listMaterialList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListMaterialShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListMaterialShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listMaterialList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListMaterialShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListMaterialsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where description equals to DEFAULT_DESCRIPTION
        defaultListMaterialShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listMaterialList where description equals to UPDATED_DESCRIPTION
        defaultListMaterialShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListMaterialShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listMaterialList where description equals to UPDATED_DESCRIPTION
        defaultListMaterialShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where description is not null
        defaultListMaterialShouldBeFound("description.specified=true");

        // Get all the listMaterialList where description is null
        defaultListMaterialShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMaterialsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListMaterialShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listMaterialList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListMaterialShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListMaterialShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listMaterialList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListMaterialShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where dateCreate is not null
        defaultListMaterialShouldBeFound("dateCreate.specified=true");

        // Get all the listMaterialList where dateCreate is null
        defaultListMaterialShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMaterialsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListMaterialShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listMaterialList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListMaterialShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListMaterialShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listMaterialList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListMaterialShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where dateEdit is not null
        defaultListMaterialShouldBeFound("dateEdit.specified=true");

        // Get all the listMaterialList where dateEdit is null
        defaultListMaterialShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMaterialsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where creator equals to DEFAULT_CREATOR
        defaultListMaterialShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listMaterialList where creator equals to UPDATED_CREATOR
        defaultListMaterialShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListMaterialShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listMaterialList where creator equals to UPDATED_CREATOR
        defaultListMaterialShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where creator is not null
        defaultListMaterialShouldBeFound("creator.specified=true");

        // Get all the listMaterialList where creator is null
        defaultListMaterialShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMaterialsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where editor equals to DEFAULT_EDITOR
        defaultListMaterialShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listMaterialList where editor equals to UPDATED_EDITOR
        defaultListMaterialShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListMaterialShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listMaterialList where editor equals to UPDATED_EDITOR
        defaultListMaterialShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListMaterialsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        // Get all the listMaterialList where editor is not null
        defaultListMaterialShouldBeFound("editor.specified=true");

        // Get all the listMaterialList where editor is null
        defaultListMaterialShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMaterialsByAnodeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        AnodeHist anodeHist = AnodeHistResourceIT.createEntity(em);
        em.persist(anodeHist);
        em.flush();
        listMaterial.addAnodeHist(anodeHist);
        listMaterialRepository.saveAndFlush(listMaterial);
        Long anodeHistId = anodeHist.getId();

        // Get all the listMaterialList where anodeHist equals to anodeHistId
        defaultListMaterialShouldBeFound("anodeHistId.equals=" + anodeHistId);

        // Get all the listMaterialList where anodeHist equals to anodeHistId + 1
        defaultListMaterialShouldNotBeFound("anodeHistId.equals=" + (anodeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListMaterialsByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listMaterial.addBendHist(bendHist);
        listMaterialRepository.saveAndFlush(listMaterial);
        Long bendHistId = bendHist.getId();

        // Get all the listMaterialList where bendHist equals to bendHistId
        defaultListMaterialShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listMaterialList where bendHist equals to bendHistId + 1
        defaultListMaterialShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListMaterialsByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listMaterial.addBuckleArrestorHist(buckleArrestorHist);
        listMaterialRepository.saveAndFlush(listMaterial);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listMaterialList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListMaterialShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listMaterialList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListMaterialShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListMaterialsByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listMaterial.addPipeHist(pipeHist);
        listMaterialRepository.saveAndFlush(listMaterial);
        Long pipeHistId = pipeHist.getId();

        // Get all the listMaterialList where pipeHist equals to pipeHistId
        defaultListMaterialShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listMaterialList where pipeHist equals to pipeHistId + 1
        defaultListMaterialShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListMaterialsByPipejointHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipejointHist pipejointHist = PipejointHistResourceIT.createEntity(em);
        em.persist(pipejointHist);
        em.flush();
        listMaterial.addPipejointHist(pipejointHist);
        listMaterialRepository.saveAndFlush(listMaterial);
        Long pipejointHistId = pipejointHist.getId();

        // Get all the listMaterialList where pipejointHist equals to pipejointHistId
        defaultListMaterialShouldBeFound("pipejointHistId.equals=" + pipejointHistId);

        // Get all the listMaterialList where pipejointHist equals to pipejointHistId + 1
        defaultListMaterialShouldNotBeFound("pipejointHistId.equals=" + (pipejointHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListMaterialsByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listMaterial.addTeeHist(teeHist);
        listMaterialRepository.saveAndFlush(listMaterial);
        Long teeHistId = teeHist.getId();

        // Get all the listMaterialList where teeHist equals to teeHistId
        defaultListMaterialShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listMaterialList where teeHist equals to teeHistId + 1
        defaultListMaterialShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListMaterialsByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listMaterial.addValveHist(valveHist);
        listMaterialRepository.saveAndFlush(listMaterial);
        Long valveHistId = valveHist.getId();

        // Get all the listMaterialList where valveHist equals to valveHistId
        defaultListMaterialShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listMaterialList where valveHist equals to valveHistId + 1
        defaultListMaterialShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListMaterialShouldBeFound(String filter) throws Exception {
        restListMaterialMockMvc.perform(get("/api/list-materials?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listMaterial.getId().intValue())))
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
        restListMaterialMockMvc.perform(get("/api/list-materials/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListMaterialShouldNotBeFound(String filter) throws Exception {
        restListMaterialMockMvc.perform(get("/api/list-materials?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListMaterialMockMvc.perform(get("/api/list-materials/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListMaterial() throws Exception {
        // Get the listMaterial
        restListMaterialMockMvc.perform(get("/api/list-materials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListMaterial() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        int databaseSizeBeforeUpdate = listMaterialRepository.findAll().size();

        // Update the listMaterial
        ListMaterial updatedListMaterial = listMaterialRepository.findById(listMaterial.getId()).get();
        // Disconnect from session so that the updates on updatedListMaterial are not directly saved in db
        em.detach(updatedListMaterial);
        updatedListMaterial
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListMaterialDTO listMaterialDTO = listMaterialMapper.toDto(updatedListMaterial);

        restListMaterialMockMvc.perform(put("/api/list-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMaterialDTO)))
            .andExpect(status().isOk());

        // Validate the ListMaterial in the database
        List<ListMaterial> listMaterialList = listMaterialRepository.findAll();
        assertThat(listMaterialList).hasSize(databaseSizeBeforeUpdate);
        ListMaterial testListMaterial = listMaterialList.get(listMaterialList.size() - 1);
        assertThat(testListMaterial.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListMaterial.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListMaterial.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListMaterial.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListMaterial.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListMaterial.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListMaterial.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListMaterial.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListMaterial.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListMaterial() throws Exception {
        int databaseSizeBeforeUpdate = listMaterialRepository.findAll().size();

        // Create the ListMaterial
        ListMaterialDTO listMaterialDTO = listMaterialMapper.toDto(listMaterial);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListMaterialMockMvc.perform(put("/api/list-materials")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMaterialDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListMaterial in the database
        List<ListMaterial> listMaterialList = listMaterialRepository.findAll();
        assertThat(listMaterialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListMaterial() throws Exception {
        // Initialize the database
        listMaterialRepository.saveAndFlush(listMaterial);

        int databaseSizeBeforeDelete = listMaterialRepository.findAll().size();

        // Delete the listMaterial
        restListMaterialMockMvc.perform(delete("/api/list-materials/{id}", listMaterial.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListMaterial> listMaterialList = listMaterialRepository.findAll();
        assertThat(listMaterialList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListMaterial.class);
        ListMaterial listMaterial1 = new ListMaterial();
        listMaterial1.setId(1L);
        ListMaterial listMaterial2 = new ListMaterial();
        listMaterial2.setId(listMaterial1.getId());
        assertThat(listMaterial1).isEqualTo(listMaterial2);
        listMaterial2.setId(2L);
        assertThat(listMaterial1).isNotEqualTo(listMaterial2);
        listMaterial1.setId(null);
        assertThat(listMaterial1).isNotEqualTo(listMaterial2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListMaterialDTO.class);
        ListMaterialDTO listMaterialDTO1 = new ListMaterialDTO();
        listMaterialDTO1.setId(1L);
        ListMaterialDTO listMaterialDTO2 = new ListMaterialDTO();
        assertThat(listMaterialDTO1).isNotEqualTo(listMaterialDTO2);
        listMaterialDTO2.setId(listMaterialDTO1.getId());
        assertThat(listMaterialDTO1).isEqualTo(listMaterialDTO2);
        listMaterialDTO2.setId(2L);
        assertThat(listMaterialDTO1).isNotEqualTo(listMaterialDTO2);
        listMaterialDTO1.setId(null);
        assertThat(listMaterialDTO1).isNotEqualTo(listMaterialDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listMaterialMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listMaterialMapper.fromId(null)).isNull();
    }
}
